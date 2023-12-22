package coffeemachine.coffeemachine.interpreter;

import coffeemachine.coffeemachine.handler.ResponseIdentifier;
import coffeemachine.coffeemachine.user.User;
import coffeemachine.coffeemachine.user.UserCache;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static coffeemachine.coffeemachine.handler.ResponseIdentifier.*;
import static coffeemachine.coffeemachine.interpreter.RequestIdentifier.*;
import static coffeemachine.coffeemachine.interpreter.RequestTrigger.*;
import static coffeemachine.coffeemachine.utils.PredicateUtils.*;

public class DefaultRequestInterpreter implements RequestInterpreter<RequestIdentifier> {

    private UserCache userCache;

    private List<RequestTrigger> rootRequestTriggers = new ArrayList<>();
    private Map<ResponseIdentifier, List<RequestTrigger>> responseToRequestTrigger = new HashMap<>();

    public DefaultRequestInterpreter(UserCache userCache) {
        this.userCache = userCache;

        rootRequestTriggers = List.of(
                wordTrigger(COFFEE_YO_MAMA, containsAllWordsTrigger("yo", "mama")),
                wordTrigger(COFFEE_POEM, containsAllWordsTrigger("poem", "coffee")),
                wordTrigger(COFFEE_RIZZ, containsAllWordsTrigger("rizz", "coffee")),
                wordTrigger(MAKE_ME_A_COFFEE,
                        containsAllWordsTrigger("make", "coffee"),
                        containsAllWordsTrigger("want", "coffee"),
                        containsAllWordsTrigger("can", "i", "coffee"),
                        containsAllWordsTrigger("give", "coffee"),
                        containsAllWordsTrigger("like", "coffee"),
                        containsAllWordsTrigger("wish", "had", "coffee"),
                        containsAllWordsTrigger("wish", "have", "coffee")),
                contentTrigger(COFFEE, containsTrigger("coffee")),
                rawContentTrigger(TEA_EMOJI, containsTrigger("\uD83C\uDF75")),
                rawContentTrigger(BUBBLE_TEA_EMOJI, containsTrigger("\uD83E\uDDCB")),
                unicodeContentTrigger(COFFEE_EMOJI, containsTrigger("\\u2615")),
                wordTrigger(TEA, containsWordTrigger("tea"))
        );
        responseToRequestTrigger.put(DID_SOMEONE_SAY_COFFEE, List.of(wordTrigger(DID_SOMEONE_SAY_COFFEE_AGREE, PAST_TENSE_AGREE_PREDICATE)));
        responseToRequestTrigger.put(SOMEONE_CALLED_FOR_ME, List.of(wordTrigger(DID_SOMEONE_SAY_COFFEE_AGREE, PAST_TENSE_AGREE_PREDICATE)));
        responseToRequestTrigger.put(COFFEE_I_CAN_MAKE_COFFEE, List.of(
                wordTrigger(YOU_WANT_ONE_AGREE, PRESENT_TENSE_AGREE_PREDICATE),
                wordTrigger(YOU_WANT_ONE_AGREE, containsAllWordsTrigger("can", "i", "have"),
                        containsAllWordsTrigger("may", "i", "have"),
                        containsAllWordsTrigger("i", "want"),
                        containsAllWordsTrigger("give", "me")
        )));
        responseToRequestTrigger.put(YOU_SUMMONED_ME, List.of(wordTrigger(DID_SOMEONE_SAY_COFFEE_AGREE, PAST_TENSE_AGREE_PREDICATE)));
        responseToRequestTrigger.put(YOU_WANT_ONE, List.of(wordTrigger(YOU_WANT_ONE_AGREE, PRESENT_TENSE_AGREE_PREDICATE)));
        responseToRequestTrigger.put(I_CAN_MAKE_YOU_ONE, List.of(wordTrigger(YOU_WANT_ONE_AGREE, PRESENT_TENSE_AGREE_PREDICATE)));
    }

    public DefaultRequestInterpreter(UserCache userCache, List<RequestTrigger> rootRequestTriggers, Map<ResponseIdentifier, List<RequestTrigger>> responseToRequestTrigger) {
        this.userCache = userCache;
        this.rootRequestTriggers = rootRequestTriggers;
        this.responseToRequestTrigger = responseToRequestTrigger;
    }

    @Override
    public RequestIdentifier getRequestIdentifier(MessageReceivedEvent event) {
        User user = userCache.createUserIfNeeded(event.getAuthor().getIdLong());

        if (responseToRequestTrigger == null || !responseToRequestTrigger.containsKey(user.getLastResponseIdentifier())) {
            for (RequestTrigger trigger : rootRequestTriggers) {
                if (trigger.isTriggered(event)) {
                    return trigger.getRequestIdentifier();
                }
            }
        }
        else {
            for (RequestTrigger trigger : responseToRequestTrigger.get(user.getLastResponseIdentifier())) {
                if (trigger.isTriggered(event)) {
                    return trigger.getRequestIdentifier();
                }
            }
            for (RequestTrigger trigger : rootRequestTriggers) {
                if (trigger.isTriggered(event)) {
                    return trigger.getRequestIdentifier();
                }
            }
        }
        return null;
    }
}

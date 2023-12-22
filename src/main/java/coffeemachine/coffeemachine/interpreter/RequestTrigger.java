package coffeemachine.coffeemachine.interpreter;

import coffeemachine.coffeemachine.utils.StringUtils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class RequestTrigger {

    private RequestIdentifier requestIdentifier;
    private Predicate<MessageReceivedEvent> predicate;

    public RequestTrigger(RequestIdentifier requestIdentifier, Predicate<MessageReceivedEvent> predicate) {
        this.requestIdentifier = requestIdentifier;
        this.predicate = predicate;
    }

    public RequestIdentifier getRequestIdentifier() {
        return requestIdentifier;
    }

    public boolean isTriggered(MessageReceivedEvent event) {
        return predicate.test(event);
    }

    public static RequestTrigger trigger(RequestIdentifier requestIdentifier, Predicate<MessageReceivedEvent> predicate) {
        return new RequestTrigger(requestIdentifier, predicate);
    }

    public static RequestTrigger rawContentTrigger(RequestIdentifier requestIdentifier, Predicate<String>... predicates) {
        return new RequestTrigger(requestIdentifier, event -> Arrays.stream(predicates).anyMatch(p -> p.test(event.getMessage().getContentRaw())));
    }

    public static RequestTrigger unicodeContentTrigger(RequestIdentifier requestIdentifier, Predicate<String>... predicates) {
        return new RequestTrigger(requestIdentifier, event -> Arrays.stream(predicates).anyMatch(p -> p.test(StringUtils.toUnicode(event.getMessage().getContentRaw()))));
    }

    public static RequestTrigger contentTrigger(RequestIdentifier requestIdentifier, Predicate<String>... predicates) {
        return new RequestTrigger(requestIdentifier, event -> {
            String sanitisedMessage = StringUtils.sanitise(event.getMessage().getContentRaw());
            return Arrays.stream(predicates).anyMatch(p -> p.test(sanitisedMessage));
        });
    }

    public static RequestTrigger wordTrigger(RequestIdentifier requestIdentifier, Predicate<String[]>... predicates) {
        return new RequestTrigger(requestIdentifier, event -> {
            String[] sanitisedWords = StringUtils.sanitise(event.getMessage().getContentRaw()).split(" ");
            return Arrays.stream(predicates).anyMatch(p -> p.test(sanitisedWords));
        });
    }

    public static RequestTrigger wordTrigger(RequestIdentifier requestIdentifier, List<Predicate<String[]>> predicates) {
        return wordTrigger(requestIdentifier, predicates.toArray(new Predicate[0]));
    }
}

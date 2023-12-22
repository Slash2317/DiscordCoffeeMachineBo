package coffeemachine.coffeemachine.handler;

import coffeemachine.coffeemachine.user.UserCache;
import coffeemachine.coffeemachine.interpreter.RequestIdentifier;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static coffeemachine.coffeemachine.handler.ResponseAction.*;
import static coffeemachine.coffeemachine.handler.ResponseIdentifier.*;
import static coffeemachine.coffeemachine.interpreter.RequestIdentifier.*;

public class DefaultRequestHandler implements RequestHandler<RequestIdentifier> {

    private UserCache userCache;

    private Map<RequestIdentifier, List<ResponseAction>> requestToResponseActions = new HashMap<>();

    public DefaultRequestHandler(UserCache userCache) {
        this.userCache = userCache;

        requestToResponseActions.put(COFFEE_POEM, List.of(
                messageResponse(COFFEE_POEM_RESPONSE, "roses are red\n" +
                        "coffee is brown\n" +
                        "never going to give you up\n" +
                        "never going to let you down")
        ));
        requestToResponseActions.put(COFFEE_YO_MAMA, List.of(
                messageResponse(YO_MAMA_RESPONSE1, "Yo mama so fat that when she go to the movies, she be sitting next to everyone"),
                messageResponse(YO_MAMA_RESPONSE2, "Yo mama so stupid she climbed a glass wall to see what is on the other side"),
                messageResponse(YO_MAMA_RESPONSE3, "Yo mama so stupid that she took a ruler to see how long she slept"),
                messageResponse(YO_MAMA_RESPONSE4, "Yo mama so fat that when she gets in an elevator, it has to go down"),
                messageResponse(YO_MAMA_RESPONSE5, "Yo mama so fat that when she ran away, they had to use all 4 sides of the milk carton to display her picture"),
                messageResponse(YO_MAMA_RESPONSE6, "Yo mama so fat, I mean, she's so fat that it's incredible. How did she get that fat? Wow, fascinating")
        ));
        requestToResponseActions.put(COFFEE_RIZZ, List.of(
                messageResponse(COFFEE_RIZZ_RESPONSE1, "Are you a cup of coffee? Because you keep me awake all night"),
                messageResponse(COFFEE_RIZZ_RESPONSE2, "Can I have a sip of your coffee? I want to taste the sweetness of your lips"),
                messageResponse(COFFEE_RIZZ_RESPONSE3, "You're the cream in my coffee, the sugar in my tea, and the only one I want to be with me"),
                messageResponse(COFFEE_RIZZ_RESPONSE4, "If you were a coffee shop, you'd be my favorite place to hang out"),
                messageResponse(COFFEE_RIZZ_RESPONSE5, "If you were a coffee bean, I'd grind for you every day"),
                messageResponse(COFFEE_RIZZ_RESPONSE6, "You're like my morning cup of coffee, I can't start my day without you"),
                messageResponse(COFFEE_RIZZ_RESPONSE7, "I don't need sugar in my coffee, I just need you to sweeten up my day"),
                messageResponse(COFFEE_RIZZ_RESPONSE8, "You must be a barista because you know just how to grind my beans"),
                messageResponse(COFFEE_RIZZ_RESPONSE9, "I can't espresso how much I like you"),
                messageResponse(COFFEE_RIZZ_RESPONSE10, "My coffee is hot but you're hotter"),
                messageResponse(COFFEE_RIZZ_RESPONSE11, "I've bean thinking about you a lot"),
                messageResponse(COFFEE_RIZZ_RESPONSE12, "You mocha me crazy!"),
                messageResponse(COFFEE_RIZZ_RESPONSE13, "Can I get your number? Because I like you a latte"),
                messageResponse(COFFEE_RIZZ_RESPONSE14, "I bet you get asked out on a latte of dates"),
                messageResponse(COFFEE_RIZZ_RESPONSE15, "My coffee isn't hot enough! Could you hold it for a while?"),
                messageResponse(COFFEE_RIZZ_RESPONSE16, "Do you believe in love at first sip or should I take another?")
        ));
        requestToResponseActions.put(MAKE_ME_A_COFFEE, List.of(
                messageResponse(COMING_RIGHT_UP, "Coming right up... :coffee:"),
                messageResponse(JUST_FOR_YOU, "Just for you... :coffee:"),
                messageResponse(ANYTHING_FOR_YOU, "Anything for you... :coffee:"),
                messageResponse(ID_MAKE_COFFEE_FOR_YOU_ANYTIME, "I'd make coffee for you anytime... :coffee:")
        ));
        requestToResponseActions.put(COFFEE, List.of(
                messageResponse(DID_SOMEONE_SAY_COFFEE, "Did... did someone say coffee?"),
                messageResponse(SOMEONE_CALLED_FOR_ME, "Someone called for me?"),
                messageResponse(COFFEE_I_CAN_MAKE_COFFEE, "Coffee? I can make coffee"),
                messageResponse(YOU_SUMMONED_ME, "You summoned me?"),
                messageResponse(SORRY_I_HAD_TO, ":coffee: Sorry, I just had to"),
                messageResponse(DISTRESS_CALL, "I can't ignore a distress call, here take this :coffee:"),
                messageResponse(ANOTHER_COFFEE_LOVER, "Ah, another coffee lover, I see you")
        ));
        requestToResponseActions.put(DID_SOMEONE_SAY_COFFEE_AGREE, List.of(
                messageResponse(GO_ON_ASK_ME, "Well go on, ask me for one"),
                messageResponse(YOU_WANT_ONE, "You want one?"),
                messageResponse(FELLOW_COFFEE_LOVER, "Good. I'm a fellow coffee lover too"),
                messageResponse(FRIEND_REQUEST, "I'd send you a friend request, but y'know"),
                messageResponse(I_CAN_MAKE_YOU_ONE, "Nice! I can make you one if you want?"),
                messageResponse(YOURE_A_REAL_ONE, "You're a real one"),
                messageResponse(I_LIKE_COFFEE_TOO, "Yeah, I like coffee too")
        ));
        requestToResponseActions.put(YOU_WANT_ONE_AGREE, List.of(
                messageResponse(COMING_RIGHT_UP, "Coming right up... :coffee:"),
                messageResponse(JUST_FOR_YOU, "Just for you... :coffee:"),
                messageResponse(ANYTHING_FOR_YOU, "Anything for you... :coffee:"),
                messageResponse(ID_MAKE_COFFEE_FOR_YOU_ANYTIME, "I'd make coffee for you anytime... :coffee:")
        ));
        requestToResponseActions.put(TEA_EMOJI, List.of(
                messageResponse(I_WISH_I_COULD_DELETE, "I wish I had permission to delete ur message rn"),
                messageResponse(PUT_THAT_THING_AWAY, "Put that thing away, there are children here"),
                messageResponse(I_CANT_BELIEVE_YOU_HAVE_DONE_THIS, "I can't believe you have done this")
        ));
        requestToResponseActions.put(BUBBLE_TEA_EMOJI, List.of(
                messageResponse(I_GUESS_THATS_ACCEPTABLE, "hmm ig that's acceptable")
        ));
        requestToResponseActions.put(COFFEE_EMOJI, List.of(
                messageResponse(WELL_DONT_MIND_IF_I_DO, "Well don't mind if I do")
        ));
        requestToResponseActions.put(TEA, List.of(
                reactionResponse(NAUSEATED_FACE_REACTION, "U+1F922")
        ));
    }

    @Override
    public void handleRequest(RequestIdentifier requestIdentifier, MessageReceivedEvent event) {
        List<ResponseAction> responseActions = requestToResponseActions.get(requestIdentifier);

        ResponseAction chosenResponseAction;
        if (responseActions.size() > 1) {
            int chosen = (int) (Math.random() * responseActions.size());
            chosenResponseAction = responseActions.get(chosen);
        }
        else {
            chosenResponseAction = responseActions.get(0);
        }

        userCache.getUser(event.getAuthor().getIdLong()).setLastResponseIdentifier(chosenResponseAction.getIdentifier());
        chosenResponseAction.perform(event);
    }
}

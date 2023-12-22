package coffeemachine.coffeemachine.handler;

import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.function.Consumer;

public class ResponseAction {

    private ResponseIdentifier identifier;
    private Consumer<MessageReceivedEvent> action;

    public ResponseAction(ResponseIdentifier identifier, Consumer<MessageReceivedEvent> action) {
        this.identifier = identifier;
        this.action = action;
    }

    public ResponseIdentifier getIdentifier() {
        return identifier;
    }

    public void perform(MessageReceivedEvent event) {
        action.accept(event);
    }

    public static ResponseAction messageResponse(ResponseIdentifier identifier, String message) {
        return new ResponseAction(identifier, event -> event.getChannel().sendMessage(message).queue());
    }

    public static ResponseAction reactionResponse(ResponseIdentifier identifier, String unicode) {
        return new ResponseAction(identifier, event -> event.getMessage().addReaction(Emoji.fromUnicode(unicode)).queue());
    }
}

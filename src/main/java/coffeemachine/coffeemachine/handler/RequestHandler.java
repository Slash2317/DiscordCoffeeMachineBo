package coffeemachine.coffeemachine.handler;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface RequestHandler<T> {

    void handleRequest(T requestIdentifier, MessageReceivedEvent event);
}

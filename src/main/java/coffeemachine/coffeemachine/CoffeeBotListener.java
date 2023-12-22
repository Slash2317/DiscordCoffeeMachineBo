package coffeemachine.coffeemachine;

import coffeemachine.coffeemachine.handler.RequestHandler;
import coffeemachine.coffeemachine.interpreter.RequestInterpreter;
import coffeemachine.coffeemachine.utils.StringUtils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CoffeeBotListener<T> extends ListenerAdapter {

    private RequestInterpreter<T> requestInterpreter;
    private RequestHandler<T> requestHandler;
    private boolean restrictChannel;

    public CoffeeBotListener(RequestInterpreter<T> requestInterpreter, RequestHandler<T> requestHandler, boolean restrictChannel) {
        this.requestInterpreter = requestInterpreter;
        this.requestHandler = requestHandler;
        this.restrictChannel = restrictChannel;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        if (restrictChannel && !StringUtils.containsIgnoreCase(event.getChannel().getName(), "coffee")) {
            return;
        }
        T requestIdentifier = requestInterpreter.getRequestIdentifier(event);
        if (requestIdentifier != null) {
            requestHandler.handleRequest(requestIdentifier, event);
        }
    }
}

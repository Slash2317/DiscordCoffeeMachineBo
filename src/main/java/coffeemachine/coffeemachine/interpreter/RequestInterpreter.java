package coffeemachine.coffeemachine.interpreter;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface RequestInterpreter<T> {

    T getRequestIdentifier(MessageReceivedEvent event);
}

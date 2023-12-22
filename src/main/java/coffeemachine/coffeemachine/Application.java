package coffeemachine.coffeemachine;

import coffeemachine.coffeemachine.user.UserCache;
import coffeemachine.coffeemachine.handler.DefaultRequestHandler;
import coffeemachine.coffeemachine.interpreter.DefaultRequestInterpreter;
import coffeemachine.coffeemachine.interpreter.RequestIdentifier;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.IOException;
import java.util.Properties;

public class Application {

    public static void main(String[] args) {
        String env = System.getProperty("app.profiles.active");
        if (env == null) {
            env = "dev";
        }

        Properties props = new Properties();
        try {
            props.load(Application.class.getClassLoader().getResourceAsStream("config-" + env + ".properties"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        UserCache userCache = new UserCache();
        CoffeeBotListener<RequestIdentifier> coffeeBotListener = new CoffeeBotListener<>(new DefaultRequestInterpreter(userCache), new DefaultRequestHandler(userCache), !env.equals("dev"));

        JDABuilder.createDefault(props.getProperty("token"))
                .setActivity(Activity.listening("the screams of the damned"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(coffeeBotListener)
                .build();
    }
}

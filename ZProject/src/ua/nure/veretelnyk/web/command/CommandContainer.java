package ua.nure.veretelnyk.web.command;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("login", new LoginCmd());
        commands.put("logout", new LogoutCmd());
        commands.put("error", new ErrorCmd());
        commands.put("register", new RegistrCmd());
        commands.put("get_stations", new GetStationsCmd());

        LOG.debug("CommandContainer initialized.");
    }

    public static Command get(String commandName){
        if (commandName == null || !commands.containsKey(commandName))
            commandName = "error";

        return commands.get(commandName);
    }
}

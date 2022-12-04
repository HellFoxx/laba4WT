package hellfoxx.command;

import hellfoxx.constant.RequestConstant;

public class CommandFactory {

    private static final CommandFactory instance = new CommandFactory();

    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        return instance;
    }

    public Command defineCommand(RequestContent content) {
        String commandName = content.getRequestParameters().containsKey(RequestConstant.COMMAND)
                ? content.getRequestParameter(RequestConstant.COMMAND)[0]
                : RequestConstant.ERROR;
        try {
            return CommandType.valueOf(commandName.toUpperCase().replaceAll("[-]", "_")).getCommand();
        } catch (IllegalArgumentException e) {
            return new DefaultCommand();
        }
    }
}

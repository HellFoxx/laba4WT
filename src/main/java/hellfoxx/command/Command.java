package hellfoxx.command;

@FunctionalInterface
public interface Command {
    CommandResult execute(RequestContent requestContent);
}

package sample.BackEnd;

public class CommandException extends Exception {
    public CommandException(String errMsg){
        super(errMsg);
    }
}

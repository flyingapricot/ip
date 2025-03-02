package orange.parser;

import orange.exception.ExceptionType;
import orange.exception.OrangeException;
import orange.Ui.Ui;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Parser {
    private static final HashSet<String> commands = new HashSet<>(Arrays.asList("mark","unmark","list","todo","event","deadline","delete"));
    private static Scanner in = new Scanner(System.in);

    public Parser() {
    }

    private String[] getUserInput() {
        String line = in.nextLine();
        return line.split(" ");
    }

    public String scanForCommandWord() {
        //Any command given to the chatbot must start with one of the command keywords defined in the commands hashset
        String commandWord = getUserInput()[0];

        //Try Catch Block to catch no keyword used
        try{
            if(!commands.contains(commandWord)) throw new OrangeException(ExceptionType.UNKNOWN_COMMAND);
        }catch(OrangeException o) {
            Ui.showError(o.getCustomMessage());
            return "";
        }

        return commandWord; //Valid command word detected
    }


}

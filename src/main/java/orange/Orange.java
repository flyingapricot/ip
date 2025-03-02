package orange;
import java.util.*;

import orange.Ui.Ui;
import orange.command.CommandHandler;
import orange.exception.OrangeException;
import orange.parser.Parser;
import orange.task.*;

import orange.storage.Storage;



public class Orange {
  private Storage storage;
  private Ui ui;
  private Parser parser;
  private TaskManager taskManager;

  public Orange() {
    try {
      storage = new Storage();
    } catch (OrangeException o) {
      System.out.println(o.getCustomMessage());
    }
    taskManager = new TaskManager();
  }


  public void run() {
    Scanner in = new Scanner(System.in);
    Ui.greeting();
    String line = "";
    line = in.nextLine();
    parser = new Parser(line);

    //Main chatbot loop
    while(!line.equals("bye")) {
      String commandWord = parser.scanForCommandWord();
      CommandHandler command = taskManager.getTask(commandWord);

      if(command != null) {
        try {
          command.execute();
        } catch (OrangeException e) {
            //Error message
            System.out.println(e.getCustomMessage());
        }
      }
      line = in.nextLine();
      parser.updateLine(line);
    }

    Ui.goodbye();

  }

  public static void main(String[] args) {
    Orange o = new Orange();
    o.run();
  }

}

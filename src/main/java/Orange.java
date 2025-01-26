import java.util.Scanner;

public class Orange {
    private static final String HORIZONTAL_LINE = "\t" + "-".repeat(50);
    private static final String CHATBOT_NAME = "ORANGE";
    public static void greeting() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "Hello! I'm " + CHATBOT_NAME);
        System.out.println("\t" + "What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void goodbye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void echo() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(!(line.equals("bye"))) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("\t" + line);
            System.out.println(HORIZONTAL_LINE);
            in = new Scanner(System.in);
            line = in.nextLine();
        }
        goodbye();
    }

    public static void main(String[] args) {
        greeting(); //Print out the greeting
        echo(); //Echo user's input
    }
}

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Lebron {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        List<String> inputlist = new ArrayList<>();
        System.out.println("_".repeat(60));
        System.out.println("Hello from Lebron!");
        System.out.println("What can I do for you?");
        System.out.println("_".repeat(60));
        Scanner sc = new Scanner(System.in);
        String input1 = "";
        System.out.println("_".repeat(60));
        while (! input1.equals("bye")) {
            input1 = sc.nextLine();
            if (input1.equals("list")) {
                for (int i =0; i < inputlist.size(); i++) {
                    System.out.println((i + 1) + ". " + inputlist.get(i));
                }

            }
            else {
                inputlist.add(input1);
                System.out.println("added: " + input1);
            }
            System.out.println("_".repeat(60));

        }
        System.out.println(input1);
        System.out.println("_".repeat(60));
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_".repeat(60));

    }
}

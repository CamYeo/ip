import java.util.Scanner;

public class Lebron {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("_".repeat(60));
        System.out.println("Hello from Lebron!");
        System.out.println("What can I do for you?");
        System.out.println("_".repeat(60));
        Scanner sc = new Scanner(System.in);
        String input1 = sc.next();
        System.out.println("_".repeat(60));
        while (! input1.equals("bye")) {
            System.out.println(input1);
            System.out.println("_".repeat(60));
            input1 = sc.next();
            System.out.println("_".repeat(60));
        }
        System.out.println(input1);
        System.out.println("_".repeat(60));
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_".repeat(60));

    }
}

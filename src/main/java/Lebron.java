import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Lebron {
    public static void main(String[] args) {
        List<task> inputlist = new ArrayList<>();
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
            else if (input1.startsWith("mark")) {
                int index = Integer.parseInt(input1.replaceAll(".*?(\\d+)$", "$1")) - 1;
                inputlist.get(index).mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(inputlist.get(index));
            }
            else if (input1.startsWith("unmark")) {
                int index = Integer.parseInt(input1.replaceAll(".*?(\\d+)$", "$1")) - 1;
                inputlist.get(index).unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(inputlist.get(index));
            }
            else {
                inputlist.add(new task(input1, 0));
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

class task {
    String name;
    int status;

    public task(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public void mark() {
        this.status = 1;
    }

    public void unmark() {
        this.status = 0;
    }


    @Override
    public String toString() {
       if (status == 1) {
           return "[X] " + this.name;
       }
       else {
           return "[ ] " + this.name;
       }
    }
}

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Lebron {
    public static void main(String[] args) {
        List<Task> inputlist = new ArrayList<>();
        System.out.println("_".repeat(60));
        System.out.println("Hello from Lebron!");
        System.out.println("What can I do for you?");
        System.out.println("_".repeat(60));
        Scanner sc = new Scanner(System.in);
        String input1 = "";
        System.out.println("_".repeat(60));
        while (! input1.equals("bye")) {
            input1 = sc.nextLine();
            if (input1.equals("bye")) {
                break;
            }

            else if (input1.equals("list")) {
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
            else if (input1.startsWith("todo")) {
                String name = input1.substring(5).trim();
                inputlist.add(new Todo(name));

                System.out.println("Got it. I've added this task:");
                System.out.println(inputlist.get(inputlist.size() - 1));
                System.out.println("Now you have " + inputlist.size() + " tasks in the list.");
            }
            else if (input1.startsWith("deadline")) {
                String[] parts = input1.substring(9).split("/by");
                String name = parts[0].trim();
                String by = parts[1].trim();

                inputlist.add(new Deadline(name, by));

                System.out.println("Got it. I've added this task:");
                System.out.println(inputlist.get(inputlist.size() - 1));
                System.out.println("Now you have " + inputlist.size() + " tasks in the list.");
            }
            else if (input1.startsWith("event")) {
                String withoutCommand = input1.substring(6);
                String[] partsFrom = withoutCommand.split("/from");
                String name = partsFrom[0].trim();

                String[] partsTo = partsFrom[1].split("/to");
                String from = partsTo[0].trim();
                String to = partsTo[1].trim();

                inputlist.add(new Event(name, from, to));

                System.out.println("Got it. I've added this task:");
                System.out.println(inputlist.get(inputlist.size() - 1));
                System.out.println("Now you have " + inputlist.size() + " tasks in the list.");

            }
            else {
                inputlist.add(new Task(input1));
                System.out.println("added: " + input1);
            }
            System.out.println("_".repeat(60));

        }
        System.out.println("_".repeat(60));
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_".repeat(60));

    }
}



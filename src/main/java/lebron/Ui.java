package lebron;

import java.util.Scanner;

public class Ui {
    private final Scanner sc = new Scanner(System.in);

    public void showLine() {
        System.out.println("_".repeat(60));
    }


    // Played on intro
    public void showWelcome() {
        showLine();
        System.out.println("Hello from Lebron!");
        showLine();
    }

    // Played on exit
    public void showBye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public String readCommand() {
        return sc.nextLine(); //reads using scanner
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void showList(TaskList tasks) {
        // Prints the list
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public String getHelpMessage() {
        return """
Hello! Here are the commands you can use:

list
  Show all tasks

todo <description>
  Add a todo task
  Example: todo read book

deadline <description> /by <date>
  Add a deadline task
  Example: deadline submit report /by Friday

event <description> /from <start> /to <end>
  Add an event task
  Example: event meeting /from 2pm /to 4pm

mark <task number>
unmark <task number>
  Mark or unmark a task

delete <task number>
  Delete a task

find <keyword>
  Search for tasks containing a keyword

help
  Show this help message

bye
  Exit the application
""";
    }

}


import java.util.Scanner;

public class Ui {
    private final Scanner sc = new Scanner(System.in);

    public void showLine() {
        System.out.println("_".repeat(60));
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello from Lebron!");
        showLine();
    }

    public void showBye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void showList(TaskList tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}


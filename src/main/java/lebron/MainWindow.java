package lebron;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Lebron lebron;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the controller and sets up the UI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcomeMessage();
    }

    /**
     * Displays a welcome message when the app starts.
     */
    private void showWelcomeMessage() {
        String welcomeText = "👋 Welcome to Lebron!\n\n"
                + "I'm your personal task management assistant. Here's what I can help you with:\n\n"
                + "• 📝 Create todos, deadlines, and events\n"
                + "• ✅ Mark tasks as complete\n"
                + "• 🔍 Search through your tasks\n"
                + "• 📋 View your entire task list\n\n"
                + "Type 'help' to see all available commands, or just start adding your first task!";

        Platform.runLater(() -> {
            DialogBox welcomeDialog = DialogBox.getDukeDialog(welcomeText, dukeImage);
            dialogContainer.getChildren().add(welcomeDialog);

            // Add fade-in animation
            FadeTransition fadeIn = new FadeTransition(Duration.millis(600), welcomeDialog);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });
    }

    /**
     * Injects the Lebron instance.
     *
     * @param l The Lebron chatbot instance
     */
    public void setDuke(Lebron l) {
        lebron = l;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Lebron's reply,
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty()) {
            return;
        }

        String response = lebron.getResponse(input);

        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox botDialog = DialogBox.getDukeDialog(response, dukeImage);

        dialogContainer.getChildren().addAll(userDialog, botDialog);

        // Add smooth fade-in animations
        animateDialog(userDialog, 0);
        animateDialog(botDialog, 150);

        userInput.clear();
    }

    /**
     * Adds a fade-in animation to a dialog box.
     *
     * @param dialog The dialog box to animate
     * @param delayMillis Delay before starting the animation
     */
    private void animateDialog(DialogBox dialog, int delayMillis) {
        dialog.setOpacity(0);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(400), dialog);
        fadeIn.setDelay(Duration.millis(delayMillis));
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }
}

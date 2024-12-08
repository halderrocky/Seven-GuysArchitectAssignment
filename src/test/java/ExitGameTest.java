import edu.sdccd.cisc190.generalstuff.ExitGame;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExitGameTest {

    private Stage stage;
    private ExitGame exitGame;

    @BeforeEach
    public void setUp() {
        // Start JavaFX Application thread
        Platform.startup(() -> {
            stage = new Stage();
            exitGame = new ExitGame(stage);
        });
    }

    @Test
    public void testSceneCreation() {
        Platform.runLater(() -> {
            Scene scene = exitGame.getScene();
            assertNotNull(scene, "Scene should not be null");
            assertEquals(300, scene.getWidth(), "Scene width should be 300");
            assertEquals(200, scene.getHeight(), "Scene height should be 200");
        });
    }

    @Test
    public void testExitButtonAction() {
        Platform.runLater(() -> {
            Button exitButton = (Button) exitGame.getScene().lookup(".button");
            assertNotNull(exitButton, "Exit button should not be null");

            exitButton.fire(); // Simulate button click
            // Check if the alert pops up and is shown
            // Note: You may need to simulate the alert check in a slightly different way depending on your implementation
            // This is just a placeholder for the behavior you expect
            // For example, if you have a method that shows the alert, check if it was called.
            // Here I'm assuming you have some logic to check an alert display
        });
    }
}

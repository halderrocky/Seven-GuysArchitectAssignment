import edu.sdccd.cisc190.altscenes.Five1Morning;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Five1MorningTest {

    private Stage stage;
    private Five1Morning morningScene;

    @BeforeEach
    public void setUp() {
        Platform.startup(() -> {
            stage = new Stage();
            morningScene = new Five1Morning(stage);
        });
    }

    @Test
    public void testSceneCreation() {
        Platform.runLater(() -> {
            Scene scene = morningScene.getScene();
            assertNotNull(scene, "Scene should not be null");
            assertEquals(400, scene.getWidth(), "Scene width should be 400");
            assertEquals(400, scene.getHeight(), "Scene height should be 400");
        });
    }

    @Test
    public void testInitialGameStatus() {
        Platform.runLater(() -> {
            Text gameStatus = (Text) morningScene.getScene().getRoot().lookup(".text");
            assertNotNull(gameStatus, "Game status text should not be null");
            assertEquals("You are tired of these loud noises; still unsure if it’s hallucinations or reality...", gameStatus.getText().trim());
        });
    }

    @Test
    public void testConvictionIncreasesOnButtonClick() {
        Platform.runLater(() -> {
            Button attackButton = (Button) morningScene.getScene().lookup("#attackButton");
            Button dodgeButton = (Button) morningScene.getScene().lookup("#dodgeButton");

            attackButton.fire();
            assertEquals(1, morningScene.getConviction(), "Conviction should increase by 1");
            dodgeButton.fire();
            assertEquals(1, morningScene.getMadness(), "Madness should increase by 1");
        });
    }

    @Test
    public void testButtonActionChangesScene() {
        Platform.runLater(() -> {
            Button attackButton = (Button) morningScene.getScene().lookup("#attackButton");
            assertNotNull(attackButton, "Attack button should not be null");

            attackButton.fire();
            // Verify that the scene changes
            assertNotEquals(morningScene.getScene(), stage.getScene(), "Scene should change after button click");
        });
    }
}
import edu.sdccd.cisc190.altscenes.FiveAttackWater;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FiveAttackWaterTest {

    private Stage stage;
    private FiveAttackWater attackWaterScene;

    @BeforeEach
    public void setUp() {
        Platform.startup(() -> {
            stage = new Stage();
            attackWaterScene = new FiveAttackWater(stage);
        });
    }

    @Test
    public void testSceneCreation() {
        Platform.runLater(() -> {
            Scene scene = attackWaterScene.getScene();
            assertNotNull(scene, "Scene should not be null");
            assertEquals(400, scene.getWidth(), "Scene width should be 400");
            assertEquals(400, scene.getHeight(), "Scene height should be 400");
        });
    }

    @Test
    public void testInitialGameStatus() {
        Platform.runLater(() -> {
            Text gameStatus = (Text) attackWaterScene.getScene().getRoot().lookup(".text");
            assertNotNull(gameStatus, "Game status text should not be null");
            assertEquals("You attacked Mika the Monkey by splashing water.", gameStatus.getText().trim());
        });
    }

    @Test
    public void testConvictionAndMadnessUpdates() {
        Platform.runLater(() -> {
            Button attackButton = (Button) attackWaterScene.getScene().lookup("#attackButton");
            Button dodgeButton = (Button) attackWaterScene.getScene().lookup("#dodgeButton");

            attackButton.fire();
            assertEquals(1, attackWaterScene.getConviction(), "Conviction should increase by 1");
            dodgeButton.fire();
            assertEquals(1, attackWaterScene.getMadness(), "Madness should increase by 1");
        });
    }
}
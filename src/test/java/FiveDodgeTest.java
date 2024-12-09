import edu.sdccd.cisc190.altscenes.FiveDodge;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class) // This may not be necessary if you're using a standard runner
public class FiveDodgeTest {
    private FiveDodge fiveDodge;

    private static final int INITIAL_CONVICTION = 0;
    private static final int INITIAL_MADNESS = 0;
    private static final int HIGH_MADNESS_THRESHOLD = 8;

    @Before
    public void setUp() {
        Platform.runLater(() -> {
            Stage stage = new Stage(); // Mock Stage for testing
            fiveDodge = new FiveDodge(stage); // Create an instance of FiveDodge
            fiveDodge.setConviction(INITIAL_CONVICTION);
            fiveDodge.setMadness(INITIAL_MADNESS);
        });

        // Wait for JavaFX to initialize the stage and the fiveDodge object
        try {
            Thread.sleep(100); // Add a small delay to ensure JavaFX initializes
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    public void testInitialStats() {
        Platform.runLater(() -> {
            assertEquals(INITIAL_CONVICTION, fiveDodge.getConviction());
            assertEquals(INITIAL_MADNESS, fiveDodge.getMadness());
        });
    }

    @Test
    public void testAttackWithWaterBottle() {
        Platform.runLater(() -> {
            fiveDodge.attackWithWaterBottle();
            assertEquals(1, fiveDodge.getConviction());
            assertEquals(-1, fiveDodge.getMadness()); // Verify decrement
        });
    }

    @Test
    public void testDodgeAttack() {
        Platform.runLater(() -> {
            fiveDodge.dodgeAttack();
            assertEquals(1, fiveDodge.getConviction());
            assertEquals(0, fiveDodge.getMadness()); // Verify no change
        });
    }

    @Test
    public void testMadnessThreshold() {
        Platform.runLater(() -> {
            fiveDodge.setMadness(HIGH_MADNESS_THRESHOLD);
            fiveDodge.dodgeAttack();
            assertEquals(HIGH_MADNESS_THRESHOLD, fiveDodge.getMadness()); // Check no change
        });
    }

    @Test
    public void testSaveLoadGameData() {
        Platform.runLater(() -> {
            fiveDodge.saveGameData();
            fiveDodge.loadGameData();
            assertEquals(1, fiveDodge.getConviction()); // Assuming conviction should be 1 after loading
            assertEquals(0, fiveDodge.getMadness()); // Assuming madness should be 0 after loading
        });
    }
}
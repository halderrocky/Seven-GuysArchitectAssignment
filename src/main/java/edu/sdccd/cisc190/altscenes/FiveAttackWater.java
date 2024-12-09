package edu.sdccd.cisc190.altscenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The FiveAttackWater class manages the player's actions during the water attack sequence,
 * allowing them to attack or dodge while tracking their stats.
 */
public class FiveAttackWater {
    private Scene scene;
    private int conviction; // Variable to track the conviction stat
    private int madness; // Variable to track the madness stat
    private final Text gameStatus;
    private final Text statsText;

    public FiveAttackWater(Stage primaryStage) {
        // Initial game status text
        gameStatus = new Text("You attacked Mika the Monkey by splashing water.\n" +
                "It was effective, but you wasted 25% of the water and realized you have to conserve it.\n" +
                "You closed the door, trapping Mika the Monkey in the electrical room.\n");
        gameStatus.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        // Text for displaying stats (conviction and madness)
        statsText = new Text("Conviction: " + conviction + " | Madness: " + madness);
        statsText.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        // Initialize the buttons
        Button oneButton = new Button("Attack with water (25% remaining)");
        Button twoButton = new Button("Dodge attack");

        // Button actions
        oneButton.setOnAction(e -> {
            if (conviction > 5) {
                gameStatus.setText("You attack successfully, dealing damage to Mika!");
                conviction += 1; // Increase conviction
            } else {
                gameStatus.setText("Your attack is weak; Mika shrugs it off.");
                madness += 1; // Increase madness due to failure
            }
            updateStats();
        });

        twoButton.setOnAction(e -> {
            if (madness < 5) {
                gameStatus.setText("You dodge successfully and escape Mika's reach!");
                conviction -= 1; // Decrease conviction for dodging
            } else {
                gameStatus.setText("You are too disoriented to dodge effectively!");
            }
            updateStats();
        });

        // Create the BorderPane layout
        BorderPane layout = new BorderPane();

        // Add game status text to the top
        layout.setTop(gameStatus);

        // Add stats text below the game status text
        layout.setBottom(statsText);

        // Create a VBox to arrange buttons vertically
        VBox buttonBox = new VBox(10); // 10px spacing between buttons
        buttonBox.getChildren().addAll(oneButton, twoButton);

        // Set the VBox containing buttons to the center of the BorderPane
        layout.setCenter(buttonBox);

        // Scene creation with appropriate size
        scene = new Scene(layout, 400, 400);
    }


    // Method to update the stats text
    /**
     * Updates the player's stats display.
     */
    private void updateStats() {
        statsText.setText("Conviction: " + conviction + " | Madness: " + madness);
    }

    // Getter for the scene
    /**
     * Gets the current scene.
     *
     * @return the Scene object representing the current scene
     */
    public Scene getScene() {
        return scene;
    }

    public int getConviction() {
        return conviction;
    }

    public int getMadness() {
        return madness;
    }
}
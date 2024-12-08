package edu.sdccd.cisc190.altscenes;

import edu.sdccd.cisc190.generalstuff.ExitGame;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;

public class FiveDodge {
    private Scene scene;
    private int conviction; // Variable to track the conviction stat
    private int madness; // Variable to track the madness stat
    private final Text gameStatus;
    private final Text statsText; // Text to display the stats
    private final String saveFileName = "game_data.txt"; // File name for saving game data

    public FiveDodge(Stage primaryStage) {
        // Initial game status text
        gameStatus = new Text("You successfully dodged the attack and you closed the door.\n" +
                "However, Mika the Monkey is smart, and she decides to turn the power back off.\n" +
                "“I should’ve used the water when necessary…”, but at least she’s locked inside.\n" +
                "“I don’t think the office is a good idea, the better idea is to get the hell out of here.”\n" +
                "Turning on the flashlight, you immediately see Ozzy the Ostrich running towards you, and he’s running towards you fast.\n");
        gameStatus.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        // Text for displaying stats (conviction and madness)
        statsText = new Text("Conviction: " + conviction + " | Madness: " + madness);
        statsText.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        // Button actions
        Button oneButton = new Button("Attack with a water bottle (50% remaining)");
        oneButton.setStyle("-fx-font-size: 14px;");
        oneButton.setOnAction(e -> {
            attackWithWaterBottle();
            primaryStage.setScene(new FiveDodge1(primaryStage).getScene());
        });

        Button twoButton = new Button("Dodge attack");
        twoButton.setStyle("-fx-font-size: 14px;");
        twoButton.setOnAction(e -> {
            dodgeAttack();
            gameStatus.setText("You slid under Ozzy the Ostrich and you ran as fast as you could.\n" +
                    "You ran towards the main entrance/exit of the Seven Guys, however, the door won’t budge.\n" +
                    "And it’s too late, Ozzy the Ostrich caught up to you.");
            primaryStage.setScene(new ExitGame(primaryStage).getScene());
        });

        // Create the BorderPane layout
        BorderPane layout = new BorderPane();

        // Add game status text to the top
        layout.setTop(gameStatus);

        // Add stats text below the game status text
        layout.setBottom(statsText);

        // Create a VBox to arrange buttons vertically
        VBox buttonBox = new VBox(10);  // 10px spacing between buttons
        buttonBox.getChildren().addAll(oneButton, twoButton);

        // Set the VBox containing buttons to the center of the BorderPane
        layout.setCenter(buttonBox);

        // Load saved game data if available
        loadGameData();

        // Scene creation with appropriate size
        scene = new Scene(layout, 400, 400);
    }

    // Method to simulate attacking with a water bottle
    private void attackWithWaterBottle() {
        // Check conditions to handle the outcome of the attack
        if (conviction < 1) {
            gameStatus.setText("You don't have enough conviction to attack!");
            return;
        }

        // Simulate the success of the attack
        conviction += 1;
        madness -= 1;
        updateStats(); // Update stats after the action

        // Save game data after the attack
        saveGameData();
    }

    // Method to simulate dodging an attack
    private void dodgeAttack() {
        // Check conditions to handle the outcome of dodging
        if (madness > 7) {
            gameStatus.setText("You are too unstable to dodge effectively!");
            return;
        }

        // Simulate the success of the dodge
        conviction += 1;
        updateStats(); // Update stats after the action

        // Save game data after dodging
        saveGameData();
    }

    // Method to update the stats text
    private void updateStats() {
        statsText.setText("Conviction: " + conviction + " | Madness: " + madness);
    }

    // Method to save the player's stats to a file
    private void saveGameData() {
        try (FileWriter writer = new FileWriter(saveFileName)) {
            writer.write("Conviction: " + conviction + "\n");
            writer.write("Madness: " + madness + "\n");
        } catch (IOException e) {
            gameStatus.setText("Error saving game data.");
        }
    }

    // Method to load the player's stats from a file
    private void loadGameData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(saveFileName))) {
            conviction = Integer.parseInt(reader.readLine().split(": ")[1]);
            madness = Integer.parseInt(reader.readLine().split(": ")[1]);
            updateStats(); // Update the UI with loaded stats
        } catch (IOException e) {
            gameStatus.setText("No saved game data found, starting fresh.");
        } catch (NumberFormatException e) {
            gameStatus.setText("Error loading game data.");
        }
    }

    // Getter for the scene
    public Scene getScene() {
        return scene;
    }
}
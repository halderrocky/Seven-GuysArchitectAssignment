package edu.sdccd.cisc190.altscenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;

public class FiveAttackWater {
    private Scene scene;
    private int conviction; // Variable to track the conviction stat
    private int madness; // Variable to track the madness stat
    private final Text gameStatus;
    private final Text statsText;
    private final Button oneButton;
    private final Button twoButton;
    private final Button threeButton;
    private final Button fourButton;
    private final Button fiveButton;
    private final Button sixButton;
    private Button continueButton = null;
    private final String saveFileName = "game_data.txt"; // File name for saving game data

    // Array to manage enemies in the game
    private final String[] enemies = {"Mika the Monkey", "Ozzy the Ostrich", "Rumble the Raccoon"};
    private final int[] enemyHealth = {100, 100, 100}; // Health points for each enemy

    public FiveAttackWater(Stage primaryStage) {
        this.continueButton = continueButton;
        // Initial game status text
        gameStatus = new Text("You attacked Mika the Monkey by splashing water.\n" +
                "It was effective, but you wasted 25% of the water and realized you have to conserve it.\n" +
                "You closed the door, trapping Mika the Monkey in the electrical room, while leaving the animatronic broken.\n" +
                "You now know it’s reality and by this time, your objective is to run back to the office.\n" +
                "However, you see Ozzy the Ostrich running towards you, and he’s running towards you fast.\n");
        gameStatus.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        // Text for displaying stats (conviction and madness)
        statsText = new Text("Conviction: " + conviction + " | Madness: " + madness);
        statsText.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        // Initialize the buttons
        oneButton = new Button("Attack with water (25% remaining)");
        twoButton = new Button("Dodge attack");
        threeButton = new Button("Continue");
        fourButton = new Button("View Enemies");
        fiveButton = new Button("Save Game");
        sixButton = new Button("Load Game");

        // Load saved game data if available
        loadGameData();

        // Button actions
        oneButton.setOnAction(e -> {
            // Update game status text for the oneButton scenario
            gameStatus.setText("You attacked with water successfully.");
            conviction += 1; // Increase conviction
            updateStats();  // Update the stats text
        });

        twoButton.setOnAction(e -> {
            // Update game status text for the twoButton scenario
            gameStatus.setText("You dodged Ozzy's attack and made a quick escape!");
            madness -= 1; // Decrease madness
            updateStats();  // Update the stats text
        });

        threeButton.setOnAction(e -> {
            gameStatus.setText("You chose to continue your escape.");
            updateStats();  // Update the stats text
        });

        fourButton.setOnAction(e -> {
            // Display enemies and their health
            StringBuilder enemyStatus = new StringBuilder("Enemies:\n");
            for (int i = 0; i < enemies.length; i++) {
                enemyStatus.append(enemies[i]).append(" - Health: ").append(enemyHealth[i]).append("\n");
            }
            gameStatus.setText(enemyStatus.toString());
        });

        fiveButton.setOnAction(e -> saveGameData()); // Save game data
        sixButton.setOnAction(e -> loadGameData()); // Load game data

        // Create the BorderPane layout
        BorderPane layout = new BorderPane();

        // Add game status text to the top
        layout.setTop(gameStatus);

        // Add stats text below the game status text
        layout.setBottom(statsText);

        // Create a VBox to arrange buttons vertically
        VBox buttonBox = new VBox(10);  // 10px spacing between buttons
        buttonBox.getChildren().addAll(oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton);

        // Set the VBox containing buttons to the center of the BorderPane
        layout.setCenter(buttonBox);

        // Scene creation with appropriate size
        scene = new Scene(layout, 400, 400);
    }

    // Method to update the stats text
    private void updateStats() {
        statsText.setText("Conviction: " + conviction + " | Madness: " + madness);
        // Optionally save game data after stats update
        // saveGameData();
    }

    // Method to save the player's stats and enemy health to a file
    private void saveGameData() {
        try (FileWriter writer = new FileWriter(saveFileName)) {
            writer.write("Conviction:" + conviction + "\n");
            writer.write("Madness:" + madness + "\n");
            writer.write("Enemies:\n");
            for (int i = 0; i < enemies.length; i++) {
                writer.write(enemies[i] + ":" + enemyHealth[i] + "\n");
            }
        } catch (IOException e) {
            gameStatus.setText("Error saving game data.");
        }
    }

    // Method to load the player's stats and enemy health from a file
    private void loadGameData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(saveFileName))) {
            conviction = Integer.parseInt(reader.readLine().split(":")[1].trim());
            madness = Integer.parseInt(reader.readLine().split(":")[1].trim());
            reader.readLine(); // Skip the "Enemies:" line
            for (int i = 0; i < enemies.length; i++) {
                String line = reader.readLine();
                String[] parts = line.split(":");
                enemyHealth[i] = Integer.parseInt(parts[1].trim());
            }
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

    public int getConviction() {
        return conviction;
    }

    public int getMadness() {
        return madness;
    }
}
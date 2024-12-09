package edu.sdccd.cisc190.generalstuff;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * The MainGame class is the entry point for the JavaFX application representing the game
 * "NightShift at Seven Guys". It extends the {@link Application} class to create a JavaFX
 * application lifecycle.
 *
 * <p>This class initializes the primary stage of the application, creates instances of
 * different scenes such as the title screen, main menu, and game screen, and sets the
 * initial scene to the title screen.</p>
 *
 * <p>It also contains the main method which launches the JavaFX application.</p>
 *
 * <p>Usage:</p>
 * <pre>
 * public static void main(String[] args) {
 *     launch(args);
 * }
 * </pre>
 *
 * <p>Example:</p>
 * <pre>
 * MainGame game = new MainGame();
 * game.start(new Stage());
 * </pre>
 *
 * @see javafx.application.Application
 * @see TitleScreen
 * @see MainMenu
 * @see PreLude
 */
public class MainGame extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create scenes for each part of the game
        TitleScreen titleScreen = new TitleScreen(primaryStage);
        MainMenu mainMenu = new MainMenu(primaryStage);
        PreLude gameScreen = new PreLude(primaryStage);

        // Set the title screen as the initial scene
        primaryStage.setTitle("NightShift at Seven Guys (UNFINISHED VERSION)");
        primaryStage.setScene(titleScreen.getScene());

        // Set the stage to full screen
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("Press ESC to exit full screen."); // Optional hint

        primaryStage.show();

        // Start a background thread for periodic updates
        startBackgroundTimer();
    }

    /**
     * Starts a background timer that updates the game state every specified interval.
     */
    private void startBackgroundTimer() {
        new Thread(() -> {
            try {
                while (true) {
                    // Simulate a periodic task by sleeping for 1 minute (60000 milliseconds)
                    Thread.sleep(60000); // Update every minute

                    // Update game state (e.g., check player status or time remaining)
                    Platform.runLater(() -> {
                        // Here we can update the UI or game state
                        System.out.println("Updating game state...");
                        // You can add more logic here, for example, updating a timer or checking conditions
                    });
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
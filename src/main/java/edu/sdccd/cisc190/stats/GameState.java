package edu.sdccd.cisc190.stats;

/**
 * The GameState class tracks and manages the player's game stats: Conviction and Madness.
 * It provides methods to retrieve and increment these stats.
 *
 * <p>Usage: This class is used to maintain the state of the player's decisions
 * throughout the game.</p>
 *
 * @author RockyHalder
 */

public class GameState {
    private int conviction;
    private int madness;

    public GameState() {
        this.conviction = 0;
        this.madness = 0;
    }

    public int getConviction() {
        return conviction;
    }

    public void increaseConviction() {
        conviction++;
    }

    public int getMadness() {
        return madness;
    }

    public void increaseMadness() {
        madness++;
    }
}

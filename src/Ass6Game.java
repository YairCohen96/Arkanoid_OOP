// Yair Cohen 313355786
import Flow.AnimationRunner;
import Flow.GameFlow;
import biuoop.GUI;
import levels.DirectHit;
import levels.FinalFour;
import levels.Green3;
import levels.LevelInformation;
import levels.WideEasy;

import java.util.ArrayList;
/**
 * Ass6Game class runs a new game, according to the game I created on assigment 5
 * in the Arkanoid.
 * author: Yair Cohen
 * version date: 30/05/22
 */

public class Ass6Game {
    /**
     * main method that runs the game.
     * @param args - gets info from the user. not used in this case.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui);
        ArrayList<LevelInformation> levels = new ArrayList<>();
        // not empty
            for (String arg : args) {
                try {
                    if (Integer.parseInt(arg) == 1) {
                        levels.add(new DirectHit());
                    } else if (Integer.parseInt(arg) == 2) {
                        levels.add(new WideEasy());
                    } else if (Integer.parseInt(arg) == 3) {
                        levels.add(new Green3());
                    } else if (Integer.parseInt(arg) == 4) {
                        levels.add(new FinalFour());
                    }
                } catch (Exception notInt) {
                    continue;
                }
            }
            if (levels.size() == 0) { //empty input
            //if (Arrays.stream(args).toList().isEmpty()) {
                levels.add(new DirectHit());
                levels.add(new WideEasy());
                levels.add(new Green3());
                levels.add(new FinalFour());
        }
        GameFlow flow = new GameFlow(runner);
        flow.runLevels(levels);
       }
    }

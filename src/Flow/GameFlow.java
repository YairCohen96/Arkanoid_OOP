package Flow;

import Animations.CountdownAnimation;
import Animations.EndScreen;
import Animations.GameLevel;
import Animations.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import levels.LevelInformation;

import java.util.List;

/**
 * Class is responsible for running the levels of the game one by one in a good flow without stopping until
 * game is over.
 * author: Yair Cohen
 * version date: 30/05/22
 */
public class GameFlow {
    private AnimationRunner runner;
    private boolean win = true;
    //private KeyboardSensor sensor;

    /**
     * Constructor for the class.
     * @param ar the animation runner that will run all of the levels according to flow.
     */
    public GameFlow(AnimationRunner ar) {
            runner = ar;
        }

    /**
     * Method will run the list of level one by one by its original order.
     * @param levels the list of levels to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        // ...
        int scoreKeeper = 0;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, runner.getGui());
            level.getScoreTrack().getCurrentScore().increase(scoreKeeper);
            level.initialize();
            this.runner.run(new CountdownAnimation(3, 3, level.getSprites()));
            while (!level.shouldStop()) {
                level.run();
            }
            scoreKeeper = level.getScoreTrack().getCurrentScore().getValue();
            if (level.getRemainingBalls().getValue() == 0) {
                win = false;
                break;
            }
        }
        KeyboardSensor key = runner.getGui().getKeyboardSensor();
        EndScreen endScreen = new EndScreen(win, scoreKeeper, runner.getGui(), key);
        runner.run(new KeyPressStoppableAnimation(key, KeyboardSensor.SPACE_KEY, endScreen));
        runner.getGui().close();
    }
}

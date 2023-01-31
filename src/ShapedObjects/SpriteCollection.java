package ShapedObjects;// Yair Cohen 313355786
import biuoop.DrawSurface;
import java.util.ArrayList;
/**
 * GameChangers.ShapedObjects.SpriteCollection class creates a new list of sprite objects for a game in the Arkanoid.
 * author: Yair Cohen
 * version date: 12/04/22
 */
public class SpriteCollection {
    private ArrayList<Sprite> sprites;

    /**
     * Constructor for the class.
     * initializes the list of sprite with the new data.
     * @param sprites - the data to be copied to the list.
     */
    public SpriteCollection(ArrayList sprites) {
        this.sprites = new ArrayList<>(sprites);
    }

    /**
     * Adds a new sprite object to the list.
     * @param s the sprite to be added
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }
    /**
     * Removes a sprite object to the list.
     * @param s the sprite to be added
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }
    /**
     * Call timePassed() on all sprites in the list.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites in the list.
     * @param d the canvas for all to be drawn on
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).drawOn(d);
        }
    }
} //end of class
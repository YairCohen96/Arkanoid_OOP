package DataTrack;//Yair Cohen 313355786
/**
 * DataTrack.HitNotifier interface informs its objects when a hit is happening in the game.
 * author: Yair Cohen
 * version date: 17/05/22
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl object to be added.
     */
    void addHitListener(HitListener hl);
    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl object to be added.
     */
    void removeHitListener(HitListener hl);
}
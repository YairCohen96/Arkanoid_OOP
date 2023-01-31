package DataTrack;// Yair Cohen 313355786
/**
 * DataTrack.Counter class is responsible for holding count of any amount.
 * of the number of blocks that remain.
 * author: Yair Cohen
 * version date: 17/05/22
 */
public class Counter {
    private int value;
    /**
     *Construct a new DataTrack.Counter with value of 0.
     */
    public  Counter() {
        value = 0;
    }
    /**
     *Construct a new DataTrack.Counter.
     * @param val first value of counter.
     */
    public Counter(int val) {
        value = val;
    }
    /**
     * add number to current count.
     * @param number to be added.
     */
    public void increase(int number) {
        value += number;
    }
    /**
     * subtract number to current count.
     * @param number to be added.
     */
    public void decrease(int number) {
        value -= number;
    }
    /**
     * @return current count.
     */
    public int getValue() {
        return value;
    }
}
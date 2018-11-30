package observer;

/**
 * IObserver will be overwritten in the view
 */
public interface IObserver {
    //observable is the object we are observing
    void update(Observable observable, Object... args);
}
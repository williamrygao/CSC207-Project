package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ViewModel for our CA implementation.
 * This class delegates work to a PropertyChangeSupport object for
 * managing the property change events.
 *
 * @param <T> The type of state object contained in the model.
 */
public class ViewModel<T> {

    /**
     * String viewName.
     */
    private final String viewName;

    /**
     * New PropertyChangeSupport.
     */
    private final PropertyChangeSupport support = new PropertyChangeSupport(
            this);

    /**
     * T state.
     */
    private T state;

    /**
     * ViewModel method.
     * @param viewName the viewName
     */
    public ViewModel(final String viewName) {
        this.viewName = viewName;
    }

    /**
     * Getter for viewName.
     * @return viewName
     */
    public String getViewName() {
        return this.viewName;
    }

    /**
     * Getter for state.
     * @return the state
     */
    public T getState() {
        return this.state;
    }

    /**
     * Setter for state.
     * @param state the state
     */
    public void setState(final T state) {
        this.state = state;
    }

    /**
     * Fires a property changed event for the state of this ViewModel.
     */
    public void firePropertyChanged() {
        this.support.firePropertyChange("state", null, this.state);
    }

    /**
     * Fires a property changed event for the state of this ViewModel, which
     * allows the user to specify a different propertyName. This can be useful
     * when a class is listening for multiple kinds of property changes.
     * <p/>
     * For example, the LoggedInView listens for two kinds of property changes;
     * it can use the property name to distinguish which property has changed.
     * @param propertyName the label for the property that was changed
     */
    public void firePropertyChanged(final String propertyName) {
        this.support.firePropertyChange(propertyName, null, this.state);
    }

    /**
     * Adds a PropertyChangeListener to this ViewModel.
     * @param listener The PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(final PropertyChangeListener
                                                  listener) {
        this.support.addPropertyChangeListener(listener);
    }
}

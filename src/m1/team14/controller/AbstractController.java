package m1.team14.controller;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import m1.team14.model.AbstractModel;
import m1.team14.view.IViewPanel;
import java.lang.reflect.Method;

public abstract class AbstractController implements PropertyChangeListener {

    private ArrayList<IViewPanel> registeredViews;
    private ArrayList<AbstractModel> registeredModels;

    public AbstractController() {
        registeredViews = new ArrayList<IViewPanel>();
        registeredModels = new ArrayList<AbstractModel>();
    }

    public void addModel(AbstractModel model) {
        registeredModels.add(model);
        model.addPropertyChangeListener(this);
    }

    public void removeModel(AbstractModel model) {
        registeredModels.remove(model);
        model.removePropertyChangeListener(this);
    }

    public void addView(IViewPanel view) {
        registeredViews.add(view);
    }

    public void removeView(IViewPanel view) {
        registeredViews.remove(view);
    }
    //  Use this to observe property changes from registered models
    //  and propagate them on to all the views.
    public void propertyChange(PropertyChangeEvent evt) {
        for (IViewPanel view: registeredViews) {
            view.modelPropertyChange(evt);
        }
    }
    /**
     * This is a convenience method that subclasses can call upon
     * to fire property changes back to the models. This method
     * uses reflection to inspect each of the model classes
     * to determine whether it is the owner of the property
     * in question. If it isn't, a NoSuchMethodException is thrown,
     * which the method ignores.
     *
     * @param propertyName = The name of the property.
     * @param newValue = An object that represents the new value
     * of the property.
     */
    protected void setModelProperty(String propertyName, Object newValue) {
        for (AbstractModel model: registeredModels) {
            try {
                Method method = model.getClass().
                    getMethod("set" + propertyName, new Class[] {
                                                      newValue.getClass()
                                                  }
                             );
                method.invoke(model, newValue);
            } catch (Exception ex) {
              //  Handle exception.
              ex.printStackTrace();
            }
        }
    }
}

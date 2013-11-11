package com.kryonation.mocha.wireframe;

import static com.kryonation.mocha.factories.MochaFactory.showFrame;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JFrame;

import com.kryonation.mocha.controllers.MochaController;
import com.kryonation.mocha.views.MochaView;



/**
 * Abstract Frame class to act as the front app controller which wires together the views/controllers
 * @author Brandon Martel 
 * @version Oct-19-2013
 */
public abstract class MochaFrame {
    protected final JFrame frame;
    protected final Map<Class<? extends MochaView<? extends JComponent>>, MochaView<? extends JComponent>> views = new HashMap<Class<? extends MochaView<? extends JComponent>>, MochaView<? extends JComponent>>();
    protected final Map<Class<? extends MochaController>, MochaController> controllers = new HashMap<Class<? extends MochaController>, MochaController>();
    protected final Map<String, Class<? extends MochaView<? extends JComponent>>> viewMap = new HashMap<String, Class<? extends MochaView<? extends JComponent>>>();
    protected final Map<String, Class<? extends MochaController>> controllerMap = new HashMap<String, Class<? extends MochaController>>();
    
    public MochaFrame() {
    	registerAllControllers();
        registerAllViews();
        registerViewComponents();
        this.frame = layout();
    }

    /**
     * Registers all application views
     * Ex. views.put(NamedView.class, new NamedView(this));
     */
    protected abstract void registerAllViews();

    /**
     * Registers all application controllers
     * Ex. controllers.put(NamedController.class, new NamedController(this));
     */
    protected abstract void registerAllControllers();

    /**
     * Registers all application view components
     * 
     */
    protected abstract void registerViewComponents();
    
    /**
     * This is what builds the current view which will be displayed in the frame
     * @return Compiled component view
     */
    protected abstract JFrame layout();

    /**
     * 
     * Launches the current frame layout
     *
     */
    protected void show() {
        showFrame(frame);
    }
    
    /**
     * Helper method for registering a controller to the app
     * @param controllerName
     * @param controllerClass
     * @param controller
     */
    public <C extends MochaController> void registerController(String controllerName, Class<C> controllerClass, C controller){
    	controllerMap.put(controllerName, controllerClass);
    	controllers.put(controllerClass, controller);
    }
    
    /**
     * Helper method for registering a view to the app
     * @param viewName
     * @param viewClass
     * @param view
     */
    public <V extends MochaView<? extends JComponent>> void registerView(String viewName, Class<V> viewClass, V view){
    	viewMap.put(viewName, viewClass);
    	views.put(viewClass, view);
    }
    
    /**
     * Retrieves view by its class reference
     * @param viewClass
     * @return Typed view, using reflection
     */
    @SuppressWarnings("unchecked")
    public <V extends MochaView<? extends JComponent>> V getView(Class<V> viewClass) {
        return (V) views.get(viewClass);
    }
    
    /**
     * Retrieves controller by its class reference
     * @param controllerClass
     * @return Typed controller, using reflection
     */
    @SuppressWarnings("unchecked")
    public <C extends MochaController> C getController(Class<? extends MochaController> controllerClass) {
        return (C) controllers.get(controllerClass);
    }

    /**
     * Retrieves view by its tagged name
     * @param view
     * @return Typed view, using reflection
     */
    @SuppressWarnings("unchecked")
    public <V extends MochaView<? extends JComponent>> V getViewByName(String view) {
        return (V) views.get(viewMap.get(view));
    }
    
    /**
     * Retrieves view by its child component
     * @param viewComponent
     * @return Typed view, using reflection
     */
    @SuppressWarnings("unchecked")
    public <V extends MochaView<? extends JComponent>> V getParentView(String viewComponent) {
        for (MochaView<? extends JComponent> view : views.values()) {
			if(view.getComponentById(viewComponent) != null){ return (V) view;}
		}
    	return null;
    }
    
    /**
     * Retrieves the controller by its tagged name
     * @param controller
     * @return Typed controller, using reflection
     */
    @SuppressWarnings("unchecked")
    public <C extends MochaController> C getControllerByName(String controller) {
        return (C) controllers.get(controllerMap.get(controller));
    }
    
    /**
     * Get the controller class by its tag name
     * @param controller
     * @return
     */
    public Class<? extends MochaController> getControllerClass(String controller){
    	return controllerMap.get(controller);
    }
    
    /**
     * Get the view class by its tag name
     * @param view
     * @return
     */
    public Class<? extends MochaView<? extends JComponent>> getViewClass(String view){
    	return viewMap.get(view);
    }
}

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
     * 
     * @param viewClass
     * @return Typed view, using reflection
     */
    @SuppressWarnings("unchecked")
    public <V extends MochaView<? extends JComponent>> V getView(Class<V> viewClass) {
        return (V) views.get(viewClass);
    }
    
    /**
     * 
     * @param controllerClass
     * @return Typed controller, using reflection
     */
    @SuppressWarnings("unchecked")
    public <C extends MochaController> C getController(Class<? extends MochaController> controllerClass) {
        return (C) controllers.get(controllerClass);
    }

}

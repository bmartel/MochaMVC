package com.kryonation.mocha.views;

import java.awt.Component;
import java.util.HashMap;

import javax.swing.JComponent;

import com.kryonation.mocha.wireframe.MochaFrame;


/**
 * A view which is a panel of nested view JComponents, wires actions to components to a MochaController to update 
 * the associated Model
 * @author Brandon Martel
 * @version Oct-19-2013
 * @param <C>
 */
public abstract class MochaView<C extends JComponent> {
    private final MochaFrame mainFrame;
    private final C contentPane;
    protected final HashMap<String,JComponent> componentMap = new HashMap<String,JComponent>();;

    /**
     * Registers the view to the presenter frame
     * @param mainFrame
     */
    public MochaView(MochaFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.contentPane = layout();
//        createComponentMap();
    }

    /**
     * Creates a nested layout which contains any JComponent subclass
     * @return JComponent
     */
    protected abstract C layout();

    /**
     * Returns the bound view presenter frame
     * @return MochaFrame
     */
    public MochaFrame getMainFrame() {
        return mainFrame;
    }
    
    /**
     * Returns this views layout (Subclass of JComponent)
     * @return JComponent
     */
    public C getContentPane() {
        return contentPane;
    }
    
    /**
     * Registers components to its ID
     * @param id
     * @param component
     * @return 
     */
    protected <V extends JComponent> V registerComponent(String id, V component){
    	componentMap.put(id, component);
    	return (V) component; 
    }
	@SuppressWarnings("unchecked")
	public <V extends JComponent> V getComponentById(String id){
		return (V) componentMap.get(id);
    }
    /**
     * Gets all JComponents in the View
     */
    protected void createComponentMap() {
        
        Component[] components = this.getContentPane().getComponents();
        for (int i=0; i < components.length; i++) {
        	if(components[i] instanceof JComponent)
        		System.out.println(components[i].getName());
        			componentMap.put(components[i].getName(), (JComponent)components[i]);
        }
	}
	
    /**
     * Finds and returns a JComponent by its registered ID(Name)
     * @param name
     * @return JComponent
     */
	public JComponent getComponentByName(String name) {
        if (componentMap.containsKey(name)) {
        	return componentMap.get(name);
        }
        else return null;
	}	
}

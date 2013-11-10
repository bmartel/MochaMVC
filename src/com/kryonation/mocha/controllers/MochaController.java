package com.kryonation.mocha.controllers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.kryonation.mocha.models.MochaModel;
import com.kryonation.mocha.wireframe.MochaFrame;

/**
 * A controller which provides mediation for MochaFrame presenter, to the MochaView, and updating the associated models with data changes.
 * @author Brandon Martel
 * @version Oct-19-2013
 *
 */
public abstract class MochaController implements PropertyChangeListener {
	private final MochaFrame frame;
	@SuppressWarnings("rawtypes")
	protected final ArrayList<MochaModel> models = new ArrayList<MochaModel>();
	protected final HashMap<String, JComponent> registeredComponents = new HashMap<String, JComponent>();
	/**
	 * Registers the presenter frame
	 * @param MochaFrame
	 */
	public MochaController(MochaFrame frame) {
		this.frame = frame;
		registerAllModels();
	}
	/**
	 * Register Data Models
	 */
	protected abstract void registerAllModels();
	
//	//(String)evt.getPropertyName(), ViewComponent to update 
//	protected <V extends JComponent> V registerViewComponent(String id, V component){
//		registeredComponents.put(id, component);
//    	return (V) component; 
//    }
//	
//	@SuppressWarnings("unchecked")
//	protected <V extends JComponent> V getViewComponentById(String id){
//		return (V) registeredComponents.get(id);
//    }

	/**
	 * Returns the bound presenter frame
	 * @return MochaFrame
	 */
	protected MochaFrame getMainFrame() {
		return frame;
	}
	
	/**
	 * Setter for model registrations
	 * @param model
	 */
	@SuppressWarnings("rawtypes")
	protected void registerModel(MochaModel model){
		models.add(model);
	}

	/**
	 * Updates registered view components with new data
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt){
		// Get the property for which fired the event from the model
		System.out.println("Class which property has changed " + evt.getSource());
		
		System.out.println("Property which was changed: "+evt.getPropertyName());
		System.out.println("Component to update: "+ getMainFrame().getViewByComponentId(evt.getPropertyName()).getViewComponentById(evt.getPropertyName()));
		
		updateComponent(getMainFrame().getViewByComponentId(evt.getPropertyName()).getViewComponentById(evt.getPropertyName()), evt.getNewValue());
		
	}
	

	protected <V extends JComponent>V updateComponent(V component, Object newValue){
		if(component instanceof JTextField){
			((JTextField) component).setText(newValue.toString());
		}else if(component instanceof JTextArea){
			((JTextArea) component).setText(newValue.toString());
		}
		return component;
		
	}
}

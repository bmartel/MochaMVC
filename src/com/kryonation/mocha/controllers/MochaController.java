package com.kryonation.mocha.controllers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;
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

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class MochaController implements PropertyChangeListener {
	private final MochaFrame frame;
	protected final Map<String, MochaModel> models = new HashMap<String, MochaModel>();
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
	protected <C extends MochaModel> void registerModel(String modelName, C model){
		models.put(modelName,model);
	}

	/**
	 * Get a registered model by its registered tag id
	 * @param modelName
	 * @return
	 */
	protected <C extends MochaModel> C getModelById(String modelName){
		return (C) models.get(modelName);
	}
	/**
	 * Updates registered view components with new data
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt){
		// Get the property for which fired the event from the model
		System.out.println("Class which property has changed " + evt.getSource());
		
		System.out.println("Property which was changed: "+evt.getPropertyName());
		System.out.println("Component to update: "+ getMainFrame().getParentView(evt.getPropertyName()).getComponentById(evt.getPropertyName()));
		
		updateComponent(getMainFrame().getParentView(evt.getPropertyName()).getComponentById(evt.getPropertyName()), evt.getNewValue());
		
	}
	
	/**
	 * Update the bound component using the best known way to display the data for each element
	 * @param component
	 * @param newValue
	 * @return
	 */
	protected <V extends JComponent>V updateComponent(V component, Object newValue){
		if(component instanceof JTextField){
			((JTextField) component).setText(newValue.toString());
		}else if(component instanceof JTextArea){
			((JTextArea) component).setText(newValue.toString());
		}else if(component instanceof JLabel){
			((JLabel) component).setText(newValue.toString());
		}
		return component;
		
	}
}

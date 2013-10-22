package com.kryonation.mocha.controllers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;

import com.kryonation.mocha.models.MochaModel;
import com.kryonation.mocha.wireframe.MochaFrame;

/**
 * A controller which provides mediation for MochaFrame presenter, to the MochaView, and updating the associated models with data changes.
 * @author brand_000
 *
 */
public abstract class MochaController implements PropertyChangeListener {
	private final MochaFrame frame;
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
	
	//(String)evt.getPropertyName(), ViewComponent to update 
	public void registerViewComponent(String id, JComponent viewComponent) {
		registeredComponents.put(id, viewComponent);
	}

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
	protected void registerModel(MochaModel model){
		models.add(model);
	}

	/**
	 * Updates registered view components with new data
	 */
	@Override
	public abstract void propertyChange(PropertyChangeEvent evt);
}
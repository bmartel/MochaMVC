package com.kryonation.mocha.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.kryonation.mocha.controllers.MochaController;

/**
 * An abstract Model which handles data binding and change events to associated controllers
 * @author brand_000
 *
 */
public abstract class MochaModel  {
	private final MochaController controller;
	protected PropertyChangeSupport mPropertyChangeSupport;
	
	public MochaModel(MochaController controller){
		 mPropertyChangeSupport = new PropertyChangeSupport(this);
		 this.controller = controller;
		 addPropertyChangeListener(this.controller);
		 
	}
	
	protected abstract void registerAllPropertyChangeListeners();
	
	/**
	 * Registers a new controller to handle the property change events
	 * @param listener
	 */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
    	mPropertyChangeSupport.addPropertyChangeListener(listener);
    }
    
    /**
     * Removes a registered controller
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
    	mPropertyChangeSupport.removePropertyChangeListener(listener);
    }
}

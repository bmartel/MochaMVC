package com.kryonation.mocha.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.kryonation.mocha.controllers.MochaController;
import com.kryonation.mocha.interfaces.DataContainer;

/**
 * An abstract Model which handles data binding and change events to associated controllers
 * @author Brandon Martel
 * @version Oct-19-2013
 */
public abstract class MochaModel<C extends MochaController> implements DataContainer {
	private C controller;
	protected PropertyChangeSupport mPropertyChangeSupport;
	

	public MochaModel(C controller){
		 mPropertyChangeSupport = new PropertyChangeSupport(this);
		 this.controller = controller;
		 addPropertyChangeListener(this.controller);
		 
	}

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

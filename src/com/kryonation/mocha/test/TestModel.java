package com.kryonation.mocha.test;

import com.kryonation.mocha.controllers.MochaController;
import com.kryonation.mocha.models.MochaModel;

@SuppressWarnings("rawtypes")
public class TestModel extends MochaModel {
	
	@SuppressWarnings("unchecked")
	public TestModel(MochaController controller) {
		super(controller);
		System.out.println("TestModel Controller: " + controller.getClass());
	}

	private int testvalue;

	// For all regular methods of retrieval, bind the controller action to a getter like the following to get the 
	// data back to a view component
	public int getTestvalue() {
		return testvalue;
	}

	// All methods modifying data and presenting a callback to a bound view component must specify 
	// the components tag name in the firePropertyChange method as shown below
	public void setTestvalue(int testvalue) {
		int oldTestValue = this.testvalue;
		this.testvalue = testvalue;
		System.out.println("Fired property change for testValue: OldValue => " + oldTestValue + "NewValue => "+this.testvalue);
		mPropertyChangeSupport.firePropertyChange("testResult",
                oldTestValue, testvalue);
	}
	
}

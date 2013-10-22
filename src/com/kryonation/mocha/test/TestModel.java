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

	public int getTestvalue() {
		return testvalue;
	}

	public void setTestvalue(int testvalue) {
		int oldTestValue = this.testvalue;
		this.testvalue = testvalue;
		System.out.println("Fired property change for testValue: OldValue => " + oldTestValue + "NewValue => "+this.testvalue);
		mPropertyChangeSupport.firePropertyChange("testValue",
                oldTestValue, testvalue);
	}
	
}

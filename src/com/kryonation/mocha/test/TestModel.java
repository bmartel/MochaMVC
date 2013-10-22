package com.kryonation.mocha.test;

import com.kryonation.mocha.controllers.MochaController;
import com.kryonation.mocha.models.MochaModel;

public class TestModel extends MochaModel {
	
	public TestModel(MochaController controller) {
		super(controller);
	}

	private int testvalue;

	public int getTestvalue() {
		return testvalue;
	}

	public void setTestvalue(int testvalue) {
		int oldTestValue = this.testvalue;
		this.testvalue = testvalue;
		System.out.println("Fired property change for testValue");
		mPropertyChangeSupport.firePropertyChange("testValue",
                oldTestValue, testvalue);
	}
	
}

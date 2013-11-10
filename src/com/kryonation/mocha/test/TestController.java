package com.kryonation.mocha.test;

import java.beans.PropertyChangeEvent;

import javax.swing.JTextField;

import com.kryonation.mocha.controllers.MochaController;
import com.kryonation.mocha.wireframe.MochaFrame;

public class TestController extends MochaController{

	public TestController(MochaFrame frame) {
		super(frame);
		System.out.println("TestController Instantiated");
	}
	
	@Override
	protected void registerAllModels() {
		registerModel(new TestModel(this));
	}

	/**
	 * Calls/Updates to Model(Request for view updates)
	 * ===================================================
	 */
	
	/**
	 * Crud and other related action tasks should be placed here
	 */
	
	/**
	 * Test for all primitives, and random other structures/objects
	 * 
	 *  ret.put(Boolean.class, Boolean.TYPE);
        ret.put(Character.class, Character.TYPE);
        ret.put(Byte.class, Byte.TYPE);
        ret.put(Short.class, Short.TYPE);
        ret.put(Integer.class, Integer.TYPE);
        ret.put(Long.class, Long.TYPE);
        ret.put(Float.class, Float.TYPE);
        ret.put(Double.class, Double.TYPE);
	 */
	
	/*
	 * Tests Void method calls
	 */
	public void updateTestModel(){
		System.out.println("Called: updateTestModel()");
		JTextField value = getMainFrame().getView(TestView.class).getViewComponentById("testValue");
		
		
		TestModel model = (TestModel) models.get(0);
		
		int intValue = 0;
		if(!value.getText().equals(""))
			intValue = Integer.parseInt(value.getText());
		System.out.println("Value in textfield: " + intValue );
		model.setTestvalue(intValue);
	}
	
	/*
	 * Tests Integer parameter calls
	 */
	public void testActionInt(int number){
		System.out.println("Called: testAction() and the number: " + number);
	}
	
	/*
	 * Tests Boolean parameter call
	 */
	public void testActionBoolean(boolean bool){
		System.out.println("Called: testAction() and the boolean of: " + bool);
	}
	
	/*
	 * Tests Boolean parameter call
	 */
	public void testActionBooleanAndInt(boolean bool, int number){
		System.out.println("Called: testAction() and the boolean of: " + bool + " and a number: " + number);
	}
	
	
	/**
	 * Updates to View (Response from change to Data Model)
	 * ===================================================
	 */
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// Get the property for which fired the event from the model
		System.out.println("");
		System.out.println("Property which was changed: "+evt.getPropertyName());
		System.out.println("Component to update: "+ getMainFrame().getView(TestView.class).getViewComponentById(evt.getPropertyName()));
		
		updateComponent(getMainFrame().getView(TestView.class).getViewComponentById(evt.getPropertyName()), evt.getNewValue());
		

	}

	public void updateTestValue(){
		
	}



}
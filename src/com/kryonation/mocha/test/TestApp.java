package com.kryonation.mocha.test;

import java.awt.Dimension;

import javax.swing.JFrame;

import static com.kryonation.mocha.factories.MochaFactory.frame;

import com.kryonation.mocha.wireframe.MochaFrame;

public class TestApp extends MochaFrame {

	@Override
	protected void registerAllViews() {
		views.put(TestView.class, new TestView(this));		
	}

	@Override
	protected void registerAllControllers() {
		controllers.put(TestController.class, new TestController(this));		
	}

	@Override
	protected JFrame layout() {
		return frame("Demo MochaMVC Framework", getView(TestView.class).getContentPane(), null, new Dimension(800,600));

	}
	public static void main(String[]args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
            	System.out.println("Running TestApp()");
               new TestApp().show();
            }
        });
		
	}

	@Override
	protected void registerViewComponents() {
		System.out.println("Registering component: " + getView(TestView.class).getComponentByName("text1"));
		controllers.get(TestController.class).registerViewComponent("testValue", getView(TestView.class).getComponentByName("text1"));
	}

}



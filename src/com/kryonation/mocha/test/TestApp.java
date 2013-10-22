package com.kryonation.mocha.test;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
	     // Set System L&F
        try {
			UIManager.setLookAndFeel(
			    UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		System.out.println("Registering component: " + getView(TestView.class).getComponentByName("testValue"));
//		controllers.get(TestController.class).registerViewComponent("testValue", getView(TestView.class).getComponentByName("text1"));
	}

}



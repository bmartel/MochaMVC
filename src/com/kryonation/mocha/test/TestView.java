package com.kryonation.mocha.test;


import javax.swing.JPanel;
import javax.swing.JTextField;

import static com.kryonation.mocha.factories.MochaFactory.button;
import static com.kryonation.mocha.factories.MochaFactory.buttons;
import static com.kryonation.mocha.factories.MochaFactory.textField;

import com.kryonation.mocha.action.MochaActionEvent;
import com.kryonation.mocha.views.MochaView;
import com.kryonation.mocha.wireframe.MochaFrame;

public class TestView extends MochaView<JPanel> {

	public TestView(MochaFrame mainFrame) {
		super(mainFrame);
		System.out.println("Instantiated TestView()");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected JPanel layout() {
//		JPanel panel = new JPanel(new BorderLayout());
		JTextField text1 = textField(10, true);
		JPanel buttonBar1 = buttons(
				registerComponent("button1",button("Void", new MochaActionEvent(this, "TestController/updateTestModel", null)))
//				registerComponent("button1",button("Integer", new MochaActionEvent(this, TestController.class,"testActionInt", new Object[]{2}))),
//				registerComponent("button1",button("Boolean", new MochaActionEvent(this, TestController.class,"testActionBoolean", new Object[]{false}))),
//				registerComponent("button1",button("Boolean", new MochaActionEvent(this, TestController.class,"testActionBooleanAndInt", new Object[]{false,2}))),
//				registerComponent("button1",button("Boolean", new MochaActionEvent(this, TestController.class,"testActionBoolean", new Object[]{false}))),
//				registerComponent("button1",button("Boolean", new MochaActionEvent(this, TestController.class,"testActionBoolean", new Object[]{false}))),
//				registerComponent("button1",button("Boolean", new MochaActionEvent(this, TestController.class,"testActionBoolean", new Object[]{false})))
		);
		buttonBar1.add(registerComponent("testValue",text1));
		return buttonBar1;
	}

}

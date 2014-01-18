package com.kryonation.mocha.test;

import javax.swing.JPanel;

import static com.kryonation.mocha.factories.MochaFactory.button;
import static com.kryonation.mocha.factories.MochaFactory.buttons;

import com.kryonation.mocha.action.MochaActionEvent;
import com.kryonation.mocha.components.MLabel;
import com.kryonation.mocha.components.MTextField;
import com.kryonation.mocha.views.MochaView;
import com.kryonation.mocha.wireframe.MochaFrame;

public class TestView extends MochaView<JPanel> {

	public TestView(MochaFrame mainFrame) {
		super(mainFrame);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected JPanel layout() {
		MTextField text1 = new MTextField(10, true);
		MLabel text2 = new MLabel("");
		JPanel buttonBar1 = buttons(
				registerComponent("button1",button("Void", new MochaActionEvent(this, "TestController.updateTestModel", null))),
				registerComponent("button1",button("Integer", new MochaActionEvent(this, "TestController.testActionInt", new Object[]{2}))),
				registerComponent("button1",button("Boolean", new MochaActionEvent(this, "TestController.testActionBoolean", new Object[]{false}))),
				registerComponent("button1",button("Boolean", new MochaActionEvent(this, "TestController.testActionBooleanAndInt", new Object[]{false,2}))),
				registerComponent("button1",button("Boolean", new MochaActionEvent(this, "TestController.testActionBoolean", new Object[]{false}))),
				registerComponent("button1",button("Boolean", new MochaActionEvent(this, "TestController.testActionBoolean", new Object[]{false}))),
				registerComponent("button1",button("Boolean", new MochaActionEvent(this, "TestController.testActionBoolean", new Object[]{false})))
		);
		buttonBar1.add(registerComponent("testValue",text1));
		buttonBar1.add(registerComponent("testResult",text2));
		return buttonBar1;
	}

}

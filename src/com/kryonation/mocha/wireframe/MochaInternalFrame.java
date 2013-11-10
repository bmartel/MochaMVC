package com.kryonation.mocha.wireframe;

import static com.kryonation.mocha.factories.MochaFactory.internalFrame;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import com.kryonation.mocha.views.MochaView;

public class MochaInternalFrame <V extends MochaView<JPanel>> extends MochaView<JInternalFrame> {

    private final JDesktopPane desktopPane;
    private final V view;
    private final JInternalFrame internalFrame;
    
	public MochaInternalFrame(MochaFrame mainFrame,JDesktopPane desktopPane, V view) {
		super(mainFrame);
		// TODO Auto-generated constructor stub
        this.desktopPane = desktopPane;
        this.view = view;
        this.internalFrame = internalFrame(view.getContentPane(), view.getClass().getSimpleName());
	}

	@Override
	protected JInternalFrame layout() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
    // ------------ Request Code Goes Here

    // ------------ Response Code Goes Here
	
	public void setTitle(String title) {
        internalFrame.setTitle(title);
    }
    
    public V getView() {
        return view;
    }

    public void show() {
        desktopPane.add(internalFrame);
        internalFrame.pack();
        internalFrame.setVisible(true);
        try {
            internalFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
    }

    public void close() {
        internalFrame.dispose();
        desktopPane.remove(internalFrame);
    }

}

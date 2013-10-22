package com.kryonation.mocha.factories;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;

/**
 * Helper method to create common swing components
 * @author Brandon Martel
 *
 */
public abstract class MochaFactory {
	
	/**
	 * Creates a JFrame according to some common supplied parameters
	 * @param title
	 * @param contentPane
	 * @param toolBar
	 * @param dimension
	 * @return JFrame
	 */
	public static JFrame frame(String title, JComponent contentPane, JToolBar toolBar, Dimension dimension) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (title != null) frame.setTitle(title);
		if (toolBar != null) frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		if (contentPane != null) frame.getContentPane().add(contentPane, BorderLayout.CENTER);
		if(dimension != null) 
			frame.setPreferredSize(dimension);
		else 
			frame.setPreferredSize(new Dimension(800, 600));
		return frame;
	}

	/**
	 * Takes a JFrame and sets it visible and formatted to screen
	 * @param frame
	 * @return JFrame
	 */
	public static JFrame showFrame(JFrame frame) {
		frame.pack();
		centerFrame(frame);
		frame.setVisible(true);
		return frame;
	}
	
	/**
	 * Centers JFrame on screen
	 * @param frame
	 * @return JFrame
	 */
	public static JFrame centerFrame(JFrame frame) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		if (frameSize.height > screenSize.height) frameSize.height = screenSize.height;
		if (frameSize.width > screenSize.width) frameSize.width = screenSize.width;
		frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		return frame;
	}
	
	/**
	 * Creates a new JPanel
	 * @param layoutManager
	 * @param title
	 * @return JPanel
	 */
	public static JPanel panel(LayoutManager layoutManager, String title) {
		JPanel panel = new JPanel();
		if (layoutManager != null) panel.setLayout(layoutManager);
		if (title != null) panel.setBorder(new TitledBorder(title));
		return panel;
	}
	
	/**
	 * Creates a new JLabel
	 * @param text
	 * @param font
	 * @return JLabel
	 */
	public static JLabel label(String text, Font font) {
		JLabel label = new JLabel();
		if (text != null) label.setText(text);
		if (font != null) label.setFont(font);
		return label;
	}
	
	/**
	 * Creates a new JTextField and sets the columnSize
	 * @param columnSize
	 * @param editable
	 * @return JTextField
	 */
	public static JTextField textField(int columnSize, boolean editable) {
		JTextField textField = new JTextField();
		if (columnSize > -1) textField.setColumns(columnSize);
		textField.setEditable(editable);
		return textField;
	}
	
	/**
	 * Creates A new Button and registers an ActionListener to it
	 * @param label
	 * @param actionListener
	 * @return JButton
	 */
	public static JButton button(String label, ActionListener actionListener) {
		JButton button = new JButton();
		if (label != null) button.setText(label);
		if (actionListener != null) button.addActionListener(actionListener);
		return button;
	}
	
	/**
	 * Creates a JButton panel
	 * @param buttons
	 * @return JPanel
	 */
	public static JPanel buttons(JButton... buttons) {
		JPanel panel = panel(new FlowLayout(), null);
		for (JButton button : buttons) panel.add(button);
		return panel;
	}
	
	/**
	 * Creates a new JTable from a TableModel
	 * @param tableModel
	 * @return JTable
	 */
	public static JTable table(TableModel tableModel) {
		JTable table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		if (tableModel != null) table.setModel(tableModel);
		return table;
	}
	
	/**
	 * Creates a new JInternalFrame with a sub JComponent inside
	 * @param contentPane
	 * @param title
	 * @return JInternalFrame
	 */
	public static JInternalFrame internalFrame(JComponent contentPane, String title) {
		JInternalFrame internalFrame = new JInternalFrame();
		internalFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		internalFrame.setResizable(true);
		internalFrame.setClosable(true);
		internalFrame.setMaximizable(true);
		internalFrame.setIconifiable(true);
		internalFrame.setPreferredSize(new Dimension(600, 400));
		if (contentPane != null) internalFrame.setContentPane(new JScrollPane(contentPane));
		if (title != null) internalFrame.setTitle(title);
		return internalFrame;
	}

	/**
	 * Creates a new JTree from a TreeModel, and registers a mouseListener
	 * @param treeModel
	 * @param mouseListener
	 * @return JTree
	 */
	public static JTree tree(TreeModel treeModel, MouseListener mouseListener) {
	    JTree tree = new JTree();
	    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	    if (treeModel != null) tree.setModel(treeModel);
	    if (mouseListener != null) tree.addMouseListener(mouseListener);
	    return tree;
	}
	
	/**
	 * Creates a new popmenu and registers actions to each button
	 * @param actions
	 * @return JPopUpMenu
	 */
	public static JPopupMenu popupMenu(Action... actions) {
		JPopupMenu popupMenu = new JPopupMenu();
		for (Action action : actions) {
			popupMenu.add(action);			
		}
		return popupMenu;
	}
}

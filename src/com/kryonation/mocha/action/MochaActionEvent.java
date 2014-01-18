package com.kryonation.mocha.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;

import com.kryonation.mocha.Exceptions.ControllerNotFoundException;
import com.kryonation.mocha.Exceptions.InvalidArgumentException;
import com.kryonation.mocha.Exceptions.MethodNotFoundException;
import com.kryonation.mocha.controllers.MochaController;
import com.kryonation.mocha.views.MochaView;

/**
 * Event management class which wires view actions to the appropriate controller methods
 * @author Brandon Martel
 * @version Oct-19-2013
 * @param <C>
 */
public class MochaActionEvent<C extends MochaView<? extends JComponent>> implements ActionListener{
	public final HashMap<Class<?>, Object> classMap;
	private final C actionView;
	private String controllerTag;
	private String actionTag;
	private Object obj;
	private Method method;
	private Object[] args;
	private Class<?>[] cls;
    private String splitChar = "\\.";

	/**
	 * Instantiates an actionevent which contracts a controller and view using a route
	 * @param view
	 * @param route
	 * @param params
	 */
	@SuppressWarnings({ "unchecked" })
	public MochaActionEvent(MochaView<? extends JComponent> view, String route, Object[] params){
		classMap = getWrapperTypes();
		actionView = (C) view;
		
		controllerTag = route.split(splitChar)[0];
		actionTag = route.split(splitChar)[1];

		Class<? extends MochaController> controllerClass = actionView.getMainFrame().getControllerClass(controllerTag);
		
		obj = actionView.getMainFrame().getControllerByName(controllerTag);

		if(obj == null){
			throw new ControllerNotFoundException("Could not find controller with name: " + controllerTag);
		}
		
		// Create the class with or without parameters
		if(params == null){
			cls = new Class[]{};
		}else{
			cls = new Class<?>[params.length];
			for(int i=0; i< params.length; i++){
				cls[i] = (Class<?>) findTypeByClass(params[i].getClass());
			}
		}
		
		// Set the arguments of the class to any passed in parameters
		args = params;
		
		// Attempt to build the method from the declared action and class structure
		try {
			method = controllerClass.getDeclaredMethod(actionTag,cls);
		} catch (NoSuchMethodException | SecurityException e) {
            throw new InvalidArgumentException("Invalid arguments passed to method " + method.getName() + ", cannot invoke target.");
		}
	}
	
	/**
	 * Creates the action event, binding it to a controller class method call
	 * @param view
	 * @param controllerClass
	 * @param actionName
	 * @param params
	 */
	@SuppressWarnings({ "unchecked" })
	public MochaActionEvent(MochaView<? extends JComponent> view, Class<? extends MochaController> controllerClass, String actionName, Object[] params){
		classMap = getWrapperTypes();
		actionView = (C) view;
		obj = actionView.getMainFrame().getController(controllerClass);

		if(obj == null){
            throw new ControllerNotFoundException("Could not find controller with class name: " + controllerClass);
		}
		
		// Create the class with or without parameters
		if(params == null){
			cls = new Class[]{};
		}else{
			cls = new Class<?>[params.length];
			for(int i=0; i< params.length; i++){
				cls[i] = (Class<?>) findTypeByClass(params[i].getClass());
			}
		}
		
		// Set the arguments of the class to any passed in parameters
		args = params;
		
		// Attempt to build the method from the declared action and class structure
		try {
			method = controllerClass.getDeclaredMethod(actionName,cls);
		} catch (NoSuchMethodException | SecurityException e) {
            throw new MethodNotFoundException("No such method found " + actionName + " for controller class " + controllerClass);
		}
	}

	/**
	 * Provides the ActionEvent trigger to call the bound controller action
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//Call the controller class method
		try {
			method.invoke(obj, args);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
            throw new InvalidArgumentException("Invalid arguments passed to method " + method.getName() + ", cannot invoke target.");
		}
	}
	
	/**
	 * Returns either the class Object, or the primitive ClassWrapper
	 * @param classType
	 * @return 
	 */
	private Object findTypeByClass(Class<?> classType){
		if(classMap.containsKey(classType))
			return classMap.get(classType);
		else
			return classType;
				
	}
	
	/**
	 * Create mapping for primitive wrapper types
	 * @return getWrapperTypes
	 */
	private final HashMap<Class<?>, Object> getWrapperTypes()
    {
        Map<Class<?>, Object> ret = new HashMap<>();
        ret.put(Boolean.class, Boolean.TYPE);
        ret.put(Character.class, Character.TYPE);
        ret.put(Byte.class, Byte.TYPE);
        ret.put(Short.class, Short.TYPE);
        ret.put(Integer.class, Integer.TYPE);
        ret.put(Long.class, Long.TYPE);
        ret.put(Float.class, Float.TYPE);
        ret.put(Double.class, Double.TYPE);
        ret.put(Void.class, Void.TYPE);
        return (HashMap<Class<?>, Object>) ret;
    }
	//
	
}

package com.monkey01.annotation;

import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @Author: feiweiwei
 * @Description:
 * @Created Date: 16:14 17/9/22.
 * @Modify by:
 */
public class PrintAnnotation {

	private String allAnnotation = new String();

	public String printAllAnnotation(){
		Set<Class<?>> clazzes = new Reflections("com.monkey01").getTypesAnnotatedWith(MyType.class);

		for (Class<?> clazz : clazzes) {
			printMyType(clazz);
		}
		System.out.println(allAnnotation);
		return allAnnotation;
	}

	private void printMyType(Class<?> clazz) {
		MyType myType = clazz.getAnnotation(MyType.class);
		allAnnotation = allAnnotation + clazz.getName() + ": " + myType.value() + "-" + myType.className() + "\n";
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			MyField myField = field.getAnnotation(MyField.class);
			if(myField != null) {
				allAnnotation = allAnnotation + myField.value() + "-" + myField.name() + "-" + myField.type() + "\n";
			}
		}
		Method[] methods = clazz.getMethods();
		for(Method method : methods){

			MyMethod myMethod = method.getAnnotation(MyMethod.class);
			if(myMethod != null) {
				allAnnotation = allAnnotation + myMethod.methodName() + "-" + myMethod.value() + "\n";
			}
		}

	}

}

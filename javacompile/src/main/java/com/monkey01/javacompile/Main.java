package com.monkey01.javacompile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: feiweiwei
 * @Description:
 * @Created Date: 17:36 17/9/22.
 * @Modify by:
 */
public class Main {

	public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException, InterruptedException {
		String javaSrc = "public class Hello{public String sayHello(String name){" + "return \"Hello,\"+name+\"!\";}}";
		try {
			JavaString2Class.compile("Hello", javaSrc, "./bin", null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

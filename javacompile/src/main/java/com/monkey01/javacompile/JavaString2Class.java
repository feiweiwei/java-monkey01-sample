package com.monkey01.javacompile;


import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by feiweiwei on 17/8/19.
 * 该类将java源码编译为class
 */

public class JavaString2Class {


    public static void compile(String javaName, String javaSrc, String classpath, String dependencyClassPath) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
    // Java 源代码
    String sourceStr = javaSrc;
    // 类名及文件名
    String clsName = javaName;
    // 当前编译器
    JavaCompiler cmp = ToolProvider.getSystemJavaCompiler();
    // Java标准文件管理器
    StandardJavaFileManager fm = cmp.getStandardFileManager(null, null,
            null);

    JavaFileObject jfo = new StringJavaObject(clsName, sourceStr);

    // 编译参数，类似于Javac <options> 中的options
    List<String> optionsList = new ArrayList<String>();

    // 编译文件的存放地方
    if(classpath!=null && dependencyClassPath!=null) {
        optionsList.addAll(Arrays.asList("-d", classpath, "-classpath", dependencyClassPath));
    }
    // 要编译的单元
    List<JavaFileObject> jfos = Arrays.asList(jfo);
    // 设置编译环境
    JavaCompiler.CompilationTask task = cmp.getTask(null, fm, null,
            optionsList, null, jfos);
    // 编译成功
    if (task.call()) {
        System.out.println(javaName + " compile ok");
//        File file = new File("./bin");
//        URLClassLoader loader = new URLClassLoader(
//                new URL[] { file.toURI().toURL() }, Thread.currentThread().getContextClassLoader());
        Class<?> clazz = null;
        clazz = beanLoader.loadClass(engineClassPackage+ entitySimpleName + "." + className);
//        clazz = loader.loadClass(javaName);
        System.out.println(clazz.getName());
    }
}

    //初始化自定义classloader
    ClassLoader beanLoader = new ClassLoader() {
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            try {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream is = getClass().getResourceAsStream(fileName);
                if (is == null) {
                    return super.loadClass(name);
                }
                byte[] b = new byte[is.available()];
                is.read(b);
                return defineClass(name, b, 0, b.length);
            } catch (IOException e) {
                throw new ClassNotFoundException(name);
            }
        }
    };
}



class StringJavaObject extends SimpleJavaFileObject {
    // 源代码
    private String content = "";

    // 遵循Java规范的类名及文件
    public StringJavaObject(String _javaFileName, String _content) {
        super(_createStringJavaObjectUri(_javaFileName), Kind.SOURCE);
        content = _content;
    }

    // 产生一个URL资源库
    private static URI _createStringJavaObjectUri(String name) {
        // 注意此处没有设置包名
        return URI.create("String:///" + name + Kind.SOURCE.extension);
    }

    // 文本文件代码
    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors)
            throws IOException {
        // TODO Auto-generated method stub
        return content;
    }

}

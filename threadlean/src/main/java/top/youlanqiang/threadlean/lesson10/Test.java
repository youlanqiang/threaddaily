package top.youlanqiang.threadlean.lesson10;

import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) throws Exception {
        MyClassLoader classLoader
                = new MyClassLoader();
        //加载class
        Class<?> aClass =classLoader.loadClass("top.youlanqiang.ext.HelloWorld");

        System.out.println(aClass.getClassLoader());

        Object helloWorld = aClass.newInstance();
        System.out.println(helloWorld);

        Method method = aClass.getMethod("welcome");
        String result = (String) method.invoke(helloWorld);
        System.out.println("result : " + result);
    }
}

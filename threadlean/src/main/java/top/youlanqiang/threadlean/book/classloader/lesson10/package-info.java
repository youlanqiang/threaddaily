package top.youlanqiang.threadlean.book.classloader.lesson10;

/**
 * JVM内置的3大类加载器
 * JVM内置了三大类加载器，不同的类加载器将负责不同的类加载到jvm内存之中
 * BootStrap ClassLoader
 * Ext ClassLoader
 * Application CLassLoader
 *
 * BootStrap根加载器，该类加载器是最为顶层的加载器，其没有任何父类加载器
 * 它是由C++编写的，主要负责虚拟机核心类库的加载
 * 整个java.lang包都是由根加载器加载的，可以通过-Xbootclasspath来指定根加载器的路径
 *
 * Ext 扩展加载器 的父类加载器是根加载器，它主要用于加载JAVA_HOME下的jre\lb\ext里面的类库
 *
 * Application 系统加载器
 * 负责加载classpath下的类库资源
 *
 */
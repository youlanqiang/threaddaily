package top.youlanqiang.threadlean.book.classloader.lesson9;

/**
 * 类的加载过程
 *
 * 类的加载过程可以被分为三个比较大的阶段
 * 分别是加载阶段，连接阶段，初始化阶段
 *
 * 加载阶段：主要负责查找并且加载类的二进制数据文件，其实就是class文件
 *
 * 连接阶段：可以分为三个阶段
 *    验证：比如class版本，class文件的魔术因子是否正确
 *    准备：为类的静态变量分配内存，并且为其初始化默认值
 *    解析：把类中的符号引用转换为直接引用
 *
 * 初始化阶段：为类的静态变量赋予正确的初始化值
 *
 * JVM对类的初始化是一个延迟的机制，使用的是lazy的方式
 * 当一个类首次使用的时候才会被初始化，一个class只会被初始化一次
 */
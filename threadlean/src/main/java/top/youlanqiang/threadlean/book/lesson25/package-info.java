package top.youlanqiang.threadlean.book.lesson25;

/**
 *
 * Two Phase Termination模式
 * 当一个线程正常结束或者因被打断而结束，
 * 或者出现异常而结束时，我们需要考虑如何同时释放线程中的资源
 * 比如文件句柄，socket套接字句柄，数据库连接等比较稀缺的资源。
 * Two Phase Termination模式
 */
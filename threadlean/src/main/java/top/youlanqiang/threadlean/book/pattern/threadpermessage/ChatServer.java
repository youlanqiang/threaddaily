package top.youlanqiang.threadlean.book.pattern.threadpermessage;

import top.youlanqiang.threadlean.book.threadpool.BasicThreadPool;
import top.youlanqiang.threadlean.book.threadpool.ThreadPool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ChatServer {

    private final int port;

    private ThreadPool threadPool;

    private ServerSocket serverSocket;

    public ChatServer(int port){
        this.port = port;
    }


    public ChatServer(){
        this(13312);
    }

    public void startServer() throws IOException {
        //创建线程池，核心线程数量为2，最大线为4，队列最大1000个任务
        this.threadPool = new BasicThreadPool(1,4,2,1000);
        this.serverSocket = new ServerSocket(port);
        this.serverSocket.setReuseAddress(true);
        System.out.println("Chat server is started and listen at port:" + port);
        this.listen();

    }

    private void listen() throws IOException {
        for(;;){
            //accept是阻塞方法，当有新的连接进入时才会返回，并且返回的是客户端的连接
            Socket client = serverSocket.accept();
            //将客户端连接
            this.threadPool.execute(new ClientHandler(client));
        }
    }
}

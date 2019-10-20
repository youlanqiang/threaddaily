package top.youlanqiang.threadlean.book.twophase;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    //客户端的socket连接
    private final Socket socket;

    //客户端的identify
    private final String clientIdentify;

    public ClientHandler(final Socket socket) {
        this.socket = socket;
        this.clientIdentify = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
    }

    //客户端的identity
    @Override
    public void run() {
        try {
            this.chat();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //尽最大努力确保socket被GC
            this.release();
        }
    }

    private void release(){

        try{
            if(socket != null){
                socket.close();
            }
        }catch(Throwable e){
            if(socket != null){
                //将socket实例加入Tracker中
                SocketCleaningTracker.track(socket);
            }
        }
    }

    private void chat() throws IOException {
        BufferedReader bufferedReader = wrap2Reader(this.socket.getInputStream());
        PrintStream printStream = wrap2Print(this.socket.getOutputStream());
        String received;
        while ((received = bufferedReader.readLine()) != null) {
            //将客户端发送的消息输出到控制台
            System.out.printf("client:%s-message:%s\n", clientIdentify, received);
            if (received.equals("quit")) {
                //如果客户端发送了quit请求，则断开与客户端的连接
                write2Client(printStream, "client will close");
                socket.close();
                break;
            }
            //向客户端发送消息
            write2Client(printStream, "Server:" + received);

        }
    }


    private BufferedReader wrap2Reader(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    private PrintStream wrap2Print(OutputStream outputStream) {
        return new PrintStream(outputStream);
    }

    private void write2Client(PrintStream print, String message) {
        print.println(message);
        print.flush();
    }
}

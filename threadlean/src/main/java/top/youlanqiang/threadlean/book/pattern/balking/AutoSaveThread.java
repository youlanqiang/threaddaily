package top.youlanqiang.threadlean.book.pattern.balking;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 自动保存文档的线程
 */
public class AutoSaveThread extends Thread{

    private final Document document;

    public AutoSaveThread(Document document){
        super("DocumentAutoSaveThread");
        this.document = document;
    }

    @Override
    public void run() {
        //主要工作就是每隔一秒自动保存一次文档
        while(true){
            try{
                document.save();
                TimeUnit.SECONDS.sleep(1);
            }catch(IOException | InterruptedException e){
                break;
            }
        }
    }
}

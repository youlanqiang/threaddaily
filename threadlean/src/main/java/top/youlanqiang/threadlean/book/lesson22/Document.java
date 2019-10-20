package top.youlanqiang.threadlean.book.lesson22;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.currentThread;

//表示正在编辑的文档类
public class Document {

    private boolean changed = false;

    private List<String> content = new ArrayList<>();

    private final FileWriter writer;

    private static AutoSaveThread autoSaveThread;


    public Document(String documentPath, String documentName) throws IOException {
        this.writer = new FileWriter(new File(documentPath, documentName));
    }


    //静态方法，用于创建文档，顺便启动自动保存文档的线程
    public static Document create(String documentPath, String documentName) throws IOException {
        Document document = new Document(documentPath, documentName);
        autoSaveThread = new AutoSaveThread(document);
        autoSaveThread.start();
        return document;
    }

    //文档的编辑，就是往 content队列中提交字符串
    public void edit(String content){
        synchronized (this){
            this.content.add(content);
            //文档改变，changed会变为true
            this.changed = true;
        }
    }

    //文档关闭的时候首先中断自动保存线程，然后关闭writer释放资源
    public void close() throws IOException{
        autoSaveThread.interrupt();
        writer.close();
    }


    public void save() throws IOException {
        synchronized (this){
            if(!changed){
                return;
            }

            System.out.println(currentThread() + " execute the save action.");
            for(String cacheLine : content){
                this.writer.write(cacheLine);
                this.writer.write("\r\n");
            }
        }
    }


}

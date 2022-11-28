package top.youlanqiang.threadlean.book.hookandexceptionhandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 在开发中经常会遇到Hook线程
 * 比如为了防止某个程序被重复启动，在进程启动时会创建一个lock文件
 * 进程收到中断信号的时候会删除掉这个lock文件，
 * 我们在mysql服务器，zookeeper，kafka等系统中都会看到lock文件的存在
 * 使用hook线程的特点，模拟一个防止重复启动的程序
 */
public class PreventDuplicated {


    private final static String LOCK_PATH = "./";

    private final static String LOCK_FILE = ".lock";

    private final static String PERMISSIONS = "rw-------";

    public static void main(String[] args) throws IOException{
        //注入Hook线程，在程序退出时删除lock文件
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("The program received kill SIGNAL");
            boolean result = getLockFile().toFile().delete();
            if(result){
                System.out.println("The lockfile was deleted");
            }else{
                System.out.println("The lockfile wasn't deleted");
            }
        }));

        //检查是否存在.lock文件
        checkRunning();

        //简单模拟当前程序正在运行
        for(;;){
            try {
                TimeUnit.MILLISECONDS.sleep(1);
                System.out.println("program is running.");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


    private static void checkRunning() throws IOException{
        Path path = getLockFile();
        if(path.toFile().exists()){
            throw new RuntimeException("The program already running");
        }
        Set<PosixFilePermission> perms = PosixFilePermissions.fromString(PERMISSIONS);
        Files.createFile(path, PosixFilePermissions.asFileAttribute(perms));
    }

    private static Path getLockFile(){
        return Paths.get(LOCK_PATH, LOCK_FILE);
    }

}

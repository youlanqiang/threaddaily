package top.youlanqiang.threadlean.book.pattern.eventbus.monitor;


import top.youlanqiang.threadlean.book.pattern.eventbus.EventBus;

import java.nio.file.*;

public class DirectoryTargetMonitor {


    private WatchService watchService;

    private final EventBus eventBus;

    private final Path path;

    private volatile boolean start = false;

    public DirectoryTargetMonitor(final EventBus eventBus, final String targetPath){
        this(eventBus, targetPath, "");
    }

    public DirectoryTargetMonitor(final EventBus eventBus, final String targetPath, final String... morePaths){
            this.eventBus = eventBus;
            this.path = Paths.get(targetPath, morePaths);
    }

    public void startMonitor() throws Exception{
        this.watchService = FileSystems.getDefault().newWatchService();
        //为路径注册感兴趣的事件
        this.path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY
        , StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_CREATE);
        System.out.printf("The directory [%s] is monitoring... \n", path);
        this.start = true;
        while(start){
            WatchKey watchKey = null;
            try{
                //当有事件发生时会返回对应的WatchKey
                watchKey = watchService.take();
                watchKey.pollEvents().forEach(event ->{
                    WatchEvent.Kind<?> kind = event.kind();
                    Path path = (Path) event.context();
                    Path child = DirectoryTargetMonitor.this.path.resolve(path);
                    //提交File

                });
            }catch(Exception e){
                this.start = false;
            }finally{
                if(watchKey != null){
                    watchKey.reset();
                }
            }
        }
    }

    public void stopMonitor() throws Exception{
        System.out.printf("The directory [%s] monitor will be stop..\n", path);
        Thread.currentThread().interrupt();
        this.start = false;
        this.watchService.close();
        System.out.printf("The directory [%s] monitor will be stop done.\n", path);
    }
}

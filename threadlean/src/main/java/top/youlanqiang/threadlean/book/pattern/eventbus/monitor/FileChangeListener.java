package top.youlanqiang.threadlean.book.pattern.eventbus.monitor;

import top.youlanqiang.threadlean.book.pattern.eventbus.Subscribe;

public class FileChangeListener {

    @Subscribe
    public void onChange(FileChangeEvent event){
        System.out.printf("%s-%s\n", event.getPath(), event.getKind());
    }


}

package top.youlanqiang.threadlean.lesson28.monitor;

import top.youlanqiang.threadlean.lesson28.Subscribe;

public class FileChangeListener {

    @Subscribe
    public void onChange(FileChangeEvent event){
        System.out.printf("%s-%s\n", event.getPath(), event.getKind());
    }


}

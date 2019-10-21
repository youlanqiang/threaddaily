package top.youlanqiang.threadlean.book.pattern.readwritelock;



//包可见，创建时使用ReadWriterLock的create方法
class ReadWriteLockImpl implements ReadWriteLock {

    //定义对象锁
    private final Object MUTEX = new Object();

    //当前有多少个线程正在写入
    private int writingWriters = 0;

    //当前有多少个线程正在等待写入
    private int waitingWriters = 0;

    //当前有多少个线程正在read
    private int readingReaders = 0;

    //read和write的偏好设置
    private boolean preferWriter;

    public ReadWriteLockImpl(){
        this(true);
    }

    //创建ReadWriteLockImpl并且传入preferWriter
    public ReadWriteLockImpl(boolean preferWriter){
        this.preferWriter = preferWriter;
    }

    @Override
    public Lock readLock() {
        return new ReadLock(this);
    }

    @Override
    public Lock writeLock() {
        return new WriteLock(this);
    }

    @Override
    public int getWritingWriters() {
        return this.writingWriters;
    }

    @Override
    public int getWaitingWriters() {
        return this.waitingWriters;
    }

    @Override
    public int getReadingReaders() {
        return this.readingReaders;
    }

    //使写线程的数量增加
    void incrementWritingWriters(){
        this.writingWriters++;
    }

    //使等待写入的线程数量增加
    void incrementWaitingWriters(){
        this.waitingWriters++;
    }

    //使读线程的数量增加
    void incrementReadingReaders(){
        this.readingReaders++;
    }

    //使写线程的数量减少
    void decrementWritingWriters(){
        this.writingWriters--;
    }

    //使等待获取写入锁的数量减一
    void decrementWaitingWriters(){
        this.waitingWriters--;
    }

    void decrementReadingReaders(){
        this.readingReaders--;
    }





    Object getMutex(){
        return this.MUTEX;
    }

    boolean getPreferWriter() {
        return preferWriter;
    }

    void changePrefer(boolean preferWriter){
        this.preferWriter = preferWriter;
    }

}

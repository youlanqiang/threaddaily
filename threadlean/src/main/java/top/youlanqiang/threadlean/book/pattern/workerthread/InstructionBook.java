package top.youlanqiang.threadlean.book.workerthread;

//在流水线上需要被加工的产品，create作为一个模板方法，提供流加工产品的说明书
public abstract class InstructionBook {

    public final void create(){
        this.firstProcess();
        this.secondProcess();
    }

    protected abstract void firstProcess();

    protected abstract void secondProcess();

}

package top.youlanqiang.threadlean.juc.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子引用类型
 */
public class AtomicReferenceTest {

    public static void main(String[] args) {
        AtomicReference<Simple> reference =  new AtomicReference<>(new Simple("ylq", 22));

        boolean result = reference.compareAndSet(new Simple("sda", 12), new Simple("sda", 121));
        System.out.println(result);
        //reference.updateAndGet(new Simple("tt", 12));

    }


    static class Simple{

        private String name;

        private Integer age;

        public Simple(String name, Integer age){
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}

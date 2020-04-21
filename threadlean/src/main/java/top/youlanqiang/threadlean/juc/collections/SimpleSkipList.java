package top.youlanqiang.threadlean.juc.collections;

import java.util.Random;

/**
 * 跳表
 */
public class SimpleSkipList {

    private final static byte HEAD_NODE = (byte) -1;
    private final static byte DATA_NODE = (byte) 0;
    private final static byte TAIL_NODE = (byte) -1;

    private static class Node{

        private Integer value;

        private Node up,down,left,right;

        private byte bit;

        public Node(Integer value, byte bit){
            this.value = value;
            this.bit = bit;
        }

        public Node(Integer value){
            this(value, DATA_NODE);
        }


    }

    private Node head;

    private Node tail;

    private int size;

    private int height;

    private Random random;

    public SimpleSkipList(){
        this.head = new Node(null, HEAD_NODE);
        this.tail = new Node(null, TAIL_NODE);
        head.right = tail;
        tail.left = head;
        this.random = new Random(System.currentTimeMillis());
    }

    private Node find(int index){
        //todo 继续完成 p62
        return null;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public int size(){
        return size;
    }
}

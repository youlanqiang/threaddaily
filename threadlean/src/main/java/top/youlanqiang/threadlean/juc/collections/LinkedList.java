package top.youlanqiang.threadlean.juc.collections;

public class LinkedList<E> {

    private final static String PLAIN_NULL = "null";

    private Node<E> first;

    private int size;

    public LinkedList() {
        this.first = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static <E> LinkedList<E> of(E... elements) {
        LinkedList<E> list = new LinkedList<>();
        if (elements.length > 0) {
            for (E ele : elements) {
                list.addFirst(ele);
            }
        }
        return list;
    }

    public LinkedList<E> addFirst(E e) {
        final Node<E> newNode = new Node<>(e);
        newNode.next = first;
        this.size++;
        this.first = newNode;
        return this;
    }

    public E removeFirst(){
        if(this.isEmpty()){
            throw new RuntimeException("The linked list is empty.");
        }
        Node<E> node = first;
        first = node.next;
        size--;
        return node.value;
    }

    public boolean contains(E e){
        Node<E> current = first;
        while(current != null){
            if(current.value == e){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value) {
            this.value = value;
        }

        @Override
        public String toString() {
            if (value != null) {
                return value.toString();
            }
            return PLAIN_NULL;
        }
    }
}

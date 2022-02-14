public class Linked<T>{
    private class Node{
        private T t;
        private Node next;
        public Node(T t,Node next){
            this.t = t;
            this.next = next;
        }
        public Node(T t){
            this(t,null);
        }
    }

    private Node head;    		//头结点
    private int size;			//链表元素个数
    //构造函数
    public Linked(){
        this.head = null;
        this.size = 0;
    }


    //链表头部添加元素
    public void addFirst(T t){
        Node node = new Node(t);	//节点对象
        node.next = this.head;
        this.head = node;
        //this.head = new Node(e,head);等价上述代码
        this.size++;
    }
    //向链表尾部插入元素
    public void addLast(T t){
        this.add(t, this.size);
    }
    //向链表中间插入元素
    public void add(T t,int index){
        if (index <0 || index >size){
            throw new IllegalArgumentException("index is error");
        }
        if (index == 0){
            this.addFirst(t);
            return;
        }
        Node preNode = this.head;
        //找到要插入节点的前一个节点
        for(int i = 0; i < index-1; i++){
            preNode = preNode.next;
        }
        Node node = new Node(t);
        //要插入的节点的下一个节点指向preNode节点的下一个节点
        node.next = preNode.next;
        //preNode的下一个节点指向要插入节点node
        preNode.next = node;
        this.size++;
    }
}



public class MyLink {
    Node head=null;//定义头结点


    class Node{
        Node next=null; //节点的引用，指向下一个节点
        int data;//节点的对象，即内容

        public Node(int data){
            this.data=data;
        }
    }
    /*
    * 向链表中插入数据
    * */
    public void addNode(int d){
        Node newNode=new Node(d);//实例化一个节点
        if (head==null){
            head=newNode;
            return;
        }
        Node tmp=head;
        while (tmp.next!=null){
            tmp=tmp.next;
        }
        tmp.next=newNode;
    }

    public boolean deleteNode(int index){
        if (index<1||index>length())
        {
            return false;
        }
        if (index==1){
            head=head.next;
            return true;
        }
        int i=1;
        Node preNode=head;
        Node curNode=preNode.next;
        while (curNode !=null){
            if (i==index){
                preNode.next=curNode.next;
                return true;
            }
            preNode=curNode;
            curNode=curNode.next;
            i++;
        }
        return false;
    }

    /**
     *
     * @return 返回节点长度
     */
    public int length() {
        int length = 0;
        Node tmp = head;
        while (tmp != null) {
            length++;
            tmp = tmp.next;
        }
        return length;
    }

    public void printList() {
        Node tmp = head;
        while (tmp != null) {
            System.out.println(tmp.data);
            tmp = tmp.next;
        }
    }

    public static void main(String[] args) {
        MyLink list = new MyLink();
        list.addNode(5);
        list.addNode(3);
        list.addNode(1);
        list.addNode(2);
        list.addNode(55);
        list.addNode(36);
        System.out.println("linkLength:" + list.length());
        System.out.println("head.data:" + list.head.data);
        list.printList();
        list.deleteNode(4);
        System.out.println("After deleteNode(4):");
        list.printList();
    }
}

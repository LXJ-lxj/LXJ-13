class MyStack{
    private int maxsize;
    private long[] stackArray;
    private  int top;
    public MyStack(int s){
        maxsize=s;//获取数组长度
        stackArray=new long[maxsize];//创建数组
        top=-1;//默认指向栈底所以为-1
    }
    public  void  push(long j){
        stackArray[++top]=j;

    }
    public long pop(){//出栈
        return  stackArray[top--];
    }
    public long peek(){
        return stackArray[top];
    }
    public  boolean isEmpty(){//判断栈空
        return (top==-1);
    }
    public boolean isFull() {//判断栈满
        return (top == maxsize - 1);
    }
}
public class Test{
    public static void main(String[] args) {

        //初始化栈
        MyStack theStack=new MyStack(10);
        theStack.push(10);
        theStack.push(20);
        theStack.push(30);
        theStack.push(40);
        theStack.push(50);

        while (!theStack.isEmpty()) {
            long value = theStack.pop();
            System.out.print(value);
            System.out.print(" ");
        }
        System.out.println("");
    }
}
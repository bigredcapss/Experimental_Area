package design.behavioral.chain.auth.builderchain;


import design.behavioral.chain.auth.Member;

/**
 * 抽象处理者角色--基于optimiaze包的代码用建造者模式进行改造
 *
 * 因为建造者模式要构建的是节点处理者，因此我们把Builder作为Handler的静态内部类，并且客户端无需进行链式组装，因此我们
 * 还可以把链式组装方法next()设置为private,使Handler更加高内聚
 */
public abstract class Handler<T> {
    protected Handler next;
    public void next(Handler next){ this.next = next;}

    public abstract void doHandler(Member member);

    public static class Builder<T>{
        private Handler<T> head;
        private Handler<T> tail;

        public Builder<T> addHandler(Handler handler){
//            do {
                if (this.head == null) {
                    this.head = this.tail = handler;
                    return this;
                }
                this.tail.next(handler);
                this.tail = handler;
//            }while (false);//真正框架中，如果是双向链表，会判断是否已经到了尾部
            return this;
        }

        public Handler<T> build(){
            return this.head;
        }

    }
}

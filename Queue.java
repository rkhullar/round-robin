public class Queue<T>
{
    private Node<T> head, tail;
    private int size;

    public Queue()
    {
        reset();
    }

    public Queue(T data)
    {
        reset(data);
    }

    public void reset()
    {
        head = null;
        tail = null;
        size = 0;
    }

    public void reset(T data)
    {
        head = new Node<>(data);
        tail = head;
        size = 1;
    }

    public boolean isEmpty()
    {
        return head == null && tail == null;
    }

    public int size()
    {
        return size;
    }

    public void enqueue(T data)
    {
        if(isEmpty())
            {reset(data); return;}
        Node<T> node = new Node<>(data);
        node.setPrev(tail);
        tail.setNext(node);
        tail = node;
        size++;
    }

    public T dequeue()
    {
        if(isEmpty())
            return null;
        T data = head.getData();
        if(size == 1)
            {reset(); return data;}
        Node<T> node = head.getNext();
        head.setNext(null);
        node.setPrev(null);
        head = node;
        size--;
        return data;
    }

    public void absorb(Queue<T> o)
    {
        while(!o.isEmpty())
            enqueue(o.dequeue());
    }

    public Node<T> getHead()
    {
        return head;
    }

    public Node<T> getTail()
    {
        return tail;
    }

    public String toString()
    {
        if(isEmpty()) return "[]";
        String s = "[";
        Node<T> node = head;
        while(node.hasNext())
        {
            s += node.toString() + ", ";
            node = node.getNext();
        }
        s += node.toString() + "]";
        return s;
    }
}

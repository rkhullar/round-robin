public class Node<T>
{
    private T data;
    private Node<T> prev, next;

    public Node(T data)
    {
        this.data = data;
        this.prev = null;
        this.next = null;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T d)
    {
        data = d;
    }

    public boolean hasPrev()
    {
        return prev != null;
    }

    public boolean hasNext()
    {
        return next != null;
    }

    public Node<T> getPrev()
    {
        return prev;
    }

    public Node<T> getNext()
    {
        return next;
    }

    public void setPrev(Node<T> node)
    {
        prev = node;
    }

    public void setNext(Node<T> node)
    {
        next = node;
    }

    public String toString()
    {
        return data.toString();
    }
}

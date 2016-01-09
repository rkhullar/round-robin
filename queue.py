"""
@author     : Rajan Khullar
@created    : 01/08/16
@updated    : 01/08/16
"""


class Node:

    def __init__(self, data):
        self.data = data
        self.prev = None
        self.next = None

    def __str__(self):
        return str(self.data)

    def hasNext(self):
        return self.next is not None

    def hasPrev(self):
        return self.prev is not None


class Queue:

    def __init__(self):
        self.size = 0
        self.head = None
        self.tail = None
        self.curr = None

    def __str__(self):
        self.curr = self.head
        if self.curr is None:
            return '[]'
        out = '['
        while self.curr.hasNext():
            out += str(self.curr) + ', '
            self.curr = self.curr.next
        if self.curr == self.tail:
            out += str(self.curr) + ']'
        return out

    def empty(self):
        return self.head is None and self.tail is None

    def peek(self):
        if self.head is not None:
            return self.head.data
        else:
            print('error: empty queue')
            return False

    def enq(self, item):
        node = Node(item)
        # insert to empty queue
        if self.tail is None:
            self.head = node
            self.tail = node
        # insert to queue with at least one item
        else:
            self.tail.next = node
            node.prev = self.tail
            self.tail = node
        self.size += 1

    def deq(self):
        self.curr = self.head
        # deque from empty queue
        if self.curr is None:
            print('error: empty queue')
            return False
        item = self.head.data
        # deque from queue with at least two items
        if self.curr.hasNext():
            self.curr = self.curr.next
            self.curr.prev = None
            self.head = self.curr
            self.size -= 1
        # deque from queue with exactly one item
        else:
            self.head = None
            self.tail = None
            self.size = 0
        return item


if __name__ == 'main':
    q = Queue()
    for i in range(10):
        q.enq(i)
        print(q)
    for i in range(10):
        q.deq()
        print(q)
    for i in range(10):
        q.enq(i)
        print(q)
    for i in range(10):
        q.deq()
        print(q)

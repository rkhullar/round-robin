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

    def has_next(self):
        return self.next is not None

    def has_prev(self):
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
        while self.curr.has_next():
            out += str(self.curr) + ', '
            self.curr = self.curr.next
        if self.curr == self.tail:
            out += str(self.curr) + ']'
        return out

    def empty(self):
        return self.head is None and self.tail is None

    def peek(self):
        return self.head.data

    def enq(self, item):
        node = Node(item)
        if self.tail is None:
            self.head = node
            self.tail = node
        else:
            self.tail.next = node
            self.tail = node
        self.size += 1

    def deq(self):
        self.curr = self.head
        if self.curr is None:
            print('cannot deque empty queue')
            return False
        item = self.head.data
        if self.curr.has_next():
            self.curr = self.curr.next
            self.head = self.curr
            self.size -= 1
        else:
            self.head = None
            self.tail = None
            self.size = 0
        return item


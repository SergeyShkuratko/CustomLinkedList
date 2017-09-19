import java.util.Iterator;

public class CustomLinkedList<T> implements Iterable<T> {

    private CustomLinkedList.CustomLinkedListNode<T> headElement;
    private CustomLinkedList.CustomLinkedListNode<T> tailElement;
    private int size = 0;

    private void initNewList(T item) {
        CustomLinkedList.CustomLinkedListNode<T> newNode = new CustomLinkedListNode<>(item);
        headElement = newNode;
        tailElement = newNode;
        newNode.setPreviousNode(null);
        newNode.setNextNode(null);
        size++;
    }

    public void addElementByIndex(int index, T item) {
        if (index < 0) {
            throw new RuntimeException("Index can not be less the 0");
        }

        if (headElement == null) {
            initNewList(item);
            return;
        }

        if (index + 1 > size) {
            index = size;
        }

        if (index == 0) {
            addFirst(item);
            return;
        }

        if (index == size - 1) {
            addLast(item);
            return;
        }

        addNodeInMiddle(index, item);
    }

    public void addElement(T item) {
        addElementByIndex(size, item);
    }

    public int indexOf(T value) {
        int i = 0;
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(value)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public boolean contains(T value) {
        int i = indexOf(value);
        if (i >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public void setElement(int index, T item) {
        if (index < 0 || index > size - 1) {
            throw new RuntimeException("Incorrect index");
        }
        CustomLinkedListNode<T> nodeByIndex = getNodeByIndex(index);
        nodeByIndex.setItem(item);
    }

    public void linkBefore(T value, CustomLinkedListNode<T> node) {
        CustomLinkedListNode<T> newNode = new CustomLinkedListNode<>(value);
        newNode.setNextNode(node);
        CustomLinkedListNode<T> previousNode = node.getPreviousNode();
        newNode.setPreviousNode(previousNode);
        node.setPreviousNode(newNode);
        previousNode.setNextNode(newNode);
    }

    public void linkAfter(T value, CustomLinkedListNode<T> node) {
        CustomLinkedListNode<T> newNode = new CustomLinkedListNode<>(value);
        CustomLinkedListNode<T> nextNode = node.getNextNode();
        newNode.setNextNode(nextNode);
        newNode.setPreviousNode(node);
        nextNode.setPreviousNode(newNode);
        node.setNextNode(newNode);
    }

    private void addNodeInMiddle(int index, T item) {
        CustomLinkedListNode<T> newNode = new CustomLinkedListNode<>(item);
        CustomLinkedListNode<T> prevNode = getNodeByIndex(index - 1);
        CustomLinkedListNode<T> nextNode = prevNode.getNextNode();
        prevNode.setNextNode(newNode);
        nextNode.setPreviousNode(newNode);
        newNode.setPreviousNode(prevNode);
        newNode.setNextNode(nextNode);
        size++;
    }

    private void addFirst(T item) {
        CustomLinkedListNode<T> oldHead = this.headElement;
        CustomLinkedList.CustomLinkedListNode<T> newNode = new CustomLinkedListNode<>(item);
        newNode.setNextNode(oldHead);
        oldHead.setPreviousNode(newNode);
        headElement = newNode;
        size++;
    }

    private void addLast(T item) {
        CustomLinkedListNode<T> oldTail = this.tailElement;
        CustomLinkedList.CustomLinkedListNode<T> newNode = new CustomLinkedListNode<>(item);
        newNode.setPreviousNode(oldTail);
        oldTail.setNextNode(newNode);
        tailElement = newNode;
        size++;
    }


    public T getValueByIndex(int id) {
        return getNodeByIndex(id).getItem();
    }

    public void removeByIndex(int index) {
        if (size == 0) {
            throw new RuntimeException("You can not remove elements from empty list");
        }
        if (index < 0 || index > size - 1) {
            throw new RuntimeException("Incorrect index: " + index + ". Index must be contained between 0 and " + (size - 1));
        }

        if (index == 0) {
            removeFirst();
            return;
        }

        if (index == size - 1) {
            removeLast();
            return;
        }
        removeMiddle(index);
    }

    private void removeMiddle(int index) {
        CustomLinkedListNode<T> nodeByIndex = getNodeByIndex(index);
        CustomLinkedListNode<T> previousNode = nodeByIndex.previousNode;
        CustomLinkedListNode<T> nextNode = nodeByIndex.nextNode;
        previousNode.setNextNode(nextNode);
        nextNode.setPreviousNode(previousNode);
        size--;
    }

    private void removeLast() {
        CustomLinkedListNode<T> oldTail = this.tailElement;
        CustomLinkedListNode<T> newTail = oldTail.getPreviousNode();
        if (newTail != null) {
            newTail.setNextNode(null);
        }
        tailElement = null;
        size--;
    }

    private void removeFirst() {
        CustomLinkedListNode<T> oldHead = this.headElement;
        CustomLinkedListNode<T> newHead = oldHead.getNextNode();
        if (newHead != null) {
            newHead.setPreviousNode(null);
        }
        headElement = newHead;
        size--;
    }

    public CustomLinkedListNode<T> getNodeByIndex(int index) {
        CustomLinkedList.CustomLinkedListNode<T> currentNode = headElement;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNextNode();
        }
        return currentNode;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            CustomLinkedListNode<T> currentNode = headElement;

            @Override
            public boolean hasNext() {
                if (currentNode != null) {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public T next() {
                T item = currentNode.getItem();
                currentNode = currentNode.getNextNode();
                return item;
            }
        };
    }

    public static class CustomLinkedListNode<T> {
        private T item;
        private CustomLinkedListNode<T> previousNode;
        private CustomLinkedListNode<T> nextNode;

        public CustomLinkedListNode(T item) {
            this.item = item;
        }

        public void setPreviousNode(CustomLinkedListNode<T> previousNode) {
            this.previousNode = previousNode;
        }

        public CustomLinkedListNode<T> getPreviousNode() {
            return previousNode;
        }

        public CustomLinkedListNode<T> getNextNode() {
            return nextNode;
        }

        public void setNextNode(CustomLinkedListNode<T> nextNode) {
            this.nextNode = nextNode;
        }

        public T getItem() {
            return item;
        }

        public void setItem(T item) {
            this.item = item;
        }
    }
}

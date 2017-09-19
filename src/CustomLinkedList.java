public class CustomLinkedList<T> {

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

    private void addNodeInMiddle(int index, T item) {
        CustomLinkedListNode<T> newNode = new CustomLinkedListNode<>(item);
        CustomLinkedListNode<T> prevNode = searchNodeByIndex(index - 1);
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


    public T getValueById(int id) {
        return searchNodeByIndex(id).getItem();
    }

    public void printAllElements() {
        CustomLinkedList.CustomLinkedListNode<T> currentNode = headElement;
        for (int i = 0; i < size; i++) {
            System.out.println(currentNode.getItem());
            currentNode = currentNode.getNextNode();
        }
    }

    public void removeByIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new RuntimeException("Incorrect index: " + index + ". Index must be contained between 0 and " + (size - 1));
        }

        if (index == 0) {
            removeFirst();
            return;
        }

        if(index == size-1){
            removeLast();
            return;
        }
        removeMiddle(index);
    }

    private CustomLinkedListNode<T> searchNodeByIndex(int index) {
        CustomLinkedList.CustomLinkedListNode<T> currentNode = headElement;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNextNode();
        }
        return currentNode;
    }

    public int getSize() {
        return size;
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
    }
}

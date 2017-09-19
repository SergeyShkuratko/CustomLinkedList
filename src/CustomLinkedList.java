public class CustomLinkedList<T> {

    private CustomLinkedList.CustomLinkedListNode<T> headElement;
    private CustomLinkedList.CustomLinkedListNode<T> tailElement;
    private int size = 0;

    public void addElementInEnd(T item) {
        CustomLinkedList.CustomLinkedListNode<T> newNode = new CustomLinkedListNode<>(item);
        if (headElement == null && tailElement == null) {
            headElement = newNode;
            tailElement = newNode;
            newNode.setPreviousNode(null);
            newNode.setNextNode(null);
            size++;
            return;
        }
        tailElement.setNextNode(newNode);
        newNode.setPreviousNode(tailElement);
//        CustomLinkedListNode<T> previousNode = tailElement.getPreviousNode();
//        if (previousNode != null) {
//            previousNode.setNextNode(newNode);
//        }
        tailElement = newNode;
        newNode.setNextNode(null);
        size++;
    }

    public void printAllElements() {
        CustomLinkedList.CustomLinkedListNode<T> currentNode = headElement;
        for (int i = 0; i < size; i++) {
            System.out.println(currentNode.getItem());
            currentNode = currentNode.getNextNode();
        }
    }

    public void addElementByIndex(int index, T item) {
        if (headElement == null) {
            throw new RuntimeException("If you want to add elements by index you must fill first element by addElementInEnd method");
        }
        if (index >= size) {
            addElementInEnd(item);
            return;
        }
        CustomLinkedList.CustomLinkedListNode<T> newNode = new CustomLinkedListNode<>(item);
        CustomLinkedList.CustomLinkedListNode<T> prevNode = searchNodeByIndex(index);
        CustomLinkedListNode<T> nextNode = prevNode.getNextNode();
        newNode.setNextNode(nextNode);
        prevNode.setNextNode(newNode);
        newNode.setPreviousNode(prevNode);
        nextNode.setPreviousNode(newNode);
        size++;
    }

    public void removeByIndex(int index) {
        CustomLinkedListNode<T> tCustomLinkedListNode = searchNodeByIndex(index);
        CustomLinkedListNode<T> previousNode = tCustomLinkedListNode.getPreviousNode();
        CustomLinkedListNode<T> nextNode = tCustomLinkedListNode.getNextNode();
        if (previousNode != null) {
            previousNode.setNextNode(nextNode);
        }
        if (nextNode != null) {
            nextNode.setPreviousNode(previousNode);
        }
        size--;
    }

    private CustomLinkedListNode<T> searchNodeByIndex(int index) {
        CustomLinkedList.CustomLinkedListNode<T> currentNode = headElement;
        for (int i = 0; i < index - 1; i++) {
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

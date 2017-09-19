import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        CustomLinkedList<String> linkedList = new CustomLinkedList<>();
        linkedList.addElementInEnd("one");
        linkedList.addElementInEnd("two");
        linkedList.addElementInEnd("three");
        linkedList.addElementByIndex(2, "1.5");
//        linkedList.removeByIndex(1);
        linkedList.printAllElements();
    }
//Linked list
}


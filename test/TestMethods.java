import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TestMethods {

    CustomLinkedList<String> linkedList = new CustomLinkedList<>();

    @Test
    public void addElement() {
        int beforeSize = linkedList.getSize();
        linkedList.addElement("newItem");
        int afterSize = linkedList.getSize();
        assertEquals(beforeSize + 1, afterSize);
    }

    @Test
    public void getElement() {
        linkedList.addElement("newLastElement");
        String valueById = linkedList.getValueById(linkedList.getSize() - 1);
        assertEquals("newLastElement", valueById);
    }

    @Test
    public void addElementByIndex() {
        linkedList.addElement("beforeByIndex");
        linkedList.addElementByIndex(0, "addByIndex");
        String valueById = linkedList.getValueById(0);
        assertEquals("addByIndex", valueById);
    }

    @Test
    public void removeElement() {
        int beforeSize = linkedList.getSize();
        linkedList.addElementByIndex(0, "newOne");
        linkedList.addElementByIndex(0, "newSecond");
        linkedList.removeByIndex(0);
        assertEquals(linkedList.getNodeByIndex(0).getItem(), "newOne");
        linkedList.removeByIndex(0);
        int afterSize = linkedList.getSize();
        assertEquals(afterSize, beforeSize);
    }

    @Test
    public void contains() {
        linkedList.addElementByIndex(0, "containsCheck");
        linkedList.addElementByIndex(0, "test4");
        linkedList.addElementByIndex(0, "test3");
        linkedList.addElementByIndex(0, "test2");
        linkedList.addElementByIndex(0, "test1");
        int containsCheck = linkedList.contains("containsCheck");
        assertEquals(4, containsCheck);
        containsCheck = linkedList.contains("test1");
        assertEquals(0, containsCheck);
    }
}

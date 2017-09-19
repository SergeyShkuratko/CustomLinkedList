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
}

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


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
        String valueById = linkedList.getValueByIndex(linkedList.getSize() - 1);
        assertEquals("newLastElement", valueById);
    }

    @Test
    public void addElementByIndex() {
        linkedList.addElement("beforeByIndex");
        linkedList.addElementByIndex(0, "addByIndex");
        String valueById = linkedList.getValueByIndex(0);
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
    public void indexOfElement() {
        linkedList.addElementByIndex(0, "indexOfCheck");
        linkedList.addElementByIndex(0, "test4");
        linkedList.addElementByIndex(0, "test3");
        linkedList.addElementByIndex(0, "test2");
        linkedList.addElementByIndex(0, "test1");
        int containsCheck = linkedList.indexOf("indexOfCheck");
        assertEquals(4, containsCheck);
        containsCheck = linkedList.indexOf("test1");
        assertEquals(0, containsCheck);
    }

    @Test
    public void contains() {
        linkedList.addElementByIndex(0, "test1");
        assertTrue(linkedList.contains("test1"));
        assertFalse(linkedList.contains("test99999"));
    }

    @Test
    public void setElement() {
        linkedList.addElementByIndex(0, "test1");
        linkedList.setElement(0, "1test");
        String item = linkedList.getNodeByIndex(0).getItem();
        assertEquals("1test", item);
    }

    @Test
    public void linkBefore() {
        linkedList.addElementByIndex(0, "test1");
        linkedList.addElementByIndex(0, "test2");
        CustomLinkedList.CustomLinkedListNode<String> nodeByIndex = linkedList.getNodeByIndex(1);
        linkedList.linkBefore("linkBefore", nodeByIndex);
        assertEquals(linkedList.getValueByIndex(0),"test2");
        assertEquals(linkedList.getValueByIndex(1),"linkBefore");
        assertEquals(linkedList.getValueByIndex(2),"test1");
    }

    @Test
    public void linkAfter() {
        linkedList.addElementByIndex(0, "test1");
        linkedList.addElementByIndex(0, "test2");
        CustomLinkedList.CustomLinkedListNode<String> nodeByIndex = linkedList.getNodeByIndex(0);
        linkedList.linkAfter("linkAfter", nodeByIndex);
        assertEquals(linkedList.getValueByIndex(0),"test2");
        assertEquals(linkedList.getValueByIndex(1),"linkAfter");
        assertEquals(linkedList.getValueByIndex(2),"test1");
    }
}

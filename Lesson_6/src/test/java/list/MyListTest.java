package list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Реализация MyList должна")
class MyListTest {
    MyList<Integer> list;

    @BeforeEach
    public void beforeAll() {
        list = new MyLinkedList<>();
        list.add( 1 );
        list.add( 2 );
        list.add( 10 );
        list.add( 1 );
        list.add( 30 );
        list.add( 2 );
    }

    @DisplayName("добавлять элементы и корректно давать размер")
    @Test
    public void testAdd() {
        assertEquals( 6, list.size() );
    }

    @DisplayName("находить элемент по индексу")
    @Test
    public void testGet() {
        assertEquals( Integer.valueOf( 2 ), list.get( 1 ));
        assertEquals( Integer.valueOf( 30 ), list.get( 4 ));
    }

    @DisplayName("возвращать null если выходим за размер")
    @Test
    public void testGetWithNull() {
        assertThrows( RuntimeException.class, () ->
                list.get( 11 )
        );
    }

    @DisplayName("находить удалять по элемент по совпадению")
    @Test
    public void testRemoveFirst() {
        boolean result = list.remove( 2 );
        assertTrue( result );
        assertEquals( 5, list.size() );
    }

    @DisplayName("возвращать false если удаление по элементу не удалось")
    @Test
    public void testRemoveFalse() {
        boolean result = list.remove( 100 );
        assertFalse( result );
        assertEquals( 6, list.size() );
    }

    @DisplayName("достать первый элемент из очереди")
    @Test
    public void testPoll() {
        assertEquals(Integer.valueOf(1), list.poll() );
    }

    @DisplayName("достать первый элемент из очереди")
    @Test
    public void testPollNull() {
        MyList<Integer> emptyList = new MyLinkedList<>();
        assertNull(emptyList.poll() );
    }

    @DisplayName("корректно сортировать Integer")
    @Test
    public void testMySort() {
        list.mySort();

        assertEquals( 6, list.size() );
        assertEquals( Integer.valueOf( 1 ), list.get( 0 ) );
        assertEquals( Integer.valueOf( 1 ), list.get( 1 ) );
        assertEquals( Integer.valueOf( 2 ), list.get( 2 ) );
        assertEquals( Integer.valueOf( 2 ), list.get( 3 ) );
        assertEquals( Integer.valueOf( 10 ), list.get( 4 ) );
        assertEquals( Integer.valueOf( 30 ), list.get( 5 ) );
    }
}

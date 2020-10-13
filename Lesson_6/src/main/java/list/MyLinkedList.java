package list;

public class MyLinkedList<T extends Comparable<T>> implements MyList<T>, MyQueue<T> {
    private ListItem<T> head = null;
    private ListItem<T> tail = null;
    private int size = 0;

    @Override
    public void add( T item ) {
        // Создаем новый элемент
        ListItem<T> internalItem = new ListItem<>();
        internalItem.value = item;
        //Если голова ещё не задана - то её присваиваем новому элементу
        if ( head == null ) {
            head = internalItem;
        }
        // Хвост заменяется на новый
        ListItem<T> tempTail = tail;
        tail = internalItem;
        // Связываем старый хвост и новый хвост между собой
        if ( tempTail != null ) {
            tempTail.next = tail;
        }
        tail.prev = tempTail;
        // Двигаемся по счетчику
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get( int index ) {
        // TODO Ошибка если индекс больше размера
        // DONE
        if(index > size()) {
            throw new RuntimeException("Индекс за границей массива");
        }

        // TODO Если индекс > size/2 - то перебираем от tail
        // DONE
        int currPosition;
        T result = null;
        ListItem<T> item;

        boolean searchFromTail = index > size / 2 ? true : false;

        item = searchFromTail ? tail : head;
        currPosition = searchFromTail ? size - 1 : 0;

        if(!searchFromTail) {
            // Пошли перебирать элементы пока не дойдем до индекса или же не выйдем за размеры списка
            while (result == null && currPosition < size) {
                if (index == currPosition) {
                    result = item.value;
                }
                item = item.next;
                currPosition++;
            }
        }
        else {
            while (result == null && currPosition >= 0) {
                if (index == currPosition) {
                    result = item.value;
                }
                item = item.prev;
                currPosition--;
            }
        }
        return result;
    }

    @Override
    public boolean remove( T item ) {
        // TODO - реализовать.
        // DONE
        int currPosition = 0;
        boolean result = false; //Если нашли и удалили - TRUE, иначе FALSE
        ListItem<T> searchItem = new ListItem<>();
        searchItem.value = item;

        ListItem<T> listItem = head;

        while (!result && currPosition < size)
        {
            if(listItem.value == searchItem.value){
                ListItem<T> prevItem = listItem.prev;
                ListItem<T> nextItem = listItem.next;

                if(listItem == head)
                {
                    head = nextItem;
                    nextItem.prev = null;
                }
                else if(listItem == tail)
                {
                    tail = prevItem;
                    prevItem.next = null;
                }
                else {
                    prevItem.next = nextItem;
                    nextItem.prev = prevItem;
                }

                size--;
                result = true;
            }

            listItem = listItem.next;
            currPosition++;
        }

        return result;
    }

    @Override
    //вытащить первый элемент из списка и удалить его
    public T poll() {
        //TODO реализовать
        //DONE

        ListItem<T> listItem = new ListItem<T>();

        if(size != 0)
        {
            listItem = head;

            ListItem<T> nextItem = listItem.next;
            head = nextItem;
            nextItem.prev = null;
            size--;
        }

        return (T) listItem;
    }

    @Override
    public void sort() {
        boolean wasChange = true;
        while ( wasChange ){
            wasChange = false;
            ListItem<T> first = head;
            ListItem<T> second = head.next;
            while ( second != null ){
                wasChange = wasChange || compareAndReplaceItem(first, second);
                first = second;
                second = second.next;
            }
        }
    }

    @Override
    //Алгоритм быстрой сортировки
    public void quickSort() {
        boolean wasChange = true;
        while ( wasChange ){
            wasChange = false;
            ListItem<T> first = head;
            ListItem<T> second = head.next;
            while ( second != null ){
                wasChange = wasChange || compareAndReplaceItem(first, second);
                first = second;
                second = second.next;
            }
        }
    }

    private boolean compareAndReplaceItem( ListItem<T> first, ListItem<T> second ) {
        if (second.value.compareTo( first.value ) < 0){
            second.prev = first.prev;
            if (second.prev == null){
                head = second;
            }
            first.next = second.next;
            if (first.next == null){
                tail = first;
            }
            second.next = first;
            first.prev = second;
            return true;
        }
        return false;
    }

    private class ListItem<T> {
        T value;
        ListItem<T> prev;
        ListItem<T> next;
    }
}

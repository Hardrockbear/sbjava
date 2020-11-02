package list;

import java.util.Arrays;

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
        if(index > size()) {
            throw new RuntimeException("Индекс за границей массива");
        }

        return (T) getItem(index).value;
    }

    public ListItem getItem (int index){
        int currPosition;
        T result = null;
        ListItem<T> item;

        boolean searchFromTail = index > size / 2;

        item = searchFromTail ? tail : head;
        currPosition = searchFromTail ? size - 1 : 0;

        if(!searchFromTail) {
            // Пошли перебирать элементы пока не дойдем до индекса или же не выйдем за размеры списка
            while (result == null && currPosition < size) {
                if (index == currPosition) {
                    break;
                }
                item = item.next;
                currPosition++;
            }
        }
        else {
            while (result == null && currPosition >= 0) {
                if (index == currPosition) {
                    break;
                }
                item = item.prev;
                currPosition--;
            }
        }

        return item;
    }

    @Override
    public boolean remove( T item ) {
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

    //вытащить первый элемент из списка и удалить его
    @Override
    public T poll() {
        ListItem<T> listItem;

        if(size != 0)
        {
            listItem = head;

            if(size > 1) {
                ListItem<T> nextItem = listItem.next;
                head = nextItem;
                nextItem.prev = null;
            }
            size--;
        }
        else {
            return null;
        }

        return listItem.value;
    }

    //Алгоритм хитрой сортировки, чтобы не заниматься перелинковкой элементов
    @Override
    public void mySort() {
        if(this.size == 0)
        {
            throw new RuntimeException("Нечего сортировать");
        }

        int tempArray[] = new int[size]; //массив int

        //перегоняем все значения элементов связанного списка в наш массив
        for (int i = 0; i < this.size; i++)
        {
            int value = (Integer) this.get(i);
            tempArray[i] = value;
        }

        Arrays.sort(tempArray); //сортируем массив

        //Перебиваем значения элементов списка согласно сортированному массиву
        for(int j = 0; j < tempArray.length; j++) {
            this.getItem(j).value = tempArray[j];
        }
    }

    //Сортировка с лекции
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

    //Вспомогательная функция для сортировки с лекции
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

package list;

public interface MyList<T extends Comparable<T>> {
    // Добавить новый элемент в конец списка
    void add(T item);

    // Количество элеметов в списке
    int size();

    // Получить элемент по индексу
    T get(int index);

    // Удалить первый элемент по совпадению. Если не найден - то false
    boolean remove(T item);

    // Достать первый элемент из очереди и удалить его
    T poll();

    // Допускаем, что T - comparable
    void sort();

    // Собственный алгоритм сортировки
    void mySort();

}
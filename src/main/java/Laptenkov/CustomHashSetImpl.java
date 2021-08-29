package Laptenkov;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Класс {@link CustomHashSetImpl<T>}, реализует множество на хеш-таблицы.
 * Класс {@link CustomHashSetImpl<T>} реализует интерфейс {@link CustomHashSet<T>}.
 * Класс {@link CustomHashSetImpl<T>} может хранить объекты любого типа.
 * @param <T>
 */
public class CustomHashSetImpl<T> implements CustomHashSet<T> {

    /**
     * Контейнер для хранения объекта {@link CustomHashSetImpl<T>}
     * @param <T>
     */
    private static class Node<T> {
        T item;
        int hash;
        Node<T> next;

        public Node(T item, int hash) {
            this.item = item;
            this.hash = hash;
        }
    }

    private final int DEFAULT_CAPACITY = 16;
    private Node<T>[] table = new Node[DEFAULT_CAPACITY];
    private int size;
    private int capacity;

    /**
     * Конструктор пустого объекта {@link CustomHashSetImpl<T>}.
     */
    CustomHashSetImpl() {
        this.capacity = DEFAULT_CAPACITY;
    }

    /**
     * Конструктор пустого объекта {@link CustomHashSetImpl<T>}
     * с заданной вместимостью capacity.
     */
    CustomHashSetImpl(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Метод {@link CustomHashSetImpl#size()} возвращает размер связанного списка
     * объекта {@link CustomHashSetImpl}.
     *
     * @return целое число типа {@link int}
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Метод {@link CustomHashSetImpl#isEmpty()} возвращает булево значение
     * при проверке объекта {@link CustomHashSetImpl} на пустоту.
     *
     * @return <code>true</code> если размер не нулевой,
     * <code>false</code> если размер нулевой.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Метод {@link CustomHashSetImpl#add(Object)} возвращает булево значение
     * при попытке добавления объекта в {@link CustomHashSetImpl}.
     *
     * @param newItem объект типа Т для добавления.
     * @return возвращает <code>true</code> если добавление успешно,
     * возвращает <code>false</code> если добавление не удалось.
     */
    public boolean add(T newItem) {

        int bucketId = getBucket(newItem);
        int newItemHash = getItemHash(newItem);

        Node<T> tmp = table[bucketId];
        Node<T> prevTmp = null;
        while (tmp != null) {

            if (newItemHash == tmp.hash && Objects.equals(newItem, tmp.item)) {
                // hash code & equals
                return false;
            }

            prevTmp = tmp;
            tmp = tmp.next;
        }

        if (prevTmp == null) {
            table[bucketId] = new Node<>(newItem, newItemHash);
        } else {
            prevTmp.next = new Node<>(newItem, newItemHash);
        }
        size++;
        return true;
    }

    /**
     * Вспомогательный метод {@link CustomHashSetImpl#getItemHash(Object)}
     * возвращает целое число - hashCode переданного
     * объекта в {@link CustomHashSetImpl}.
     *
     * @param newItem объект типа Т для хеширования.
     * @return целое число int.
     */
    private int getItemHash(T newItem) {
        if (newItem == null) {
            return 0;
        }

        return newItem.hashCode();
    }

    /**
     * Вспомогательный метод {@link CustomHashSetImpl#getBucket(Object)}
     * возвращает целое число - номер бакета для размещения переданного
     * объекта в {@link CustomHashSetImpl}.
     *
     * @param newItem объект типа Т для определия бакета.
     * @return целое число int.
     */
    private int getBucket(T newItem) {
        if (newItem == null) {
            return 0;
        }

        return newItem.hashCode() % (table.length - 1) + 1;
    }

    /**
     * Метод {@link CustomHashSetImpl#remove(Object)} возвращает булево значение
     * при попытке удаления объекта в {@link CustomHashSetImpl}.
     *
     * @param item объект типа Т для удаления.
     * @return возвращает <code>true</code> если удаление успешно,
     * возвращает <code>false</code> если удаление не удалось.
     */
    @Override
    public boolean remove(T item) {
        if (size == 0) {
            return false;
        }

        int hash = getItemHash(item);
        int removeItemBucket = getBucket(item);
        Node<T> tmp = table[removeItemBucket];
        Node<T> prevTmp = null;

        while (tmp != null) {
            if (tmp.hash == hash && tmp.item.equals(item)) {
                break;
            }
            prevTmp = tmp;
            tmp = tmp.next;
        }

        if (prevTmp == null) {
            if (tmp.next == null) {
                table[removeItemBucket] = null;
            } else {
                table[removeItemBucket] = tmp.next;
            }
        } else {
            prevTmp.next = tmp.next;
        }
        size--;

        return true;

//        int hash = getItemHash(item);
//        Node<T> bucket = table[getBucket(item)];
//        if (bucket.next == null) {
//            return false;
//        }
//
//        while (bucket.next != null) {
//            if (bucket.next.hash == hash && bucket.next.item.equals(item)) {
//                bucket.next.item = null;
//                bucket.next = null;
//                size--;
//                return true;
//            }
//        }
//        return false;
    }

    /**
     * Метод {@link CustomHashSetImpl#contains(Object)} возвращает булево значение
     * при проверке наличия объекта в {@link CustomHashSetImpl}.
     *
     * @param item объект типа Т для проверки.
     * @return возвращает <code>true</code> если объект присутствует,
     * возвращает <code>false</code> если объект отсутствует.
     */
    @Override
    public boolean contains(T item) {
        int hash = getItemHash(item);
        if (null == item && table[0] != null) {
            return true;
        }

        Node<T> tmp = table[getBucket(item)];
        while (tmp != null) {
            if (tmp.hash == hash && tmp.item.equals(item)) {
                return true;
            }
            tmp = tmp.next;
        }

        return false;
    }
//        int hash = getItemHash(item);
//        Node<T> bucket = table[getBucket(item)];
//
//
//        if (hash == 0 && getBucket(item) == 0) {
//            return table[0] == null ? false : table[0].item == null;
//        }
//
//        while (null != bucket.next) {
//            if (bucket.next.hash == hash && bucket.next.item.equals(item)) {
//                return true;
//            }
//            bucket = bucket.next;
//        }
//        return false;

    /**
     * Метод {@link CustomHashSetImpl#toString()}
     * возвращает строковое представление дерева {@link CustomHashSetImpl}
     *
     * @return объект типа String в формате '[ element1 element2 ... elementN ]
     * или [ ] если дерево пустое.
     */
    @Override
    public String toString() {
        StringBuilder cb = new StringBuilder();
        cb.append("[");

        for (int i = 0; i < table.length; ++i) {
            Node<T> tmp = table[i];
            while (tmp != null) {
                cb.append(" " + tmp.item);
                tmp = tmp.next;
            }
        }
        cb.append(" ]");

        return cb.toString();
    }

    /**
     * Метод {@link CustomHashSetImpl<T>#toArray()}
     * возвращает копию текущего объекта
     * {@link CustomHashSetImpl<T>}.
     *
     * @return объект типа {@link CustomHashSetImpl<T>}.
     */
    @Override
    public Object[] toArray() {
        if (size == 0) {
            return new Object[0];
        }

        Object[] newData = new Object[size];
        int j = 0;
        for (int i = 0; i < table.length; ++i) {
            Node<T> tmp = table[i];
            while (tmp != null) {
                newData[j++] = tmp.item;
                tmp = tmp.next;
            }
        }

        return newData;
    }
}

package Laptenkov;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования public методов класса {@link CustomHashSetImpl<Object>}.
 *
 * @author habatoo
 */
class CustomHashSetImplTest {

    private CustomHashSet<Integer> customEmptyHashSet;
    private CustomHashSet<Integer> customNotEmptyHashSet;

    /**
     * Инициализация экземпляров тестируемого класса {@link CustomHashSetImpl}.
     */
    @BeforeEach
    void setUp() {
        customEmptyHashSet = new CustomHashSetImpl<>();

        customNotEmptyHashSet = new CustomHashSetImpl<>();
        customNotEmptyHashSet.add(25);
        customNotEmptyHashSet.add(50);
        customNotEmptyHashSet.add(1);
        customNotEmptyHashSet.add(99);
    }

    /**
     * Очистка экземпляров тестируемого класса {@link CustomHashSetImpl}.
     */
    @AfterEach
    void tearDown() {
        customEmptyHashSet = null;
        customNotEmptyHashSet = null;
    }

    /**
     * Проверяет создание пустого экземпляра {@link CustomHashSetImpl}.
     * Сценарий, при котором конструктор отрабатывает пустую коллекцию,
     * проверяет размер коллекции
     * равный 0 и отображение коллекции.
     */
    @Test
    public void customHashSetImpl_Test() {
        customEmptyHashSet = new CustomHashSetImpl<>();
        Assertions.assertEquals(0, customEmptyHashSet.size());
        Assertions.assertEquals(
                "[ ]", customEmptyHashSet.toString());
    }

    /**
     * Метод {@link CustomHashSetImplTest#size_Test()}
     * проверяет метод {@link CustomHashSetImpl#size()}.
     * Проверяет размер не пустого экземпляра {@link CustomHashSetImpl}.
     * Сценарий, при котором пустой экземпляр возвращает величину
     * объекта равную 0, не пустой экземпляр возвращает 6.
     */
    @Test
    void size_Test() {
        Assertions.assertEquals(0, customEmptyHashSet.size());
        Assertions.assertEquals(4, customNotEmptyHashSet.size());
    }

    /**
     * Метод  {@link CustomHashSetImplTest#isEmpty_Test()}
     * проверяет метод {@link CustomHashSetImpl#isEmpty()}.
     * Проверяет на пустоту экземпляр объекта {@link CustomHashSetImpl}.
     * Сценарий, при котором пустой экземпляр возвращает <code>true</code>,
     * не пустой экземпляр возвращает <code>false</code>.
     */
    @Test
    void isEmpty_Test() {
        Assertions.assertEquals(true, customEmptyHashSet.isEmpty());
        Assertions.assertEquals(false, customNotEmptyHashSet.isEmpty());
    }

    /**
     * Метод {@link CustomHashSetImplTest#addSuccess_Test()}
     * Проверяет проверяет метод {@link CustomHashSetImpl#add(Object)}.
     * Сценарий, при котором объект успешно отрабатывает добавление
     * не пустого объекта типа Т и возвращает <code>true</code>.
     */
    @Test
    void addSuccess_Test() {
        Assertions.assertEquals(0, customEmptyHashSet.size());
        Assertions.assertEquals(true, customEmptyHashSet.add(15));
        Assertions.assertEquals(1, customEmptyHashSet.size());
        Assertions.assertEquals(true, customEmptyHashSet.add(99));
        Assertions.assertEquals(2, customEmptyHashSet.size());
        Assertions.assertEquals(false, customEmptyHashSet.add(99));
        Assertions.assertEquals(2, customEmptyHashSet.size());
    }

    /**
     * Метод {@link CustomHashSetImplTest#addFail_Test()}
     * Проверяет проверяет метод {@link CustomHashSetImpl#add(Object)}.
     * Сценарий, при котором уже существующий объект не успешно
     * добавляется и возвращает <code>true</code>.
     */
    @Test
    void addFail_Test() {
        Assertions.assertEquals(true, customEmptyHashSet.add(99));
        Assertions.assertEquals(false, customEmptyHashSet.add(99));
    }

    /**
     * Метод {@link CustomHashSetImplTest#removeFail_Test()}
     * Проверяет проверяет метод {@link CustomHashSetImpl#remove(Object)}.
     * Сценарий, при котором объект успешно отрабатывает удаление
     * не пустого существующего в дереве объекта.
     */
    @Test
    void removeSuccess_Test() {

        Assert.assertEquals(
                Arrays.asList(1, 50, 99, 25),
                Arrays.asList(customNotEmptyHashSet.toArray()));
        Assertions.assertEquals(true, customNotEmptyHashSet.remove(25));

        Assertions.assertEquals(true, customNotEmptyHashSet.remove(1));
        Assert.assertEquals(
                Arrays.asList(50, 99),
                Arrays.asList(customNotEmptyHashSet.toArray()));
    }

    /**
     * Метод {@link CustomHashSetImplTest#removeFail_Test()}
     * Проверяет проверяет метод {@link CustomHashSetImpl#remove(Object)}.
     * Сценарий, при котором объект не успешно отрабатывает удаление
     * не пустого не существующего в дереве объекта.
     */
    @Test
    void removeFail_Test() {
        Assertions.assertEquals(false, customEmptyHashSet.remove(9999));
    }


    /**
     * Метод {@link CustomHashSetImplTest#containsSuccess_Test()}
     * Проверяет проверяет метод {@link CustomHashSetImpl#contains(Object)}.
     * Сценарий, при котором проверяется наличие существующего объекта и
     * возвращает <code>true</code>.
     */
    @Test
    void containsSuccess_Test() {
        Assertions.assertEquals(true, customNotEmptyHashSet.contains(25));
        Assertions.assertEquals(true, customNotEmptyHashSet.contains(1));
    }

    /**
     * Метод {@link CustomHashSetImplTest#containsFail_Test()}
     * Проверяет проверяет метод {@link CustomHashSetImpl#contains(Object)}.
     * Сценарий, при котором проверяется наличие не существующего объекта и
     * возвращает <code></code>.
     */
    @Test
    void containsFail_Test() {
        Assertions.assertFalse(customEmptyHashSet.contains(0));
        Assertions.assertFalse(customNotEmptyHashSet.contains(100));

    }

    /**
     * Метод {@link CustomHashSetImplTest#testToStringEmpty_Test()}
     * Проверяет отображение экземпляр объекта {@link CustomHashSetImpl}
     * методом {@link CustomHashSetImpl#toString()}.
     * Сценарий, при котором пустой экземпляр проверяется на отображение
     * тестовых строк.
     */
    @Test
    void testToStringEmpty_Test() {
        Assertions.assertEquals("[ ]", customEmptyHashSet.toString());
    }

    /**
     * Метод {@link CustomHashSetImplTest#testToStringNotEmpty_Test()}
     * Проверяет отображение экземпляр объекта {@link CustomHashSetImpl}
     * методом {@link CustomHashSetImpl#toString()}.
     * Сценарий, при котором не пустой экземпляр проверяется на отображение
     * тестовых строк.
     */
    @Test
    void testToStringNotEmpty_Test() {
        Assertions.assertEquals(
                "[ 1 50 99 25 ]", customNotEmptyHashSet.toString());
    }

    /**
     * Метод {@link CustomHashSetImplTest#toArray_Test} проверяет
     * метод {@link CustomHashSetImpl#toArray} на
     * создание копии очереди {@link CustomHashSetImpl}.
     * Сценарий проверяет идентичность созданной копии и исходной очереди по элементно.
     */
    @Test
    void toArray_Test() {
        Assert.assertEquals(
                Arrays.asList(1, 50, 99, 25),
                Arrays.asList(customNotEmptyHashSet.toArray()));
        Assert.assertEquals(Arrays.asList(),
                Arrays.asList(customEmptyHashSet.toArray()));
    }

    //////////////////////////////////////////////////////////////////////////

    private CustomHashSet<Integer> set = new CustomHashSetImpl<>(16);

    @Test
    public void sizeTest() {

        Assert.assertEquals(0, set.size());
        Assert.assertTrue(set.isEmpty());

        for (int i = 1; i <= 10; ++i) {
            Assert.assertTrue(set.add(i));
            Assert.assertEquals(i, set.size());
        }

        for (int i = 10; i >= 1; --i) {
            Assert.assertFalse(set.add(i));
        }
        Assert.assertEquals(10, set.size());

        for (int i = 10; i >= 1; --i) {
            Assert.assertTrue(set.remove(i));
            Assert.assertEquals(i - 1, set.size());
        }

        Assert.assertTrue(set.isEmpty());
    }

    @Test
    public void addTest() {

        Assert.assertEquals(0, set.toArray().length);

        Assert.assertTrue(set.add(10));
        Assert.assertFalse(set.add(10));
        Assert.assertEquals(Arrays.asList(10), Arrays.asList(set.toArray()));

        Assert.assertTrue(set.add(5));
        Assert.assertEquals(Arrays.asList(5, 10), Arrays.asList(set.toArray()));

        Assert.assertTrue(set.add(15));
        Assert.assertFalse(set.add(15));
        Assert.assertEquals(Arrays.asList(15, 5, 10), Arrays.asList(set.toArray()));

        Assert.assertFalse(set.add(15));
        Assert.assertEquals(Arrays.asList(15, 5, 10), Arrays.asList(set.toArray()));

        Assert.assertTrue(set.add(7));
        Assert.assertEquals(Arrays.asList(15, 5, 7, 10), Arrays.asList(set.toArray()));

        Assert.assertTrue(set.add(1));
        Assert.assertEquals(Arrays.asList(15, 1, 5, 7, 10), Arrays.asList(set.toArray()));

        Assert.assertTrue(set.add(9));
        Assert.assertEquals(Arrays.asList(15, 1, 5, 7, 9, 10), Arrays.asList(set.toArray()));

        Assert.assertTrue(set.add(20));
        Assert.assertEquals(Arrays.asList(15, 1, 5, 20, 7, 9, 10), Arrays.asList(set.toArray()));

        Assert.assertTrue(set.add(100));
        Assert.assertEquals(Arrays.asList(15, 1, 5, 20, 7, 9, 10, 100), Arrays.asList(set.toArray()));

        Assert.assertEquals(8, set.size());
    }

    @Test
    public void removeTest() {

        Assert.assertTrue(set.add(10));
        Assert.assertEquals(1, set.size());
        Assert.assertTrue(set.contains(10));

        Assert.assertTrue(set.remove(10));
        Assert.assertFalse(set.contains(10));
        Assert.assertEquals(0, set.size());

        set.add(30);

        set.add(50);
        set.add(10);

        set.add(5);
        set.add(20);
        set.add(70);
        set.add(100);
        set.add(60);
        set.add(65);
        set.add(55);
        set.add(56);
        set.add(19);

        Assert.assertEquals(Arrays.asList(30, 60, 19, 50, 5, 20, 65, 10, 70, 100, 55, 56), Arrays.asList(set.toArray()));

        set.remove(20);
        set.remove(100);

        Assert.assertEquals(Arrays.asList(30, 60, 19, 50, 5, 65, 10, 70, 55, 56), Arrays.asList(set.toArray()));

        set.remove(65);

        Assert.assertEquals(Arrays.asList(30, 60, 19, 50, 5, 10, 70, 55, 56), Arrays.asList(set.toArray()));

        set.remove(50);
        Assert.assertEquals(Arrays.asList(30, 60, 19, 5, 10, 70, 55, 56), Arrays.asList(set.toArray()));

        set.remove(30);
        Assert.assertEquals(Arrays.asList(60, 19, 5, 10, 70, 55, 56), Arrays.asList(set.toArray()));

        set.remove(5);
        set.remove(10);
        set.remove(70);
        set.remove(19);
        set.remove(55);
        set.remove(56);
        set.remove(60);

        Assert.assertEquals(0, set.size());
        Assert.assertEquals(Collections.emptyList(), Arrays.asList(set.toArray()));
    }

    @Test
    public void containsTest() {
        set.add(30);

        set.add(50);
        set.add(10);

        set.add(5);
        set.add(20);
        set.add(70);
        set.add(100);
        set.add(60);
        set.add(65);
        set.add(55);
        set.add(56);
        set.add(19);
        set.add(null);

        Assert.assertTrue(set.contains(null));
        Assert.assertFalse(set.contains(200));
        Assert.assertEquals(Arrays.asList(null, 30, 60, 19, 50, 5, 20, 65, 10, 70, 100, 55, 56), Arrays.asList(set.toArray()));

        for (Object value : set.toArray()) {

            Assert.assertTrue(set.contains((Integer) value));
        }
    }

    @Test
    public void toStringTest() {

        set.add(30);

        set.add(50);
        set.add(10);

        Assert.assertEquals("[ 30 50 10 ]", set.toString());

        set.add(5);
        set.add(20);
        set.add(70);
        set.add(100);

        Assert.assertEquals("[ 30 50 5 20 10 70 100 ]", set.toString());

        set.add(60);
        set.add(65);
        set.add(55);
        set.add(56);
        set.add(19);

        Assert.assertFalse(set.contains(200));
        Assert.assertEquals("[ 30 60 19 50 5 20 65 10 70 100 55 56 ]", set.toString());
    }

    @Test
    public void toArrayTest() {

        set.add(30);
        set.add(null);
        set.add(50);
        set.add(10);

        Assert.assertEquals(Arrays.asList(null, 30, 50, 10), Arrays.asList(set.toArray()));

        set.add(5);
        set.add(20);
        set.add(70);
        set.add(100);

        Assert.assertEquals(Arrays.asList(null, 30, 50, 5, 20, 10, 70, 100), Arrays.asList(set.toArray()));

        set.add(60);
        set.add(65);
        set.add(55);
        set.add(56);
        set.add(19);

        Assert.assertFalse(set.contains(200));
        Assert.assertEquals(Arrays.asList(null, 30, 60, 19, 50, 5, 20, 65, 10, 70, 100, 55, 56), Arrays.asList(set.toArray()));
    }
}
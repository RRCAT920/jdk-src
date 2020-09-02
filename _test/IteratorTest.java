import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author huzihao
 * @since 2020/9/2 16:53
 */
public class IteratorTest {
    /**
     * 越界next会抛出NoSuchElementException
     */
    @Test
    public void testNext() {
        Collection collection = new ArrayList(1);
        collection.add(1);

        Iterator itor = collection.iterator();
        itor.next();
        try {
            itor.next();
        } catch (NoSuchElementException e) {
            System.out.println("越界");
        }
    }

    /**
     * 🙅错误的迭代方式1
     * next每次都会移动游标
     */
    @Test
    public void wrongTraverse1() {
        Collection collection = new ArrayList(10);
        for (int i = 1; i < 11; i++) {
            collection.add(i);
        }

        Iterator itor = collection.iterator();
        var list = new ArrayList(10);

        try {
            while ((itor.next()) != null) {
                list.add(itor.next());
            }
        } catch (NoSuchElementException e) {
            var testList = new ArrayList();
            testList.add(2);
            testList.add(4);
            testList.add(6);
            testList.add(8);
            testList.add(10);
            assert testList.equals(list);
            System.out.println("越界");
        }
    }

    /**
     * 🙅错误的迭代方式2
     * iterator会返回新的迭代器
     */
    @Test
    public void wrongTraverse() {
        Collection collection = new ArrayList(10);
        for (int i = 1; i < 11; i++) {
            collection.add(i);
        }

        while (collection.iterator().hasNext()) {
            assert 1 == (Integer) collection.iterator().next();
            System.out.println("Running");
        }
    }
}

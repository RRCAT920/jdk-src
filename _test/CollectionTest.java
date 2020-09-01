import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * @author huzihao
 * @since 2020/9/2 04:11
 */
public class CollectionTest {
    /**
     * 最佳实践🥇：用Collection存储对象时，对象需要实现equals方法
     */
    @Test
    public void testContains() {
        final int[] count = {0};

        class A {
            private final String value;

            public A(String value) {
                this.value = value;
            }

            @Override
            public boolean equals(Object o) {
                count[0]++;
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                A a = (A) o;
                return Objects.equals(value, a.value);
            }

            @Override
            public int hashCode() {
                return Objects.hash(value);
            }
        }

        class B {
            private final String value = "same value";
        }

        Collection c1 = Arrays.asList(121, "hello", new A("same value"), new B());
        assert c1.contains(new A("same value"));
        assert 3 == count[0];
        assert !c1.contains(new B());
    }
}

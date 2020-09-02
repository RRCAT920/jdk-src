import org.junit.Test;

import java.util.ArrayList;

/**
 * @author huzihao
 * @since 2020/9/2 21:10
 */
public class ArrayListTest {
    /**
     * <p>7.0</p>
     * ArrayList()是饿汉式的（默认容量：10）
     * <p>8.0</p>
     * ArrayList()是懒汉式的
     */
    @Test
    public void testForNonParameterCtor() {
        assert new ArrayList(0).equals(new ArrayList());
    }
}

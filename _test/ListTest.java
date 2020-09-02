import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huzihao
 * @since 2020/9/2 22:12
 */
public class ListTest {
    /**
     * remove的名字查找
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void removeSeries() {
        List list = new ArrayList(5);
        list.add(1);
        list.add(2);
        list.add(3);

        list.remove(2);
        assert "[1, 2]".equals(list.toString());

        list.remove(Integer.valueOf(1));
        assert "[2]".equals(list.toString());
    }
}

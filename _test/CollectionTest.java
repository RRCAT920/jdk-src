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
        /* 最终事实常量的技巧 */
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

        @SuppressWarnings({"Raw use of parameterized class 'Collection'", "rawtypes"})
        Collection c1 = Arrays.asList(121, "hello", new A("same value"), new B());
        assert c1.contains(new A("same value"));
        assert 3 == count[0];
        assert !c1.contains(new B());
    }


    @SuppressWarnings("rawtypes")
    @Test
    public void testClearForArraysAsList() {
        Collection collection = Arrays.asList(1, 2, 3);
        try {
            collection.clear();
        } catch (UnsupportedOperationException e) {
            System.out.println("Arrays.asList返回的是内部类，而不是java.util.ArrayList");
        }
    }

    /**
     * <h3>泛型可变参数</h3>
     * 可变参数和泛型不能很好地交互，因为可变参数机制是在数组上面构建的脆弱的抽象，
     * 并且数组具有与泛型不同的类型规则。 虽然泛型可变参数不是类型安全的，但它们是合法的。 如果选
     * 择使用泛型（或参数化）可变参数编写方法，请首先确保该方法是类型安全的，然后使用
     * {@code @SafeVarargs} 注解对其进行标注，以免造成使用不愉快。
     * <br><br>
     * {@code @SafeVarargs}  注解的规则很简单：在每种方法上使用 {@code @SafeVarargs} ，并使用
     * 泛型或参数化类型的可变参数，这样用户就不会因不必要的和令人困惑的编译器警告而担忧。 这意味着
     * 你不应该写危险或者 toArray 等不安全的可变参数方法。 每次编译器警告你可能会受到来自你控制
     * 的方法中泛型可变参数的堆污染时，请检查该方法是否安全。 提醒一下，在下列情况下，泛型可变参数
     * 方法是安全的：
     * 1. 它不会在可变参数数组中存储任何东西
     * 2. 它不会使数组（或克隆）对不可信代码可见。 如果违反这些禁令中的任何一项，请修复。
     * 请注意， SafeVarargs 注解只对不能被重写的方法是合法的，因为不可能保证每个可能的重写方
     * 法都是安全的。 在 Java 8 中，注解仅在静态方法和 final 实例方法上合法; 在 Java 9 中，它在私有实
     * 例方法中也变为合法。
     * 使用 SafeVarargs 注解的替代方法是采用条目 28 的建议，并用 List 参数替换可变参数（这
     * 是一个变相的数组）
     */
    @SuppressWarnings({"all"})
    @Test
    public void testAsList() {
        Collection collection = Arrays.asList(1, 2);
        assert 2 == collection.size();

        Collection collection1 = Arrays.asList(new int[]{1, 2});
        assert 1 == collection1.size();

        Collection collection2 = Arrays.asList(new Integer[]{1, 2});
        assert 2 == collection2.size();
//        System.out.println(new Integer[]{1, 2});
    }
}

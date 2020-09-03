import org.junit.Test;

import java.util.HashSet;
import java.util.Objects;

/**
 * @author huzihao
 * @since 2020/9/3 21:07
 */
public class HashSetTest {
    /**
     * 先哈希后比较
     */
    @Test
    public void testHashing() {
        HashSet set = new HashSet();
        Person p1 = new Person(1001,"AA");
        Person p2 = new Person(1002,"BB");
        set.add(p1);
        set.add(p2);

        p1.name = "CC";
        set.remove(p1);
        assert 2 == set.size();

        set.add(new Person(1001,"CC"));
        assert 3 == set.size();

        set.add(new Person(1001,"AA"));
        assert 4 == set.size();
    }
}

class Person {
    int id;
    String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
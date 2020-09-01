import org.junit.Test;

import java.util.Arrays;

/**
 * @author huzihao
 * @since 2020/8/28 22:05
 */
public class StringTest {
    /**
     * 测试 String 的 CASE_INSENSITIVE_ORDER 属性
     */
    @Test
    public void CaseInsensitiveSort() {
        String[] teams = new String[5];
        teams[0] = "Manchester United";
        teams[1] = "chelsea";
        teams[2] = "Arsenal";
        teams[3] = "liverpool";
        teams[4] = "EVERTON";

        Arrays.sort(teams);
        System.out.println("大小写敏感：" + Arrays.toString(teams));

        Arrays.sort(teams, String.CASE_INSENSITIVE_ORDER);
        System.out.println("大小写不敏感：" + Arrays.toString(teams));
    }

    /**
     * 测试 String 的 trim(): 去除首尾字符集中U+0020及之前的字符
     * 测试 String 的 strip(): 仅去除首尾空格
     */
    @Test
    public void testTrim() {
        String aString = '\u0010' + " 1223 ";
        System.out.println(aString.trim());
        System.out.println(aString.strip());
    }
}

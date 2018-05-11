package java8;

import java.util.Arrays;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class LambdaTest {

    public static void main(String[] args) {
        Arrays.asList("1", "2", 3).forEach(
                e -> {
                    System.out.println(e);
                }
        );

        test1();
    }

    static void test1(){
         Arrays.asList("a","b","c").sort(
                (e1,e2) -> {
                    System.out.println(e1);
                    System.out.println(e2);
                    return 1;
                }
        );
    }
}

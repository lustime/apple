package java8;

import java.util.List;
import java.util.Optional;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17040407
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class OptionalTest {

    public static void main(String[] args) {

        Optional<String> fullName = Optional.ofNullable("abc");
        System.out.println("Full name is set?" + fullName.isPresent());
        System.out.println("Full name: "+ fullName.orElseGet(() -> "[none]"));
        System.out.println(fullName.map(s -> "hey" + s + "!").orElse("hey Stranger!"));

    }
}

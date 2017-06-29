package regexTest;

import org.junit.Test;
import regex.DealStrSub;

/**
 * Created by 17040407 on 2017/6/29.
 */
public class RegexTest {

    @Test
    public void Test1()
    {
        String str="abc123.|ahahqwe";

        String regex = "abc(.*?)qwe";

        String subStr = DealStrSub.getSubUtilSimple(str,regex);

        System.out.println(subStr);

    }
}

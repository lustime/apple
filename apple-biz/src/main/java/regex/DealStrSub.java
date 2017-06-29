package regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 17040407 on 2017/6/29.
 * 正则截取字符串
 */
public class DealStrSub {
    public static String getSubUtilSimple(String soap,String regex)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(soap);

        while (matcher.find())
            return matcher.group(1);
        return "";
    }

    public static List<String> getSubUtil(String soap,String regex)
    {
        List<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(soap);

        while (matcher.find())
        {
            int i=1;
            list.add(matcher.group(i));
            i++;
        }
        return list;
    }
}

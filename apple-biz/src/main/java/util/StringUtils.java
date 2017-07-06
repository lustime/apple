package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类
 * Created by 17040407 on 2017/7/6.
 */
public class StringUtils {
    /**
     * 判断是否为空串
     *
     * @param s
     * @return
     */
    public static boolean isNotNull(String s) {
        if (null != s && s.trim().length() != 0)
            return true;
        return false;
    }

    /**
     * 截取字符串指定长度，不足左补零
     *
     * @param s
     * @param length
     * @return
     */
    public static String leftFillZero(String s, int length) {
        int fillLength = 0;
        if (s != null) {
            if (s.trim().length() > length)
                return s.trim().substring(0, length);
            else
                fillLength = length - s.length();
        } else {
            fillLength = length;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < fillLength; i++) {
            sb.append("0");
        }
        if (s != null)
            return sb.append(s).toString();
        return sb.toString();
    }

    /**
     * 截取字符串指定长度，不足右补空格
     *
     * @param s
     * @param length
     * @return
     */
    public static String leftFillSpace(String s, int length) {
        int fillLength = 0;
        if (s != null) {
            if (s.trim().length() > length)
                return s.trim().substring(0, length);
            else
                fillLength = length - s.length();
        } else {
            fillLength = length;
        }
        StringBuffer sb = new StringBuffer();
        if (s != null)
            sb.append(s.trim());
        for (int i = 0; i < fillLength; i++) {
            sb.append(" ");
        }
        if (sb.length() > length)
            return sb.substring(0, length);
        return sb.toString();
    }

    /**
     * 去除字符串中的空格,换行,制表符
     *
     * @param s
     * @return
     */
    public static String trim(String s) {
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(s);
        String after = m.replaceAll("");
        return after;
    }

    public static String[] split(String s,String regex)
    {
        String[] sa=s.split(regex);
        return sa;
    }

    /**
     * 判断字符串是否为空或者空串
     * @param str
     * @return
     */
    public static boolean isBlank(String str)
    {
        if(str==null||str.length()==0)
            return true;
        int strLen=str.length();
        for(int i=0;i<strLen;i++)
        {
            if(!Character.isWhitespace(str.charAt(i)))
                return false;
        }
        return true;
    }

    public static boolean isNotBlank(String str)
    {
        return !isBlank(str);
    }


}

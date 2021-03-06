/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: RegexMatches.java
 * Author:   17040407
 * Date:     17-8-22 下午3:11
 * Description: 智能风控系统
 * History: //修改记录
 * <author> <time> <version> <desc>
 * 修改人姓名      修改时间      版本号        描述
 */

package regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17040407
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class RegexMatches {

    @Test
    public void test1()
    {
        String line="This order was placed for QT3000! OK?";
        String pattern="(\\D*)(\\d+)(.*)";

        Pattern r=Pattern.compile(pattern);

        Matcher m=r.matcher(line);
        System.out.println(m.groupCount());
    }
}

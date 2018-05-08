/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: nio.java
 * Author:   17040407
 * Date:     18-1-4 上午10:28
 * Description: 智能风控系统
 * History: //修改记录
 * <author> <time> <version> <desc>
 * 修改人姓名      修改时间      版本号        描述
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17040407
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class nio {

    public static void main(String[] args) throws IOException {
        {
            for(int i=0;i<10;i++){
                Path path = Files.createTempDirectory("");
                System.out.println(path);
            }
        }
    }
}

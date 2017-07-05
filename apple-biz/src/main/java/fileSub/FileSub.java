package fileSub;
import org.junit.Test;
import java.io.*;

/**
 * Created by 17040407 on 2017/7/5.
 */
public class FileSub {

    @Test
    public void sub() throws Exception {
        File file = new File("/opt/logs");

        File[] files = file.listFiles();

        BufferedReader br = null;
        BufferedWriter bw = null;

        for (File file1 : files) {
            if (file1.getName().startsWith("A1010001_MLS_CREDIT_INFO_D")) {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(file1), "UTF-8"));
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\opt\\logs\\A1010001_MLS_CREDIT_INFO_D_20170704_0003.csv"), "UTF-8"));

                String line;
                while ((line = br.readLine()) != null) {
                    String[] list = line.split(",");
                    String newLine = list[0] + "," + "0.0,0.0,0.0,0.0,0.0,0.0,0.0";

                    bw.write(newLine);
                    bw.newLine();
                    bw.flush();
                }
            }
        }

        bw.close();
        br.close();

    }
}

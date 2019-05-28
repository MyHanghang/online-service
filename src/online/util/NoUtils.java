package online.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自动生成学生学号
 */
public class NoUtils {

    public static String getNo() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String str = sdf.format(date);
        return str.substring(5);
    }

    public static String getManNo() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String str = sdf.format(date);
        return "CZ" + str.substring(6);
    }
}

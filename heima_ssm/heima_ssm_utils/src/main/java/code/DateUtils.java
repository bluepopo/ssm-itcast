package code;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**日期类型于字符串转换的工具类
 *
 */
public class DateUtils {

    /**
     *  日期转换成字符串
     */
    public static  String date2String(Date date,String patt){
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        String format = sdf.format(date);

        return format;
    }

    /**
     *  字符串转换成日期
     */
    public static  Date string2Date(String str,String patt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date parse = sdf.parse(str);
        return parse;
    }

}

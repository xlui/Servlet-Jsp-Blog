package style.dx.java.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	/**
	 * 将 Date 对象转换为格式化的 String
	 * @param date: 将被转换的 Date 对象
	 * @return `stringFormatDate`
	 */
	public static String getString(Date date) {
		return format.format(date);
	}

	/**
	 * 将格式化的 Format 对象转换成 Date 对象
	 * @param date 格式化的 String 对象
	 * @return Date 对象
	 * @throws ParseException String 格式不符合要求
	 */
	public static Date getDate(String date) throws ParseException {
		return format.parse(date);
	}
}

package style.dx.java.utils;

import java.io.UnsupportedEncodingException;

/**
 * 封装一些字符串常用的操作
 */
public class StringUtils {

	/**
	 * 检查字符串是否为空
	 * @param string 要检查的字符串
	 * @return 布尔返回值
	 */
	public static boolean isEmpty(String string) {
		return string == null || string.trim().equals("");
	}

	/**
	 * 剪切字符串
	 * @param string 原始字符串
	 * @param begin 开始
	 * @param end 结束
	 * @return 子串
	 */
	public static String cutString(String string, int begin, int end) {
		if (string.length() < end || string.length() < begin)
			return string;
		return string.substring(begin, end);
	}

	/**
	 * 字符串解码，解决 url 传输文件值出现的乱码问题
	 * @param string 原始字符串
	 * @return UTF-8 编码的字符串
	 * @throws UnsupportedEncodingException 解码出错
	 */
	public static String decode(String string) throws UnsupportedEncodingException {
		return new String(string.getBytes("ISO-8859-1"), "UTF-8");
	}
}

package util;

import java.util.List;

/**
 * 
 * @author Sun
 * @date 2018年3月23日 下午2:57:07
 */
public class AccessFlagsUtil {
	
	/**
	 * Array index.
	 */
	private static int num;
	
	/**
	 * Array.
	 */
	private static List<String> list = null;
	
	@SuppressWarnings("static-access")
	public AccessFlagsUtil(List<String> list, int num) {
		this.list = list;
		this.num = num;
	}
	
	/**
	 * Get access flag info.
	 * @return
	 */
	public int info() {
		String str = list.get(num) + list.get(++num);
		String info = "";
		char first = str.charAt(0);
		char second = str.charAt(1);
		char third = str.charAt(2);
		char fourth = str.charAt(3);
		if (fourth == '1') {
			info += "public ";
		}
		if (third == '1') {
			info += "final";
		}
		if (second == '2') {
			info += "interface";
		} else if (second == '4') {
			info += "abstract";
		}
		if (first == '1') {
			info += "synthetic";
		} else if (first == '2'){
			info += "annotation";
		} else if (first == '4') {
			info += "enum";
		}
		System.out.print(info);
		return num;
	}

}

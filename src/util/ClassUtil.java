package util;

import java.util.List;

import common.Common;

/**
 * 
 * @author Sun
 * @date 2018年3月20日 下午3:03:18
 */
public class ClassUtil {
	
	/**
	 * Array index.
	 */
	private static int num;
	
	/**
	 * Array.
	 */
	private static List<String> list;
	
	/**
	 * Constructor.
	 */
	@SuppressWarnings("static-access")
	public ClassUtil(List<String> list, int num) {
		this.list = list;
		this.num = num;
	}
	
	/**
	 * Get info.
	 * @return
	 */
	public int info() {
		classInfo();
		superClassInfo();
		interfaceInfo();
		return num;
	}
	
	/**
	 * This class name.
	 * @return
	 */
	private static String classInfo() {
		String str = list.get(++num) + list.get(++num);
		Integer id = Integer.parseInt(str, 16);
		String nameId = Common.CONSTANT_POOL.get(id - 1);
		String name = Common.CONSTANT_POOL.get(Integer.parseInt(nameId) - 1);
		System.out.println(name.trim() + ";");
		return str;
	}
	
	/**
	 * Super class name.
	 * @return
	 */
	private static String superClassInfo() {
		String str = list.get(++num) + list.get(++num);
		Integer id = Integer.parseInt(str, 16);
		String nameId = Common.CONSTANT_POOL.get(id - 1);
		String name = Common.CONSTANT_POOL.get(Integer.parseInt(nameId) - 1);
		System.out.println(name.trim() + "();");
		return str;
	}
	
	/**
	 * Interface name.
	 * @return
	 */
	private static String interfaceInfo() {
		String str = list.get(++num) + list.get(++num);
		Integer number = Integer.parseInt(str, 16);
		for (int i = 0; i < number; i++) {
			Integer id = Integer.parseInt(list.get(++num) + list.get(++num), 16);
			String nameId = Common.CONSTANT_POOL.get(id - 1);
			String name = Common.CONSTANT_POOL.get(Integer.parseInt(nameId) - 1).trim();
			System.out.print(name);
		}
		System.out.println();
		return str;
	}
	
	/**
	 * Check legality.
	 * @param list
	 * @return
	 */
	public static boolean isIllegalClass(List<String> list) {
		if (!checkSize(list)) {
			return false;
		}
		String str = "";
		for (int i = 0; i < 4; i++) {
			str += list.get(i);
		}
		return checkHead(str.toUpperCase());
	}
	
	private static boolean checkHead(String str) {
		return str.equals("CAFEBABE") ? true : false;
	}
	
	/**
	 * Chekc size.
	 * @param list
	 * @return
	 */
	private static boolean checkSize(List<String> list) {
		return list.size() > 10 ? true : false;
	}
	
}

package util;

import java.util.List;

import common.Common;

/**
 * 
 * @author Sun
 * @date 2018年3月20日 下午4:45:06
 */
public class ConstantPoolUtil {
	
	/**
	 * Size of constant pool.
	 */
	private static int size;
	
	/**
	 * Index of list.
	 */
	private static int num = 10;

	private static List<String> list = null;
	
	/**
	 * Constructor
	 * @param list
	 */
	@SuppressWarnings("static-access")
	public ConstantPoolUtil(List<String> list) {
		this.list = list;
	}
	
	/**
	 * Get basic info.
	 * @return
	 */
	public int info() {
		System.out.println("Constant pool size : " + size());
		constant();
		return num;
	}

	/**
	 * Get size of constant pool.
	 * @param list
	 */
	private static int size() {
		String str = list.get(8) + list.get(9);
		Integer thisSize = Integer.parseInt(str, 16);
		size = thisSize;
		return size;
	}

	/**
	 * Get basic info from content. #The number is from one#
	 * @param list
	 */
	private static void constant() {
		String str = "Constant pool:\n";
		for (int i = 1; i < size; i++) {
			Integer hex = Integer.parseInt(list.get(num), 16);
			str += "\t#" + i + " = ";
			switch (hex) {
				case 1: str += utf8().trim() + "\n"; break; 
				case 3: str += integer(); break;
				case 4: str += floatNumber(); break;
				case 5: str += longNumber(); break;
				case 6: str += doubleNumber(); break;
				case 7: str += classInfo(); break;
				case 8: str += string(); break;
				case 9: str += fieldref(); break;
				case 10: str += methodref(); break;
				case 11: str += interfaceMethod(); break;
				case 12: str += nameAndType(); break;
				default: break;
			}
		}
		System.out.println(str);
	}

	/**
	 * UTF-8 1
	 * @param list
	 * @return
	 */
	private static String utf8() {
		String str = "Utf8\t\t";
		Integer length = Integer.parseInt(list.get(++num) + list.get(++num), 16);
		int len = ++num + length;
		char[] ch = new char[len];
		int j = 0;
		for (; num < len; num++,j++) {
			int k = Integer.parseInt(list.get(num), 16);
			ch[j] = (char) k;
		}
		String content = String.valueOf(ch);
		Common.CONSTANT_POOL.add(content.trim());
		str += content;
		return str;
	}
	
	/**
	 * Integer 3
	 * @param num
	 * @param list
	 * @return
	 */
	private static String integer() {
		String str = "Integer\t\t";
		Integer index = getNumber(4);
		Common.CONSTANT_POOL.add(index.toString());
		str += index + "\n";
		num++;
		return str;
	}

	/**
	 * Float 4
	 * @return
	 */
	private static String floatNumber() {
		String str = "Float\t\t";
		String hex = "";
		int len = num + 4;
		num++;
		for (; num <= len; num++) {
			hex += list.get(num);
		}
		Float classInfo = HexFloatUtil.transform(hex);
		Common.CONSTANT_POOL.add(classInfo.toString());
		str += classInfo + "\n";
		return str;
	}
	
	/**
	 * Long 5
	 * @param list
	 * @return
	 */
	private static String longNumber() {
		String str = "Long\t\t";
		String hex = "";
		int len = num + 8;
		num++;
		for (; num <= len; num++) {
			hex += list.get(num);
		}
		Long classInfo = Long.parseLong(hex, 16);
		Common.CONSTANT_POOL.add(classInfo.toString());
		str += classInfo + "\n";
		return str;
	}
	
	/**
	 * Double 6
	 * @param list
	 * @return
	 */
	private static String doubleNumber() {
		String str = "Double\t\t";
		String hex = "";
		int len = num + 8;
		num++;
		for (; num <= len; num++) {
			hex += list.get(num);
		}
		Double classInfo = HexDoubleUtil.transform(hex);
		Common.CONSTANT_POOL.add(classInfo.toString());
		str += classInfo + "\n";
		return str;
	}
	
	/**
	 * Class info 7
	 * @param list
	 * @return
	 */
	private static String classInfo() {
		String str = "Class\t\t";
		Integer classInfo = getNumber(2);
		Common.CONSTANT_POOL.add(classInfo.toString());
		str += "#" + classInfo + "\n";
		num++;
		return str;
	}
	
	/**
	 * String index 8
	 * @param list
	 * @return
	 */
	private static String string() {
		String str = "String\t\t";
		Integer classInfo = getNumber(2);
		Common.CONSTANT_POOL.add(classInfo.toString());
		str += "#" + classInfo + "\n";
		num++;
		return str;
	}
	
	/**
	 * Fields 9
	 * @param list
	 * @return
	 */
	private static String fieldref() {
		String str = "Fieldref\t\t";
		Integer classInfo = getNumber(2);
		Integer nameAndTypeInfo = getNumber(2);
		Common.CONSTANT_POOL.add(classInfo + "." + nameAndTypeInfo);
		str += "#" + classInfo + "." + "#" + nameAndTypeInfo + "\n";
		num++;
		return str;
	}
	
	/**
	 * Method 10
	 * @param num
	 * @param list
	 * @return
	 */
	private static String methodref() {
		String str = "Methodref\t\t";
		Integer classInfo = getNumber(2);
		Integer nameAndTypeInfo = getNumber(2);
		Common.CONSTANT_POOL.add(classInfo + "." + nameAndTypeInfo);
		str += "#" + classInfo + "." + "#" + nameAndTypeInfo + "\n";
		num++;
		return str;
	}
	
	/**
	 * Interface 11
	 * @param list
	 * @return
	 */
	private static String interfaceMethod() {
		String str = "InterfaceMethodref\t\t";
		Integer classInfo = getNumber(2);
		Common.CONSTANT_POOL.add(classInfo.toString());
		str += "#" + classInfo + "\n";
		num++;
		return str;
	}
	
	/**
	 * Some name and type index 12
	 * @param list
	 * @return
	 */
	private static String nameAndType() {
		String str = "NameAndType\t";
		Integer classInfo = getNumber(2);
		Integer nameAndTypeInfo = getNumber(2);
		Common.CONSTANT_POOL.add(classInfo + "." + nameAndTypeInfo);
		str += "#" + classInfo + "." + "#" + nameAndTypeInfo + "\n";
		num++;
		return str;
	}

	private static Integer getNumber(int n) {
		String str = "";
		Integer i = 0;
		for (int j = 0; j < n; j++) {
			str += list.get(++num);
		}
		i = Integer.parseInt(str, 16);
		return i;
	}
	
}

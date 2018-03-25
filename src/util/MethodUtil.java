package util;

import java.util.List;

import common.Common;

/**
 * Get content from method. access_flags u2 1 name_index u2 1 descriptor_index
 * u2 1 attributes_count u2 1 attributes attribute_info attributes_count
 * 
 * @author Sun
 * @date 2018年3月25日 下午1:54:47
 */
public class MethodUtil {

	/**
	 * Index of array.
	 */
	private static int num;

	/**
	 * Array.
	 */
	private static List<String> list;

	/**
	 * Constructor.
	 * 
	 * @param num
	 * @param list
	 */
	@SuppressWarnings("static-access")
	public MethodUtil(int num, List<String> list) {
		this.num = num;
		this.list = list;
	}

	/**
	 * Enum of access flag info.
	 * 
	 * @author Sun
	 * @date 2018年3月25日 下午2:50:21
	 */
	private enum AccessFlag {
		NULL(0, ""), ACC_PUBLIC(1, "public "), ACC_PRIVTE(2, "private "), ACC_PROTECTED(4, "protected "), ACC_STATIC(8,
				"static "), ACC_FINAL(16, "final "), ACC_SYNCHRONIZED(32, "synchronized "), ACC_BRIDGE(64,
						"bridge "), ACC_VARARGS(128, "varargs "), ACC_NATIVE(256, "native "), ACC_ABSTRACT(1024,
								"abstract "), ACC_STRICT(2048, "strict "), ACC_SYNTHETIC(4096, "synthetic");
		private int index;
		private String info;

		private AccessFlag(int index, String info) {
			this.index = index;
			this.info = info;
		}

		public int getIndex() {
			return index;
		}

		public String getInfo() {
			return info;
		}

		public static String getInfoByIndex(int index) {
			for (AccessFlag flag : AccessFlag.values()) {
				if (flag.getIndex() == index) {
					return flag.getInfo();
				}
			}
			return "";
		}
	}

	/**
	 * Get all method content.
	 * 
	 * @return
	 */
	public int info() {
		methodInfo();
		return num;
	}

	private static void methodInfo() {
		int number = Integer.parseInt(list.get(++num) + list.get(++num), 16);
		String str = "";
		for (int i = 0; i < number; i++) {
			int accessFlag = getNum(2);
			str += AccessFlag.getInfoByIndex(accessFlag) + " ";
			System.out.println(str);
			int nameIndex = getNum(2);
			String name = Common.CONSTANT_POOL.get(nameIndex - 1);
			str += name + " ";
			int descriptIndex = getNum(2);
			String descriptor = Common.CONSTANT_POOL.get(descriptIndex - 1);
			str += descriptor + " ";
			int attributesCount = getNum(2);
			for (int j = 0; j < attributesCount; j++) {
				int index = getNum(2);
				String attribute = Common.CONSTANT_POOL.get(index - 1);
				if (attribute.equals("Code")) {
					str += attribute + ":\n";
					@SuppressWarnings("unused")
					int length = getNum(4);
					int maxStack = getNum(2);
					str += "stack=" + maxStack + ", ";
					int local = getNum(2);
					str += "locals=" + local + "\n";
					int codeLength = getNum(4);
					for (int n = 0; n < codeLength; n++) {
						int codeIndex = getNum(1);
						// 此处省略查表行为
						if (codeIndex == 42) {
							str += "\t" + n + ": " + "aload_0\n";
						}
						if (codeIndex == 177) {
							str += "\t" + n + ": " + "return\n";
						}
					}
				}
			}
		}
		System.out.println(str);
	}

	/**
	 * Get the number from list.
	 * 
	 * @param size
	 * @return
	 */
	private static Integer getNum(int size) {
		String content = "";
		for (int i = 0; i < size; i++) {
			content += list.get(++num);
		}
		return Integer.parseInt(content, 16);
	}

}

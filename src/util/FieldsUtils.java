package util;

import java.util.List;

import common.Common;

/**
 * Field table structures. access_flags u2 1. name_index u2 1. descriptor_index
 * u2 1. attributes_count u2 1. attribute attributes attributes_count.
 * 
 * @author Sun
 * @date 2018年3月25日 下午12:18:45
 */
public class FieldsUtils {

	/**
	 * Index of array.
	 */
	private static int num;

	/**
	 * Array.
	 */
	private static List<String> list;

	private enum AccessFlag {
		NULL(0, ""), ACC_PUBLIC(1, "public "), ACC_PRIVTE(2, "private "), ACC_PROTECTED(4, "protected "), ACC_STATIC(8,
				"static "), ACC_FINAL(16, "final "), ACC_VOLATILE(64, "volatile "), ACC_TRANSIENT(128,
						"transient "), ACC_SYNTHETIC(4096, "synthetic "), ACC_ENUM(16384, "enum");
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

	private enum Descriptor {
		B("B", "byte"), C("C", "char"), D("D", "double"), F("F", "float"), I("I", "int"), J("J", "long"), S("S",
				"short"), Z("Z", "boolen"), V("V", "void");
		private String flag;
		private String info;

		private Descriptor(String flag, String info) {
			this.flag = flag;
			this.info = info;
		}

		public String getFlag() {
			return flag;
		}

		public String getInfo() {
			return info;
		}

		public static String getInfoByFlag(String flag) {
			for (Descriptor d : Descriptor.values()) {
				if (flag.equals(d.getFlag())) {
					return d.getInfo();
				}
			}
			return "";
		}
	}

	/**
	 * Constructor
	 * 
	 * @param num
	 * @param list
	 */
	@SuppressWarnings("static-access")
	public FieldsUtils(int num, List<String> list) {
		this.num = num;
		this.list = list;
	}

	/**
	 * Files info.If info is null return space.
	 */
	public int info() {
		System.out.println(fieldInfoNotInMethod());
		return num;
	}

	/**
	 * Files info in class.
	 * 
	 * @return
	 */
	private static String fieldInfoNotInMethod() {
		String str = "";
		int number = Integer.parseInt(list.get(++num) + list.get(++num), 16);
		if (number != 0) {
			for (int i = 0; i < number; i++) {
				//
				int accessFlag = Integer.parseInt(list.get(++num) + list.get(++num), 16);
				int flagOne = accessFlag & 0x000F;
				str += AccessFlag.getInfoByIndex(flagOne).trim();
				int flagTwo = accessFlag & 0x00F0;
				str += AccessFlag.getInfoByIndex(flagTwo).trim();
				int flagThree = accessFlag & 0xF000;
				str += AccessFlag.getInfoByIndex(flagThree).trim();
				//
				int nameIndex = Integer.parseInt(list.get(++num) + list.get(++num), 16);
				//
				int descriptorIndex = Integer.parseInt(list.get(++num) + list.get(++num), 16);
				String descript = Common.CONSTANT_POOL.get(descriptorIndex - 1).trim();
				str += " " + Descriptor.getInfoByFlag(descript);
				str += " " + Common.CONSTANT_POOL.get(nameIndex - 1).trim() + " ";
				//
				int extraInfo = Integer.parseInt(list.get(++num) + list.get(++num), 16);
				if (extraInfo != 0) {
					// TODO
				}
			}
		}
		return str;
	}

}

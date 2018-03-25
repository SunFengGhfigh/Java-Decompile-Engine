package controller;

import java.util.List;

import util.AccessFlagsUtil;
import util.ClassUtil;
import util.ConstantPoolUtil;
import util.FieldsUtils;
import util.MethodUtil;
import util.ReadUtil;
import util.VersionUtil;

/**
 * 
 * @author Sun
 * @date 2018年3月20日 下午3:05:15
 */
public class Main {

	public static void main(String[] args) throws Exception {
		List<String> list = ReadUtil.readClassFile("E://Main.class");
		// List<String> list = ReadUtil.readClassFile("File Name");
		boolean isClass = ClassUtil.isIllegalClass(list);
		if (!isClass) {
			throw new Exception("Not a class file");
		}
		VersionUtil.getVersion(list);
		ConstantPoolUtil constantPool = new ConstantPoolUtil(list);
		int num = constantPool.info();
		AccessFlagsUtil accessFlagsUtil = new AccessFlagsUtil(list, num);
		num = accessFlagsUtil.info();
		ClassUtil classUtil = new ClassUtil(list, num);
		num = classUtil.info();
		FieldsUtils fieldsUtils = new FieldsUtils(num, list);
		num = fieldsUtils.info();
		MethodUtil methodUtil = new MethodUtil(num, list);
		methodUtil.info();
	}

}

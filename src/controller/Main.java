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
 * @date 2018Äê3ÔÂ20ÈÕ ÏÂÎç3:05:15
 */
public class Main {

	public static void main(String[] args) throws Exception {
		List<String> list = ReadUtil.readClassFile("E://Main.class");
		// List<String> list = ReadUtil.readClassFile("File Name");
		// 自行调用
	}

}

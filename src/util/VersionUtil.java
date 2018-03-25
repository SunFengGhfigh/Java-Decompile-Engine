package util;

import java.util.List;

import common.Version;

/**
 * 
 * @author Sun
 * @date 2018年3月20日 下午3:47:42
 */
public class VersionUtil {

	/**
	 * Get all version info.
	 * 
	 * @param list
	 */
	public static void getVersion(List<String> list) {
		String minorVersion = list.get(4) + list.get(5);
		String majorVersion = list.get(6) + list.get(7);
		Integer minor = Integer.parseInt(minorVersion, 16);
		Integer major = Integer.parseInt(majorVersion, 16);
		String majorVersionName = Version.getNameByVersion(major);
		System.out.println("Main Version : " + majorVersionName + " -> " + major + "." + minor);
	}

}

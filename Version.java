package common;

/**
 * 
 * @author Sun
 * @date 2018年3月20日 下午2:58:12
 */
public enum Version {

	/**
	 * Java's version begin with 45
	 */
	JDK1(45), JDK2(46), JDK3(47), JDK4(48), JDK5(49), JDK6(50), JDK7(51), JDK8(52), JDK9(53);

	/**
	 * JDK major version
	 */
	public Integer version;

	Version(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return this.version;
	}

	/**
	 * Get the version name by version index.
	 * 
	 * @param version
	 * @return
	 */
	public static String getNameByVersion(Integer version) {
		String name = "";
		for (Version v : Version.values()) {
			if (version.equals(v.getVersion())) {
				name = v.toString();
				break;
			}
		}
		return name;
	}

}

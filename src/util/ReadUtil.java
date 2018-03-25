package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sun
 * @date 2018年3月20日 下午3:37:03
 */
public class ReadUtil {

	/**
	 * 读取url相关地址的class文件，以16进制存储，置于list之中，将之返回
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static List<String> readClassFile(String url) throws IOException {
		File file = new File(url);
		InputStream inputStream = new FileInputStream(file);
		List<String> list = new ArrayList<>();
		while (true) {
			int a = inputStream.read();
			if (a == -1) {
				break;
			} else {
				String str = Integer.toHexString(a);
				if (str.length() == 1) {
					str = "0" + str;
				}
				list.add(str);
			}
		}
		inputStream.close();
		return list;
	}

}

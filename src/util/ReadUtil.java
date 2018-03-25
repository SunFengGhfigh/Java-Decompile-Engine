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
 * @date 2018��3��20�� ����3:37:03
 */
public class ReadUtil {

	/**
	 * ��ȡurl��ص�ַ��class�ļ�����16���ƴ洢������list֮�У���֮����
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

package util;

/**
 * Double-precision floating-point (16 -> 10)
 * 
 * @author Sun
 * @date 2018年3月21日 下午3:04:56
 */
public class HexDoubleUtil {

	private static final Long EXPONENTIAL_TEMPLE = 0x7FFL;

	/**
	 * 将十六进制双精度浮点数转化为十进制双精度浮点数 本段代码中因个人太懒，没有对局部变量命名进行优 化，看官们将就着看看
	 * 
	 * @param str
	 */
	public static Double transform(String str) {
		Long a = Long.parseUnsignedLong(str, 16);
		int sign = Integer.parseInt(str.substring(0, 1), 16) >> 3;
		int e = (int) ((a >> 52 & EXPONENTIAL_TEMPLE) - 1023);
		if (e >= 0) {
			long d = a & 0x001FFFFFFFFFFFFFL;
			// Calculate round number.
			long k = d >> (52 - e);
			double integerSum = (1 << e);
			int integerBinary = 0;
			for (int i = 0; i < e; i++) {
				integerBinary = (int) ((k >> i) % 2);
				integerSum += integerBinary * (2 << (i - 1));
			}
			// Calculate decimal.
			long j = 0x0L;
			for (int i = 0; i < 52 - e; i++) {
				j <<= 1;
				j |= 1;
			}
			long t = j & d;
			double detailSum = 0D;
			int binary = 0;
			for (int i = 52; i >= 52 - e; i--) {
				binary = (int) ((t >> (52 - i)) % 2);
				detailSum += binary * Math.pow(2, e - i);
			}
			double sum = integerSum + detailSum;
			return sign == 1 ? -sum : sum;
		} else {
			double detailSum = Math.pow(2, e);
			long t = a & 0x000F_FFFF_FFFF_FFFFL;
			int binary = 0;
			for (int i = 52; i > 0; i--) {
				binary = (int) ((t >> (52 - i)) % 2);
				detailSum += binary * Math.pow(2, e - i);
			}
			return sign == 1 ? -detailSum : detailSum;
		}
	}

}

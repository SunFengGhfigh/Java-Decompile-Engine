package util;

/**
 * 
 * @author Sun
 * @date 2018年3月22日 下午7:07:41
 */
public class HexFloatUtil {

	private static final Long SIGN_TEMPLE = 0xFFL;

	private static final Long EXPONENTIAL_TEMPLE = 0b0111_1111_1L;

	/**
	 * Single-precision floating-point (16 -> 10)
	 * 
	 * @param str
	 */
	public static float transform(String str) {
		Long a = Long.parseLong(str, 16);
		int sign = (int) (a >> 31 & SIGN_TEMPLE);
		int e = (int) ((a >> 23 & EXPONENTIAL_TEMPLE) - 127);
		if (e >= 0) {
			long d = a & 0b0000_0000_0111_1111_1111_1111_1111_1111L;
			// Cal round number.
			long k = d >> (23 - e);
			float integerSum = (1 << e);
			int integerBinary = 0;
			for (int i = 0; i < e; i++) {
				integerBinary = (int) ((k >> i) % 2);
				integerSum += integerBinary * (2 << (i - 1));
			}
			// Cal decimal.
			long j = 0x0L;
			for (int i = 0; i < 23 - e; i++) {
				j <<= 1;
				j |= 1;
			}
			long t = j & d;
			float detailSum = 0f;
			int binary = 0;
			for (int i = 23; i >= 0; i--) {
				binary = (int) ((t >> (23 - i)) % 2);
				detailSum += binary * Math.pow(2, e - i);
			}
			float sum = integerSum + detailSum;
			return sign == 1 ? -sum : sum;
		} else {
			float detailSum = (float) Math.pow(2, e);
			long t = a & 0b0000_0000_0111_1111_1111_1111_1111_1111L;
			int binary = 0;
			for (int i = 23; i > 0; i--) {
				binary = (int) ((t >> (23 - i)) % 2);
				detailSum += binary * Math.pow(2, e - i);
			}
			return sign == 1 ? -detailSum : detailSum;
		}
	}

}

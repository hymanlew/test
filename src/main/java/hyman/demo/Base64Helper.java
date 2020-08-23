package hyman.demo;

import org.springframework.util.Base64Utils;

public class Base64Helper {

	/**
	 * 编码
	 * @param byteArray
	 * @return
	 */
	public static String encode(byte[] byteArray) {
		
	    return new String(Base64Utils.encode(byteArray));
	}
	 
	/**
	 * 解码
	 * @param base64EncodedString
	 * @return
	 */
	public static byte[] decode(String base64EncodedString) {
		return Base64Utils.decode(base64EncodedString.getBytes());
	}
}

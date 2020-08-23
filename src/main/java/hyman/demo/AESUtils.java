package hyman.demo;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author qyf
 * @version 1.0
 *aes与c#互通的加密
 */
public class AESUtils {
	
	public static final String KEY_ALGORITHM = "AES";

	public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
	/**
	 * 加密方法
	 * @param data 需要加密的字符串
	 * @param key  加密的key
	 * @param iv   偏移量
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public static String encryptAES(String data,String key,String iv) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
             
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
 
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
              
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
 
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);
 
            return Base64Helper.encode(encrypted).trim();
 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	/**
	 * 解密
	 * @param data 需要解密的字符串
	 * @param key  解密的key
	 * @param iv  偏移量
	 * @return  解密后的字符串
	 * @throws Exception
	 */
	public static String decryptAES(String data,String key,String iv) throws Exception {
        try
        {
            byte[] encrypted1 = Base64Helper.decode(data);
              
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
              
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
 
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString.trim();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	/**
	 * 网站平台UK解密
	 * @param uk
	 * @param salt	盐
	 * @param iterativeCount  迭代次数
	 * @return
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	public static String ukAesDecrypt(String uk,String aesKey,String salt,int iterativeCount) throws Exception {
		SecretKeySpec key = getKey(aesKey, iterativeCount, salt);
		IvParameterSpec iv = getIv(aesKey, iterativeCount, salt);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		return new String(cipher.doFinal(hexStringToBytes(uk)), "utf-8");
	}
	
	/**
	 * 
	 * @param strKey
	 * @param iterativeCount
	 * @param salt
	 */
	public static SecretKeySpec getKey(String strKey, int iterativeCount, String salt) {
		try {
			byte[] k2 = PBKDF2.derive(strKey, salt, iterativeCount, 128);
			return  new SecretKeySpec(k2, 0, 32, KEY_ALGORITHM);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error initializing setKey class. Cause: " + e);
		}
	}

	public static IvParameterSpec getIv(String strKey, int iterativeCount, String salt) {
		try {
			byte[] b = salt.getBytes();
			return new IvParameterSpec(b);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error initializing setKey class. Cause: " + e);
		}
	}
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}
	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
	/**
	 * uk加密
	 * @param str  需要加密的字符串
	 * @param aesKey 加密key
	 * @param salt   加密盐
	 * @param iterativeCount  迭代次数
	 * @return
	 * @throws Exception
	 */
	public static String ukAesEncrypt(String str,String aesKey,String salt,int iterativeCount) throws Exception {
		SecretKeySpec key = getKey(aesKey, iterativeCount, salt);
		IvParameterSpec iv = getIv(aesKey, iterativeCount, salt);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		return bytesToHexString(cipher.doFinal(str.getBytes("utf-8")));
	}
	/**
	 * MD5加密
	 * 
	 * @return
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString().toUpperCase();
	}
	
}

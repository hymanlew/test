package hyman.utils2;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

/**
 * C#秘钥与Java互转
 */
public class CSharpOrJavaUtil {

	/**
	 * C#私钥转换成java私钥
	 */
	public static String privateKeyFromXml(String privateKeyXml) {
		privateKeyXml = privateKeyXml.replaceAll("\r", "").replaceAll("\n", "");

		KeyFactory keyFactory;
		try {
			String modulusXml = privateKeyXml.substring(privateKeyXml.indexOf("<Modulus>") + 9,
					privateKeyXml.indexOf("</Modulus>"));
			BigInteger modulus = new BigInteger(1, Base64.getDecoder().decode(modulusXml));

			String publicExponentXml = privateKeyXml.substring(privateKeyXml.indexOf("<Exponent>") + 10,
					privateKeyXml.indexOf("</Exponent>"));
			BigInteger publicExponent = new BigInteger(1, Base64.getDecoder().decode(publicExponentXml));

			String privateExponentXml = privateKeyXml.substring(privateKeyXml.indexOf("<D>") + 3,
					privateKeyXml.indexOf("</D>"));
			BigInteger privateExponent = new BigInteger(1, Base64.getDecoder().decode(privateExponentXml));

			String primePXml = privateKeyXml.substring(privateKeyXml.indexOf("<P>") + 3, privateKeyXml.indexOf("</P>"));
			BigInteger primeP = new BigInteger(1, Base64.getDecoder().decode(primePXml));

			String primeQXml = privateKeyXml.substring(privateKeyXml.indexOf("<Q>") + 3, privateKeyXml.indexOf("</Q>"));
			BigInteger primeQ = new BigInteger(1, Base64.getDecoder().decode(primeQXml));

			String primeExponentPXml = privateKeyXml.substring(privateKeyXml.indexOf("<DP>") + 4,
					privateKeyXml.indexOf("</DP>"));
			BigInteger primeExponentP = new BigInteger(1, Base64.getDecoder().decode(primeExponentPXml));

			String primeExponentQXml = privateKeyXml.substring(privateKeyXml.indexOf("<DQ>") + 4,
					privateKeyXml.indexOf("</DQ>"));
			BigInteger primeExponentQ = new BigInteger(1, Base64.getDecoder().decode(primeExponentQXml));

			String crtCoefficientXml = privateKeyXml.substring(privateKeyXml.indexOf("<InverseQ>") + 10,
					privateKeyXml.indexOf("</InverseQ>"));
			BigInteger crtCoefficient = new BigInteger(1, Base64.getDecoder().decode(crtCoefficientXml));

			RSAPrivateCrtKeySpec rsaPriKey = new RSAPrivateCrtKeySpec(modulus, publicExponent, privateExponent, primeP,
					primeQ, primeExponentP, primeExponentQ, crtCoefficient);
			
			keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory.generatePrivate(rsaPriKey);
			byte[] bytes = Base64.getEncoder().encode(privateKey.getEncoded());
			return new String(bytes, Charset.forName("utf-8"));
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return null;
	}

	/**
	 * C#公钥转换成java公钥
	 */
	public static String publicKeyFromXml(String publicKeyXml) {
		KeyFactory keyFactory;
		publicKeyXml = publicKeyXml.replaceAll("\r", "").replaceAll("\n", "");
		try {
			String modulusXml = publicKeyXml.substring(publicKeyXml.indexOf("<Modulus>") + 9,
					publicKeyXml.indexOf("</Modulus>"));
			BigInteger modulus = new BigInteger(1, Base64.getDecoder().decode(modulusXml));

			String exponentXml = publicKeyXml.substring(publicKeyXml.indexOf("<Exponent>") + 10,
					publicKeyXml.indexOf("</Exponent>"));
			BigInteger publicExponent = new BigInteger(1, Base64.getDecoder().decode(exponentXml));

			RSAPublicKeySpec rsaPubKey = new RSAPublicKeySpec(modulus, publicExponent);
			keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(rsaPubKey);
			byte[] bytes = Base64.getEncoder().encode(publicKey.getEncoded());
			return new String(bytes, Charset.forName("utf-8"));
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

	/**
	 * java私钥转换成C#私钥
	 */
	public static String privateKeyToXml(RSAPrivateCrtKey privateKey) {

		String modulusBase64 = Base64.getEncoder().encodeToString(privateKey.getModulus().toByteArray());
		String modulus = modulusBase64.replace("\r", "").replace("\n", "");

		String exponentBase64 = Base64.getEncoder().encodeToString(privateKey.getPublicExponent().toByteArray());
		String exponent = exponentBase64.replace("\r", "").replace("\n", "");

		String pBase64 = Base64.getEncoder().encodeToString(privateKey.getPrimeP().toByteArray());
		String p = pBase64.replace("\r", "").replace("\n", "");

		String qBase64 = Base64.getEncoder().encodeToString(privateKey.getPrimeQ().toByteArray());
		String q = qBase64.replace("\r", "").replace("\n", "");

		String dpBase64 = Base64.getEncoder().encodeToString(privateKey.getPrimeExponentP().toByteArray());
		String dp = dpBase64.replace("\r", "").replace("\n", "");

		String dqBase64 = Base64.getEncoder().encodeToString(privateKey.getPrimeExponentQ().toByteArray());
		String dq = dqBase64.replace("\r", "").replace("\n", "");

		String dBase64 = Base64.getEncoder().encodeToString(privateKey.getPrivateExponent().toByteArray());
		String d = dBase64.replace("\r", "").replace("\n", "");

		String inverseQBase64 = Base64.getEncoder().encodeToString(privateKey.getCrtCoefficient().toByteArray());
		String inverseQ = inverseQBase64.replace("\r", "").replace("\n", "");

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<RSAKeyValue>\n");
		stringBuilder.append("<Modulus>").append(modulus).append("</Modulus>\n");
		stringBuilder.append("<Exponent>").append(exponent).append("</Exponent>\n");
		stringBuilder.append("<P>").append(p).append("</P>\n");
		stringBuilder.append("<Q>").append(q).append("</Q>\n");
		stringBuilder.append("<DP>").append(dp).append("</DP>\n");
		stringBuilder.append("<DQ>").append(dq).append("</DQ>\n");
		stringBuilder.append("<InverseQ>").append(inverseQ).append("</InverseQ>\n");
		stringBuilder.append("<D>").append(d).append("</D>\n");
		stringBuilder.append("</RSAKeyValue>");
		return stringBuilder.toString();
	}

	/**
	 * java公钥转换成C#公钥
	 */
	public static String publicKeyToXml(RSAPublicKey publicKey) {
		String modulusBase64 = Base64.getEncoder().encodeToString(publicKey.getModulus().toByteArray());
		String modulus = modulusBase64.replace("\r", "").replace("\n", "");

		String exponentBase64 = Base64.getEncoder().encodeToString(publicKey.getPublicExponent().toByteArray());
		String exponent = exponentBase64.replace("\r", "").replace("\n", "");

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<RSAKeyValue>\n");
		stringBuilder.append("<Modulus>").append(modulus).append("</Modulus>\n");
		stringBuilder.append("<Exponent>").append(exponent).append("</Exponent>\n");
		stringBuilder.append("</RSAKeyValue>");
		return stringBuilder.toString();
	}

	public static void main(String[] args) {
		String privateXml = "<RSAKeyValue>\n"
				+ "  <Modulus>pJlJIR0+G/vs7mhycs0Zd5DbWokMCsLX7Ih373+ljlteRmTm8IHj0Sl8zA26Qu/oAmqh9OFk806zW3TXl90RghSeQhzZBXFHBrTFN4U/o3wPhZBdFazh80bbskrmQ/jmz70qUm3HmGzsb3toxzN6Ehakdo7qP2Kc6aOVTyXrYpk=</Modulus>\n"
				+ "  <Exponent>AQAB</Exponent>\n"
				+ "  <P>1WKkFg6jlS2eZSVLiH1CK4b9uhr7qE1/L+tPqKQs+PzHX4eiZXnyAlQ+kgGmL7n1wR1RWNuiYnDZUetmTogaDQ==</P>\n"
				+ "  <Q>xXhsuEnNJNQcQHEVTPZoulbMbTV1VZIDQ1zjG8fvu8sv6IBYcR5+EsC8n3/6RkW8/iCJDzxE++VHzhoSQSoDvQ==</Q>\n"
				+ "  <DP>zr/rcn+umd0Aisnu/Ik48smxz39TdIfaAwkBPsoL1Re+6V2WyLG1/fG4SmmUpsuMRRdt+SWdmbnzpr7peo++hQ==</DP>\n"
				+ "  <DQ>MWi2W04sBEEGaKFi4QTuo2FAeTrdBvIn2t0M/lCCjYyDijtC5drpVKvhBk+xQZAFf9iIMsWzxQtTciBX3PI0SQ==</DQ>\n"
				+ "  <InverseQ>wGEfA3LWM4NHgRnXDqwwOUs3OtqWK0tsJPPcFMci+Bcgy96JpFTCr7bubXHMu14bdopCWUann2d3UuwEpvP+</InverseQ>\n"
				+ "  <D>mK3TRtMwRJb33OGnn9OeFumYfy92qxi3X6Hq1o6qDBW2qkd4bImfv+ni6AinyOVuaadt2Y+lq4dKGcCVJzoZvPm1VKxD2y7xKa8/vEbPRiRTt0qnPq9T7UJkpDsiXf/zOMfWdjc3uA1bPnQ65RWHSJ7zAE+Gd7xnyCE5MEyijLE=</D>\n"
				+ "</RSAKeyValue>";
		System.out.println(privateKeyFromXml(privateXml));
	}

}

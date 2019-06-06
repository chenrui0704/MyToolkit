package pers.cr.toolkit.util;


import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;


public class DESEncryptUtils {

	private final static String SECRETKEY = "mobilewinx@easipass@1234";

	private final static String IV = "01234567";

	private final static String ENCODING = "utf-8";

	/**
	 * 创建时间: 2019年3月15日 16:54:54
	 * 创建人: cr
	 * 信息 : 加密
	 *
	 * @param plainText
	 * @return
	 */
	public static String encode(String plainText) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(SECRETKEY.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(IV.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] encryptData = cipher.doFinal(plainText.getBytes(ENCODING));
		return Base64Utils.encode(encryptData);
	}

	/**
	 * 创建时间: 2019年3月15日 16:54:54
	 * 创建人: cr
	 * 信息 : 解密
	 *
	 * @param encryptText
	 * @return
	 */
	public static String decode(String encryptText) throws Exception {
		if (encryptText.equals("")) {
			return encryptText;
		} else {
			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(SECRETKEY.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory
					.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(IV.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
			byte[] decryptData = cipher.doFinal(Base64Utils.decode(encryptText));
			return new String(decryptData, ENCODING);
		}
	}

}

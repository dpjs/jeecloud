package com.jeecloud.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * <p>
 * 类名
 * </p>
 * aes加密/解密 此处使用AES-128-ECB加密模式，key需要为16位。
 * @author Administrator
 * @version 1.0
 */
public class AesUtil {
	/**
	 * aesKey 
	 */
	public static String cKey = "?[JEECYohgew/.?|";
	/**
	 * <p>
	 * 方法名
	 * </p>
	 * 加密
	 * @param sSrc 加密字符串
	 * @param sKey 加密密钥
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String sSrc, String sKey) throws Exception {
		if (sKey == null) {
			System.out.print("Key为空null");
			return null;
		}
		int length = 16;
		// 判断Key是否为16位
		if (sKey.length() != length) {
			System.out.print("Key长度不是16位");
			return null;
		}
		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		// "算法/模式/补码方式"
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		IvParameterSpec iv = new IvParameterSpec("alwi2hvnaz.s923k".getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes());
		// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
		return Base64Util.encode(encrypted);
	}
	/**
	 * <p>
	 * 方法名
	 * </p>
	 * 解密
	 * @param sSrc 解密文件
	 * @param sKey 解密密钥
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String sSrc, String sKey) throws Exception {
		try {
			// 判断Key是否正确
			if (sKey == null) {
				System.out.print("Key为空null");
				return null;
			}
			int length = 16;
			// 判断Key是否为16位
			if (sKey.length() != length) {
				System.out.print("Key长度不是16位");
				return null;
			}
			byte[] raw = sKey.getBytes("UTF-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec("alwi2hvnaz.s923k".getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			// 先用base64解密
			byte[] encrypted1 = Base64Util.decode(sSrc);
			try {
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original);
				return originalString;
			} catch (Exception e) {
				System.out.println(e.toString());
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}
	
	
}

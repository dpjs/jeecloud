package com.jeecloud.common.util;

/**
 * 
 * JAVA 版的BASE64加密
 * 
 * 标准的Base64加密后的字符串长度可以被4整出,比如:
 * 字符		加密后
 * a		YQ==
 * aa		YWE=
 * aaa		YWFh
 * aaab		YWFhYg==
 * aaabb	YWFhYmI=
 * aaabbb	YWFhYmJi
 * 
 * 一旦加密后的字符能被4整除，就用"="来补充
 * 
 * 本例是在网上找的，原本是标准的，遵照RFC2045～RFC2049规范。
 * 
 * PHP版的BASE64可以解密标准的BASE64密文，也可以解密不带后边"="的密文，为了能解密PHP的BASE64和AuthCode加密。
 * （PHP的AuthCode中也用到了BASE64）我将本类做了修改，使之也可以解密不带"="的BASE64密文。
 * 原理很简单，将密文对4取模（"密文"%4），看差即为就补几个"="，然后就得到一个标准的BASE64密文并解之。
 * 
 * @author by yue
 * @date 2012 11 13
 * 
 * 
 */
import java.io.UnsupportedEncodingException;

/**
 * 
 * @ClassName: Base64Util 
 * @Description: base64工具类
 * @author: admin
 * @date: 2018年3月15日 上午11:25:39
 */
public class Base64Util {
	private static char[] base64EncodeChars = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1',
			'2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
	private static byte[] base64DecodeChars = new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4,
			5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26,
			27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1,
			-1, -1, -1 };

	public static String encode(byte[] data) {
		StringBuffer sb = new StringBuffer();
		int len = data.length;
		int i = 0;
		int b1, b2, b3;
		while (i < len) {
			b1 = data[i++] & 0xff;
			if (i == len) {
				sb.append(base64EncodeChars[b1 >>> 2]);
				sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
				sb.append("==");
				break;
			}
			b2 = data[i++] & 0xff;
			if (i == len) {
				sb.append(base64EncodeChars[b1 >>> 2]);
				sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
				sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
				sb.append("=");
				break;
			}
			b3 = data[i++] & 0xff;
			sb.append(base64EncodeChars[b1 >>> 2]);
			sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
			sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
			sb.append(base64EncodeChars[b3 & 0x3f]);
		}
		return sb.toString();
	}
	public static byte[] decode(String str) throws UnsupportedEncodingException {
		// 对4取模后的余数
		int remainder = str.length() % 4;
		int temp = 2;
		if (remainder == temp) {
			str = str + "==";
		} else if (remainder == temp + 1) {
			str = str + "=";
		}
		StringBuffer sb = new StringBuffer();
		byte[] data = str.getBytes("US-ASCII");
		int len = data.length;
		int i = 0;
		int b1, b2, b3, b4;
		while (i < len) {
			do {
				b1 = base64DecodeChars[data[i++]];
			} while ((i < len) && (b1 == -1));
			if (b1 == -1) {
				break;
			}
			do {
				b2 = base64DecodeChars[data[i++]];
			} while ((i < len) && (b2 == -1));
			if (b2 == -1) {
				break;
			}
			sb.append((char) ((b1 << 2) | ((b2 & 0x30) >>> 4)));
			do {
				b3 = data[i++];
				if (b3 == 61) {
					return sb.toString().getBytes("iso8859-1");
				}
				b3 = base64DecodeChars[b3];
			} while ((i < len) && (b3 == -1));
			if (b3 == -1) {
				break;
			}
			sb.append((char) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));
			do {
				b4 = data[i++];
				if (b4 == 61) {
					return sb.toString().getBytes("iso8859-1");
				}
				b4 = base64DecodeChars[b4];
			} while ((i < len) && (b4 == -1));
			if (b4 == -1) {
				break;
			}
			sb.append((char) (((b3 & 0x03) << 6) | b4));
		}
		return sb.toString().getBytes("iso8859-1");
	}
	public static void main(String[] args) throws UnsupportedEncodingException {
		String s = "abcd";
		System.out.println("加密前：" + s);
		String x = encode(s.getBytes());
		System.out.println("加密后：" + x);
		System.out.println("解密后：" + new String(decode("YWFhYmJiY2NjZA===")));
		System.out.println(encode("abcde".getBytes()));
	}
}

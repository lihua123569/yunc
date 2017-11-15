package com.yunc.upms.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * 使用DES加密与解密,可对byte[],String类型进行加密与解密 密文可使用String,byte[]存储.
 * 
 * 方法: void getKey(String strKey)从strKey的字条生成一个Key
 * 
 * String getEncString(String strMing)对strMing进行加密,返回String密文 String
 * getDesString(String strMi)对strMin进行解密,返回String明文
 * 
 * byte[] getEncCode(byte[] byteS)byte[]型的加密 byte[] getDesCode(byte[]
 * byteD)byte[]型的解密
 */

public class DesEncrypter {
	private static Logger log = LoggerFactory.getLogger(DesEncrypter.class);

	
	private static final String Algorithm = "DESede"; // 定义 加密算法,可用
	private static final String key = "A314BA5A3C85E86KK887WSWS";// DES,DESede,

	// Blowfish

	// keybyte为加密密钥，长度为24字节
	// src为被加密的数据缓冲区（源）
	public static byte[] encryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

			// 加密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			log.info("加密失败",e1);
		} catch (javax.crypto.NoSuchPaddingException e2) {
			log.info("加密失败",e2);
		} catch (java.lang.Exception e3) {
			log.info("加密失败",e3);
		}
		return null;
	}

	// keybyte为加密密钥，长度为24字节
	// src为加密后的缓冲区
	public static byte[] decryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

			// 解密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			log.info("解密失败",e1);
		} catch (javax.crypto.NoSuchPaddingException e2) {
			log.info("解密失败",e2);
		} catch (java.lang.Exception e3) {
			log.info("解密失败",e3);
		}
		return null;
	}

	// 转换成十六进制字符串
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";

		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;

		}
		return hs.toUpperCase();
	}

	public static byte[] getEncCode(byte[] szSrc) {
		// 添加新安全算法,如果用JCE就要把它添加进去
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		final byte[] keyBytes = key.getBytes();
		return encryptMode(keyBytes, szSrc);
	}

	/**
	 * 加密String明文输入,String密文输出
	 * 
	 * @param strMing
	 * @return
	 */
	public static String getEncryptString(String strMing) {
		BASE64Encoder base64en = new BASE64Encoder();
		// return
		// URLEncoder.encode(base64en.encode(getEncCode(strMing.getBytes())));
		return base64en.encode(getEncCode(strMing.getBytes()));
	}

	/**
	 * 解密 以String密文输入,String明文输出
	 * 
	 * @param strMi
	 * @return
	 */
	public static String getDesString(String strMi) {
		BASE64Decoder base64De = new BASE64Decoder();
		byte[] byteMing = null;
		byte[] byteMi = null;
		String strMing = "";
		try {
			byteMi = base64De.decodeBuffer(strMi);
			byteMing = decryptMode(key.getBytes(), byteMi);
			strMing = new String(byteMing, "UTF8");
		} catch (Exception e) {
			log.info("String解密失败",e);
		} finally {
			base64De = null;
			byteMing = null;
			byteMi = null;
		}
		return strMing;
	}

	/**
	 * 解密 以String密文输入,String明文输出
	 * 
	 * @param strMi
	 * @return
	 */
	public static String getDecryptString(String strMi) {
		strMi = new String(getDesString(strMi).getBytes());
		return strMi;
	}

	/**
	 * 将s进行SHA编码
	 * 
	 * @return
	 */
	public static byte[] getSHA(String s) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			log.info("将s进行SHA编码",e);
		}
		md.update(s.getBytes());
		return md.digest();

	}

	/**
	 * 将 s 进行 BASE64 编码
	 * 
	 * @return
	 * 
	 */
	public static String getBASE64(byte[] s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s);
	}

	/**
	 * 将 BASE64 编码的字符串 s 进行解码
	 * 
	 * @return
	 */
	public static String getFromBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		String strEnc = "XE3VY2gpivp6oq4H5bZGTMBBuqKQ9z9XigzsJ%2bqxVawaPuNzdkLFUqtLXjDldxoqJK0SaMf74F8qFwWzcsgNUbxF9xhdcIHWgjFyu3Nvvo2%2bHsd9eDf25o3BEqQFt1jNtBbkPiXgy%2b1tpdfVQD14maO4TcS6NXxU";
		String str = "W9Dv9bfHb6Nc01dLfT69H40HnNmcJw6vgfq0rVHyrT0SgLfOedR0TepMA6LMRCJ%2B1DsEj4k4uCYo%0AvBAVIduznnUHGnwTKbBMFZPPnbIJ2IOdyXg1P0r1Hw%3D%3D";
//		log.info("nihao:" + getDecryptString(str));
		getStr(str);
//		log.info("getStr:" + getDecryptString(str));
		// strEnc = URLDecoder.decode(strEnc);
		strEnc = DesEncrypter.getFromBASE64(strEnc);
		// strEnc=DesEncrypter.getDecryptString(strEnc);
		// String temp= DesEncrypter.byte2hex(DesEncrypter.getSHA(strEnc));
		// DesEncrypter.getBASE64(temp.getBytes());

		// strEnc=DesEncrypter.getBASE64(DesEncrypter.getEncryptString(strEnc).
		// getBytes());
		// strEnc=URLEncoder.encode(strEnc);
		// strEnc=URLEncoder.encode(strEnc);
		// log.info("加密数据："+strEnc);
		strEnc = "DD7B2729E7B35789A831987BC396E4ED";
		strEnc = DesEncrypter.getDecryptString(DesEncrypter.getFromBASE64(strEnc));
		//log.info("解密数据：" + strEnc);

	}

	public static void getStr(String loginCheckValue) {
		// 转码
		try {
			loginCheckValue = URLDecoder.decode(loginCheckValue, "utf-8");
			// 解密
			String ming = DesEncrypter.getDecryptString(loginCheckValue);
			// split
			if (ming != null) {
				String[] arr = ming.split("##");
				// userBaseBean.setNickname(URLDecoder.decode(arr[5], "UTF-8"));
				for (int i = 0; i < arr.length; i++) {
//					log.info(URLDecoder.decode(arr[i], "UTF-8"));
				}
			}
		} catch (UnsupportedEncodingException e) {
			log.info(" 转码  解密",e);
		}
	}

}

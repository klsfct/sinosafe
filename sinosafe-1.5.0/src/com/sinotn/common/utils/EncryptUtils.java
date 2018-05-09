package com.sinotn.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.sinotn.common.exception.EncryptException;


/**
 * 工具类: 加密-提供DES加密解密,MD5加密
 */

public class EncryptUtils {
	public final static String password = "l1o987vet";
	
	public static void main(String[] args){
		System.out.print(EncryptUtils.decryptAES("7C12CD13FF0300756D4E5E8569C49757"));
	}
	/**
	 * 加密
	 * @param content 需要加密的内容
	 * @param password 加密密码
	 * @return
	 */
	public static String encryptAES(String content) {
		if(content == null) return null;
		if(content.equals("")) return "";
		try {
			//得到一个使用AES算法的KeyGenerator的实例；
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			//通过KeyGenerator产生一个key(密钥算法已定义，为AES)
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			//将加密字符串转成成utf8的字节数组
			byte[] byteContent = content.getBytes("utf-8");
			//使用私钥加密,把刚才生成的key当作参数，初始化使用刚才获得的私钥加密类，Cipher.ENCRYPT_MODE意思是加密
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			//将加密字节转成成16进制字符串
			return parseByte2HexStr(result); // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new EncryptException("nvce.err");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			throw new EncryptException("nvce.err");
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			throw new EncryptException("nvce.err");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new EncryptException("nvce.err");
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			throw new EncryptException("nvce.err");
		} catch (BadPaddingException e) {
			e.printStackTrace();
			throw new EncryptException("nvce.err");
		}
	}
	
	/**
	 * 解密
	 * @param content 待解密内容
	 * @param password 解密密钥
	 * @return
	 * @throws  
	 */
	public static String decryptAES(String content){
		if(content == null) return null;
		if(content.equals("")) return "";
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(parseHexStr2Byte(content));
			try{
				String a=  new String(result,"UTF8"); // 加密
				return a;
			}catch(UnsupportedEncodingException ex){
				ex.printStackTrace();
				throw new EncryptException("nvce.err");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new EncryptException("nvce.err");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			throw new EncryptException("nvce.err");
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			throw new EncryptException("nvce.err");
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			throw new EncryptException("nvce.err");
		} catch (BadPaddingException e) {
			e.printStackTrace();
			throw new EncryptException("nvce.err");
		}catch(Exception e){
			e.printStackTrace();
			throw new EncryptException("nvce.err");
		}
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	private static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	private static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}

package com.th.ams.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

/**
 * @author : wangtianhua
 * @date : 2019/08/14 0:23
 * @description : DOTO
 */
public class GenerateAuthorizationCode {

	private static final String publicKeyFileName = "e:" + File.separator + "public-rsa.der";
	private static final String privateKeyFileName = "e:" + File.separator + "user-rsa.pfx";
	// 私钥文件获取时设置的密钥
	private static final String pfxPassword = "20152180201";
	// alias名称
	private static String aliasName = "th";

	public static void main(String[] a) throws Exception {
		test0();
		System.out.println("-------------------------------------------------------------------------------------");
	}

	public static void test0() throws Exception {
		String keyStr = RandomString.getRandomString(245);
		System.out.println("要加密的245位key的字符串：" + keyStr);
		byte[] key = keyStr.getBytes();
		String data = "{\"id\":null,\"projectId\":null,\"serverCodes\":[\"92000747\",\"89569074\",\"06289034\"],\"sn\":\"SN190709580451\",\"state\":2,\"stateDescription\":\"生效中\",\"type\":0,\"typeDescription\":\"临时授权\",\"featureExpireDate\":1577808000000,\"tokenExpireDate\":1577808000000,\"tenantCount\":999999999,\"portalTerminalConcurrency\":999999999,\"userCount\":999999999,\"userTerminalCount\":999999999,\"userType\":0,\"portalUserEnabled\":true,\"simpleVisitorTerminalCount\":999999999,\"oneKeyPortalEnabled\":true,\"smsPortalEnabled\":true,\"weixinPortalEnabled\":true,\"facebookPortalEnabled\":true,\"emailPortalEnabled\":true,\"enhancedVisitorTerminalCount\":999999999,\"visitorSelfHelpEnabled\":true,\"qrCodeGuestEnabled\":true,\"weChatWorkPortalEnabled\":true,\"dingTalkPortalEnabled\":true,\"externalPortalEnabled\":true,\"businessVisitorGrantType\":1,\"externalAuthTerminalCount\":999999999,\"externalAuthApCount\":999999999,\"externalAuthType\":0,\"appAuthEnabled\":true,\"appAuthTerminalCount\":999999999,\"auth8021xEnabled\":true,\"ipAssignmentEnabled\":true,\"advancedPoliciesEnabled\":true,\"reportEnabled\":true,\"wlanAccessServerCount\":999999999,\"strongAccessServerCount\":999999999,\"smsTokenCount\":999999999,\"mobileTokenCount\":999999999,\"hardwareTokenCount\":999999999,\"haEnabled\":true,\"auditEnabled\":true,\"techSupportEnabled\":true,\"techSupportType\":1,\"techSupportTypeDescription\":\"待审核\",\"techSupportState\":2,\"techSupportStateDescription\":\"技术服务中\",\"techSupportExpireDate\":1577808000000,\"techSupportUpdateTime\":1577808000000,\"createTime\":1565712000000,\"updateTime\":1565798400000,\"companyName\":\"05891128\",\"otherInfo\":null,\"apCount\":999999999,\"tacacsEnabled\":true,\"techSupportExpiredDate\":1577808000000,\"featureExpiredDate\":1577808000000,\"tokenExpiredDate\":1577808000000}";
		String encryptLicense = encryptLicense(key,data);
		System.out.println("License加密后的十六进制：" + encryptLicense);
		System.out.println("License加密后的十六进制长度：" + encryptLicense.length());
		System.out.println("-------------------------------------------------------------------------------------");

		byte[] encryptRSA = encryptRSA(key);
		System.out.println("key 通过RSA加密后：" + new String(encryptRSA,"utf-8"));
		System.out.println("-------------------------------------------------------------------------------------");

		String sn = "SN190709580451";
		String encryptKey = encryptKey(sn,encryptRSA);
		System.out.println("RSA加密后的 key 再通过AES加密后十六进制：" +  new String(encryptKey));
		System.out.println(encryptKey.length());
		System.out.println("-------------------------------------------------------------------------------------");

		byte[] decryptKey = decryptKey(sn,encryptKey);
		System.out.println(" key 通过 AES 解密 RSA 加密的 key 得到RSA加密的key：" + new String(decryptKey,"utf-8"));
		System.out.println("-------------------------------------------------------------------------------------");

		byte[] decodeRSA = decodeRSA(decryptKey);
		System.out.println("通过 RSA 解密 AES 解密的key（就是RSA加密后key的值）：" + new String(decodeRSA));
		System.out.println("-------------------------------------------------------------------------------------");

		String decryptLicense = decryptLicense(decodeRSA,encryptLicense);
		System.out.println("license解密得到原始数据：" + decryptLicense);
		System.out.println("-------------------------------------------------------------------------------------");

		String json = enerateKeyJson(encryptKey,encryptLicense);
		String base64 = base64Encode(json);
		System.out.println("通过base64编码生成授权码：" + base64);
		System.out.println("base64编码后的长度：" + base64.length());
	}

	/**
	 * 功能描述: <br>
	 * 生成 json 密钥
	 * @author : wangtianhua
	 * @date : 2019/08/14 21:27
	 * @params : [key, license]
	 * @return : java.lang.String
	 */
	public static String enerateKeyJson(String key, String license){
		// 按顺序插入
		JSONObject json = new JSONObject(true);
		json.put("key",key);
		json.put("license",license);
		return json.toJSONString(json);
	}

	/**
	 * 功能描述: <br>
	 * 生成授权码
	 * @author : wangtianhua
	 * @date : 2019/08/14 21:27
	 * @params : [sourceData]
	 * @return : java.lang.String
	 */
	public static String base64Encode(String sourceData){
		byte[] bt = sourceData.getBytes();
		return Base64.encodeBase64String(bt);
	}

	/**
	 * 功能描述: <br>
	 * 通过私钥文件进行加密数据
	 * @author : wangtianhua
	 * @date : 2019/08/15 19:25
	 * @params : [source]
	 * @return : java.lang.String 加密后经过base64处理的字符串
	 */
	public static byte[] encryptRSA(byte[] source) throws Exception {
		InputStream fis = null;
		try {
			fis = new FileInputStream(privateKeyFileName);
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			char[] pscs = pfxPassword.toCharArray();
			keyStore.load(fis, pscs);
			PrivateKey priKey = (PrivateKey) (keyStore.getKey(aliasName, pscs));
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, priKey);
			byte[] epByte = cipher.doFinal(source);
			//String epStr = Base64.encodeBase64String(epByte);
			return epByte;
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 功能描述: <br>
	 * 通过公钥文件进行解密数据
	 * @author : wangtianhua
	 * @date : 2019/08/15 19:25
	 * @params : [source]
	 * @return : java.lang.String 解密后的明文字符串
	 */
	public static byte[] decodeRSA(byte[] source) throws Exception {
		InputStream fis = null;
		try {
			fis = new FileInputStream(publicKeyFileName);
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			Certificate cerCert = cf.generateCertificate(fis);
			PublicKey pubKey = cerCert.getPublicKey();
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, pubKey);
			byte[] epByte = cipher.doFinal(source);
			return epByte;
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 功能描述: <br>
	 * 加密 key
	 * @author : wangtianhua
	 * @date : 2019/08/14 17:15
	 * @params : [serviceId, encryptedData]
	 * @return : byte[]
	 */
	public static String encryptKey(String serviceId, byte[] encryptedData) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("md5");
			md5.update(serviceId.getBytes());
			SecretKeySpec aesKey = new SecretKeySpec(md5.digest(), "AES");
			md5.update("b9b66590-6668-4b08-95aa-e373a65867b7".getBytes());
			IvParameterSpec iv = new IvParameterSpec(md5.digest());
			Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			aesCipher.init(Cipher.ENCRYPT_MODE, aesKey, iv);
			byte[] decrypted = aesCipher.doFinal(encryptedData);
			String key = Hex.encodeHexString(decrypted);
			return key;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 功能描述: <br>
	 * 解密 key
	 * @author : wangtianhua
	 * @date : 2019/08/14 20:04
	 * @params : [serviceId, encryptedData]
	 * @return : byte[]
	 */
	public static byte[] decryptKey(String serviceId, String encryptedKey){
		try {
			MessageDigest md5 = MessageDigest.getInstance("md5");
			md5.update(serviceId.getBytes());
			SecretKeySpec aesKey = new SecretKeySpec(md5.digest(), "AES");
			md5.update("b9b66590-6668-4b08-95aa-e373a65867b7".getBytes());
			IvParameterSpec iv = new IvParameterSpec(md5.digest());
			Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			aesCipher.init(Cipher.DECRYPT_MODE, aesKey, iv);
			byte[] decrypted = aesCipher.doFinal(Hex.decodeHex(encryptedKey.toCharArray()));
			return decrypted;
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 功能描述: <br>
	 * 加密 license
	 * @author : wangtianhua
	 * @date : 2019/08/16 00:24
	 * @params : [key, encryptedData] 245字节的字符串， 要加密的数据
	 * @return : java.lang.String 返回 16 进制的字符串
	 */
	public static String encryptLicense(byte[] key, String encryptedData) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("md5");
			md5.update(key);
			SecretKeySpec aesKey = new SecretKeySpec(md5.digest(), "AES");
			md5.update("1ccb541a-8009-40fa-bff6-d6fab398b36f".getBytes());
			IvParameterSpec iv = new IvParameterSpec(md5.digest());
			Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			aesCipher.init(Cipher.ENCRYPT_MODE, aesKey, iv);
			byte[] decrypted = aesCipher.doFinal(encryptedData.getBytes());
			String license = Hex.encodeHexString(decrypted);
			return license;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 功能描述: <br>
	 * 解密 license
	 * @author : wangtianhua
	 * @date : 2019/08/14 14:03
	 * @params : [key, encryptedLicense]
	 * @return : java.lang.String
	 */
	public static String decryptLicense(byte[] key, String encryptedLicense){
		try {
			MessageDigest md5 = MessageDigest.getInstance("md5");
			md5.update(key);
			SecretKeySpec aesKey = new SecretKeySpec(md5.digest(), "AES");
			md5.update("1ccb541a-8009-40fa-bff6-d6fab398b36f".getBytes());
			IvParameterSpec iv = new IvParameterSpec(md5.digest());
			Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			aesCipher.init(Cipher.DECRYPT_MODE, aesKey, iv);
			byte[] decrypted = aesCipher.doFinal(Hex.decodeHex(encryptedLicense.toCharArray()));
			String license = new String(decrypted, "UTF-8");
			return license;
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}


}

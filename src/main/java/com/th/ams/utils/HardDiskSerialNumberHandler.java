package com.th.ams.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.lang.reflect.UndeclaredThrowableException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author : wangtianhua
 * @date : 2019/08/11 23:50
 * @description : 硬盘序列号
 */
public class HardDiskSerialNumberHandler {
	private static String sn = null;
	public HardDiskSerialNumberHandler(){

	}

	public static void main(String[] a) throws Exception {
		System.out.println(getPublicKey());
		Calendar defaultTime = Calendar.getInstance();
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "{\"key\":\"fdc85c8c7d9d592ff0cd8f17c6daa9a0fceb3003f1392c0963c8cecbc67f3998a5cd503a46a638ebb42b4490176b7646ab4cfa9b07000a6f5e823617cf45a71463f6a453a592b3145cf4e20d1f15d6540937c7492ba1fe8cd0043fc5019c3e5b685cdccc659822c5e885f31f7f8598cca3e56a55694e86d94d0020c8bc6b193d367c17c944ca3c58af7bc232b4be4a26515d903d19f0013f734fba904bc2606287a12c4739f7e7e73aa13b9e5c9d17dea7ba5b65aacab4d84108dfcb63d5ff4e8c2fb00ee1db47e2dfe092cb034ea06bca37d66a9c214c2e5ed4a71e947e3badc0a994b23aa9b1dd720e48a33ed1f0a6751a88d961447b56f7ddc578777e5b74b7fbab7c20764ac2a69da531fcd20f0e\",\"license\":\"80ca140e5530f54785e3600bf797a62ea1528ff75d1d67368cccd610eb69e29e0f4b1e423ffaac02c8c7018788661e232702c2dcaa8f4498a76eca4c4c03f40660eaebdf6821e0e4f22ebcb6cdf7813b8d426a452047a0519e8ed492e3e342f3c61e67c226aebbea9a2fa08b48b2aab6f715e60eb544c6d0e885099af20c707da3626f2c585bddaa7e33f5f89b6384eb3c09265126950dc785a0e5aee826faa626df39b9a96375b485f4b126a78bc5db7a98b430d754fd2bfa9bca325343b3817cf2fe98d0db12a45546e33308640424697237c01bd9b5e4d946ab4420503d888edd4cd5d8b2020f3bfff428e19f448023fafb72e78c9942cc962c75fbd4f607fd25ebe7d3ce0363caf637b32fc721af858dd3aa2dcccc1de3e38592fc1ac4107e38aedfe8a9a9efc3d1f6fab4c07f6ec0536297c54ae673500adf339fa0018705d3b4b84277bc903f5140c8927916cc45dcfd95bc3f06c9120626130e6cdb12eacf8ca2a96540700dbe51615f9fb76e9e179681e5c4ffb7832536cdb244763f764689f87fe44ffe99571246e9353acdf81c240467fa3b7d92b3bcd86a0fc6bbd568a6e0acf7401ec0411aa8af39424681b76334719ce7b6ef3aa14893fd7592bd4e59f9f50723dd78f5c574ffd44299e595736481214aa30ab7311f229a01c7532c59cf1c94f1bdada2e6633a67923a886ab57e7674279ed4bd7f6a7500e4b249d4112e01874c108ec8eb683b04e496f61975768c8817e7aba75f8dcf0a871abb6f9cdb44668f4f3c06093a1d5ff954638f9627178b725a2921a970d415434889b8ed256ce907bab5aa40ab5c7dd921265544683933461566d8bbf73e7d25bff2c26406a59dfa65fed3018b1d835921e422e1eb32253b788f6a8a5ca3f497732db786bf90784e2b9dd867db7f2d29c992580f0ff1e9fbc7751844047e78f691792e3a2e17d5c7274cd9b7f4b3406702cbaefbcd9f5dfd69e8db1b21f3ce6b9c9be1c9d14cf9104acbfad6c85a69dcc2061ac4a8f6404a917cbb6ee60703d21c0a432ecf1197f5dbd2463b7f0e5fcf1980e226bd7d22f2fe676770a3fe4ebe90a87dd71a03c86a63d5b4da83ee5b0803c2b4a8431072b5d962fe9ffcec50a197789d7dec42f82e69ca448b54897476f547a72f8c4366b4ce92632c11341d8313c8ed21fb5fed93d4195f061ebe4baf29d90c88c1af4431150be9482902f5eb7d9dc400a4614dcc208bbcedbc361f8db1ea0533d8d0ac07612042241f7f344e59d356569d01e8a713d88b4a8de58c36312f6768eac20b24be8fcd34695c122dab95d557b237eae572307bab647cade19cb80c07f5959c5e66693cc6bde567e858f32df7ebf27d43ee06ca23ab6ca55604af68f796540109954d0ab9b8c40e7f346d05b86eea06c221d9b6476d9aa241d0d588b4b01536016c1814e53e5f27135e3252976cd636e27996ccf345813907d6674e19fffb8836cb6b72b385c39ddf20bd236df14831350a98607d4c9780ca4b3deff334dd9c86c95ec0496b07fce959d373a879bed0edb363d8296f6eba0ed35a849b931a2039fcd2ffaf9155386f973d666a73ecda26748518fe1a74eb37c36b206f2981a11d5a305c07df1af5b4f2262232bc5e822d03b52086842b3944b3e50fda3c598389b8a713a164927b5f69f390fccf8767ce430222480a62d21c62f3d610e06e0a5ceae70e902f9383b94fdbb88ade9318ca6a77fb2ba5ca5cbbdc0471acf8ad80f029204dca64fe2ad9f2af3b95bfaf92ec0cb0a0185bd7fdebf65dd4337acdc0dc628789122972b47ff78dd8dcb9a62be1e3cabc011df1a96239fad2a5a05e507ed60eb1643bd1c2a435a84b330ff6f0254bcc61cc16c7b45d5d2b396e429440c301137a6293c643e6d4890a9688b5956cc4e788019975d8fcb4aab3966ac3f4a777beb52efdd82db3f2af2a822a8d05a7abc9773590bab97b37c9b3af3becd6599aa58a8c3c62b7ea3c93b46f345f7bbc7906df680963e2473892cee953440e1b0c6d162ad82dd177c5201e4340ceaf30875161d0410d4bb708a24120ba1f659440e117fb41a6c75f41d57a96dd028bffa36365c7961a807198ae82a63253449c474973416ed3c39660a0da9ce3de5be3398a16963fd3eb5799d874e55a8de7a87321fa207349ac811de5db04960760b24173619aacf12a6a8120c265b61d177198a14294e76cb1ca99fcc1e21afdcde109472ff88a6f362111ef6b4b4cf1cdaab06e456da07ae33f0c73d28bfaeb86b7d21c179407e93e9b4d7114769ec65785bad9e1c8a830f9e776ccd33a4fb293bcd7b9d1f4a1600c6e5bbfa3618a50a1ee3e0bef9de324bf04ea431ffbde50a3a77f8ddf091bf57cd141b90f103dc0838e1e3273c41c76bdd4e4f89674eec99a86e1bdd9b5226c3fcc0521bd6ec4eda65dd70de04c5d06c6651d7556f408e413f556fa5f319c1fb9050d\"}";
		Map map = objectMapper.readValue(json,HashMap.class);

		System.out.println(map.get("key").toString().length());
		System.out.println(map.get("license").toString().length());


		// System.out.println(new Date("2019/07/01").getTime());
	}

	public static PrivateKey generatePrivateKey(byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeySpec keySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePrivate(keySpec);
	}

	protected static Cipher getRsaCiphers() {
		try {
			Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			rsaCipher.init(2, getPublicKey());
			return rsaCipher;
		} catch (GeneralSecurityException | IOException var2) {
			throw new RuntimeException(var2);
		}
	}

	protected static PublicKey getPublicKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		InputStream publicKeyIs = new FileInputStream("e:\\licpub.der");
		Throwable var2 = null;
		try {
			byte[] buffer = new byte[20000];
			int length;
			for(length = 0; length < buffer.length && publicKeyIs.available() > 0; length += publicKeyIs.read(buffer, length, buffer.length - length)) {
				;
			}
			System.out.println(length);
			X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Arrays.copyOf(buffer, length));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
			PublicKey var8 = publicKey;
			return var8;
		} catch (Throwable var17) {
			var2 = var17;
			throw var17;
		} finally {
			if (publicKeyIs != null) {
				if (var2 != null) {
					try {
						publicKeyIs.close();
					} catch (Throwable var16) {
						var2.addSuppressed(var16);
					}
				} else {
					publicKeyIs.close();
				}
			}
		}
	}


	public static byte[] hmac_sha(String crypto, byte[] keyBytes, byte[] text) {
		try {
			Mac hmac = Mac.getInstance(crypto);
			SecretKeySpec macKey = new SecretKeySpec(keyBytes, "RAW");
			hmac.init(macKey);
			return hmac.doFinal(text);
		} catch (GeneralSecurityException var5) {
			throw new UndeclaredThrowableException(var5);
		}
	}

	public static String getDigest(String serialNumber) {
		byte[] hash = hmac_sha("HmacSHA1", serialNumber.getBytes(), new byte[8]);
		int offset = hash[hash.length - 1] & 15;
		int binary = (hash[offset] & 127) << 24 | (hash[offset + 1] & 255) << 16 | (hash[offset + 2] & 255) << 8 | hash[offset + 3] & 255;
		int otp = binary % 100000000;

		String result;
		for(result = Integer.toString(otp); result.length() < 8; result = "0" + result) {
			;
		}
		return result;
	}

	/**
	 * 功能描述: <br>
	 * 生成 SN 号
	 * @author : wangtianhua
	 * @date : 2019/08/12 13:49
	 * @params : []
	 * @return : java.lang.String
	 */
	public static String getServerId(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
		Date date = Calendar.getInstance().getTime();
		String str = RandomString.getRandomString();
		return "SN" + dateFormat.format(date) + str;
	}

	/**
	 * 功能描述: <br>
	 * 判断系统类型获取硬盘 SN 号
	 * @author : wangtianhua
	 * @date : 2019/08/11 23:54
	 * @params : []
	 * @return : java.lang.Boolean
	 */
	public static String getHardwareSerialNumber() {
		String osType = System.getProperty("os.name").toLowerCase();
		String sn = null;
		if (osType.startsWith("win")) {
			sn = getSerialNumberByWin();
		} else if (osType.startsWith("linux")) {
			sn = getSerialNumberByLinux();
		} else {
			if (!osType.startsWith("mac")) {
				throw new RuntimeException("oher system");
			}
			sn = getSerialNumberByMac();
		}
		return sn;
	}

	/**
	 * 功能描述: <br>
	 * 获取 Windows SerialNumber 号
	 * @author : wangtianhua
	 * @date : 2019/08/12 01:00
	 * @params : []
	 * @return : java.lang.String
	 */
	private static final synchronized String getSerialNumberByWin() {
		if (null != sn){
			return sn;
		} else {
			Process process = null;
			try {
				process = Runtime.getRuntime().exec(new String[] { "wmic", "bios", "get", "serialnumber"});
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			try {
				process.getOutputStream().close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			InputStream input = process.getInputStream();
			Scanner sc = new Scanner(input);
			try {
				sn = "";
				while (sc.hasNext()){
					sn += sc.next();
				}
			} finally {
				closeStream(input,null);
			}

			if (sn == null) {
				throw new RuntimeException("找不到电脑 SN 号");
			} else {
				return formatHardware(sn);
			}
		}
	}

	/**
	 * 功能描述: <br>
	 * 获取 Mac SN 号
	 * @author : wangtianhua
	 * @date : 2019/08/12 11:19
	 * @params : []
	 * @return : java.lang.String
	 */
	private static final synchronized String getSerialNumberByMac() {
		if (sn != null) {
			return sn;
		} else {
			OutputStream out = null;
			InputStream input = null;
			Runtime runtime = Runtime.getRuntime();
			Process process = null;

			try {
				process = runtime.exec(new String[]{"/usr/sbin/system_profiler", "SPHardwareDataType"});
			} catch (IOException var18) {
				throw new RuntimeException(var18);
			}

			out = process.getOutputStream();
			input = process.getInputStream();

			try {
				out.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			String line = null;
			String marker = "UUID";

			try {
				while((line = br.readLine()) != null) {
					if (line.contains(marker)) {
						sn = line.split(":")[1].trim();
						break;
					}
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				closeStream(input,null);
			}

			if (sn == null) {
				throw new RuntimeException("找不到电脑 SN 号");
			} else {
				return formatHardware(sn);
			}
		}
	}

	/**
	 * 功能描述: <br>
	 * 获取 Linux SerialNumber 号
	 * @author : wangtianhua
	 * @date : 2019/08/12 12:37
	 * @params : []
	 * @return : java.lang.String
	 */
	private static final synchronized String getSerialNumberByLinux() {
		if (sn == null) {
			readDmiId();
		}
		if (sn == null) {
			readDmidecode();
		}
		if (sn == null) {
			readLshal();
		}
		if (sn == null) {
			throw new RuntimeException("找不到电脑 SN 号");
		} else {
			return formatHardware(sn);
		}
	}

	private static BufferedReader read(String command) {
		Runtime runtime = Runtime.getRuntime();
		Process process = null;

		try {
			process = runtime.exec(command.split(" "));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		OutputStream out = process.getOutputStream();
		InputStream input = process.getInputStream();

		try {
			out.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return new BufferedReader(new InputStreamReader(input));
	}

	private static void readDmidecode() {
		String line = null;
		String marker = "UUID:";
		BufferedReader br = null;

		try {
			br = read("dmidecode -t system");
			while((line = br.readLine()) != null) {
				if (line.indexOf(marker) != -1) {
					sn = line.split(marker)[1].trim();
					break;
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			closeStream(null, br);
		}
	}

	private static void readDmiId() {
		String line = null;
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader("/sys/class/dmi/id/product_uuid"));
			do {
				if ((line = br.readLine()) == null) {
					return;
				}
			} while(!StringUtils.isNotBlank(line));

			sn = line.trim();
			return;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			closeStream(null, br);
		}
	}

	private static void readLshal() {
		String line = null;
		String marker = "system.hardware.serial =";
		BufferedReader br = null;

		try {
			br = read("lshal");
			while((line = br.readLine()) != null) {
				if (line.indexOf(marker) != -1) {
					sn = line.split(marker)[1].replaceAll("\\(string\\)|(\\')", "").trim();
					break;
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			closeStream(null, br);
		}
	}

	private static void closeStream(InputStream input,BufferedReader br){
		if (null != input){
			try {
				input.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		if (null != br){
			try {
				br.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static String formatHardware(String sn){
		if (null != sn && "" != sn){
			sn = sn.toUpperCase();
			sn = sn.replaceAll("SERIALNUMBER", "");
			sn = sn.replaceAll("VMWARE", "");
			sn = sn.replaceAll("-", "");
			sn = sn.replaceAll(" ", "");
			return sn;
		}
		return null;
	}
}

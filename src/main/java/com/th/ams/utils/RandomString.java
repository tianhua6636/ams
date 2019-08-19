package com.th.ams.utils;

import java.util.Random;

/**
 * @author : wangtianhua
 * @date : 2019/08/12 13:39
 * @description : 随机字符串
 */
public class RandomString {
	public static final String COMPLEX_CHARS = "0123456789ABCDEFGHJKMNPQRSTUVWXYZ";
	public static final Integer COMPLEX_LEN = 6;
	public RandomString() {
	}

	/**
	 * 功能描述: <br>
	 * 默认获取长度为 6 的随机字符串
	 * @author : wangtianhua
	 * @date : 2019/08/12 13:47
	 * @params : []
	 * @return : java.lang.String
	 */
	public static String getRandomString() {
		Random random = new Random();
		StringBuilder valueBuilder = new StringBuilder(COMPLEX_LEN);
		while(valueBuilder.length() < COMPLEX_LEN) {
			valueBuilder.append(COMPLEX_CHARS.charAt(random.nextInt(COMPLEX_CHARS.length())));
		}
		return valueBuilder.toString();
	}

	public static String getRandomString(String chars, int length) {
		Random random = new Random();
		StringBuilder valueBuilder = new StringBuilder(length);
		while(valueBuilder.length() < length) {
			valueBuilder.append(chars.charAt(random.nextInt(chars.length())));
		}
		return valueBuilder.toString();
	}

	public static String getRandomString(int length){
		Random random=new Random();
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<length;i++){
			int number=random.nextInt(3);
			long result=0;
			switch(number){
				case 0:
					result=Math.round(Math.random()*25+65);
					sb.append(String.valueOf((char)result));
					break;
				case 1:
					result=Math.round(Math.random()*25+97);
					sb.append(String.valueOf((char)result));
					break;
				case 2:
					sb.append(String.valueOf(new Random().nextInt(10)));
					break;
			}
		}
		return sb.toString();
	}
}

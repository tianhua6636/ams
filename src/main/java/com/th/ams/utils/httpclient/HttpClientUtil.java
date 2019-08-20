package com.th.ams.utils.httpclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author : wangtianhua
 * @date : 2019/08/20 16:05
 * @description : httpclient url请求工具类
 */
public class HttpClientUtil {

	private static PoolingHttpClientConnectionManager cm;
	private static String EMPTY_STR = "";
	private static String UTF_8 = "UTF-8";

	private static void init() {
		if (cm == null) {
			cm = new PoolingHttpClientConnectionManager();
			// 整个连接池最大连接数
			cm.setMaxTotal(50);
			// 每路由最大连接数，默认值是2
			cm.setDefaultMaxPerRoute(5);
		}
	}

	/**
	 * 通过连接池获取HttpClient
	 *
	 * @return
	 */
	private static CloseableHttpClient getHttpClient() {
		init();
		return HttpClients.custom().setConnectionManager(cm).build();
	}
	/**
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		HttpGet httpGet = new HttpGet(url);
		return getResult(httpGet);
	}
	/**
	 * @param url、params
	 * @return
	 */
	public static String get(String url, Map<String, String > params) throws URISyntaxException {
		URIBuilder ub = new URIBuilder();
		ub.setPath(url);

		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		ub.setParameters(pairs);

		HttpGet httpGet = new HttpGet(ub.build());
		return getResult(httpGet);
	}
	/**
	 * @param url、headers、params
	 * @return
	 */
	public static String get(String url, Map<String, Object> headers, Map<String, String> params)
			throws URISyntaxException {
		URIBuilder ub = new URIBuilder();
		ub.setPath(url);

		if (params != null) {
			ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
			ub.setParameters(pairs);
		}

		HttpGet httpGet = new HttpGet(ub.build());
		for (Map.Entry<String, Object> param : headers.entrySet()) {
			httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
		}
		return getResult(httpGet);
	}
	/**
	 * @param url
	 * @return
	 */
	public static String post(String url) {
		HttpPost httpPost = new HttpPost(url);
		return getResult(httpPost);
	}
	/**
	 * @param url、params
	 * @return
	 */
	public static String post(String url, Map<String, String> params) throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(url);
		//设置表单提交编码
		httpPost.setEntity(new UrlEncodedFormEntity(covertParams2NVPS(params), "utf-8"));
//      httpPost.setEntity(new StringEntity(JSON.toJSONString(params), ContentType.APPLICATION_FORM_URLENCODED));
		return getResult(httpPost);
	}
	/**
	 * @param url、headers、params
	 * @return
	 */
	public static String post(String url, Map<String, Object> headers, Map<String, String> params)
			throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(url);

		if (params != null) {
			for (Map.Entry<String, Object> param : headers.entrySet()) {
				httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));
			}
		}

		httpPost.setEntity(new StringEntity(JSON.toJSONString(params), ContentType.APPLICATION_JSON));

		return getResult(httpPost);
	}
	/**
	 * @param method、url
	 * @return
	 */
	public static String request(String method, String url)
			throws UnsupportedEncodingException {

		RequestBuilder requestBuilder = RequestBuilder.create(method);
		requestBuilder.setUri(url);
		return getResult(requestBuilder);
	}
	/**
	 * @param method、url、params
	 * @return
	 */
	public static String request(String method, String url, Map<String, Object> params)
			throws UnsupportedEncodingException {

		RequestBuilder requestBuilder = RequestBuilder.create(method);
		requestBuilder.setUri(url);

		EntityBuilder entityBuilder = EntityBuilder.create();
		entityBuilder.setContentEncoding(UTF_8);
		entityBuilder.setText(JSON.toJSONString(params));
		entityBuilder.setContentType(ContentType.APPLICATION_FORM_URLENCODED);

		requestBuilder.setEntity(entityBuilder.build());

		return getResult(requestBuilder);
	}
	/**
	 * @param method、url、headers、params
	 * @return
	 */
	public static String request(String method, String url, Map<String, Object> headers, Map<String, String> params)
			throws UnsupportedEncodingException {

		RequestBuilder requestBuilder = RequestBuilder.create(method);
		requestBuilder.setUri(url);

		for (Map.Entry<String, Object> param : headers.entrySet()) {
			requestBuilder.addHeader(param.getKey(), String.valueOf(param.getValue()));
		}

		EntityBuilder entityBuilder = EntityBuilder.create();
		entityBuilder.setContentEncoding(UTF_8);
		entityBuilder.setText(JSON.toJSONString(params));
		entityBuilder.setContentType(ContentType.APPLICATION_JSON);

		requestBuilder.setEntity(entityBuilder.build());

		return getResult(requestBuilder);
	}
	/**
	 * @param url、file、fileName
	 * @return
	 */
	public static String uploadFile(String url, byte[] file, String fileName) {

		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept-Encoding", "gzip");
		httpPost.setHeader("charset", "utf-8");

		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
		multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		multipartEntityBuilder.setCharset(Charset.forName(UTF_8));
		multipartEntityBuilder.addBinaryBody("file", file, ContentType.MULTIPART_FORM_DATA, fileName);

		httpPost.setEntity(multipartEntityBuilder.build());

		return getResult(httpPost);
	}

	private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, String> params) {
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> param : params.entrySet()) {
			if (param.getValue() != null) {
				pairs.add(new BasicNameValuePair(param.getKey(), param.getValue()));
			}
		}

		return pairs;
	}

	/**
	 * 处理Http请求
	 *
	 * @param request
	 * @return
	 */
	private static String getResult(HttpRequestBase request) {
		// CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpClient httpClient = getHttpClient();
		try {
			CloseableHttpResponse response = httpClient.execute(request);
			// response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// long len = entity.getContentLength();// -1 表示长度未知
				String result = EntityUtils.toString(entity, UTF_8);
				response.close();
				// httpClient.close();
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}

		return EMPTY_STR;
	}

	/**
	 * 处理Http请求
	 *
	 * @param requestBuilder
	 * @return
	 */
	private static String getResult(RequestBuilder requestBuilder) {
		// CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpClient httpClient = getHttpClient();
		try {
			CloseableHttpResponse response = httpClient.execute(requestBuilder.build());
			// response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// long len = entity.getContentLength();// -1 表示长度未知
				String result = EntityUtils.toString(entity, UTF_8);
				response.close();
				// httpClient.close();
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}

		return EMPTY_STR;
	}

	public static HttpClientResult toHttpClientResult (String resultStr) {
		return JSONObject.parseObject(resultStr, HttpClientResult.class);
	}
}

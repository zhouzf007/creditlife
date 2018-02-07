package com.entrobus.credit.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.entrobus.credit.common.bean.FileUploadResult;
import com.entrobus.credit.common.bean.WebResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.zip.GZIPInputStream;

/**
 * Http网络访问工具类
 */
public class HttpClientUtil {

	/**
	 * 发起Http Get请求
	 * @param url 请求地址
	 * @param param 请求参数
	 * @return 响应字符串
	 */
	public static String doGet(String url, Map<String, String> param) {

		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		String resultString = "";
		CloseableHttpResponse response = null;
		try {
			// 创建uri
			URIBuilder builder = new URIBuilder(url);
			if (param != null) {
				for (String key : param.keySet()) {
					builder.addParameter(key, param.get(key));
				}
			}
			URI uri = builder.build();

			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);


			// 执行请求
			response = httpclient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(response);
			close(httpclient);
		}
		return resultString;
	}

	/**
	 * 发起Http Get请求
	 * @param url 请求地址
	 * @return 响应字符串
	 */
	public static String doGet(String url) {
		return doGet(url, null);
	}

	/**
	 * 发起Http Post请求
	 * @param url 请求地址
	 * @param param 请求参数
	 * @return 响应字符串
	 */
	public static String doPost(String url, Map<String, String> param) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader("Content-type","application/x-www-form-urlencoded; charset=utf-8");
			httpPost.setHeader("Accept", "application/json");
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, Charset.forName("UTF-8"));
				httpPost.setEntity(entity);
			}
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(response);
		}

		return resultString;
	}

	/**
	 * 发起Http Post请求
	 * @param url 请求地址
	 * @param postEntity 请求参数
	 * @return 响应字符串
	 */
	public static String doPost(String url, String postEntity) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建参数列表
			if (StringUtils.isNotEmpty(postEntity)) {
				//请求参数
				StringEntity stringEntity = new StringEntity(postEntity, Consts.UTF_8);
				httpPost.setEntity(stringEntity);
			}
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(response);
		}

		return resultString;
	}

	/**
	 * 发起Http Post请求
	 * @param url 请求地址
	 * @param json json格式的字符串参数
	 * @return 响应字符串
	 */
	public static String doPostJson(String url, String json) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建请求内容
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(response);
		}

		return resultString;
	}
	/**
	 * 获取输入流,返回的InputStream使用完后 应 自行调用close(is)关闭
	 * @param url 文件url
	 * @return 返回InputStream
	 */
	public static InputStream getInputStream(String url) throws IOException {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
			// 创建Http Post请求
		HttpGet httpGet = new HttpGet(url);
		// 执行http请求
		response = httpClient.execute(httpGet);
//		int status = response.getStatusLine().getStatusCode();
//		if ( status >= 400){
//			throw new IOException("获取InputStream失败！status:" + status + "。url:"+url);
//		}
		return response.getEntity().getContent();
	}

	/**
	 * 加载gz文件流并消费
	 * @param url 文件url
	 * @param consumer 对流的每一行进行处理
	 * @return 响应字符串
	 */
	public static void getGZAndConsumer(String url,Consumer<String> consumer) {
		// 创建Httpclient对象
		InputStream is = null;

		BufferedReader br = null;
		try {
			is = getInputStream(url);
			br = new BufferedReader(new InputStreamReader(new GZIPInputStream(is), "UTF-8"));
			br.lines().forEach(consumer);//遍历每一行，并使用
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			close(br);
			close(is);
		}
	}

	/**
	 * 关闭IO流
	 * @param closeable
	 */
	public static void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}


	/**
	 * 上传文件
	 * @param url 远程服务器地址
	 * @param file 要上传的文件
	 * @throws  ParseException
	 * @throws  IOException
	 */
	public static FileUploadResult postFile(String url,File file)throws ParseException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		FileUploadResult result = null;
		CloseableHttpResponse response = null;
		try{
			// 要上传的文件的路径

			// 把一个普通参数和文件上传给下面这个地址 是一个servlet
			HttpPost httpPost =new HttpPost(url);
			// 把文件转换成流对象FileBody
			FileBody bin =new FileBody(file);
//			StringBody uploadFileName =new StringBody(
//					"00", ContentType.create(
//							"text/plain", Consts.UTF_8));
			//以浏览器兼容模式运行，防止文件名乱码。
			HttpEntity reqEntity = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
					.addPart("file", bin)//uploadFile对应服务端类的同名属性<File类型>
					.setCharset(CharsetUtils.get("UTF-8")).build();

			httpPost.setEntity(reqEntity);

			System.out.println("发起请求的页面地址 "+ httpPost.getRequestLine());
			// 发起请求 并返回请求的响应
			response = httpClient.execute(httpPost);
			System.out.println("----------------------------------------");
			// 打印响应状态
			System.out.println(response.getStatusLine());
			// 获取响应对象
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				// 打印响应长度
				System.out.println("Response content length: " + resEntity.getContentLength());
				// 打印响应内容
				String resultStr = EntityUtils.toString(resEntity, Charset.forName("UTF-8"));
				System.out.println(resultStr);
				JSONObject resultJson = JSON.parseObject(resultStr);
				if (resultJson != null)
					result = resultJson.getObject(WebResult.DATA, FileUploadResult.class);

			}
			// 销毁
			EntityUtils.consume(resEntity);
		}finally{
			close(response);
			close(httpClient);
		}
		if (result == null) {
			result = new FileUploadResult();
			result.setUploadSuccess(false);
		}
		return result;
	}

}

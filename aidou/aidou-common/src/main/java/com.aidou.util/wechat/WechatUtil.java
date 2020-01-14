package com.aidou.util.wechat;

import com.aidou.util.entity.wechat.UserAccessToken;
import com.aidou.util.exception.WechatTokenNotFoundException;
import com.aidou.util.tool.GsonUtil;
import org.springframework.beans.factory.annotation.Value;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


/**
 * 微信工具类
 * 
 */
public class WechatUtil {


	/**
	 * 获取UserAccessToken实体类
	 * @param code
	 * @return
	 * @throws IOException
	 */
	public static UserAccessToken getUserAccessToken (String code) {
		// 根据传入的code,拼接出访问微信定义好的接口的URL
		String url = "https://api.weixin.qq.com/sns/jscode2session?"
				+ "appid=wx938259363d663a46&"
				+ "secret=953e65efd2edbbf948f8e47161490545&"
				+ "grant_type=authorization_code&"
				+ "js_code="+code;
		// 向相应URL发送请求获取token json字符串
		String tokenStr = httpsRequest(url, "GET", null);
		System.out.println(tokenStr);
		try {
			// 将json字符串转换成相应对象
			UserAccessToken jsonToPojo = GsonUtil.gsonToBean(tokenStr, UserAccessToken.class);
			return jsonToPojo ;
		} catch (Exception e) {
			throw new WechatTokenNotFoundException("获取用户accessToken失败");
		}
	}

	
	

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return json字符串
	 */
	public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
		e.printStackTrace();
		}
		return buffer.toString();
	}
}

/**  
* <p>Title: UUIDFactory.java</p>
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2017</p>  
* <p>Company: http://www.zcai.pro/</p>  
* @author donghui  
* @version 1.0  
*/
package com.aidou.util.tool;


import org.apache.commons.lang3.StringUtils;
import java.util.Random;

/**  
* <p>Title: UUIDFactory</p>  
* <p>Description: </p>  
* @author zhangpp  
*/
public class UUIDFactory {

	/**
	 * 获取主键
	 * <p>Title: getUUID</p>  
	 * <p>Description: </p>  
	 * @return uuid字符串
	 */
	public static String getUUID() 
	{  
		//为了防止高并发重复,再获取3个随机数
		long strDate = System.nanoTime();
		String random = getRandom620(6);
		String prefix = "LCK";
		return prefix + strDate + random;
	}  
	
	
	/**
	 * 获取订单号
	 * <p>Title: getRandom</p>  
	 * <p>Description: </p>  
	 * @return 订单号字符串
	 */
	public static String getRandom()
	{
		//得到17位时间如：20170411094039080
		//为了防止高并发重复,再获取3个随机数
		long strDate = System.nanoTime();
		String random = getRandom620(6);
		String prefix = "LCK";
		return prefix + strDate + random;
	}
	
	/**
	 * 获取6-10 的随机位数数字
	 * @param length 想要生成的长度
	 * @return 随机字符串
	 */
	private static String getRandom620(Integer length) {
		String result = "";
		Random rand = new Random();
		int n = 20;
		if (null != length && length > 0) {
			n = length;
		}
		int randInt = 0;
		for (int i = 0; i < n; i++) {
			randInt = rand.nextInt(10);
			result += randInt;
		}
		return result;
	}
	
	/**
	 * 获取订单号
	 * <p>Title: getTrans</p>  
	 * <p>Description: </p>  
	 * @return 获取订单号
	 */
	public static String getOrderId()
	{
		//格式化当前时间
		long strDate = System.nanoTime();
		String random = getRandom620(6);
		String prefix = "PAY";
		return prefix + strDate + random;
	}
	
	/**
	 * 充值订单号
	 * <p>Title: getCzOrderId</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	public static String getCzOrderId()
	{
		//格式化当前时间
		long strDate = System.nanoTime();
		String random = getRandom620(6);
		String prefix = "LCK";
		return prefix + strDate + random;
	}
		
	/**
	 * 8位数
	 * <p>Title: add</p>  
	 * <p>Description: </p>  
	 * @param val 原始值
	 * @return 加1后的值
	 */
	public synchronized static String add(String val)
	{
		if(StringUtils.isBlank(val))
		{
			return "00000001";
		}
		int addVal = Integer.parseInt(val) + 1;
		return String.format("%08d", addVal);
	}
}

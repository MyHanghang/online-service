/*
 *RedisUtils.java
 *chenzhiwei
 * 2018年4月11日
 */
package online.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class RedisUtils {
	/**读取redis.properties 配置文件
	 * @return
	 * @throws IOException
	 */
	public Properties getPprVue(){
		InputStream inputStream = RedisUtils.class.getClassLoader().getResourceAsStream("redis.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			//读取配置文件出错
			e.printStackTrace();
		}
		return p;
	}
}

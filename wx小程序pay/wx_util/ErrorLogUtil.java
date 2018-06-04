package util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.impl.Log4JLogger;
/**
 * 异常信息写入.log的日志中
 * 使用在service
 * @author MaoLG
 *
 * 2018-3-22下午4:04:27
 */

public class ErrorLogUtil {
	
	private static Log4JLogger l = new Log4JLogger("log4j.properties");
	
	
	/**
	 * 
	 * @param e 异常对象
	 */
	public static void log(Exception e){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		l.error(sdf.format(new Date()));
		l.error(e, e.fillInStackTrace());
	}
	
}

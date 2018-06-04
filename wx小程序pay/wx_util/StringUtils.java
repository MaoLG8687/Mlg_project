package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class StringUtils extends org.apache.commons.lang.StringUtils{

	public static String rename(String fileName) {
		int lastDot = fileName.lastIndexOf(".");
		String suffix = fileName.substring(lastDot);
		return idGenerate() + suffix;
	}
	
	/**
	 * uuid
	 * @return
	 */
	public static String idGenerate() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * 获取时间戳
	 * @return
	 */
	public static String getTimestamp(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDDHHmmss");
		String format = sdf.format(new Date());
		return format;
	}
	
	 /**
     * 产生6位数的验证码
     * @return
     */
    public static String getCode(){
    	Random r = new Random();
		String[] s = {"1","2","3","4","5","6","7","8","9","0"};
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < 6; i++){
			int num = r.nextInt(10);
			sb.append(s[num]);
		}
		return sb.toString();
    }
    
    /**
     * 转码 将中文转为%E8%BF
     * @param s
     * @return
     */
    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = String.valueOf(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    /**
     * 将%E4%BD%A0转换为汉字
     * @param s
     * @return
     */
    public static String unescape(String s) {
        StringBuffer sbuf = new StringBuffer();
        int l = s.length();
        int ch = -1;
        int b, sumb = 0;
        for (int i = 0, more = -1; i < l; i++) {
            /* Get next byte b from URL segment s */
            switch (ch = s.charAt(i)) {
            case '%':
                ch = s.charAt(++i);
                int hb = (Character.isDigit((char) ch) ? ch - '0'
                        : 10 + Character.toLowerCase((char) ch) - 'a') & 0xF;
                ch = s.charAt(++i);
                int lb = (Character.isDigit((char) ch) ? ch - '0'
                        : 10 + Character.toLowerCase((char) ch) - 'a') & 0xF;
                b = (hb << 4) | lb;
                break;
            case '+':
                b = ' ';
                break;
            default:
                b = ch;
            }
            /* Decode byte b as UTF-8, sumb collects incomplete chars */
            if ((b & 0xc0) == 0x80) { // 10xxxxxx (continuation byte)  
                sumb = (sumb << 6) | (b & 0x3f); // Add 6 bits to sumb  
                if (--more == 0)
                    sbuf.append((char) sumb); // Add char to sbuf  
            } else if ((b & 0x80) == 0x00) { // 0xxxxxxx (yields 7 bits)  
                sbuf.append((char) b); // Store in sbuf  
            } else if ((b & 0xe0) == 0xc0) { // 110xxxxx (yields 5 bits)  
                sumb = b & 0x1f;
                more = 1; // Expect 1 more byte  
            } else if ((b & 0xf0) == 0xe0) { // 1110xxxx (yields 4 bits)  
                sumb = b & 0x0f;
                more = 2; // Expect 2 more bytes  
            } else if ((b & 0xf8) == 0xf0) { // 11110xxx (yields 3 bits)  
                sumb = b & 0x07;
                more = 3; // Expect 3 more bytes  
            } else if ((b & 0xfc) == 0xf8) { // 111110xx (yields 2 bits)  
                sumb = b & 0x03;
                more = 4; // Expect 4 more bytes  
            } else /*if ((b & 0xfe) == 0xfc)*/{ // 1111110x (yields 1 bit)  
                sumb = b & 0x01;
                more = 5; // Expect 5 more bytes  
            }
            /* We don't test if the UTF-8 encoding is well-formed */
        }
        return sbuf.toString();
    }
    
    
    /**
     * StringUtils工具类方法
     * 获取一定长度的随机字符串，范围0-9，a-z
     * @param length：指定字符串长度
     * @return 一定长度的随机字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}

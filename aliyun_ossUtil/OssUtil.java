package privateUtil;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;

/**
 * aliOss上传
 * @author MaoLG
 *
 * @date 2018-4-11 下午1:59:13
 */
public class OssUtil {
	// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
	private static final String accessKeyId = "";
	private static final String accessKeySecret = "";
	/**
	 * @param file	表单提交
	 * @param folderName 文件夹名称
	 * @return
	 * @throws IOException
	 */
	public static String ossUpload(MultipartFile file, String folderName) throws IOException{
		String fileName = file.getOriginalFilename();
	    // endpoint以杭州为例，其它region请按实际情况填写
	    String endpoint = "http://oss-cn-beijing.aliyuncs.com";

	    // 创建OSSClient实例
	    OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
	    // 上传文件流
	    InputStream inputStream = file.getInputStream();
	    //第一个参数固定, 第二个参数文件名 , 所在文件夹, 第三个参数二进制流
	    String filePath = folderName+"/"+fileName;
	    ossClient.putObject("cooplan", filePath, inputStream);
	    // 关闭client
	    ossClient.shutdown();
		
		
		return filePath;
	}
	
	/**
	 * 
	 * @param file 文件流
	 * @param folderName 文件夹的名字
	 * @param fileName	文件的名字
	 * @return
	 * @throws IOException
	 */
	public static String ossUpload(MultipartFile file, String folderName, String fileName) throws IOException{
	    // endpoint以杭州为例，其它region请按实际情况填写
	    String endpoint = "http://oss-cn-beijing.aliyuncs.com";
	    
	    // 创建OSSClient实例
	    OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
	    // 上传文件流
	    InputStream inputStream = file.getInputStream();
	    //第一个参数固定, 第二个参数文件名 , 所在文件夹, 第三个参数二进制流
	    String filePath = folderName+"/"+fileName;
	    ossClient.putObject("cooplan", filePath, inputStream);
	    // 关闭client
	    ossClient.shutdown();
		
		
		return filePath;
	}
	
	
	/**
	 * 
	 * @param is 二进制文件流
	 * @param folderName 文件夹名称
	 * @param fileName	文件名称
	 * @return
	 * @throws IOException
	 */
	public static String ossUpload(InputStream is, String folderName, String fileName) throws IOException{
	    // endpoint以杭州为例，其它region请按实际情况填写
	    String endpoint = "http://oss-cn-beijing.aliyuncs.com";
	    // 创建OSSClient实例
	    OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
	    //第一个参数固定, 第二个参数文件名 , 所在文件夹, 第三个参数二进制流
	    String filePath = folderName+"/"+fileName;
	    ossClient.putObject("cooplan", filePath, is);
	    // 关闭client
	    ossClient.shutdown();
		
		
		return filePath;
	}
	
	/**
	 * 删除文件
	 * @param fileName 文件名
	 */
	public static void ossDelete(String fileName){
	    // endpoint以杭州为例，其它region请按实际情况填写
	    String endpoint = "http://oss-cn-beijing.aliyuncs.com";
	    // 创建OSSClient实例
	    OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
	    // 删除Object
	    ossClient.deleteObject("cooplan", fileName);
	    // 关闭client
	    ossClient.shutdown();
	}
	
}

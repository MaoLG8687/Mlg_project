package privateUtil;

import java.util.Map;
import java.util.Properties;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import vo.SendMail;


import constant.Constant;

/**
 * 发送Mail 工具
 * @author MaoLG
 *
 * @2018-6-7上午11:04:44
 *<!-- Email发送jar -->
		<dependency >  
            <groupId >com.sun.mail </groupId >  
            <artifactId >javax.mail </artifactId >  
            <version >1.5.4 </version >  
        </dependency >  
 *
 */
public class SendMailUtil {
	
	public static void send (SendMail sd) throws Exception{
		Properties props = new Properties();
		//阿里的smtp服务器
		props.put("mail.smtp.host", sd.getHost());
		
		// 需要经过授权，也就是有户名和密码的校验，这样才能通过验证
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.ssl.enable", true);
		
		 // 用刚刚设置好的props对象构建一个session
		Session session = Session.getDefaultInstance(props);
		
		 // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
		// 用（你可以在控制台（console)上看到发送邮件的过程）
//		session.setDebug(true);
		
		 // 用session为参数定义消息对象
		MimeMessage message = new MimeMessage(session);
		
		try {
			// 加载发件人地址
			message.setFrom(new InternetAddress(sd.getUser()));
			// 加载收件人地址
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(sd.getTo()));
			// 加载标题
			message.setSubject(map.get("subject"));	
			
			// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
			Multipart multipart = new MimeMultipart();
			// 设置邮件的文本内容
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setText("邮件文本");
			multipart.addBodyPart(contentPart);
			// 添加附件
			BodyPart messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(sd.getAffix());//附件地址
			
			
			// 添加附件的内容
			messageBodyPart.setDataHandler(new DataHandler(source));
			   // 添加附件的标题
			// 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
//			sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
			messageBodyPart.setFileName(MimeUtility.encodeText("附件名称"));
			multipart.addBodyPart(messageBodyPart);
			
			
			//发送邮件内容添加图片
			BodyPart bodyPart = new MimeBodyPart(); 
			bodyPart.setDataHandler(new DataHandler("邮件文本(可放HTML代码)","text/html;charset=UTF-8")); 

			multipart.addBodyPart(bodyPart); 
			message.setContent(multipart);// 设置邮件内容对象 
 
			 // 将multipart对象放到message中
			message.setContent(multipart);
			// 保存邮件
			message.saveChanges();
			// 发送邮件
			Transport transport = session.getTransport("smtp");
			
			// 连接服务器的邮箱
			transport.connect(sd.getHost(), sd.getUser(), sd.getPwd());
			// 把邮件发送出去
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
	}
	
}

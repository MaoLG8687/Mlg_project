package vo;

import java.io.Serializable;

/**
 * 用来发送邮件的类
 * @author MaoLG
 *
 * @2018-6-7上午11:01:35
 */
public class SendMail implements Serializable{
	
	 private String host; // smtp服务器smtp.mxhichina.com
	 private String from; // 发件人地址
	 private String to; // 收件人地址
	 private String affix; // 附件地址
	 private String affixName; // 附件名称
	 private String user; // 用户名
	 private String pwd; // 密码
	 private String subject; // 邮件标题
	 
	public SendMail() {
		super();
	}
	public SendMail(String host, String from, String to, String affix,
			String affixName, String user, String pwd, String subject) {
		super();
		this.host = host;
		this.from = from;
		this.to = to;
		this.affix = affix;
		this.affixName = affixName;
		this.user = user;
		this.pwd = pwd;
		this.subject = subject;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getAffix() {
		return affix;
	}
	public void setAffix(String affix) {
		this.affix = affix;
	}
	public String getAffixName() {
		return affixName;
	}
	public void setAffixName(String affixName) {
		this.affixName = affixName;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	 
}

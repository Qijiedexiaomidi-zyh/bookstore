package com.abc.bookstore.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
/**
 * 发送邮件的工具类
 */
public class MailUtils {

	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException, GeneralSecurityException {

		//定义一个Properties对象
		Properties props = new Properties();
		//邮件传输协议
		props.setProperty("mail.transport.protocol", "SMTP");
		//设置邮件服务器主机名QQ
		props.setProperty("mail.host", "smtp.163.com");
		//设置邮件服务是否需要验证
		props.setProperty("mail.smtp.auth", "true");

		//定义验证信息
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				//return new PasswordAuthentication("1430612680@qq.com", "jtieutvuqltzbafj");
				return new PasswordAuthentication("zyh15237228271@163.com", "QCOLBZEZMQEZPGWH");
			}
		};

		//创建和邮件服务器的会话
		Session session = Session.getInstance(props, auth);

		//设置发送邮件的相关内容
		Message message = new MimeMessage(session);

		//设置邮件的发送方
		message.setFrom(new InternetAddress("zyh15237228271@163.com"));

		//设置邮件的接收方
		message.setRecipient(RecipientType.TO, new InternetAddress(email));

		//设置邮件的主题
		message.setSubject("用户激活");
		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

		message.setContent(emailMsg, "text/html;charset=utf-8");

		//阿里云部署解决邮件发送不成功的问题
		//SSL加密
		MailSSLSocketFactory mailSSLSocketFactory= new MailSSLSocketFactory();//可能会有异常，添加抛出异常
		mailSSLSocketFactory.setTrustAllHosts(true);
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.socketFactory", mailSSLSocketFactory);

		Transport.send(message);
	}
}

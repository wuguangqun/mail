package com.ruiyun.gp.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {

	private static String MAIL_HOST = "";
	private static String MAIL_ACCOUNT = "";
	private static String MAIL_PWD = "";

	private static String MAIL_FROM = "";
	private static String MAIL_TO = "";
	private static String MAIL_TITLE = "";

	static {
		ProFileReader uploadPropFile;
		uploadPropFile = new ProFileReader(EmailUtil.class.getResourceAsStream("/resource/mailconfig.properties"));
		MAIL_HOST = uploadPropFile.getParamValue("MAIL_HOST");
		MAIL_ACCOUNT = uploadPropFile.getParamValue("MAIL_ACCOUNT");
		MAIL_PWD = uploadPropFile.getParamValue("MAIL_PWD");
		MAIL_FROM = uploadPropFile.getParamValue("MAIL_FROM");
		MAIL_TO = uploadPropFile.getParamValue("MAIL_TO");
		MAIL_TITLE = uploadPropFile.getParamValue("MAIL_TITLE");
	}

	public static void sendMial(String sendContent) throws Exception {
		// 使用JavaMail发送邮件的5个步骤
		// 1、创建session
		Session session = getMailSession();
		// 2、通过session得到transport对象
		Transport ts = getMailTransport(session);
		// 3、创建邮件
		Message message = createSimpleMail(session, sendContent);
		// 4、发送邮件
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();
	}

	private static Session getMailSession() {
		Properties prop = new Properties();
		prop.setProperty("mail.host", MAIL_HOST);
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(prop);
		// 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		session.setDebug(true);
		return session;
	}

	private static Transport getMailTransport(Session session) throws NoSuchProviderException, MessagingException {
		Transport ts = session.getTransport();
		// 使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
		ts.connect(MAIL_HOST, MAIL_ACCOUNT, MAIL_PWD);
		return ts;
	}

	private static MimeMessage createSimpleMail(Session session, String sendContent) throws Exception {
		// 创建邮件对象
		MimeMessage message = new MimeMessage(session);
		// 指明邮件的发件人
		message.setFrom(new InternetAddress(MAIL_FROM));
		// 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
		message.setRecipients(Message.RecipientType.TO, MAIL_TO);
		// 邮件的标题
		message.setSubject(MAIL_TITLE);
		// 邮件的文本内容
		message.setContent(sendContent, "text/html;charset=UTF-8");
		// 返回创建好的邮件对象
		return message;
	}

	/**
	 * 指定发给谁
	 * 
	 * @param sendContent
	 * @param mailTo
	 * @throws Exception
	 */
	public static void sendMial(String sendContent, String mailTo) throws Exception {
		// 使用JavaMail发送邮件的5个步骤
		// 1、创建session
		Session session = getMailSession();
		// 2、通过session得到transport对象
		Transport ts = getMailTransport(session);
		// 3、创建邮件
		Message message = createSimpleMail(session, sendContent, mailTo);
		// 4、发送邮件
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();
	}

	private static MimeMessage createSimpleMail(Session session, String sendContent, String mailTo) throws Exception {
		// 创建邮件对象
		MimeMessage message = new MimeMessage(session);
		// 指明邮件的发件人
		message.setFrom(new InternetAddress(MAIL_FROM));
		// 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
		message.setRecipients(Message.RecipientType.TO, mailTo);
		// 邮件的标题
		message.setSubject(MAIL_TITLE);
		// 邮件的文本内容
		message.setContent(sendContent, "text/html;charset=UTF-8");
		// 返回创建好的邮件对象
		return message;
	}
}

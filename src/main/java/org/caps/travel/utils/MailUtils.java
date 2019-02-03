/*
package org.caps.travel.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.util.Properties;

public class MailUtils {

    @Autowired
    private static JavaMailSender mailSender;

    public static void sendMail(String email, String emailMsg){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("279205343@qq.com");
        message.setTo(email);
        message.setSubject("激活邮件");
        message.setText(emailMsg);
        mailSender.send(message);
	}

	public static void main (String[] args){
        //发送激活邮件
        String emailMsg = "恭喜您注册成功，请点击下面的连接进行激活账户"
                + "<a href='http://localhost:8091/active?activeCode="+111+"'>"
                + "http://localhost:8091/active?activeCode="+111+"</a>";
        try {
            MailUtils.sendMail("294802555@qq.com", emailMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/

package org.caps.travel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Title: ApplicationTests</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/23 12:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TravelApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

    @Autowired
    private JavaMailSender mailSender;
    @Test
    public void sendSimpleMail() throws Exception {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("279205343@qq.com");
        message.setTo("294802555@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");
        mailSender.send(message);
    }
}

package com.mmd.carrotmail;

import com.mmd.carrotmail.service.IMailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarrotMailApplicationTests {

    @Autowired
    private IMailService iMailService;
    @Test
    void contextLoads() {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        iMailService.sendHtmlMail("1692785832@qq.com","test simple mail",content);
    }



}

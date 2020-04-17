package com.mmd.carrotmail.controller;

import com.mmd.carrotmail.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author carrot
 * @date 2020/4/17 11:40
 */
@RequestMapping("/mail")
@RestController
public class MailSenderController {

    @Autowired
    private IMailService iMailService;
    @RequestMapping(value = "/sendHtmlMail",method = RequestMethod.POST)
    public  void sendHtmlMail() {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        iMailService.sendHtmlMail("1692785832@qq.com","test simple mail",content);
    }

    @RequestMapping(value = "/str",method = RequestMethod.GET)
    public  String str() {
        return "success";
    }
}

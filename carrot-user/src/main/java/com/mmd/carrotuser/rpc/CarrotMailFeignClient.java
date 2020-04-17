package com.mmd.carrotuser.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author carrot
 * @date 2020/4/17 11:52
 */
@FeignClient("carrot-mail")
public interface CarrotMailFeignClient {
    /**
     * 发送html的邮件
     */
    @RequestMapping(value = "/mail/sendHtmlMail",method = RequestMethod.POST)
    void sendHtmlMail();
}

package cn.geny.health.manager.factory;


import cn.geny.health.utils.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 * 
 * @author ruoyi
 */
public class AsyncFactory
{
    private static final Logger logger = LoggerFactory.getLogger(AsyncFactory.class);



    /**
     * 异步发送邮件任务
     */
    public static TimerTask sendTextEmail(String emailAddress,String content)
    {
        return new TimerTask()
        {
            @Override
            public void run()
            {
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setText(content);
                simpleMailMessage.setFrom("hydraone123@qq.com");
//                simpleMailMessage.setFrom("m72875509qiaonuo@163.com");
                simpleMailMessage.setTo(emailAddress);
                SpringUtils.getBean(JavaMailSender.class).send(simpleMailMessage);
            }
        };
    }
}

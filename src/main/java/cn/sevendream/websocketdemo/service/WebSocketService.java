package cn.sevendream.websocketdemo.service;

/**
 * Created by yangyibo on 17/1/12.
 */

import cn.sevendream.websocketdemo.pojo.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import java.util.Date;
import java.util.Locale;

@Service
public class WebSocketService {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketService.class);
    @Autowired
    //使用SimpMessagingTemplate 向浏览器发送消息
    private SimpMessagingTemplate template;
    public void sendMessage() throws Exception{
        for(int i=0;i<10;i++) {
            Thread.sleep(1000);
            template.convertAndSend("/topic/getResponse",
                    new ResponseMessage("Welcome,simon !"+DateUtils.format(new Date(),"YYYY-MM-dd HH:mm:ss", Locale.CHINESE)));
            logger.info("sendMessage:第{}次发送消息",i+1);
        }
    }

}

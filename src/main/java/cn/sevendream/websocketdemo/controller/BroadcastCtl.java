package cn.sevendream.websocketdemo.controller;

import cn.sevendream.websocketdemo.pojo.RequestMessage;
import cn.sevendream.websocketdemo.pojo.ResponseMessage;
import cn.sevendream.websocketdemo.service.WebSocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@CrossOrigin
public class BroadcastCtl {
    private static final Logger logger = LoggerFactory.getLogger(BroadcastCtl.class);

    @Autowired
    private WebSocketService ws;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RequestMapping(value = "/ws")
    public String ws(){
        return  "ws";
    }
    @MessageMapping("/welcome")//浏览器发送请求通过@messageMapping 映射/welcome 这个地址。
    @SendTo("/topic/getResponse")//服务器端有消息时,会订阅@SendTo 中的路径的浏览器发送消息。
    public ResponseMessage say(RequestMessage message){
        return new ResponseMessage("Welcome, " + message.getName() + "!");
    }

    /**
     * 这里模拟主动推送，借助定时器等可以调用service推送消息给前端
     * @return
     * @throws Exception
     */
    //http://localhost:8090/pushMessage
    @RequestMapping("/pushMessage")
    @ResponseBody
    public String say2()throws Exception
    {
        ws.sendMessage();
        return "我已会向http://localhost:8080/ws路径发送信息";
    }

}

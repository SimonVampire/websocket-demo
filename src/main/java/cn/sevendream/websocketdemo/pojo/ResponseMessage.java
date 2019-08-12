package cn.sevendream.websocketdemo.pojo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhangxue9
 * @descrition
 * @date 2019-8-5 10:06
 */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ResponseMessage {
    private String responseMessage;
}

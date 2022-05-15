package cn.geny.health.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/5/14 21:22
 */
@Data
public class MessageBO {
    @JsonProperty("id")
    private String id;
    @JsonProperty("contentType")
    private String contentType;
    @JsonProperty("body")
    private String body;
    @JsonProperty("attachments")
    private List<String> attachments;
    @JsonProperty("createdAt")
    private Date createdAt;
    @JsonProperty("senderId")
    private String senderId;
    @JsonProperty("conversationId")
    private String conversationId;
}

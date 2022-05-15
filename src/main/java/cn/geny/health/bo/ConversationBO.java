package cn.geny.health.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/5/14 22:27
 */
@Data
public class ConversationBO {
    @JsonProperty("id")
    private String id;
    @JsonProperty("participants")
    private List<Participant> participants;
    @JsonProperty("type")
    private final String type = "ONE_TO_ONE";
    @JsonProperty("unreadCount")
    private final Integer unreadCount = 0;
    @JsonProperty("messages")
    private List<MessageBO> messages;
}

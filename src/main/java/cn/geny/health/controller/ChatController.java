package cn.geny.health.controller;

import cn.geny.health.bo.ConversationBO;
import cn.geny.health.bo.MessageBO;
import cn.geny.health.bo.Participant;
import cn.geny.health.bo.User;
import cn.geny.health.common.AjaxResult;
import cn.geny.health.po.Account;
import cn.geny.health.po.Message;
import cn.geny.health.service.MessageService;
import cn.geny.health.service.impl.UserService;
import cn.geny.health.utils.SecurityUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/5/14 18:41
 */
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    UserService userService;
    List<MessageBO> list = new ArrayList<>();

    @Autowired
    MessageService messageService;


    @GetMapping("/conversations")
    public AjaxResult conversations() {
        return getFileAjaxResult("conversations.json");
    }

    @GetMapping("/conversations1")
    public AjaxResult conversations1() {
        String userid = SecurityUtils.getUserId();
        ConversationBO conversationBO = new ConversationBO();
        conversationBO.setId("0082596802a681008d44bb4234028f61");
        Account account = userService.getById(conversationBO.getId());
        User user = SecurityUtils.convertAccountToUser(account);
        Participant participant = new Participant();
        participant.setId(user.getId());
        participant.setAvatar(user.getPhotoURL());
        participant.setName(user.getDisplayName());
        participant.setUsername(user.getName());


        Account currentAccount = userService.getById(userid);
        User currentUser = SecurityUtils.convertAccountToUser(currentAccount);
        Participant currentParticipant = new Participant();
        currentParticipant.setId(currentUser.getId());
        currentParticipant.setAvatar(currentUser.getPhotoURL());
        currentParticipant.setName(currentUser.getDisplayName());
        currentParticipant.setUsername(currentUser.getName());

        List<Participant> participants = new ArrayList<>();
        participants.add(currentParticipant);
        participants.add(participant);
        conversationBO.setParticipants(participants);

        List<String> ids= new ArrayList<>();
        ids.add(userid);
        ids.add(user.getId());

        List<Message> messages = messageService.list(new QueryWrapper<Message>().in("to_user", ids).in("create_by", ids));
        List<MessageBO> messageBOList = messages.stream().map(message -> {
            MessageBO messageBO = new MessageBO();
            messageBO.setSenderId(message.getCreateBy());
            messageBO.setId(message.getToUser());
            messageBO.setCreatedAt(message.getCreateTime());
            messageBO.setContentType("text");
            messageBO.setBody(message.getMsg());
            return messageBO;
        }).collect(Collectors.toList());
        conversationBO.setMessages(messageBOList);
        List<ConversationBO> conversationBOs = new ArrayList<>();
        conversationBOs.add(conversationBO);
        return AjaxResult.success().put("conversations",conversationBOs);
    }

    @GetMapping("/conversations2")
    public AjaxResult conversations2() {
        Set<String> userConversation = messageService.getUserConversation(SecurityUtils.getUserId());
        List<String> collect = new ArrayList<>(userConversation);
        List<ConversationBO> conversations = messageService.getConversations(collect);
        return AjaxResult.success().put("conversations",conversations);
    }

    @PutMapping("/sendMessage")
    public AjaxResult sendMessage(@RequestBody MessageBO messageBO) {
        messageService.saveMessageBO(messageBO);
        return AjaxResult.success();
    }

    @GetMapping("/conversation")
    //    public AjaxResult conversation(@RequestParam("conversationKey") String conversationKey) {
//        File file = new File("src/main/resources/chat/" + "conversation.json");
//        StringBuffer sb = new StringBuffer();
//        try (FileInputStream fis = new FileInputStream(file);) {
//            int len;
//            byte[] buffer = new byte[4096];
//            while ((len = fis.read(buffer)) > 0) {
//                sb.append(new String(Arrays.copyOfRange(buffer, 0, len)));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////        {
////            "id": "d8d17862-4e92-4c5e-8220-82fef335455f",
////                "body": "Voluptas sunt magni adipisci praesentium saepe.",
////                "contentType": "text",
////                "attachments": [],
////            "createdAt": "2022-05-14T11:49:08.664Z",
////                "senderId": "8864c717-587d-472a-929a-8e5f298024da-0"
////        }
//        JSONObject jsonObject = JSONObject.parseObject(sb.toString());
//        MessageBO messageBO = new MessageBO();
//        messageBO.setId("d8d17862-4e92-4c5e-8220-82fef335455f");
//        messageBO.setBody("芜湖");
//        messageBO.setContentType("text");
//        messageBO.setAttachments(new ArrayList<>());
//        messageBO.setCreatedAt(new Date());
//        messageBO.setSenderId("8864c717-587d-472a-929a-8e5f298024da-0");
//        list.forEach((item) -> jsonObject.getJSONObject("conversation").getJSONArray("messages").add(JSON.toJSON(item))
//        );
////        return getFileAjaxResult("conversation1.json");
//        AjaxResult ajaxResult = AjaxResult.success();
//        ajaxResult.putAll(jsonObject);
//        return ajaxResult;
//    }
    public AjaxResult conversation(@RequestParam("conversationKey") String conversationKey) {
//        conversationKey = "0082596802a681008d44bb4234028f61";
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("conversation",messageService.getConversation(conversationKey));
        return ajaxResult;
    }


    @GetMapping("/contacts")
    public AjaxResult contacts() {
        return getFileAjaxResult("contacts.json");
    }


    @GetMapping("/participants")
    public AjaxResult participants(@RequestParam("conversationKey") String conversationKey) {
        AjaxResult success = AjaxResult.success();
        Account account = userService.getById(conversationKey);
        User user = SecurityUtils.convertAccountToUser(account);
        List<Participant> list1 = new ArrayList<>();
        list1.add(messageService.getParticipant(user));
        success.put("participants",list1);
        return success;
//        return getFileAjaxResult("participants.json");
    }

    public AjaxResult getFileAjaxResult(String fileName) {
        File file = new File("src/main/resources/chat/" + fileName);
        StringBuffer sb = new StringBuffer();
        try (FileInputStream fis = new FileInputStream(file);) {
            int len;
            byte[] buffer = new byte[4096];
            while ((len = fis.read(buffer)) > 0) {
                sb.append(new String(Arrays.copyOfRange(buffer, 0, len)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.putAll(JSONObject.parseObject(sb.toString()));
        return ajaxResult;
    }
}

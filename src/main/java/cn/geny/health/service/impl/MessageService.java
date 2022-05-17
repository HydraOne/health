package cn.geny.health.service.impl;

import cn.geny.health.bo.ConversationBO;
import cn.geny.health.bo.MessageBO;
import cn.geny.health.bo.Participant;
import cn.geny.health.bo.User;
import cn.geny.health.mapper.MessageMapper;
import cn.geny.health.po.Account;
import cn.geny.health.po.Message;
import cn.geny.health.service.impl.UserService;
import cn.geny.health.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TODO
 * @author wangjiahao
 * @date 2022/5/14 23:16
 */
@Service
public class MessageService extends ServiceImpl<MessageMapper, Message> {
    @Autowired
    UserService userService;

    public boolean saveMessageBO(MessageBO messageBO){
        Message message = new Message();
        message.setId(messageBO.getId());
        message.setMsg(messageBO.getBody());
        message.setToUser(messageBO.getConversationId());
        message.setContentType(messageBO.getContentType());
        return this.save(message);
    }

    public List<ConversationBO> getConversations(List<String> contacts){
        String userid = SecurityUtils.getUserId();

        List<ConversationBO> conversationBOs = new ArrayList<>();

        Account currentAccount = userService.getById(userid);
        User currentUser = SecurityUtils.convertAccountToUser(currentAccount);
        Participant currentParticipant = getParticipant(currentUser);

        contacts.forEach(contact->{
            ConversationBO conversationBO = new ConversationBO();
            conversationBO.setId(contact);
            Account account = userService.getById(conversationBO.getId());
            User user = SecurityUtils.convertAccountToUser(account);
            Participant participant = getParticipant(user);
            List<Participant> participants = new ArrayList<>();
            participants.add(currentParticipant);
            participants.add(participant);
            conversationBO.setParticipants(participants);
            List<String> ids= new ArrayList<>();
            ids.add(userid);
            ids.add(user.getId());
            List<Message> messages = this.list(new QueryWrapper<Message>().in("to_user", ids).in("create_by", ids).orderByAsc("create_time"));
            List<MessageBO> messageBOList = messages.stream().map(message -> {
                MessageBO messageBO = new MessageBO();
                messageBO.setSenderId(message.getCreateBy());
                messageBO.setId(message.getToUser());
                messageBO.setCreatedAt(message.getCreateTime());
                String contentType = message.getContentType();
                messageBO.setContentType(StringUtils.isNotBlank(contact)?contentType:"text");
                messageBO.setBody(message.getMsg());
                return messageBO;
            }).collect(Collectors.toList());
            conversationBO.setMessages(messageBOList);
            conversationBOs.add(conversationBO);
        });
        return conversationBOs;
    }

    public Participant getParticipant(User user){
        Participant participant = new Participant();
        participant.setId(user.getId());
        participant.setAvatar(user.getPhotoURL());
        participant.setName(user.getDisplayName());
        participant.setUsername(user.getName());
        return participant;
    }

    public ConversationBO getConversation(String contact){
        List<String> contactList = new ArrayList<>();
        contactList.add(contact);
        List<ConversationBO> conversations = getConversations(contactList);
        return conversations.get(0);
    }

    public Set<String> getUserConversation(String currentUser){
        Set<String> contactList = new HashSet<>();
        List<Message> list = this.list(new QueryWrapper<Message>().eq("to_user", currentUser).or().eq("create_by", currentUser));
        list.forEach(item->{
            contactList.add(item.getToUser());
            contactList.add(item.getCreateBy());
        });
        contactList.remove(currentUser);
        return contactList;
    }

}

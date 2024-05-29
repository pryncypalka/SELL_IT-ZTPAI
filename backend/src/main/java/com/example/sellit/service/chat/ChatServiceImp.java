package com.example.sellit.service.chat;

import com.example.sellit.model.ChatData;
import com.example.sellit.repository.ChatDataRepository;
import com.example.sellit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImp implements ChatService{

    private ChatDataRepository chatDataRepository;

    @Autowired
    public ChatServiceImp(ChatDataRepository chatDataRepository, UserRepository userRepository) {
        this.chatDataRepository = chatDataRepository;
    }


    @Override
    public ChatData saveChatData(ChatData chatData) {

        return chatDataRepository.save(chatData);
    }
}

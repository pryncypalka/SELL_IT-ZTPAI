package com.example.sellit.controller;

import com.example.sellit.dto.ChatDataDto;
import com.example.sellit.dto.openAiRequest.ChatRequest;
import com.example.sellit.dto.openAiRequest.Content;
import com.example.sellit.dto.openAiRequest.Message;
import com.example.sellit.dto.openAiResponse.ChatResponse;
import com.example.sellit.model.ChatData;
import com.example.sellit.model.User;
import com.example.sellit.service.chat.ChatService;
import com.example.sellit.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/api/OpenAI")
public class ChatController {

    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;
    private final UserService userService;
    private final ChatService chatService;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    public ChatController(UserService userService, ChatService chatService) {
        this.userService = userService;
        this.chatService = chatService;

    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam String userText) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = userService.getUserIdByEmail(auth.getName());
        User user = userService.getUser(userId);

        Integer freeOffers = userService.getUserFreeOffers(auth.getName());
        if (freeOffers <= 0) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        else
        {
            userService.updateUserFreeOffers(userId, freeOffers - 1);
        }
        ChatRequest request = new ChatRequest(model, Arrays.asList(
                new Message("system", Arrays.asList(new Content("text", "You will receive a brief description of an item from the user that they wish to sell. Based on this description, write a template for the sales listing. Ensure the listing is written in the same language as the user's input. If the user does not provide important parameters for the item, include fields in the template for the user to fill in these details using the following format (in correct language): - characteristic/feature: [fill in (in correct language)] "))),
                new Message("user", Arrays.asList(new Content("text", userText)))
        ));


        ChatResponse openaiResponse = restTemplate.postForObject(apiUrl, request, ChatResponse.class);
        ChatData chatData = new ChatData();
        chatData.setUser(user);
        chatData.setPromptId(openaiResponse.getId());
        chatData.setTokenUsed(openaiResponse.getUsage().getTotal_tokens());
        chatService.saveChatData(chatData);

        String content = openaiResponse.getChoices().get(0).getMessage().getContent();

        return ResponseEntity.ok(content);
    }
}
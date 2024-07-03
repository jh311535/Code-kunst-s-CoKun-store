package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import service.ChatGptService;

@Controller
public class ChatController {
    @Autowired
    private ChatGptService chatGptService;

    @GetMapping("/chat")
    public String chat(@RequestParam(name = "prompt", required = false) String prompt,
                       @RequestParam(name = "chat", required = false) Boolean chat,
                       Model model, HttpSession session) {
        if (Boolean.TRUE.equals(chat)) {
            if (prompt != null && !prompt.isEmpty()) {
                String response = chatGptService.getChatGptResponse(prompt);
                
                // 세션에서 채팅 기록 가져오기
                List<Message> chatHistory = (List<Message>) session.getAttribute("chatHistory");
                if (chatHistory == null) {
                    chatHistory = new ArrayList<>();
                }
                
                // 사용자의 메시지 추가
                chatHistory.add(new Message("user", prompt));
                // 챗봇의 응답 추가
                chatHistory.add(new Message("bot", response));
                
                // 세션에 채팅 기록 저장
                session.setAttribute("chatHistory", chatHistory);
                
                model.addAttribute("response", response);
            }
        } else {
            // 세션 초기화
            session.removeAttribute("chatHistory");
        }
        return "chat";
    }
    
    public static class Message {
        private String sender;
        private String text;

        public Message(String sender, String text) {
            this.sender = sender;
            this.text = text;
        }

        public String getSender() {
            return sender;
        }

        public String getText() {
            return text;
        }
    }
}

package com.example.demo.controller;

import com.example.demo.entity.ChatMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/chatOllama")
public class ChatController {

    private final OllamaChatModel ollamaChatModel;

    @Autowired
    public ChatController(OllamaChatModel ollamaChatModel) {
        this.ollamaChatModel = ollamaChatModel;
    }

    @PostMapping("/send")
    public Mono<ResponseEntity<String>> sendMessage(@RequestBody ChatMessage message) {
        try {
            // 创建Prompt对象
            Prompt prompt = new Prompt(message.getContent(),
                    OllamaOptions.create()
                            .withModel("llama3")  // 使用指定的模型
            );

            // 调用Ollama模型
            ChatResponse chatResponse = ollamaChatModel.call(prompt);

            // 获取Ollama模型的响应内容
            String responseContent = chatResponse.getResult().getOutput().getContent();
            return Mono.just(ResponseEntity.ok().body(responseContent));
        } catch (Exception e) {
            e.printStackTrace();  // 实际部署时考虑适当的错误处理
            return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing your request: " + e.getMessage()));
        }
    }
}

package cn.ehai.chatgpt.controller;

import cn.ehai.chatgpt.aspect.RateLimit;
import cn.ehai.chatgpt.model.ChatGptDTO;
import cn.ehai.chatgpt.model.QuestionDTO;
import cn.ehai.chatgpt.service.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/openai")
public class OpenAiController {

    @Autowired
    ChatGPTService chatGPTService;


    @RateLimit(debug = false)
    @PostMapping  ("/search")
    public ChatGptDTO searchChatGpt(@RequestBody QuestionDTO questionDTO) throws ExecutionException, InterruptedException {

        return chatGPTService.searchChatGpt(questionDTO);
    }
}

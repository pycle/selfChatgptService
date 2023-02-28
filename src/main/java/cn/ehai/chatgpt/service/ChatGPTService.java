package cn.ehai.chatgpt.service;

import cn.ehai.chatgpt.model.ChatGptDTO;
import cn.ehai.chatgpt.model.QuestionDTO;

import java.util.concurrent.ExecutionException;

public interface ChatGPTService {


    ChatGptDTO searchChatGpt(QuestionDTO questionDTO) throws InterruptedException;
}

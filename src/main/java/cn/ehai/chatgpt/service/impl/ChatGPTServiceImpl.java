package cn.ehai.chatgpt.service.impl;

import cn.ehai.chatgpt.domain.ChatGptLog;
import cn.ehai.chatgpt.mapper.ChatGptLogMapper;
import cn.ehai.chatgpt.model.ChatGptDTO;
import cn.ehai.chatgpt.model.QuestionDTO;
import cn.ehai.chatgpt.service.ChatGPTService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;



@Service
public class ChatGPTServiceImpl implements ChatGPTService {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final BlockingQueue<Future<ChatGptDTO>> blockingQueue = new LinkedBlockingQueue<>();


    @Autowired
    ChatGptLogMapper chatGptLogMapper;
    ChatGptDTO chatgptresult = new ChatGptDTO();
    ChatGptLog chatgptlog = new ChatGptLog();
    ChatGptLog chatgptlog1 = new ChatGptLog();
    //    private final static  BlockingQueue<Future<ChatGptDTO>> requestQueue = new LinkedBlockingQueue<Future<cn.ehai.chatgpt.model.ChatGptDTO>>();
//    private static Boolean flag=true;
    private static final int flag = 0;


    @Override
    public ChatGptDTO searchChatGpt(QuestionDTO questionDTO) {
        String question=questionDTO.getQuestion();
        if (question.length() >= 400) {
            chatgptresult.setErrorCode(15000001);
            chatgptresult.setMessage("FAIL");
            chatgptresult.setSuccess(false);
            chatgptresult.setResult("问题超长 不能提交");
        }
        try {
            // 提交任务到线程池
            Future<ChatGptDTO> future = executorService.submit(() -> request_chat_gpt(questionDTO));

            // 将任务放入阻塞队列中
            blockingQueue.put(future);

            // 从阻塞队列中取出下一个任务并执行，直到队列为空
            while (!blockingQueue.isEmpty()) {
                future = blockingQueue.take();
                return future.get();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            chatgptresult.setErrorCode(15000001);
            chatgptresult.setMessage("FAIL");
            chatgptresult.setSuccess(false);
            chatgptresult.setResult("请求超时 稍后重试");
            return chatgptresult;

        }

        return chatgptresult;
    }
    private ChatGptDTO request_chat_gpt (QuestionDTO questionDTO){
            String question=questionDTO.getQuestion();
            String userinfo=questionDTO.getUserinfo();
            //先入一个日志
            chatgptlog1.setTo("chatGPT");
            chatgptlog1.setForm(userinfo);
            chatgptlog1.setContent(question);
//ThreadLocal
            List<String> key_list = new ArrayList<>();
            key_list.add("sk-xxx");
            key_list.add("sk-xxx");
            key_list.add("sk-xxx");
            String key = key_list.get(flag >= 3 ? 0 : flag + 1);
//        OpenAiService service = new OpenAiService(key, Duration.ofSeconds(60));
            OpenAiService service = new OpenAiService(key, Duration.ofSeconds(60));

            boolean state = true;
            try {
                CompletionRequest completionRequest = CompletionRequest.builder()
                        .model("text-davinci-003")
                        .prompt(question)
                        .topP(1.0)
//                .echo(true)
                        .user("testing")
                        .n(1)
                        .temperature(0.0)
                        .maxTokens(800)
                        .build();
//        System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(60000));
                CompletionResult completionresult = service.createCompletion(completionRequest);
                System.out.println(completionresult.getChoices());
                String id = completionresult.getId();
//            completionresult.getChoices().forEach(System.out::println);
//            String a=completionresult.getChoices().get(0).getText();
                String output = completionresult.getChoices().get(0).getText();
                chatgptresult.setErrorCode(0);
                chatgptresult.setMessage("SUCCESS");
                chatgptresult.setSuccess(state);
                chatgptresult.setResult(output);

                chatgptlog1.setContinuationId(id);
                chatgptlog1.setState(0);

                chatgptlog.setTo(userinfo);
                chatgptlog.setForm("chatGPT");
                chatgptlog.setContent(output);
                chatgptlog.setContinuationId(id);
                chatgptlog.setState(0);
                save_log(chatgptlog);
//                LocalDateTime now = LocalDateTime.now();
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
//                String formattedNow = now.format(formatter);
//                System.out.println("Current time: " + formattedNow);
                return chatgptresult;
            } catch (Exception e) {
                System.out.println(e);
                chatgptresult.setErrorCode(15000001);
                chatgptresult.setMessage("FAIL");
                chatgptresult.setSuccess(state);
                chatgptresult.setResult("请求超时 稍后重试");
                chatgptlog1.setContinuationId("FAIL");
                chatgptlog1.setState(1);
                return chatgptresult;

            } finally {
                save_log(chatgptlog1);
            }
        }
        private void save_log (ChatGptLog chatgptlog){
            chatGptLogMapper.insert(chatgptlog);
        }


}
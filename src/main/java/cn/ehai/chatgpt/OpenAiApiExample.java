package cn.ehai.chatgpt;


import com.theokanning.openai.OpenAiApi;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import java.util.ArrayList;
import java.util.List;

public class OpenAiApiExample {
    public static void getchatgpt(String[] args) throws Exception {
        List<String> key_list=new ArrayList<>();
        key_list.add("sk-xxx");
        key_list.add("sk-xxx");
//        MyOpenAiApi myOpenAiApi = new MyOpenAiApi();
//        OpenAiService service = new OpenAiService();
        OpenAiService service = new OpenAiService("sk-xxx");
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("text-davinci-002")
                .prompt("java写个闭包")
//                .echo(true)
                .user("testing")
                .temperature(0.0)
                .n(1)
                .maxTokens(800)
                .build();
        CompletionResult result = service.createCompletion(completionRequest);
//        String id = result.getId();
        service.createCompletion(completionRequest).getChoices().forEach(System.out::println);

    }
    /*
    *   使用更低的temperature 使得回答更稳定
    *   增加重试机制，重新发起API调用。
    *   限制并发和频次，做个队列
    *   回答结果用打字机特效展示 降低用户感知
    *   做缓存做增量，或者批量问
    *   用更原始的模型（ 备用
    *   增加请求超时时间
    *   vpn的问题
    * */
}
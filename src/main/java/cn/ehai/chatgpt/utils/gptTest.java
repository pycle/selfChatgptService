package cn.ehai.chatgpt.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.OpenAiApi;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import javax.annotation.processing.Completion;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.theokanning.openai.service.OpenAiService.*;

public class gptTest {
    static void aaa(){
        ObjectMapper mapper = defaultObjectMapper();
        OkHttpClient client = defaultClient("sk-xxx", Duration.ofSeconds(60))
                .newBuilder()
//                .proxy()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60,TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = defaultRetrofit(client, mapper);

        OpenAiApi api = retrofit.create(OpenAiApi.class);
        OpenAiService service = new OpenAiService(api);

        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt("如何对大量新闻进行特征提取和分类")
                .echo(false)
                .user("testing")
                .temperature(0.0)
                .n(1)
                .maxTokens(800)
                .build();

        CompletionResult re = service.createCompletion(completionRequest);
        System.out.println(re.getId());
        String completionId = re.getId();

        re.getChoices().forEach(System.out::println);
    }


    public static void main(String[] args) throws Exception {
        aaa();
    }


}

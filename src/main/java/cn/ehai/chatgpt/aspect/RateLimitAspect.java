package cn.ehai.chatgpt.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.ehcache.impl.internal.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;


@Aspect
@Component
public class RateLimitAspect {

    private static final int MAX_REQUESTS = 20; // 最大请求数量
    private static final int INTERVAL_SECONDS = 60; // 限制时间间隔，单位：秒

    private Map<String, LinkedList<Long>> requests = new ConcurrentHashMap<>();

//    @Around("execution(* cn.ehai.chatgpt.controller.OpenAiController.searchChatGpt())") //
    @Around("@annotation(rateLimit)")
    public Object Around(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        LinkedList<Long> times = requests.get(methodName);
        long now = System.currentTimeMillis();
        if (times == null) {
            times = new LinkedList<>();
            times.addLast(now);
//            System.out.println("aaaaaa"+times.size());
            requests.put(methodName, times);
        } else {
            // 清理超过时间间隔的请求记录
            while (!times.isEmpty() && times.peekFirst() < now - INTERVAL_SECONDS * 1000) {
                times.removeFirst();
            }
            // 检查请求次数是否超过限制
            if (times.size() >= MAX_REQUESTS) {
                throw new RuntimeException("请求过于频繁，请等待 " + INTERVAL_SECONDS + " 秒钟后再试。");
            }
            times.addLast(now);
        }
        return joinPoint.proceed();
    }

}


package cn.ehai.chatgpt;
import org.mybatis.spring.annotation.MapperScan;
//import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;

import java.util.concurrent.ExecutorService;


@SpringBootApplication
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@MapperScan("cn.ehai.chatgpt.mapper")
@EnableAspectJAutoProxy  // 启用 AOP 功能
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

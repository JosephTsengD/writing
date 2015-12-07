package ncu.edu.writing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/*
 * @EnableAutoConfiguration
 * SpringApplication
 * 由Spring Boot框架提供。
 * SpringApplication.run()方法執行後，Spring Boot的autoconfigure確認這是一個WEB應用
 * 接著在内嵌的Tomcat容器中Spring application context，提供 port: 8080， http://localhost:8080
 * 同時在Spring Context中根據默認的設定配置Spring WebMvc
 * */
@Configuration
@EnableWebMvc
@EnableAutoConfiguration
@ComponentScan
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

package ncu.edu.writing.autoconfig;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebMvcAutoConfiguration extends WebMvcAutoConfigurationAdapter{
	
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
//			registry.addInterceptor(interceptor)
		};
}

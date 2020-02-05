package main.java.com.codingcon.config;

import main.java.com.codingcon.model.AreaMap;
import main.java.com.codingcon.service.OptimizerService;
import main.java.com.codingcon.service.OptimizerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static main.java.com.codingcon.model.AreaMap.initializeMap;

@Configuration
public class SpringConfig {
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/").allowedOrigins("http://localhost:9000");
//            }
//        };
//    }

    @Bean
    public OptimizerService OptimizerService(){
        return new OptimizerServiceImpl();
    }

    @Bean
    public AreaMap AreaMap(){
        return initializeMap();
    }

}

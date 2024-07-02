package by.vhundred.filters.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*") // Разрешить запросы с любых источников
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Разрешенные HTTP методы
                .allowedHeaders("*"); // Разрешенные заголовки
    }
}
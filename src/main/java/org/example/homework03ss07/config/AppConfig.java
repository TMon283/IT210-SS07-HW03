package org.example.homework03ss07.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.example.homework03ss07")

//để cấu hình được các đường dẫn tương đối
public class AppConfig implements WebMvcConfigurer {
    // 1. Bean SpringResourceTemplateResolver - đọc cấu hình folder file view
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    // 2. Bean SpringTemplateEngine - bộ máy trung tâm xử lý và chuyển các cú pháp thymeleaf
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        // Để sử dụng được layout dialect của thymeleaf phải thêm vào
//        engine.setDialect(new LayoutDialect());

        return engine;
    }

    // 3. Bean ThymeleafViewResolver - giao tiếp Spring MVC với Thymeleaf
    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = "C:\\Users\\ADMIN\\Desktop\\homework03-ss07\\src\\main\\webapp\\images";
        //đường dẫn tương đối chúng ta sử dụng
        registry.addResourceHandler("/images/**")
                // đường dẫn tuyệt đối chỉ đến đích danh thư mục chính xác
                .addResourceLocations("file:" + path);
    }
}
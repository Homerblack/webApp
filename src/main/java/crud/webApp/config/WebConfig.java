package crud.webApp.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public  void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/uploads/property_images/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/uploads/property_images/");

        // Optional: Keep default static locations (good practice)
        registry.addResourceHandler("/**")
                .addResourceLocations(
                        "classpath:/static/",
                        "classpath:/public/",
                        "classpath:/resources/",
                        "classpath:/META-INF/resources/"
                );
    }
}

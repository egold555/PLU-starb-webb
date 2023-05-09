package webb.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.pattern.PathPatternParser;

@Configuration
public class TrailingSlashConfiguration implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        System.out.println("Configuring trailing slash");
        configurer.setUseTrailingSlashMatch(true);

        //PathPatternParser.defaultInstance.setMatchOptionalTrailingSeparator(true);
    }
}

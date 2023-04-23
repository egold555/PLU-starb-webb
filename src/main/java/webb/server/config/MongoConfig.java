package webb.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import webb.shared.dtos.helpers.CellDTOReadingConverter;
import webb.shared.dtos.helpers.CellDTOWritingConverter;

import java.util.Arrays;

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = "webb")
public class MongoConfig {

    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(new CellDTOWritingConverter(), new CellDTOReadingConverter()));
    }
}

package duy.project.yoot_management.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true) // Map by field directly (Without using getter/setter)
                .setMatchingStrategy(MatchingStrategies.STRICT) // Mapp exact field name
                .setAmbiguityIgnored(true); // Ignore ambiguity when multiple fields match

        return modelMapper;
    }

}

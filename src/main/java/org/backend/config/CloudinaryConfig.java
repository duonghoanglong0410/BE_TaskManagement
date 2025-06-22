package org.backend.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dmk9yyuox",
                "api_key", "493722934577789",
                "api_secret", "JInCWHN_V4AXk9bls48W2IlBBFg"
        ));
    }
}

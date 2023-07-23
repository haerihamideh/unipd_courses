package com.unipd.semicolon;

import com.unipd.semicolon.business.constants.Constants;
import com.unipd.semicolon.business.constants.ConstantsBean;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.unipd.semicolon")
@EntityScan(basePackages = {"com.unipd.semicolon.core.entity"})
@ServletComponentScan
@EnableJpaRepositories(basePackages = {"com.unipd.semicolon.core.repository", "com.unipd.semicolon.core.dao"})
@EnableScheduling
public class SemicolonApplication extends SpringBootServletInitializer {

    private final ConstantsBean constantsBean;

    public SemicolonApplication(ConstantsBean constantsBean) {
        this.constantsBean = constantsBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(SemicolonApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SemicolonApplication.class);
    }

    @PostConstruct
    public void initiateLocalUploaderApplication() {
        Constants.initialize(constantsBean);
    }
}

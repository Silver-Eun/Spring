package com.spring.songjava.configuration;

import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.ResourceLoader;

import java.util.Properties;

@Configuration
public class GlobalConfig {

    final Logger logger = (Logger) LoggerFactory.getLogger(getClass());

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ResourceLoader resourceLoader;

    private String uploadFilePath;
    private String uploadResourcePath;
    private String scheduleCronExample1;

    private Boolean local;
    private Boolean dev;
    private Boolean prod;

    @PostConstruct
    public void init() {
        logger.info("init");
        String[] activeProfiles = context.getEnvironment().getActiveProfiles();
        String activeProfile = "local";
        if (ObjectUtils.isNotEmpty(activeProfiles)) {
            activeProfile = activeProfiles[0];
        }
        String resourcePath = String.format("classpath:globals/global-%s.properties", activeProfile);
        try {
            Resource resource = resourceLoader.getResource(resourcePath);
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);

            this.uploadFilePath = properties.getProperty("uploadPath");
            this.uploadResourcePath = properties.getProperty("uploadFile.resourcePath");
            this.scheduleCronExample1 = properties.getProperty("schedule.cron.example1");
            this.local = activeProfile.equals("local");
            this.dev = activeProfile.equals("dev");
            this.prod = activeProfile.equals("prod");
        } catch (Exception e) {
            logger.error("e", e);
        }
    }

    public String getUploadFilePath() {
        return uploadFilePath;
    }

    public String getScheduleCronExample1() {
        return scheduleCronExample1;
    }

    public String getUploadResourcePath() {
        return uploadResourcePath;
    }

    public boolean isLocal() {
        return local;
    }

    public boolean isDev() {
        return dev;
    }

    public boolean isProd() {
        return prod;
    }
}

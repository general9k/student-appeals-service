package ru.ystu.studentappealsservice.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Configuration
@EnableJpaRepositories(basePackages = {"ru.ystu.studentappealsservice.repository"})
@EntityScan(basePackages = {"ru.ystu.studentappealsservice.model"})
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
public class ApplicationConfig {

    @Bean
    public DateTimeProvider auditingDateTimeProvider() {
        return () -> Optional.of(OffsetDateTime.now(ZoneOffset.UTC).truncatedTo(ChronoUnit.SECONDS));
    }
}

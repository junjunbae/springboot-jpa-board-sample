package com.example.sampleboard.config;

import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaAuditConfig {

    /**
     * 도메인들에 공통적으로 존재하는 컬럼에 자동으로 값을 넣어주는 기능
     * > 생성자, 수정자 : 생성/수정 시 접속한 계정을 기록 (security context에서 계정정보를 조회한 후 name으로 대입)
     * > 생성일시 : 최초 생성일시
     * > 수정일시 : 마지막 수정일시
     */

    @Bean
    public AuditorAware<String> auditorProvider(){
        return new AuditorAwareImpl();
    }

    private class AuditorAwareImpl implements AuditorAware<String> {

        @Override
        @NonNull
        public Optional<String> getCurrentAuditor() {
            return Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());
        }
    }
}

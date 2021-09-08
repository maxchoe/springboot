package com.koscom.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration //Spring에서 설정 클래스 임을 알려주는 annotation
@EnableJpaAuditing
public class JpaConfig {
}

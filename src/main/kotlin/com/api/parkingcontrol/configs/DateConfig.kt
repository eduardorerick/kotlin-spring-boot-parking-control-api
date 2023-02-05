package com.api.parkingcontrol.configs

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import java.time.format.DateTimeFormatter


@Configuration
class DateConfig {
    companion object {
        private const val DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val LocalDateTimeSerializer = LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT))
    }

    @Bean
    @Primary
    fun objectMapper(): ObjectMapper {
        val module = JavaTimeModule()
        module.addSerializer(LocalDateTimeSerializer)
        return ObjectMapper().registerModule(module)
    }


}
package com.github.juliocesarscheidt.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseService {

  protected Logger logger = LoggerFactory.getLogger(BaseService.class);

  protected Timestamp getTimestamp() {
    ZonedDateTime zdt = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("America/Sao_Paulo"));
    return Timestamp.valueOf(zdt.toLocalDateTime());
  }
}

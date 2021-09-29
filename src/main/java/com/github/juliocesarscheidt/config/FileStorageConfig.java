package com.github.juliocesarscheidt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageConfig {

  private String uploadDir;

  public FileStorageConfig() {
  }

  public FileStorageConfig(String uploadDir) {
    this.uploadDir = uploadDir;
  }

  public String getUploadDir() {
    return uploadDir;
  }

  public void setUploadDir(String uploadDir) {
    this.uploadDir = uploadDir;
  }
}

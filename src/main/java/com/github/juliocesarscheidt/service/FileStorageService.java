package com.github.juliocesarscheidt.service;

import com.github.juliocesarscheidt.config.FileStorageConfig;
import com.github.juliocesarscheidt.exception.FileNotFoundException;
import com.github.juliocesarscheidt.exception.FileStorageException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService extends BaseService {

  private final Path fileStorageLocation;

  @Autowired
  public FileStorageService(FileStorageConfig fileStorageConfig) {
    this.fileStorageLocation =
        Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();
    System.out.println("fileStorageLocation" + fileStorageLocation);

    try {
      Files.createDirectories(this.fileStorageLocation);
    } catch (Exception e) {
      throw new FileStorageException("Could not create the directory to store files", e);
    }
  }

  public String storeFile(MultipartFile file) {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    System.out.println("fileName" + fileName);

    try {
      if (fileName.contains("..")) {
        throw new FileStorageException("Filename is invalid " + fileName);
      }

      Path targetLocation = this.fileStorageLocation.resolve(fileName).normalize();
      System.out.println("targetLocation " + targetLocation);

      Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

      return fileName;

    } catch (Exception e) {
      throw new FileStorageException("Could not store file " + fileName, e);
    }
  }

  public Resource loadFileAsResource(String fileName) {
    try {
      Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
      System.out.println("filePath " + filePath);

      Resource resource = new UrlResource(filePath.toUri());
      if (resource.exists()) {
        return resource;
      }

      throw new FileNotFoundException("File not found " + fileName);

    } catch (Exception e) {
      throw new FileNotFoundException("File not found " + fileName, e);
    }
  }
}

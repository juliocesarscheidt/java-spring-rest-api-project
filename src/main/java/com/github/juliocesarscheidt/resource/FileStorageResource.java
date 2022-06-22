package com.github.juliocesarscheidt.resource;

import com.github.juliocesarscheidt.data.dto.FileStorageResponseDTO;
import com.github.juliocesarscheidt.service.FileStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Api(
    value = "File Endpoint",
    tags = {"File"})
@RestController
@RequestMapping("/v1/file")
public class FileStorageResource extends BaseResource {

  protected Logger logger = LoggerFactory.getLogger(FileStorageResource.class);

  @Autowired private FileStorageService fileStorageService;

  @ApiOperation(value = "Upload File")
  @PostMapping(
      value = "/uploadFile",
      produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.OK)
  public FileStorageResponseDTO uploadFile(@RequestParam("file") MultipartFile file) {
    String fileName = fileStorageService.storeFile(file);
    String fileDownloadUri =
        ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/v1/file/downloadFile/")
            .path(fileName)
            .toUriString();

    return new FileStorageResponseDTO(
        fileName, fileDownloadUri, file.getContentType(), file.getSize());
  }

  @ApiOperation(value = "Download File")
  @GetMapping(
      value = "/downloadFile/{fileName:.+}",
      produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.OK)
  public ResponseEntity<Resource> downloadFile(
      @PathVariable String fileName, HttpServletRequest request) {
    Resource resource = fileStorageService.loadFileAsResource(fileName);
    String contentType = null;

    try {
      contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
    } catch (Exception e) {
      logger.error(e.getMessage());
    }

    if (contentType == null) {
      contentType = "application/octet-stream";
    }

    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(contentType))
        .header(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + resource.getFilename() + "\"")
        .body(resource);
  }
}

package com.example.triodb.files;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@Slf4j
public class FileController {
    private final FileService fileService;
    @Autowired
    FileController(FileService fileService){
        this.fileService = fileService;
    }
    @GetMapping("/images/db/{fileName}")
    public ResponseEntity<ByteArrayResource> retrieve(@PathVariable String fileName){
    Files file = fileService.getFile(fileName);
    ByteArrayResource body = new ByteArrayResource(file.getData());

    return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, file.getMimeType()).body(body);
    }

    @PostMapping("images/db/upload")
    public SaveResult upload(@RequestPart MultipartFile file){
        try{
            Files files = fileService.save(file);
            return SaveResult.builder().error(false).fileName(files.getFileName())
                    .link(createImageLink(files.getFileName())).build();
        } catch (Exception e){
            log.error("Failed to save image", e);
            return SaveResult.builder().error(true).fileName(file.getOriginalFilename()).build();
        }
    }

    @DeleteMapping("images/db/delete")
    public ResponseEntity<String> delete(@RequestParam String fileName){
        fileService.delete(fileName);
        return ResponseEntity.ok("{\"line\":\"File deleted\"}");
    }

    private String createImageLink(String fileName){
        return ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/images/db/" + fileName)
            .toUriString();
    }
}

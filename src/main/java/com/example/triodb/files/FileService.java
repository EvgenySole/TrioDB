package com.example.triodb.files;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
@Slf4j
public class FileService {
    private final FileRepository fileRepository;
    @Autowired
    FileService(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }
    Files getFile(String fileName){
        return fileRepository.findByFileName(fileName).orElseThrow(FilesNotFoundException::new);
    }

    public Files save(MultipartFile file) throws IOException {
        if (fileRepository.existsByFileName(file.getOriginalFilename())){
            log.info("Image {} have already existed", file.getOriginalFilename());
            return Files.builder().fileName(file.getOriginalFilename()).build();
        }
        Files filesBuilder = Files.builder().fileName(file.getOriginalFilename()).
                mimeType(file.getContentType()).data(file.getBytes()).build();
        return fileRepository.save(filesBuilder);
    }

    public void delete(String fileName) {
        Files files = fileRepository.findByFileName(fileName).orElseThrow(FilesNotFoundException::new);
        boolean exists = fileRepository.existsByFileName(fileName);
        if (!exists){
            throw new IllegalStateException("File " + files + " doesn't exist");
        }
        fileRepository.delete(files);
    }
}

package com.example.triodb.files;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface FileRepository extends CrudRepository<Files, Long> {
    Optional<Files> findByFileName(String filename);
    boolean existsByFileName(String fileName);
}

package com.example.triodb.files;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class SaveResult {
    private boolean error;
    private String fileName;
    private String link;
}

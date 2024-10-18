package com.example.triodb.files;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
@Table(name = "files")
public class Files {
    @Id
    @Column(name = "file_id", nullable = false)
    @SequenceGenerator(
            name = "files_sequence",
            sequenceName = "files_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "files_sequence"
    )
    private Long fileId;

    private String fileName;

    @Column(name = "mime_type")
    private String mimeType;

    private byte[] data;
}

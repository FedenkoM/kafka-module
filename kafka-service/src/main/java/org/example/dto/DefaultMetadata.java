package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultMetadata {

    private String eventVersion;
    private String eventSource;
    private String eventName;
    private LocalDateTime eventTime;

}

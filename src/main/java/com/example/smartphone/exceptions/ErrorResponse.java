package com.example.smartphone.exceptions;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
public class ErrorResponse {
    private String errorMsg;
    private LocalDateTime Timestamp;
    private int status;
}

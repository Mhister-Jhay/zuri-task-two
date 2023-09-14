package org.jhay.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@AllArgsConstructor
@Builder
@Data
public class ExceptionResponse {
    private String message;
    private String path;
    private String processedTime;
    private int statusCode;
}

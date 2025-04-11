package com.chikodiann.employee_mgmt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "ResponseDTO",
        description = "Schema representing a standard successful response"
)
@Data @AllArgsConstructor
public class ResponseDTO {

    @Schema(description = "HTTP status code as a string", example = "200")
    private String statusCode;

    @Schema(description = "Message describing the outcome of the operation", example = "Request processed successfully")
    private String statusMsg;

}

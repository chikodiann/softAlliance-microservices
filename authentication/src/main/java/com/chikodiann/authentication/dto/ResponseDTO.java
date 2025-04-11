package com.chikodiann.authentication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "Response",
        description = "Standard format for successful API responses"
)
@Data @AllArgsConstructor
public class ResponseDTO {

    @Schema(
            description = "HTTP status code of the response",
            example = "200"
    )
    private String statusCode;

    @Schema(
            description = "Brief message describing the result",
            example = "Request processed successfully"
    )
    private String statusMsg;

}

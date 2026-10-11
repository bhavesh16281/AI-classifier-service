package com.bhavesh16281.ai_classifier_service.dto;

import jakarta.validation.constraints.NotBlank;

public record ClassifyRequest(

    @NotBlank(message = "Subject must not be blank")
    String subject,

    @NotBlank(message = "Description must not be blank")
    String description
) {
    
}

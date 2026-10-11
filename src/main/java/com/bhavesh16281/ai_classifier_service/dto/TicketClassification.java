package com.bhavesh16281.ai_classifier_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record TicketClassification(

    @JsonPropertyDescription("The Customer's underlying issue category.One of: BILLING, TECHNICAL_ISSUE, ACCOUNT_ACCESS, FEATURE_REQUEST, GENERAL_INQUIRY")
    String intent,

    @JsonPropertyDescription("The priority of the ticket. One of: POSITIVE, NEUTRAL, NEGATIVE")
    String sentiment,

    @JsonPropertyDescription ("How urgently this ticket needs to be handled. One of: LOW, MEDIUM, HIGH, URGENT")
    String priority,

    @JsonPropertyDescription("The support team best suited to handle this ticket. One of: BILLING_TEAM, TECHNICAL_SUPPORT, ACCOUNT_MANAGEMENT, FEATURE_TEAM, GENERAL_SUPPORT")
    String supportTeam
) {
    
}

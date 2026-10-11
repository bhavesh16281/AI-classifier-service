package com.bhavesh16281.ai_classifier_service.service;

import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

@Service 
public class TicketClassifierService {
    
    private static final PromptTemplate CLASSIFY_TEMPLATE = new PromptTemplate(
        """
                You are a support ticket triage assistant for a SaaS company. 
                Read the ticket below and classify it honestly, using only the text
                given.

                Subject: {subject}
                Description: {description}

                Rules:
                - intent must be one of: BILLING, TECHNICAL_ISSUE, ACCOUNT_ACCESS, FEATURE_REQUEST,GENERAL_INQUIRY
                - sentiment must be one of: POSITIVE, NEUTRAL, NEGATIVE
                - priority must be one of: LOW, MEDIUM, HIGH, URGENT
                - suggestedTeam must be one of: The support team best suited to own this ticket. One of: BILLING_TEAM, TECHNICAL_SUPPORT, ACCOUNT_MANAGEMENT, PRODUCT_TEAM, GENERAL_SUPPORT
                """     
    );

    // private final ChatClient chatClient;
}

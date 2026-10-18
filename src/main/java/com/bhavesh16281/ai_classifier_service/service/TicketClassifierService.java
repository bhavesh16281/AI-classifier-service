package com.bhavesh16281.ai_classifier_service.service;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import com.bhavesh16281.ai_classifier_service.dto.ClassifyRequest;
import com.bhavesh16281.ai_classifier_service.dto.TicketClassification;
import com.bhavesh16281.ai_classifier_service.exception.AiProviderException;

import io.github.resilience4j.retry.annotation.Retry;

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

    private final ChatClient chatClient;

    public TicketClassifierService(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @Retry(name = "groq-classify")
    public TicketClassification classify(ClassifyRequest request) {

        Prompt prompt = CLASSIFY_TEMPLATE.create(Map.of(
            "subject", request.subject(),
            "description", request.description()
        ));
        
        try{

            TicketClassification classification = chatClient.prompt(prompt).call().entity(TicketClassification.class);

            if (classification == null) {
                throw new AiProviderException("Classification failed: result is null");
            }

            return classification;
        }catch(AiProviderException e){
            System.out.println("Classification failed: " + e.getMessage());
            throw e;
        }catch(Exception e){
            System.out.println("Classification failed: " + e.getMessage());
            throw new AiProviderException("Failed to classify ticket", e);
        }       
    }
}

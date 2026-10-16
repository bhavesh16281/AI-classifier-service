package com.bhavesh16281.ai_classifier_service.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bhavesh16281.ai_classifier_service.service.TicketClassifierService;

import jakarta.validation.Valid;

import com.bhavesh16281.ai_classifier_service.dto.ClassifyRequest;
import com.bhavesh16281.ai_classifier_service.dto.TicketClassification;
@RestController 
@RequestMapping("api/v1")
public class ClassifierContoller {

    private final TicketClassifierService ticketClassifierService;

    public ClassifierContoller(TicketClassifierService ticketClassifierService) {
        this.ticketClassifierService = ticketClassifierService;
    }

    @PostMapping("/classify")
    public ResponseEntity<TicketClassification> classify(@RequestBody @Valid ClassifyRequest request) {

        return ResponseEntity.ok(ticketClassifierService.classify(request));
    }
    
}

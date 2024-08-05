package com.develop.estore.ProductService.command.controllers;

import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.TrackingEventProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("management")
public class EventsReplayController {

    private final EventProcessingConfiguration eventProcessingConfiguration;

    public EventsReplayController(EventProcessingConfiguration eventProcessingConfiguration) {
        this.eventProcessingConfiguration = eventProcessingConfiguration;
    }

    @PostMapping("/event-processer/{processorName}/reset")
    public ResponseEntity<?> replayEvents(@PathVariable String processorName) {
        Optional<TrackingEventProcessor> trackingEventProcessor = eventProcessingConfiguration.eventProcessor(processorName, TrackingEventProcessor.class);
        if(trackingEventProcessor.isPresent()) {
            TrackingEventProcessor eventProcessor = trackingEventProcessor.get();
            eventProcessor.shutDown();
            eventProcessor.resetTokens();
            eventProcessor.start();
            return ResponseEntity.ok().body(String.format("The event processor with name %s has been reset", processorName));
        } else {
            return ResponseEntity.badRequest().body(String.format("The event processor with name %s is not a tracking event processor", processorName));
        }
    }
}

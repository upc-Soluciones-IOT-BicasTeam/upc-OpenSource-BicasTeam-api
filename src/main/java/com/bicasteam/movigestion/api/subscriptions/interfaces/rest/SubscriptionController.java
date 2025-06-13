package com.bicasteam.movigestion.api.subscriptions.interfaces.rest;

import com.bicasteam.movigestion.api.subscriptions.domain.model.aggregates.Subscription;
import com.bicasteam.movigestion.api.subscriptions.domain.model.commands.CreateSubscriptionCommand;
import com.bicasteam.movigestion.api.subscriptions.domain.model.enums.SubscriptionState;
import com.bicasteam.movigestion.api.subscriptions.domain.services.SubscriptionCommandService;
import com.bicasteam.movigestion.api.subscriptions.domain.services.SubscriptionQueryService;
import com.bicasteam.movigestion.api.subscriptions.interfaces.rest.resources.CreateSubscriptionResource;
import com.bicasteam.movigestion.api.subscriptions.interfaces.rest.resources.SubscriptionResource;
import com.bicasteam.movigestion.api.subscriptions.interfaces.rest.transform.CreateSubscriptionCommandFromResourceAssembler;
import com.bicasteam.movigestion.api.subscriptions.interfaces.rest.transform.SubscriptionResourceFromEntityAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionCommandService commandService;
    private final SubscriptionQueryService queryService;

    public SubscriptionController(SubscriptionCommandService commandService, SubscriptionQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<SubscriptionResource> create(@RequestBody CreateSubscriptionResource resource) {
        CreateSubscriptionCommand command = CreateSubscriptionCommandFromResourceAssembler.toCommandFromResource(resource);
        Optional<Subscription> result = commandService.handle(command);
        return result.map(subscription ->
                        new ResponseEntity<>(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(subscription), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionResource>> getAll() {
        List<Subscription> list = queryService.findAll();
        return ResponseEntity.ok(list.stream()
                .map(SubscriptionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResource> getById(@PathVariable Long id) {
        return queryService.findById(id)
                .map(subscription -> ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(subscription)))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SubscriptionResource>> getByUserId(@PathVariable Long userId) {
        List<Subscription> list = queryService.findByUserId(userId);
        return ResponseEntity.ok(list.stream()
                .map(SubscriptionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList()));
    }

    @GetMapping("/payment-date/{date}")
    public ResponseEntity<List<SubscriptionResource>> getByPaymentDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Subscription> list = queryService.findByPaymentDate(date);
        return ResponseEntity.ok(list.stream()
                .map(SubscriptionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList()));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateByUserId(@PathVariable Long userId, @RequestBody CreateSubscriptionResource resource) {
        CreateSubscriptionCommand command = CreateSubscriptionCommandFromResourceAssembler.toCommandFromResource(resource);
        boolean updated = commandService.updateByUserId(userId, command);
        return updated ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean deleted = commandService.deleteById(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteByUserId(@PathVariable Long userId) {
        boolean deleted = commandService.deleteByUserId(userId);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

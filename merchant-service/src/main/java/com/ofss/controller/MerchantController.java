package com.ofss.controller;

import com.ofss.dto.MerchantRequest;
import com.ofss.dto.MerchantResponse;
import com.ofss.service.MerchantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {
    private final MerchantService merchantService;
    public MerchantController(MerchantService merchantService) { this.merchantService = merchantService; }
    @PostMapping public ResponseEntity<MerchantResponse> createMerchant(@Valid @RequestBody MerchantRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body(merchantService.createMerchant(request)); }
    @GetMapping("/{merchantId}") public ResponseEntity<MerchantResponse> getMerchantById(@PathVariable Long merchantId) { return ResponseEntity.ok(merchantService.getMerchantById(merchantId)); }
    @GetMapping public ResponseEntity<List<MerchantResponse>> getAllMerchants() { return ResponseEntity.ok(merchantService.getAllMerchants()); }
    @PutMapping("/{merchantId}") public ResponseEntity<MerchantResponse> updateMerchant(@PathVariable Long merchantId, @Valid @RequestBody MerchantRequest request) { return ResponseEntity.ok(merchantService.updateMerchant(merchantId, request)); }
    @DeleteMapping("/{merchantId}") public ResponseEntity<Void> deleteMerchant(@PathVariable Long merchantId) { merchantService.deleteMerchant(merchantId); return ResponseEntity.noContent().build(); }
}

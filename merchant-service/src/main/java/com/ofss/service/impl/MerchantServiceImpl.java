package com.ofss.service.impl;

import com.ofss.dto.MerchantRequest;
import com.ofss.dto.MerchantResponse;
import com.ofss.entity.Merchant;
import com.ofss.exception.MerchantNotFoundException;
import com.ofss.repository.MerchantRepository;
import com.ofss.service.MerchantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class MerchantServiceImpl implements MerchantService {
    private final MerchantRepository merchantRepository;
    public MerchantServiceImpl(MerchantRepository merchantRepository) { this.merchantRepository = merchantRepository; }
    @Override public MerchantResponse createMerchant(MerchantRequest request) {
        Merchant merchant = new Merchant();
        merchant.setMerchantName(request.getMerchantName()); merchant.setCategory(request.getCategory()); merchant.setLocation(request.getLocation());
        return mapToResponse(merchantRepository.save(merchant));
    }
    @Override @Transactional(readOnly = true) public MerchantResponse getMerchantById(Long merchantId) { return mapToResponse(findMerchant(merchantId)); }
    @Override @Transactional(readOnly = true) public List<MerchantResponse> getAllMerchants() { return merchantRepository.findAll().stream().map(this::mapToResponse).toList(); }
    @Override public MerchantResponse updateMerchant(Long merchantId, MerchantRequest request) {
        Merchant merchant = findMerchant(merchantId);
        merchant.setMerchantName(request.getMerchantName()); merchant.setCategory(request.getCategory()); merchant.setLocation(request.getLocation());
        return mapToResponse(merchantRepository.save(merchant));
    }
    @Override public void deleteMerchant(Long merchantId) { merchantRepository.delete(findMerchant(merchantId)); }
    private Merchant findMerchant(Long merchantId) { return merchantRepository.findById(merchantId).orElseThrow(() -> new MerchantNotFoundException("Merchant not found with ID: " + merchantId)); }
    private MerchantResponse mapToResponse(Merchant merchant) { return new MerchantResponse(merchant.getMerchantId(), merchant.getMerchantName(), merchant.getCategory(), merchant.getLocation()); }
}

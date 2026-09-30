package com.ofss.service;

import com.ofss.dto.MerchantRequest;
import com.ofss.dto.MerchantResponse;
import java.util.List;

public interface MerchantService {
    MerchantResponse createMerchant(MerchantRequest request);
    MerchantResponse getMerchantById(Long merchantId);
    List<MerchantResponse> getAllMerchants();
    MerchantResponse updateMerchant(Long merchantId, MerchantRequest request);
    void deleteMerchant(Long merchantId);
}

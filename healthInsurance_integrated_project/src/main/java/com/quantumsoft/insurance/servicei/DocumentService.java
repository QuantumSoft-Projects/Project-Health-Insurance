package com.quantumsoft.insurance.servicei;

import com.quantumsoft.insurance.entity.ClaimDocument;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {
    void uploadDocuments(Long claimId, MultipartFile[] files);

    //List<ClaimDocument> getDocuments(Long claimId);
}

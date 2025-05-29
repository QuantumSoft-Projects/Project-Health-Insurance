package com.quantumsoft.insurance.serviceimpl;

import com.quantumsoft.insurance.entity.Claim;
import com.quantumsoft.insurance.entity.ClaimDocument;
import com.quantumsoft.insurance.repository.ClaimRepository;
import com.quantumsoft.insurance.repository.DocumentRepository;
import com.quantumsoft.insurance.servicei.DocumentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final ClaimRepository claimRepository;
    private final DocumentRepository documentRepository;

    private final Path uploadDir = Paths.get("uploads");

    public DocumentServiceImpl(ClaimRepository claimRepository, DocumentRepository documentRepository) {
        this.claimRepository = claimRepository;
        this.documentRepository = documentRepository;
    }

    @Override
    public void uploadDocuments(Long claimId, MultipartFile[] files) {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));

        for (MultipartFile file : files) {
            validateFile(file);

            try {
                Files.createDirectories(uploadDir);
                String uniqueName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                Path path = uploadDir.resolve(uniqueName);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                ClaimDocument doc = new ClaimDocument();
                doc.setClaim(claim);
                doc.setFileName(file.getOriginalFilename());
                doc.setFileType(file.getContentType());
                doc.setFilePath(path.toString());
                doc.setUploadDate(LocalDateTime.now());

                documentRepository.save(doc);
            } catch (IOException e) {
                throw new RuntimeException("Could not store file " + file.getOriginalFilename(), e);
            }
        }
    }

    private void validateFile(MultipartFile file) {
        List<String> allowedTypes = Arrays.asList("application/pdf", "image/png", "image/jpeg");
        if (!allowedTypes.contains(file.getContentType())) {
            throw new RuntimeException("File type not allowed: " + file.getContentType());
        }

        if (file.getSize() > 5 * 1024 * 1024) { // 5MB
            throw new RuntimeException("File size exceeds 5MB limit: " + file.getOriginalFilename());
        }
    }

//    @Override
//    public List<ClaimDocument> getDocuments(Long claimId) {
//        return documentRepository.findByClaimId(claimId);
//    }

}

package com.quantumsoft.insurance.controller;

import com.quantumsoft.insurance.entity.Claim;
import com.quantumsoft.insurance.entity.ClaimDocument;
import com.quantumsoft.insurance.servicei.ClaimService;
import com.quantumsoft.insurance.servicei.DocumentService;
import com.quantumsoft.insurance.serviceimpl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/claim")
@CrossOrigin("*")
public class ClaimController {
    private final ClaimService claimService;
    private final DocumentService documentService;

    @Autowired
    private JwtService jwtService;

    public ClaimController(ClaimService claimService, DocumentService documentService) {
        this.claimService = claimService;
        this.documentService = documentService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Claim> submitClaim(@RequestBody Claim claim) {
        Claim createdClaim = claimService.submitClaim(claim);
        return ResponseEntity.ok(createdClaim);
    }


    @PostMapping(value = "/saveDocuments/{claimId}")
    public ResponseEntity<String> uploadDocuments(@PathVariable Long claimId,
                                                  @RequestParam("files") MultipartFile[] files) {
        System.out.println("in claim controller");
        documentService.uploadDocuments(claimId, files);
        return ResponseEntity.ok("Files uploaded successfully");
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<Claim>> getClaimsByUser(@PathVariable Long userId, @RequestHeader("Authorization")String bearerToken) {
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("CLAIMSMANAGER")) {
            List<Claim> claims = claimService.getClaimsByUser(userId);
            return ResponseEntity.ok(claims);
        }else
            return new ResponseEntity<List<Claim>>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(value = "/singleClaim/{claimId}")
    public ResponseEntity<Claim> getClaimById(@PathVariable Long claimId) {
        Claim claim = claimService.getClaim(claimId);
        return new ResponseEntity<>(claim, HttpStatus.OK);
    }

    @GetMapping(value = "/documents/{claimId}")
    public ResponseEntity<List<ClaimDocument>> getDocuments(@PathVariable Long claimId) {
        List<ClaimDocument> documents = claimService.getDocuments(claimId);
        return ResponseEntity.ok(documents);
    }

    @PutMapping(value = "/status/{claimId}")
    public ResponseEntity<String> updateStatus(@PathVariable Long claimId ,@RequestBody Claim claim, @RequestHeader("Authorization")String bearerToken) {
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("CLAIMSMANAGER")) {
            claimService.updateStatus(claimId, claim.getStatus());
            return ResponseEntity.ok("Claim status updated");
        }else
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }
}

package com.bankingmanagement.controller;

import com.bankingmanagement.entity.Branch;
import com.bankingmanagement.exception.BankDetailsNotFound;
import com.bankingmanagement.exception.BranchDetailsNotFound;
import com.bankingmanagement.model.BankTO;
import com.bankingmanagement.model.BranchTO;
import com.bankingmanagement.service.BranchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/branches")
public class BranchController {
    @Autowired
    private BranchService branchService;

    //http://localhost:9090/api/v2/banks GET  status code data
    @GetMapping
    public ResponseEntity<List<BranchTO>> getAllBranches(){
        log.info("Inside the BranchControllerV2.getAllBranches");

        List<BranchTO> branches;

        try{
            branches = branchService.findAll();
            log.info("Branch Details, branchTOs:{}", branches);
        }catch (BranchDetailsNotFound ex){
            log.error("Branch details not found", ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex1){
            log.error("Exception while getting branch details", ex1);
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of the BranchControllerV2.getAllBranches");
        return  new ResponseEntity<>(branches, HttpStatus.OK);
    }
}

package com.bankingmanagement.service;

import com.bankingmanagement.entity.Branch;
import com.bankingmanagement.exception.BankDetailsNotFound;
import com.bankingmanagement.exception.BranchDetailsNotFound;
import com.bankingmanagement.model.BranchTO;
import com.bankingmanagement.repository.BranchRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BranchServiceImpl implements BranchService{
    @Autowired
    private BranchRepo branchRepo;

    @Override
    public List<BranchTO> findAll() throws BranchDetailsNotFound {
        log.info("Inside the BranchServiceImplV2.findAll");

        List<Branch> branches = branchRepo.findAll();
        log.info("brancheslist","..."+branches.toString());

        if(CollectionUtils.isEmpty(branches)){
            log.error("Branches details not found");
            throw new BranchDetailsNotFound("Branch details not found");
        }

        List<BranchTO> branchTOS = branches.stream().map(branch -> {
            BranchTO branchTO = new BranchTO();
            branchTO.setBranchAddress(branch.getBranchAddress());
            branchTO.setBranchId(branch.getBranchId());
            branchTO.setBranchName(branch.getBranchName());
            return branchTO;
        }).collect(Collectors.toList());

        log.info("End of BranchServiceImpl.findAll");
        return  branchTOS;
    }
}

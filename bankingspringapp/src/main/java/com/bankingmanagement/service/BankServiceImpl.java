package com.bankingmanagement.service;

import com.bankingmanagement.entity.Bank;
import com.bankingmanagement.exception.BankDetailsNotFound;
import com.bankingmanagement.model.BankRequest;
import com.bankingmanagement.model.BankTO;
import com.bankingmanagement.repository.BankRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BankServiceImpl  implements BankService{
    @Autowired
    private BankRepo bankRepository;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<BankTO> findAll() throws BankDetailsNotFound {
        log.info("Inside the BankServiceImplV2.findAll");

        List<Bank> banks = bankRepository.findAll();
        log.info("bankslist","..."+banks.toString());

        if(CollectionUtils.isEmpty(banks)){
            log.error("Bank details not found");
            throw new BankDetailsNotFound("Bank details not found");
        }

        List<BankTO> bankTOS = banks.stream().map(bank -> {
            BankTO bankTO = new BankTO();
            bankTO.setBankAddress(bank.getBankAddress());
         //   bankTO.setBankCode(bank.getBankCode());
            bankTO.setBankName(bank.getBankName());
            return bankTO;
        }).collect(Collectors.toList());

        log.info("End of BankServiceImpl.findAll");
        return  bankTOS;
    }

    @Override
    public BankTO findBankDetails(String id) throws BankDetailsNotFound {
        log.info("Input to BankServiceImpl.findBankDetails, code:{}", id);
        Optional<Bank> bank = bankRepository.findById(id);
        log.info("Bank details for the code: {} and the details:{}", id , bank);

        if(bank.isEmpty()){
            log.error("Bank details are not found for the bank code:{}", id);
            throw new BankDetailsNotFound("Bank details not found");
        }
        Bank bank1 = bank.get();
        BankTO bankDTO = new BankTO();
        bankDTO.setBankName(bank1.getBankName());
        bankDTO.setBankAddress(bank1.getBankAddress());



        log.info("End of BankServiceImpl.findBankDetails");
        return bankDTO;
    }

    @Override
    public BankTO findBankByName(String name) throws BankDetailsNotFound {
        log.info("Input to BankServiceImpl.findBankByName, name:{}", name);

        Optional<Bank> bank = bankRepository.findByBankName(name);
        log.info("Bank details for the name: {} and the details:{}", name , bank.get());

        if(bank.isEmpty()){
            log.error("Bank details are not found for the bank name:{}", name);
            throw new BankDetailsNotFound("Bank details not found");
        }
        Bank bank1 = bank.get();
        BankTO bankTO = new BankTO();
        bankTO.setBankName(bank1.getBankName());
        bankTO.setBankAddress(bank1.getBankAddress());


        log.info("End of BankServiceImpl.findBankByName");
        return bankTO;
    }

    @Override
    public BankTO save(BankRequest bankRequest) throws BankDetailsNotFound {
        log.info("Inside the BankServiceImpl.save, bankRequest:{} ", bankRequest);

        Bank bank = new Bank();
        bank.setBankCode("" + bankRequest.getBankCode());
        bank.setBankName(bankRequest.getBankName());
        bank.setBankAddress(bankRequest.getBankAddress());
        Bank saveBank = bankRepository.save(bank);

        if(Objects.isNull(saveBank)){
            log.error("Bank details not saved");
            throw new BankDetailsNotFound("Bank details not saved");
        }

        log.info("Bank details, bank:{}", saveBank);
        BankTO bankDTO = new BankTO();
        bankDTO.setBankName(saveBank.getBankName());
        bankDTO.setBankAddress(saveBank.getBankAddress());

        log.info("End of BankServiceImpl.save");
        return bankDTO;
    }

    @Override
    public String delete(String bankCode) throws BankDetailsNotFound {
        log.info("Input to BankingServiceImpl.delete, bankCode:{}", bankCode);
        bankRepository.deleteById(bankCode);
        return "Bank details has been deleted";
    }


}

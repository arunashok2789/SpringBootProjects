package com.bankingmanagement.controller;

import com.bankingmanagement.exception.BankDetailsNotFound;
import com.bankingmanagement.model.BankRequest;
import com.bankingmanagement.model.BankTO;
import com.bankingmanagement.service.BankService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/banks")
public class BankController {
    @Autowired
    private BankService bankService;

    //http://localhost:9090/api/v2/banks GET  status code data
    @GetMapping
    public ResponseEntity<List<BankTO>> getAllBanks(){
        log.info("Inside the BankControllerV2.getAllBanks");

        List<BankTO> bankTOS;

        try{
            bankTOS = bankService.findAll();
            log.info("Bank Details, bankTOs:{}", bankTOS);
        }catch (BankDetailsNotFound ex){
            log.error("Bank details not found", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex1){
            log.error("Exception while getting bank details", ex1);
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        log.info("End of the BankControllerV2.getAllBanks");
        return  new ResponseEntity<>(bankTOS, HttpStatus.OK);
    }

    // http://localhost:9090/api/v1/banks/999
// http://localhost:9090/api/v1/banks?code=999&name=sbi
    @GetMapping("/{id}")
    public ResponseEntity<BankTO> getBankByPathvariableId(@PathVariable("id")  String code){
        log.info("Input to BankController.getBankByCode, id:{}", code);
        BankTO bankDTO = null;
        try {
            bankDTO = bankService.findBankDetails(code);
        } catch (BankDetailsNotFound e){
            log.error("Bank details not found for the bank id:{}", code);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex){
            log.error("Exception while getting bank details by id", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("End of BankController.getBankById");
        return new ResponseEntity<BankTO>(bankDTO, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<BankTO> getBankById(@RequestParam("id")  String code){
        log.info("Input to BankController.getBankByCode, id:{}", code);
        BankTO bankDTO = null;
        try {
            bankDTO = bankService.findBankDetails(code);
        } catch (BankDetailsNotFound e){
            log.error("Bank details not found for the bank id:{}", code);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex){
            log.error("Exception while getting bank details by id", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("End of BankController.getBankById");
        return new ResponseEntity<BankTO>(bankDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BankTO> save(@RequestBody @Valid BankRequest bankRequest){
        log.info("Inside the BankController.save, bankRequest:{}", bankRequest);

        BankTO bankDTO = null;
        try{
            bankDTO = bankService.save(bankRequest);
            log.info("Response, bankDTO:{}", bankDTO);
        } catch (BankDetailsNotFound e) {
            log.error("Bank details not saved", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex){
            log.error("Exception while getting bank details by code", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of BankController.save");
        return new ResponseEntity<BankTO>(bankDTO, HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<BankTO> update(@RequestBody BankRequest bankRequest){
        log.info("Inside the BankController.update, bankRequest:{}", bankRequest);

        if(bankRequest == null){
            log.info("Invalid bank request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BankTO bankDTO = null;
        try{
            bankDTO = bankService.save(bankRequest);
            log.info("Response, bankDTO:{}", bankDTO);
        }catch (Exception ex){
            log.error("Exception while getting bank details by code", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<BankTO>(bankDTO, HttpStatus.OK);

    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam(value = "code") int code){
        log.info("Inside BankController.delete, bank code:{}", code);
        String response = null;

        try {
            response = bankService.delete(String.valueOf(code));
            log.info("Delete bank details, response:{}", response);
        }catch (BankDetailsNotFound ex){
            log.error("Exception while deleting the bank details", ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex){
            log.error("Exception while deleting the bank details", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

}

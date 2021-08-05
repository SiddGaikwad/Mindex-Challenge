package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.CompensationOutput;
import com.mindex.challenge.exception.ServiceException;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

@RestController
public class CompensationController {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;


    @GetMapping("/getcompensation/{id}")
    public CompensationOutput readCompensationData(@PathVariable String id) throws ServiceException {
        LOG.debug("Received request to read compensation for id [{}]", id);
        return compensationService.readCompensationData(id);
    }

    @RequestMapping(value = "/compensation")
    public void storeCompensation(@RequestParam("id") String id,@RequestParam("salary") BigDecimal salary) throws ServiceException {

        LOG.debug("Received request to store compensation for id : ",id);
        compensationService.storeCompensation(id,salary);
    }
}

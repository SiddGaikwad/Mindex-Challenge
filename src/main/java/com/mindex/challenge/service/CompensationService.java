package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.CompensationOutput;
import com.mindex.challenge.exception.ServiceException;

import java.math.BigDecimal;
import java.util.Date;

public interface CompensationService {

     CompensationOutput readCompensationData(String id) throws ServiceException;
     void storeCompensation(String id, BigDecimal salary) throws ServiceException;

}

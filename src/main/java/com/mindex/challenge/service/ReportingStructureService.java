package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.exception.ServiceException;

public interface ReportingStructureService{
    ReportingStructure findReportees(String id) throws ServiceException;
}

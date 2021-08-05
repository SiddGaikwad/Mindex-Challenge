package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.CompensationOutput;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.exception.ServiceException;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class CompensationServiceImpl implements CompensationService {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompensationRepository compensationRepository;

    @Override
    public CompensationOutput readCompensationData(String id) throws ServiceException {
        LOG.debug("Read request for employee with employee id", id);
        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new ServiceException("Employee not found in the Employee Database :" + id);
        }

        Compensation compensation = compensationRepository.findByEmployee(employee);
        if (compensation == null) {
            throw new ServiceException("Compensation details are not present for the employee :" + id);
        }
        CompensationOutput output = new CompensationOutput();
        output.setEmployeeID(id);
        output.setFirstName(employee.getFirstName());
        output.setLastName(employee.getLastName());
        output.setSalary(compensation.getSalary());
        output.setEffectiveDate(compensation.getEffectiveDate());
        return output;
    }

    @Override
    public void storeCompensation(String id, BigDecimal salary) throws ServiceException {
        //String id = compensation.getEmployee().getEmployeeId();
        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new ServiceException("Employee not found in the Employee Database :" + id);
        }
        Date effectiveDate = new Date();
        Compensation compensation = new Compensation(employee,salary,effectiveDate);

        try {
            LOG.info(" Employee details matched in the Employee Database!");
            compensationRepository.insert(compensation);
        } catch (Exception e) {
            throw new ServiceException("Incorrect data for the employee" + id);
        }
    }


}

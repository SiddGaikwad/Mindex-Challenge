package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.exception.ServiceException;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public ReportingStructure findReportees(String id) throws ServiceException {

        ReportingStructure reportingStructure= new ReportingStructure();
        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new ServiceException("Invalid employeeId: " + id);
        }
        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(getReports(employee));


        return reportingStructure;

    }


    private int getReports(Employee employee) {
        //Employee employee = employeeRepository.findByEmployeeId(id);

        List<Employee> reportingEmployees= employee.getDirectReports();
        if(reportingEmployees==null){
            LOG.info(" There are no reporting employees for the employee "+employee.getEmployeeId());
            return 0;
        }

        int reportCount= reportingEmployees.size();
        for(Employee emp: reportingEmployees){
            Employee tempEmployee=employeeRepository.findByEmployeeId(emp.getEmployeeId());
            emp.setFirstName(tempEmployee.getFirstName());
            emp.setLastName(tempEmployee.getLastName());
            emp.setPosition(tempEmployee.getPosition());
            emp.setDepartment(tempEmployee.getDepartment());
            emp.setDirectReports(tempEmployee.getDirectReports());
            reportCount+=getReports(emp);
        }

        return reportCount;
    }
}

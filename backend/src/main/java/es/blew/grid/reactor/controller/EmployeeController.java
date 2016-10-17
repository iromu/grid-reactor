package es.blew.grid.reactor.controller;

import es.blew.grid.reactor.integration.Employee;
import es.blew.grid.reactor.integration.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@CrossOrigin
//@RepositoryRestController
@RestController
public class EmployeeController extends BaseDataController {
    @Autowired
    private EmployeeRepository repository;

    @RequestMapping(value = "/employees")
    public Mono<List<Employee>> list() {

        Pageable page = new PageRequest(0, 200);
        Page<Employee> employees = repository.findAll(page);

        boolean isParallel = false;
        List<Employee> employeeList = employees.getContent();
        // add other links as needed

        return Mono.just(employeeList);
    }
}

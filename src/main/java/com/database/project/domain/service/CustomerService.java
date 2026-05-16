package com.database.project.domain.service;

import com.database.project.api.dto.CustomerRequestDTO;
import com.database.project.api.dto.CustomerResponseDTO;
import com.database.project.api.mapper.CustomerMapper;
import com.database.project.domain.model.Customer;
import com.database.project.exception.NotFoundException;
import com.database.project.infrastructure.repository.CustomerRepository;
import com.database.project.infrastructure.repository.ICustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomer {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository repository;

    private final CustomerMapper mapper;

    public CustomerService(final CustomerRepository repository, final CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public CustomerResponseDTO get(Long id) {
        log.info("id {}", id);
        return repository.findById(id).
                map(mapper::customerResponseDTO).
                orElseThrow(() -> new NotFoundException(id, "Id not found"));
    }

    @Override
    public List<CustomerResponseDTO> getAll() {
        List<CustomerResponseDTO> listCustomer = mapper.customerResponseDtoList(repository.findAll());
        if(listCustomer.isEmpty()){
            throw new NotFoundException(null, "No customers found");
        }
        return listCustomer;
    }

    @Override
    public void post(CustomerRequestDTO requestDto) {
        repository.save(mapper.customer(requestDto));
    }

    @Override
    public void delete(Long id) {
        if(!repository.existsById(id)){
            throw new NotFoundException(id, "Id does not exist");
        }
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, CustomerRequestDTO request) {
        if(!repository.existsById(id)){
            throw new NotFoundException(id, "Id not found");
        }
        Customer person = mapper.customer(request);
        person.setId(id);
        repository.save(person);
    }
}

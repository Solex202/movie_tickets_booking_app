package com.lotachukwu.movieticketbookingapplication.repository;

import com.lotachukwu.movieticketbookingapplication.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<String, Customer> {
}

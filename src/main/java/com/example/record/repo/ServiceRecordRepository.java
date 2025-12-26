package com.example.record.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.record.model.ServiceRecord;

public interface ServiceRecordRepository extends MongoRepository<ServiceRecord, String> {}

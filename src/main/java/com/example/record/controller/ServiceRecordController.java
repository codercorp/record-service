package com.example.record.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.example.record.repo.ServiceRecordRepository;
import com.example.record.model.ServiceRecord;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ServiceRecordController {

    private final ServiceRecordRepository repo;

    @GetMapping("/servicerecords")
    public List<ServiceRecord> all() {
        return repo.findAll();
    }

    @GetMapping("/servicerecords/{id}")
    public ServiceRecord get(@PathVariable String id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping("/servicerecord")
    public ServiceRecord create(@RequestBody ServiceRecord obj) {
        return repo.save(obj);
    }

    @PutMapping("/servicerecord/{id}")
    public ServiceRecord update(@PathVariable String id, @RequestBody ServiceRecord obj) {
        obj.setId(id);
        return repo.save(obj);
    }

    @DeleteMapping("/servicerecord/{id}")
    public void delete(@PathVariable String id) {
        repo.deleteById(id);
    }
}

package com.example.record.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "servicerecords")
@Data
@NoArgsConstructor
public class ServiceRecord {
    @Id
    private String id;
    private String vehicleId;
    private String type;
    private String description;
    private String date;
    private double cost;

}

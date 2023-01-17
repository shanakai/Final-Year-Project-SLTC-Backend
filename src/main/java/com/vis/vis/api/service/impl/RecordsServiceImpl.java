package com.vis.vis.api.service.impl;

import com.vis.vis.api.entity.Records;
import com.vis.vis.api.repository.RecordsRepository;
import com.vis.vis.api.service.RecordsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecordsServiceImpl implements RecordsService {
@Autowired
    private RecordsRepository recordsRepository;


    @Override
    public Records addRecord(Records record) {
        return recordsRepository.save(record);
    }

    @Override
    public Records updateRecord(Records record) {
        return recordsRepository.save(record);
    }

    @Override
    public List<Records> getUr(String id) {
        return recordsRepository.getRecordsByUserid(id);
    }
}

package com.vis.vis.api.service;

import com.vis.vis.api.entity.Records;

import java.util.List;

public interface RecordsService {

    Records addRecord(Records record);
    Records updateRecord(Records record);

    List<Records> getUr(String id);
}

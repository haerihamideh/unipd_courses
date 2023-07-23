package com.unipd.semicolon.business.service;

import com.unipd.semicolon.core.entity.TimeTable;


import java.util.List;

public interface TimeTableService {

    TimeTable save(
            Long id,
            String from_hour,
            String to_hour
    );
}

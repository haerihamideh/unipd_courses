package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.service.TimeTableService;
import com.unipd.semicolon.core.entity.TimeTable;
import com.unipd.semicolon.core.repository.entity.TTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TimeTableServiceImp implements TimeTableService {
    @Autowired
    private TTableRepository timeTableRepository;

    @Override
    public TimeTable save(Long id,
                          String from_hour,
                          String to_hour
                          ) {
        return timeTableRepository.save(new TimeTable(id, from_hour, to_hour));

    }
}

package com.swpuiot.service.impl;

import com.swpuiot.bean.MeetingData;
import com.swpuiot.repository.MeetingRepository;
import com.swpuiot.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DataServiceImpl implements DataService {

    private MeetingRepository repository;

    @Autowired
    public DataServiceImpl(MeetingRepository repository) {
        this.repository = repository;
    }

    public int insertMeetingDetail() {
        return 0;
    }

    public List<MeetingData> getAllMeeting() {
        return repository.getAllMeeting();
    }

    public List<MeetingData> getMeetingByName(String MeetingName) {
        return repository.getMeetingByName(MeetingName);
    }

    public int insertMeeting(MeetingData meetingData) {
        return repository.insertIntoMeeting(meetingData);
    }

}

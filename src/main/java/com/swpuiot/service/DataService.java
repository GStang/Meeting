package com.swpuiot.service;

import com.swpuiot.bean.MeetingData;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DataService {

    int insertMeetingDetail();

    List<MeetingData> getAllMeeting();

    List<MeetingData> getMeetingByName(String MeetingName);

    int insertMeeting(MeetingData meetingData);
}

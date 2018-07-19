package com.swpuiot.repository;

import com.swpuiot.bean.MeetingData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.executor.result.DefaultResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MeetingRepository {
    private SqlSessionFactoryBean sqlSession;
    private SqlSession session;

    @Autowired
    public MeetingRepository(SqlSessionFactoryBean sqlSession) throws Exception {
        this.sqlSession = sqlSession;
        session = this.sqlSession.getObject().openSession();
    }

    public SqlSessionFactoryBean getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSessionFactoryBean sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<MeetingData> getMeetingByName(@Param("MeetingName") String MeetingName) {
        DefaultResultHandler handler = new DefaultResultHandler();
        session.select("selectMeetingByName",MeetingName, handler);
//        List<MeetingData> meeting = handler.getResultList();
        MeetingData data = (MeetingData) handler.getResultList().get(0);
        ArrayList<MeetingData> list = new ArrayList<MeetingData>();
        if (data != null) {
            list.add(data);
        }
        return list;
    }

    public List<MeetingData> getAllMeeting() {
        return session.selectList("selectAllMeet");
//        System.out.println(time);
    }

    public int insertIntoMeeting(MeetingData meetingData) {
        return session.insert("insertInMeeting", meetingData);
    }

    public List<MeetingData> selectByMeetingTime(String data){
        return session.selectList("selectMeetingByTime");
    }
}

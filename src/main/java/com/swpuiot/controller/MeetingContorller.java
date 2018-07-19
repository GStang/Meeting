package com.swpuiot.controller;

import com.swpuiot.base.BaseController;
import com.swpuiot.bean.HttpResponse;
import com.swpuiot.bean.MeetingData;
import com.swpuiot.exception.NoMeetingFoundException;
import com.swpuiot.service.DataService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

import java.util.Date;
import java.util.List;

@Controller
public class MeetingContorller extends BaseController {
    private DataService service;
    private MultipartResolver resolver;

    @Autowired
    public MeetingContorller(DataService service, MultipartResolver resolver) {
        this.resolver = resolver;
        this.service = service;
    }



    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<MeetingData> getAll() {
        List<MeetingData> datas = service.getAllMeeting();
        return datas;
    }

    @ResponseBody
    @RequestMapping(value = "/getMeetingByName", method = RequestMethod.GET)
    public MeetingData getOne(String MeetingName) {
        List<MeetingData> datas = service.getMeetingByName(MeetingName);
        if (datas.get(0) == null) {
            throw new NoMeetingFoundException();
        }
        return datas.get(0);
    }

    @RequestMapping(value = "/download")
    public ResponseEntity<byte[]> downloadMeetingFile(@RequestParam("path")String filename) throws IOException {
        String dfileName = new String(filename.getBytes("iso-8859-1"), "utf-8");
        String path = "/home/ubuntu/tmpfile/" + dfileName+".txt";
        File file = new File(path);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment",
                new String((dfileName+".txt").getBytes("utf-8"),"iso-8859-1"));
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public int insertMeeting(@RequestBody MeetingData data) {
        int count = service.insertMeeting(data);
        return count;
    }

    @RequestMapping(value = "/insertDetail", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public String insertMeeting(@RequestParam("data") MultipartFile file,
                                @RequestParam("meetingName")String meetingName,
                                @RequestParam("meetingTime")Date time) {
        if (!file.isEmpty()) {
            try {
                String path = "/home/ubuntu/tmpfile/" + file.getOriginalFilename();
                MeetingData data = new MeetingData();
                data.setMeetingName(meetingName);
                data.setDate(time);
                data.setPath(path);
                file.transferTo(new File("/home/ubuntu/tmpfile/" + file.getOriginalFilename()));
                service.insertMeeting(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }

    @ResponseBody
    @ExceptionHandler(value = {NoMeetingFoundException.class})
    public HttpResponse handleException() {
        HttpResponse response = new HttpResponse();
        response.setHttpCode(404);
        return response;
    }



    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        super.initBinder(request, binder);
    }
}

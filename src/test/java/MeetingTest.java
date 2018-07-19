import com.sun.org.apache.xml.internal.serialize.Method;
import com.swpuiot.bean.MeetingData;
import com.swpuiot.config.RootConfig;
import com.swpuiot.config.WebConfig;
import com.swpuiot.initializer.WebAppInitializer;
import com.swpuiot.repository.MeetingRepository;
import com.swpuiot.service.DataService;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.results.ResultMatchers;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
//@Transactional
@WebAppConfiguration(value = "src/main/resources")
public class MeetingTest {
    @Autowired
    DataService service;
    @Autowired
    protected WebApplicationContext webContext;
    private MockMvc mockMvc;

    @Before()
    public void init() {
        ServletContext context = webContext.getServletContext();
        System.out.println(context);
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @Test
    public void getMeetingByNameTest() {
        List<MeetingData> data = service.getMeetingByName("608第4次寝室大会");
        System.out.println(data.get(0));
    }

    @Autowired
    MeetingRepository repository;
    @Test
    public void findMeetingByTimeRepositoryTest(){
        List<MeetingData> list = repository.selectByMeetingTime("2018-7-11");
        for (MeetingData data :
                list) {
            System.out.println(data.getMeetingName());
        }
    }

    @Test
    public void downloadFile(){
//        mockMvc.perform(new MockHttpServletRequestBuilder("/"))
    }

    @Test
    public void uploadsFile() throws Exception {
        MockMultipartFile file = new MockMultipartFile("data", "608寝室大会3号文件.txt",
                "text/plain", ("一定要测试中文sadflkasjfaslkkljfskljlkzxjfklzxjflkwjiorweujrjjsadf" +
                "salkdfjasdlkfjsdaklfjsdalkfsdjafkldsjflk" +
                "sjakldfjasdlkfjasdlkfjdsalkfj" +
                "qwesakldfjlksajewrqknlksnv,xccnwjlksjadf" +
                "lsakdjfklweqjlkrnsdlndsflwdfksadl" +
                "sladkfjaslkdfjdslkfjdwlkdjelwkjwelk").getBytes("utf-8"));
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/insertDetail")
                .file(file).param("id", "123456")).andExpect(status().isOk());

    }

    @Test
    public void insertMeeting() throws Exception {
        MockMultipartFile file = new MockMultipartFile("data","608寝室第4次寝室大会.txt",
        "text/plain",("寝室会议寝室会议寝室会议寝室会议").getBytes("utf-8"));
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/insertDetail")
                .file(file)
        .param("meetingName","608第4次寝室大会")
        .param("meetingTime","2018-7-11")
        ).andExpect(status().isOk());
    }
    /**
     * mockMVC测试查询
     */
    @Test
    public void getMeetingByNameControllerTest() throws Exception {
        String result =
                mockMvc.perform(MockMvcRequestBuilders.get("/getMeetingByName")
                        .param("MeetingName", "608第4次寝室大会")
                )
                        .andReturn().getResponse().getContentAsString();
        System.out.println(result);

    }

//    public void getAllMeeting()
    @Test
    public void insertMeetingData() {
        MeetingData meetingData = new MeetingData();
        meetingData.setMeetingName("608第4次寝室大会");
        service.insertMeeting(meetingData);
    }


    @Test
    public void getAllMeeting() throws Exception {
        String s =
                mockMvc.perform(MockMvcRequestBuilders.get("/getAll"))
                        .andReturn().getResponse().getContentAsString();
        System.out.println(s);
    }

}

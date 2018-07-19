import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataTest {

    @Test
    public void DateTest(){
        Date date = new Date();
        System.out.println(date.toString());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String s = format.format(date);
        System.out.println(s);
    }
}

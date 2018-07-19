import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.junit.Test;

public class JsonTest {

    @Test
    public void JsonTest(){
        JsonNode node = JsonNodeFactory.instance.textNode("{\n" +
                "\t\"TEST\": \"TEST\",\n" +
                "\t\"testText\": \"lalala\"\n" +
                "}");


    }
}

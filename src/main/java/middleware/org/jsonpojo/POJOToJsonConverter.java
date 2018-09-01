package middleware.org.jsonpojo;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class POJOToJsonConverter {

    private static final Logger LOG = LogManager.getLogger(POJOToJsonConverter.class);

    public String convert(Object pojo) {
        LOG.info("convert method in POJOToJsonConverter called");
        ObjectMapper mapper = new ObjectMapper();
        String rtnJson = "";
        try {
            rtnJson = mapper.writeValueAsString(pojo);
        } catch (JsonGenerationException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        } catch (JsonMappingException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        LOG.info("Returning json: " + rtnJson);
        return rtnJson;
    }
}

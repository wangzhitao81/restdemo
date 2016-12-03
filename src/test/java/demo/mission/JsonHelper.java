package demo.mission;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonHelper {
	static ObjectMapper mapper = new ObjectMapper();
	
	public static String toJson(Object obj) throws IOException{
		Writer writer=new StringWriter();
		try {
			mapper.writeValue(writer, obj);
			String json=writer.toString();
			writer.close();
			return json;
		} catch (JsonGenerationException e) {
			throw e;
		} catch (JsonMappingException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}
	public static <T> T fromJson(String json, Class<T> classOfT) throws JsonParseException, JsonMappingException, IOException{
		return mapper.readValue(json, classOfT);
	}
	public static <T> T fromJson(String json, TypeReference<T> valueTypeRef)
	        throws IOException, JsonParseException, JsonMappingException
	    {
			return mapper.readValue(json, valueTypeRef);	        
	    } 
}

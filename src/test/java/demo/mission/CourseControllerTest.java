package demo.mission;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import demo.ralph.dto.CourseDto;

public class CourseControllerTest {
	private final static String baseUrl="http://localhost:8080";
	@Test
	public void testGetCourse() {
		String url = baseUrl+"/course/6/";
		try {
			String json = HttpHelper.get(url);
			
			CourseDto dto = JsonHelper.fromJson(json, CourseDto.class);
			assertEquals("0006",dto.getCode());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testSave(){
		CourseDto dto = new CourseDto();
		dto.setCode("testcode");
		dto.setName("test name");
		
		try {
			String url = baseUrl+"/course/save";
			String postData =JsonHelper.toJson(dto);
			String json = HttpHelper.Post(url, postData);
			CourseDto result = JsonHelper.fromJson(json, CourseDto.class);
			assertEquals("testcode",result.getCode());
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

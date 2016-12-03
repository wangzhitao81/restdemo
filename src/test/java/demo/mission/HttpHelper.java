package demo.mission;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import javax.ws.rs.core.Response;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import demo.ralph.common.MyConstants;


public class HttpHelper {

	static HttpClient httpClient = HttpClientBuilder.create().build();

	public static String readContent(HttpResponse response){
		StringBuilder entityBuilder = new StringBuilder();
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent(), "UTF-8"));
			String output;
			while ((output = br.readLine()) != null) {
				entityBuilder.append(output);
			}
			br.close();
		} catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
		}
		System.out.println("服务返回:"+entityBuilder.toString());
		return entityBuilder.toString();
	}
	public static String Post(String url,String postData){
		try {
			System.out.println("post url is "+url);
			System.out.println("post data is "+postData);
			HttpPost postRequest = new HttpPost(url);
//			postRequest.addHeader("accept", "application/json; charset=utf-8");
			postRequest.addHeader("content-type", "application/json; charset=utf-8");

			StringEntity input = new StringEntity(postData,"UTF-8");
			input.setContentType("application/json;charset=UTF-8");
//			input.setContentEncoding("UTF-8");
			postRequest.setEntity(input);
			
			HttpResponse response = httpClient.execute(postRequest);
			int statusCode= response.getStatusLine().getStatusCode();
			if(statusCode == Response.Status.UNAUTHORIZED.getStatusCode()){
				return "{\"result\":401,\"msg\":\"无权限访问该资源\",\"token\":null}";
			}
			if ( statusCode!= 200) {
				throw new RuntimeException("Failed : HTTP error code :"+statusCode);
			}

			String rJson=readContent(response);
			System.out.println("返回的JSON串 :" + rJson);
			return rJson;
		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		throw new RuntimeException("Failed : 错误的执行结果");
		 
	}
	public static String Put(String url,String data){
		try {
			System.out.println("put url is "+url);
			System.out.println("put data is "+data);
			HttpPut putRequest = new HttpPut(url);
//			postRequest.addHeader("accept", "application/json; charset=utf-8");
			putRequest.addHeader("content-type", "application/json; charset=utf-8");

			StringEntity input = new StringEntity(data,"UTF-8");
			input.setContentType("application/json;charset=UTF-8");
//			input.setContentEncoding("UTF-8");
			putRequest.setEntity(input);
			
			HttpResponse response = httpClient.execute(putRequest);
			
			int statusCode= response.getStatusLine().getStatusCode();
			if(statusCode == Response.Status.UNAUTHORIZED.getStatusCode()){
				return "{\"result\":401,\"msg\":\"无权限访问该资源\",\"token\":null}";
			}
			if ( statusCode!= 200) {
				throw new RuntimeException("Failed : HTTP error code :"+statusCode);
			}

			String rJson=readContent(response);
			System.out.println("返回的JSON串 :" + rJson);
			return rJson;
		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		throw new RuntimeException("Failed : 错误的执行结果");
		 
	}
	public static String Delete(String url){
		try {
			System.out.println("Delete url is "+url);
			HttpDelete request = new HttpDelete(url);
//			postRequest.addHeader("accept", "application/json; charset=utf-8");
			request.addHeader("content-type", "application/json; charset=utf-8");


			HttpResponse response = httpClient.execute(request);
			
			int statusCode= response.getStatusLine().getStatusCode();
			if(statusCode == Response.Status.UNAUTHORIZED.getStatusCode()){
				return "{\"result\":401,\"msg\":\"无权限访问该资源\",\"token\":null}";
			}
			if ( statusCode!= 200) {
				throw new RuntimeException("Failed : HTTP error code :"+statusCode);
			}

			String rJson=readContent(response);
			System.out.println("返回的JSON串 :" + rJson);
			return rJson;
		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		throw new RuntimeException("Failed : 错误的执行结果");
		 
	}
	public static String get(String url) throws Exception{
		HttpGet httpGet=new HttpGet(url);
		HttpResponse response;
		try {
			response = httpClient.execute(httpGet);
			int statusCode= response.getStatusLine().getStatusCode();
			if(statusCode == Response.Status.UNAUTHORIZED.getStatusCode()){
				return "{\"result\":401,\"msg\":\"无权限访问该资源\",\"token\":null}";
			}
			if(statusCode == MyConstants.EXCEPTION){
				return "{\"result\":-1,\"msg\":\"发生了异常\",\"token\":null}";
			}
			if ( statusCode == 404) {
				return "{\"result\":404,\"msg\":\"不存在的资源地址\",\"token\":null}";
			}
			if ( statusCode == 500) {
				return "{\"result\":500,\"msg\":\"服务端异常,请与服务商联系\",\"token\":null}";
			}
			if ( statusCode!= 200) {
				throw new RuntimeException("Failed : HTTP error code :"+statusCode);
			}
			String json = HttpHelper.readContent(response);
			return json;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new Exception("没有返回正确的数据");
	}
}

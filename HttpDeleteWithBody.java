package com.mif.atlas.RestClient;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.databind.ObjectMapper;


class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "DELETE";

    public String getMethod() {
        return METHOD_NAME;
    }

    public HttpDeleteWithBody(final String uri) {
        super();
        setURI(URI.create(uri));
    }

    public HttpDeleteWithBody(final URI uri) {
        super();
        setURI(uri);
    }

    public HttpDeleteWithBody() {
        super();
    }
    public static void sendDelete(String URL, String PARAMS) throws IOException {
	    String[] restResponse = new String[2];
	        CloseableHttpClient httpclient = HttpClients.createDefault();

	        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(URL);
	        StringEntity input = new StringEntity(PARAMS, ContentType.APPLICATION_JSON);
	        String auth = "Admin:admin";
			byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("ISO-8859-1")));
			String authHeader = "Basic " + new String(encodedAuth);
			httpDelete.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
	        httpDelete.setEntity(input);  

	        Header requestHeaders[] = httpDelete.getAllHeaders();
	        CloseableHttpResponse response = httpclient.execute(httpDelete);	           
	        
	    }
    public static void main(String[] args) throws IOException {
    	
    	Map<String, Object> assetMap = new HashMap<>();
    	List<String> tagList = new ArrayList<>();
    	tagList.add("tag2");
    	assetMap.put("assetId", "c920b389-3771-41f8-a93e-b283e5db19c7");
    	assetMap.put("tagNames", tagList);
    	String json = new ObjectMapper().writeValueAsString(assetMap);
    	System.out.println("AssetMap "+json);
    	sendDelete("http://datanomist.in:4400/rest/2.0/assets/c920b389-3771-41f8-a93e-b283e5db19c7/tags", json.toString());
	}
}

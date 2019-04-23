package com.filter.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This Rest controller is responsible to invoke the public api to find jobs.
 * 
 * @author Dipak Patil
 */
@RestController
public class SearchController {
    
	Logger LOGGER = Logger.getLogger(SearchController.class.getName());
	
    @PostConstruct
    public void init() {
    	LOGGER.log(Level.SEVERE, "SearchController Constructed.");
    }
    
    @Value("${public.api.URL}")
    private String apiURL;
    
    /**
     * The API is responsible to invoke a public api using Http request and return the search result.
     * 
     * @param filterBeginsWithStr Parameter passed to public api for search
     * @return 
     */
	@GetMapping(path="/getJobs", produces = "application/json")
	public ResponseEntity<String> getJobsData(@RequestParam(name = "begins_with") String filterBeginsWithStr) {
		StringBuffer content = null;
		try {
			URL url = new URL(createURL(filterBeginsWithStr));
			HttpURLConnection con = HttpConnectionHelper.createHttpConnection(url);
			content = HttpConnectionHelper.invokeAndReadHttpResponse(con);
			HttpConnectionHelper.closeHttpConnection(con);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Exception occured while e");
			return new ResponseEntity<>(
			          "Search found no results or Internal error occured during search", 
			          HttpStatus.BAD_REQUEST);
		} 
		return new ResponseEntity<>(content.toString(), HttpStatus.OK);
	}
	
	/**
	 * Create api URL for connection.
	 * @param filterBeginsWithStr
	 * @return URL string for invocation.
	 */
	private String createURL(String filterBeginsWithStr) {
		LOGGER.log(Level.SEVERE, "URL from properties:" + apiURL);
		return apiURL + "?begins_with=\""+ filterBeginsWithStr + "\"";
	}
}

package com.filter.app.test;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Unit test class for SearchController
 * @author Dipak Patil
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {AppConfig.class})
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
public class SearchControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    /**
     * Test using a valid filter string.
     * @throws Exception
     */
    @Test
    public void getJobsSuccess() throws Exception {
        mockMvc.perform(get("/getJobs").param("begins_with", "software"))
                .andExpect(content().string(containsString("Software")));
    }
    
    /**
     * Test using a filter string which results no result.
     * @throws Exception
     */
    @Test
    public void getJobsNoResult() throws Exception {
        mockMvc.perform(get("/getJobs").param("begins_with", "xxxxxxx"))
                .andExpect(content().string(containsString("Search found no results or Internal error occured during search")));
    }
}

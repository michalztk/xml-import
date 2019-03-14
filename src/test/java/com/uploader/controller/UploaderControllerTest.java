package com.uploader.controller;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UploaderControllerTest {
	@Autowired
	private UploaderController uploaderController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(uploaderController).build();
	}

	/**
	 * Checks whether we get proper view
	 * 
	 * @throws Exception
	 */
	@Test
	public void testHomePage() throws Exception {
		uploaderController = mock(UploaderController.class);
		this.mockMvc.perform(get("/")).andExpect(view().name("file-uploader"));
	}

	/**
	 * Checks if we get 404 error when page doesn't exist
	 * 
	 * @throws Exception
	 */
	@Test
	public void testNonExistingPage() throws Exception {
		uploaderController = mock(UploaderController.class);
		this.mockMvc.perform(get("/test-page")).andExpect(status().isNotFound());
	}

}

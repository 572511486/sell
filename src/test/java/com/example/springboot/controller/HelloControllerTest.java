package com.example.springboot.controller;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@BeforeClass
	public static void beforeClass() {
		System.out.println("beforeClass.true==>" + true);
	}

	@Before
	public void setUp() throws Exception {
		List list = new ArrayList();
		System.out.println("Before.true==>" + true);
	}

	@Test
	public void hello() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/").param("name", "IMOOC").contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string("helloIMOOC")).andReturn();

		System.out.println("===" + result.getResponse().getContentAsString() + "===");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("After.true==>" + true);
	}

	@AfterClass
	public static void aeforeClass() {
		System.out.println("aeforeClass.true==>" + true);
	}

}
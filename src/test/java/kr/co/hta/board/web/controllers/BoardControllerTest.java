package kr.co.hta.board.web.controllers;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:/META-INF/spring/test-root-context.xml","classpath:/META-INF/spring/test-app-servlet.xml"})
@Transactional
public class BoardControllerTest {
	
	@Autowired
	BoardController boardController;
	
	MockMvc mockMvc = null;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders
				.standaloneSetup(boardController)
				.build(); 
	}
	
	@Test
	public void testBoardController() {
		assertThat(boardController, notNullValue());
	}
	
	@Test
	public void testList() throws Exception {
		mockMvc.perform(get("/list.do"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(view().name("board/list.jsp"))
				.andExpect(model().attributeExists("boards"));
	}
	@Test
	public void testDetail() throws Exception {
		mockMvc.perform(get("/board/detail.do").param("no", "241"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(view().name("board/detail.jsp"))
				.andExpect(model().attributeExists("board"))
				.andExpect(model().attribute("board", notNullValue()));
	}

}

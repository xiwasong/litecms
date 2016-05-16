package cn.hn.java.cms.api.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import cn.hn.java.cms.api.admin.user.UserController;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class UserControllerTest {

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
	}

	@Test
	public void getHello() throws Exception {
//		mvc.perform(MockMvcRequestBuilders.get("/user/list.json").accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk());
//				.andExpect(content().string(equalTo("Greetings from Spring Boot!")));
	}
}

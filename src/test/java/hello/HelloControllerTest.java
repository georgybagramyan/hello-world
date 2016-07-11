package hello;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = MockServletContext.class)
@SpringApplicationConfiguration(Application.class)
@WebAppConfiguration
public class HelloControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mvc;
    private Random random;
    private Gson gson;


    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        //mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
        random = new Random();
        gson = new Gson();
    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
    }

    @Test
    public void createPerson() throws Exception {
        String[] adressen = {"Willem lepelstraat 15, 2000 Antwerpen","Du Chastellei 39, 2170 Merksem", "Nationalestraat 5, 2000 Antwerpen"};
        String[] namen = {"Wim","Jan","Bruno"};

        Person person = new Person();
        person.setAddress(adressen[random.nextInt(adressen.length-1)]);
        person.setAge(random.nextInt(60));
        person.setName(namen[random.nextInt(namen.length-1)]);

        String serializedPerson = gson.toJson(person);

        mvc.perform(
                post("/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedPerson))
                .andExpect(status().isCreated());

        MvcResult mvcResult = mvc.perform(get("/person/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        System.out.println("!!!!!CONTENT!!!!!:" + mvcResult.getResponse().getContentAsString());

    }
}
package hello;

import com.google.gson.Gson;
import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "person",method = RequestMethod.GET)
    public String person(){
        Person person = new Person();
        person.setAdress("Willemp lepelstraat 15, 2000 Antwerpen");
        person.setAge(27);
        person.setName("Georgy Bagramyan");

        Gson gson = new Gson();

        return gson.toJson(person);
    }

}
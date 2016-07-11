package hello;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "/")
public class HelloController {

    @Autowired
    private PersonRepository repo;
    private Random random = new Random();

    public HelloController() {
        init();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "person/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Person person(@PathVariable long id){
        Person person = repo.findOne(id);

        if(person == null){
            person = new Person("Georgy",27,"Willem lepelstraat 15, 2000 Antwerpen");
        }

        return person;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public void SavePerson(@RequestBody Person person){
        repo.save(person);
    }

    private void init(){
        this.random = random;
    }


}
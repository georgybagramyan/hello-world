package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService{

    private HelloRepo repo;

    @Autowired
    public HelloServiceImpl(HelloRepo repo) {
        this.repo = repo;
    }

    @Override
    public void getHello(int id) {

    }
}

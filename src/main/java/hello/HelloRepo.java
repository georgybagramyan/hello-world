package hello;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lollik on 23/06/2016.
 */
public interface HelloRepo extends JpaRepository<Hello, Integer> {
}

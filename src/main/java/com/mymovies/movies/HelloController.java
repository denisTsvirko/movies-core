package com.mymovies.movies;

// import java.util.Optional;

// import org.hibernate.mapping.List;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

// import com.mymovies.movies.entity.TestTable;
// import com.mymovies.movies.repository.TestTableRepository;


// import com.mymovies.movies.Entity.TestTable;
// import com.mymovies.movies.Repository.TestTableRepository;


@Controller
public class HelloController {

    // @Autowired
    // private TestTableRepository repository;

    @RequestMapping(value = "/", method=RequestMethod.GET)
    @ResponseBody
    public String requestMethodName() {
        // Iterable<TestTable> repo = repository.findAll();
    
        //  String text = "";
        // // for (TestTable teable : repo) {
        // //     text += teable.toString();
        // // }

        // Long id = 1L;
        // Optional<TestTable> repo = repository.findById(id);
    
        // return "ttttt: " + repo.get().toString();
        /*package com.mymovies.movies.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.mymovies.movies.entity.TestTable;

public interface TestTableRepository extends CrudRepository<TestTable, Long> {
    List<TestTable> findByTest(String test);

}
 */
        return ";=";
    }

}

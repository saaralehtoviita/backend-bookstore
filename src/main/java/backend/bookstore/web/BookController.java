package backend.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ResponseBody
public class BookController {

    @GetMapping("/index") //http://localhost:8080/index 
    public String greeting() {
        return "Tervetuloa kirjakauppaan, sovellus näyttää toimivan!";
    }    
}

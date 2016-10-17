package es.blew.grid.reactor;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView index = new ModelAndView("index");
        index.addObject("base_rest_url", "http://localhost:9001");
        return index;
    }
}
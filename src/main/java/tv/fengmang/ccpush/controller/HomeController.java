package tv.fengmang.ccpush.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/online_list")
    public String onlineList() {
        return "online_list";
    }
}

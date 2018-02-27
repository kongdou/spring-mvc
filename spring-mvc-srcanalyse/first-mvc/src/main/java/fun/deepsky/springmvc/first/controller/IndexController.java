package fun.deepsky.springmvc.first.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fun.deepsky.springmvc.first.annotation.MyLog;

@RestController
@RequestMapping(value = "/index")
public class IndexController {
	
	@MyLog(requestUrl = "/index请求")
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
		System.out.println("index....");
        return "index";
    }
}

package fun.deepsky.springmvc.first.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fun.deepsky.springmvc.first.annotation.MyLog;

@Controller
@RequestMapping("/first")
public class FirstController implements EnvironmentAware{

	private final Log logger = LogFactory.getLog(FirstController.class);
	
	@RequestMapping(value="/",method=RequestMethod.HEAD)
	public String head() {
		return "/index.jsp";
	}
	
	@RequestMapping(value= {"/index","/"},method= {RequestMethod.GET})
	public String index(Model model) {
		logger.info("================processed by index================");
		model.addAttribute("msg","success index");
		return "/index.jsp";
	}
	
	@MyLog(requestUrl="/index请求")
	@RequestMapping(value = {"/hello"} ,params= {"hello=world"},method= {RequestMethod.GET})
	public String hello_world(Model model) {
		logger.info("================hello world================");
		model.addAttribute("msg","hello world method");
		return "/index.jsp";
	}

	private Environment environment = null;
	
	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
}

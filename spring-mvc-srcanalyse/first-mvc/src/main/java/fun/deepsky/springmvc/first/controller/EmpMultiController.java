package fun.deepsky.springmvc.first.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class EmpMultiController extends MultiActionController{

	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/index.jsp");  
		mav.addObject("msg","add method");
        return mav;
    }
	
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/index.jsp");  
		mav.addObject("msg","delete method");
        return mav;
    }

	public ModelAndView test(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/index.jsp");  
		mav.addObject("msg","test method");
        return mav;
    }
	
}

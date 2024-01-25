package spDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import service.MemberService;

public class C01_mList implements Controller{
	
	@Autowired
	MemberService service;
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
    	
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("banana", service.selectList());
    	mv.setViewName("member/memberList");
        return mv;
    }
}
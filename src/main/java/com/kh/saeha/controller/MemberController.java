package com.kh.saeha.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.saeha.service.MemberService;
import com.kh.saeha.vo.MemberVO;

@Controller
@RequestMapping("/sae_member/*")
public class MemberController {
   
   private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
   
   @Inject
   MemberService service;
   
   //회원가입 get 11
   @RequestMapping(value = "/register", method = RequestMethod.GET)
   public void getRegister() throws Exception{
      logger.info("get register 2022 1201");
   }
   
   //회원가입 get 22
   @RequestMapping(value = "/register", method = RequestMethod.POST)
   public String postRegister(MemberVO vo)throws Exception{
      logger.info("post register");
      service.register(vo);
      
      return "redirect:/"; 
   }
   
   // 로그인 33
   @RequestMapping(value = "/login", method = RequestMethod.GET)
   public void getlogin() throws Exception {
	   logger.info("get login~~~ 33");
   }
   
   @RequestMapping(value = "/login", method = RequestMethod.POST)
   public String login(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
	   logger.info("post login 444");
	   
	   HttpSession session = req.getSession();
	   MemberVO login = service.login(vo);	   
	   
	   System.out.println("login");
	   System.out.println(login);
	   if(login == null) {
		   session.setAttribute("member", null);
		   rttr.addFlashAttribute("msg", false);
	   } else {
		   session.setAttribute("member", login);
		   System.out.println(session.getAttribute("member"));
		   logger.info("member777 : " + login);
	   }
	   System.out.println("====end=====");
	   return "redirect:/";
   }
   
   // 로그아웃
	  @RequestMapping(value = "/logout", method = RequestMethod.GET) 
	  public String logout(HttpSession session) throws Exception {
		  
		  session.invalidate();
		  
		  return "redirect:/";
	  }
	 
	 //회원정보 수정
   @RequestMapping(value="/memberUpdateView", method = RequestMethod.GET)
   public String registerUpdateView() throws Exception{
	   return "sae_member/memberUpdateView";
   }
   
   @RequestMapping(value="/memberUpdate", method = RequestMethod.POST)
   public String registerUpdate(MemberVO vo, HttpSession session) throws Exception{
	   
	   service.memberUpdate(vo);
	   
	   session.invalidate();
	   
	   return "redirect:/";
   }
}
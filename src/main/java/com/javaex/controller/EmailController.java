package com.javaex.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.DAO.MemberDAO;
import com.javaex.VO.MemberVO;

@Controller
public class EmailController {

	@Autowired // 스프링이 dao 클래스를 생성한다. 이미 주소가 들어가 있다
	private MemberDAO dao; //@Repositroy붙은 것을 찾아가 클래스를 생성해서 주소를 반환해준다.

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form() {
		System.out.println("form");
		return "/WEB-INF/views/form.jsp";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute MemberVO vo) {
		dao.MemberInsert(vo);
		return "redirect:/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		ArrayList<MemberVO> list = dao.searchAll();
		model.addAttribute("list", list);
		return "/WEB-INF/views/list.jsp";
	}
	
}

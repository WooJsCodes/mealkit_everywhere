package com.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.dto.Criteria;
import com.admin.dto.CustomerDTO;
import com.admin.dto.response.PageResponseDTO;
import com.admin.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@RequestMapping("")
	public String main(Model model, Criteria cri) {


		if(cri.getOrderCri() == null) {
			cri.setOrderCri("custKey");
		}
		
		if(cri.getEmailKeyword() != null 
				&& cri.getNameKeyword() != null 
				&& cri.getPhNumberKeyword() != null) {
			
			if(cri.getEmailKeyword().isEmpty()) {
				cri.setEmailKeyword(null);
			}
			if(cri.getNameKeyword().isEmpty()) {
				cri.setNameKeyword(null);
			}
			if(cri.getPhNumberKeyword().isEmpty()) {
				cri.setPhNumberKeyword(null);
			}
		}

		List<CustomerDTO> customerList = new ArrayList<>();

		PageResponseDTO pageResponseDTO = customerService.getPageMaker(cri);

		customerList = customerService.getCustList(cri);
		if(!customerList.isEmpty()) {
			model.addAttribute("customerList", customerList);
			
		}else {
			model.addAttribute("customerList", "empty");
		}

		model.addAttribute("pageNumList", pageResponseDTO.getPageNumList());
		model.addAttribute("pageMaker", pageResponseDTO.getPageMaker());

		model.addAttribute("pageNum", cri.getPageNum());
		model.addAttribute("ascDesc", cri.getAscDesc());
		model.addAttribute("orderCri", cri.getOrderCri());
		model.addAttribute("amountCust", cri.getAmount());
		model.addAttribute("emailKeyword", cri.getEmailKeyword());
		model.addAttribute("nameKeyword", cri.getNameKeyword());
		model.addAttribute("phNumberKeyword", cri.getPhNumberKeyword());
		model.addAttribute("criteria", cri);

		model.addAttribute("content", "/customer/customerPage");

		return "main";
	}


	//회원 차단 설정 메소드
	@RequestMapping("/locked")
	public String changeLocked(CustomerDTO customerDTO, Criteria cri, Model model){

		customerService.changeLocked(customerDTO);
		model.addAttribute("custKey", customerDTO.getCustKey());
		model.addAttribute("ascDesc", cri.getAscDesc());
		model.addAttribute("orderCri", cri.getOrderCri());
		model.addAttribute("amountCust", cri.getAmount());

		return "redirect:/customer";
	}
}

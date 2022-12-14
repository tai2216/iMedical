package com.jerryboot.springbootdemo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.jerryboot.springbootdemo.dao.EditLogDao;
import com.jerryboot.springbootdemo.dao.MemberDao;
import com.jerryboot.springbootdemo.dto.MemberDto;
import com.jerryboot.springbootdemo.model.Commodities;
import com.jerryboot.springbootdemo.model.EditLog;
import com.jerryboot.springbootdemo.model.Employee;
import com.jerryboot.springbootdemo.model.Member;
import com.jerryboot.springbootdemo.model.ShoppingCart;
import com.jerryboot.springbootdemo.service.CommoditiesService;
import com.jerryboot.springbootdemo.service.EditLogService;
import com.jerryboot.springbootdemo.service.MemberService;
import com.jerryboot.springbootdemo.service.ShoppingCartService;



@SuppressWarnings("unchecked")
@Controller
public class MemberController {

	@Autowired
	private MemberService service;
	@Autowired
	private MemberDao dao;
	@Autowired
	private EditLogDao editLogDao;
	@Autowired
	private EditLogService editLogService;
	@Autowired
	private CommoditiesService cService;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	private Member newRegister = new Member();

	
//--------------------------------- ?????? ----------------------------------------

//	@PostMapping("/LoginOK")
//	public String LoginOK() {
//		return "FrontMember/frontMemberLoginOK";
//	}
//	@PostMapping("/LoginFail")
//	public String LoginFail(RedirectAttributes redirectAttributes) {
//		redirectAttributes.addFlashAttribute("loginError", "??????????????????????????????????????????");
//		return "redirect:frontMemberLogin";
//	}
//	@GetMapping("/Logout")
//	public String Logout(RedirectAttributes redirectAttributes) {
//		redirectAttributes.addFlashAttribute("logoutMessage", "?????????????????????????????????");
//		return "redirect:frontMemberLogin";
//	}
	
	/*
	 * ???????????????
	 */
	
//	public Principal user(Principal principal) {
//		return principal;
//		
//	}

//	@PostMapping("/memberpost")
//	public String postForm(@RequestParam("username") String loginUser, 
//							@RequestParam("password") String loginPassword,
//							HttpSession httpSession, 
//							RedirectAttributes redirectAttributes) {
//
//		List<Member> result = service.checkLogin(loginUser, loginPassword);
//		httpSession.setAttribute("loginMember", result);
//		if (result.isEmpty() == true) {
//			redirectAttributes.addFlashAttribute("loginError", "??????????????????????????????????????????");
//
//			return "redirect:frontMemberLogin";
//		} else {
//			httpSession.setAttribute("loginSession", result);
//			return "FrontMember/frontMemberLoginOK";
//		}
//	}
	
		@PostMapping("/memberaddpost")
		public String postAddForm(@RequestParam("username") String loginUser,
				@RequestParam("password") String loginPassword, HttpSession httpSession,
				RedirectAttributes redirectAttributes) {

			List<Member> result = service.checkAdd(loginUser, loginPassword);
			httpSession.setAttribute("loginMember", result);
			if (result.isEmpty() == true) {
				redirectAttributes.addFlashAttribute("AddError", "????????????????????????????????????????????????????????????");

				return "redirect:frontMemberAdd";
			} else {
				httpSession.setAttribute("loginSession", result);
				return "FrontMember/frontAddMemberOK";
			}
		}

//		@GetMapping("/FrontMember/logout")
//
//		public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session,
//				RedirectAttributes redirectAttributes) {
//			session.invalidate();
//
//			Cookie[] cs = request.getCookies();
//			for (Cookie c : cs) {
//				if ("JSESSIONID".equals(c.getName())) {
//					c.setMaxAge(0);
//					response.addCookie(c);
//				}
//			}
//
//			redirectAttributes.addFlashAttribute("logoutMessage", "???????????????????????????????????????");
//			return "redirect:memberlogin";
//		}
	
	
//////----------------------- ???????????? -------------------------------------------------------------	
		
				@GetMapping("/FrontMember/goAddMember") // ????????????
				public String addMemberPage2(Model model) {
					model.addAttribute("memberBean", new Member());

					return "FrontMember/frontAddMemberPage";

				}

				/*
				 * AJAX
				 * ?????????????????????
				 */
				@ResponseBody //??????json????????????
				@PostMapping("/FrontMember/goAddMember")//?????????????????????
				public Integer ajaxAccountCheck(@RequestBody MemberDto dto) {
//					???ResponseBody??????Json???dto??????ResponseBody??????
					String memberAccount = dto.getDtoMemberAccount();
					System.out.println(memberAccount);
//					*****??????????????????ShoppingCart??????,???????????????????????????????????????,??????error
					Member oneOfAccount = service.finByAccount(memberAccount);
					if(oneOfAccount == null) {
						return 0;
					}
					return 1;
				}
				
				/*
				 * AJAX??????
				 */
				
				@PostMapping("/FrontMember/addMember")	
				public String addMember(@ModelAttribute("memberBean") Member addMem) {
					//??????
					BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
					String encodePassword = bCryptPasswordEncoder.encode(addMem.getMemberPassword());
					addMem.setMemberPassword(encodePassword);
					service.addMember(addMem);

					return "FrontMember/frontAddMemberOK";
				}

//				@GetMapping("/FrontMember/frontMemberLogin") // ?????????????????????
//				public String frontAddMember(Model model) {
//					model.addAttribute("memberBean", new Member());
//
//					return "FrontMember/frontMemberLogin";
//
//				}

				@PostMapping("/FrontMember/frontMemberLogin")	// ?????????????????????
				public String frontMemberLogin(@ModelAttribute("memberBean") Member addMem) {
					service.addMember(addMem);

					return "FrontMember/frontMemberLoginOK";
				}
//////---------------------- ???????????? -----------------------------
		
		@GetMapping("/FrontMember/memberCentre") 
		public String frontMemberLoginOK(Model model) {
			model.addAttribute("memberBean", new Member());

			return "FrontMember/memberCentre";

		}
		
		@GetMapping("/FrontMember/memberData") 
		public String frontMemberData(Model model) {
			String account = SecurityContextHolder.getContext().getAuthentication().getName();
			model.addAttribute("memberNewData", service.finByAccount(account));
//			model.addAttribute("memberBean", new Member());

			return "/FrontMember/memberData";

		}
		@PostMapping("/FrontMember/memberData")
		public String FrontMemberData(@ModelAttribute("memberNewData") Member addMem) {
			service.addMember(addMem);
			return "/FrontMember/memberCentre";
		}

		@PostMapping("/FrontMember/memberCentre")	
		public String memberCentre(@ModelAttribute("memberBean") Member addMem) {
			service.addMember(addMem);

			return "/FrontMember/memberCentre";
		}
		@GetMapping("/FrontMember/ShoppingCartList")
		public String showShoppingList(Model model) {
			String account = SecurityContextHolder.getContext().getAuthentication().getName();
			Member thisMem = service.finByAccount(account);
			Set<ShoppingCart> scSet = thisMem.getShoppigCarts();
			model.addAttribute("shoppingCartSet", scSet);
			
			
			
			
			return "/FrontMember/ShoppingCartList";
		}
		
	//-------------------------------------------------------
		
//		@GetMapping("/FrontMember/editmember") // ????????????
//		public String editMemberePage(@RequestParam("id") Integer id, Model model) {
//			Optional<Member> someMem = dao.findById(id);
//			model.addAttribute("someMember", someMem);
//			return "FrontMember/editMemberPage";
//		}
//
//		@PostMapping("/FrontMember/editMember")
//		public String editMember(@ModelAttribute("memberBean") Member mem, Model model) {
//
//			Member update = dao.save(mem);
//			model.addAttribute("memberBean", update);
//			return "redirect:../Backstage/getAllMember";
//
//		}
	
	
	
// ---------------------------------- ???????????? ------------------------------
		
	@GetMapping("/Backstage/getAllMember")
	public String findAllMember(Model model,@RequestParam(name="p",defaultValue = "1")Integer pageNumber) {
		Page<Member> page = service.findByPage(pageNumber);
		model.addAttribute("page",page);
		return "Backstage/jsp/allMember";
	}
	
	@GetMapping("/Backstage/editMember")
	public String editMemberPage(@RequestParam("id") Integer id, Model model) {
		Optional<Member> someMem = dao.findById(id);
		model.addAttribute("someMember",someMem);
		return "Backstage/jsp/editMemberPage";
	}
	
	
	
	@PostMapping("/Backstage/editMember")
	public String editMember(@ModelAttribute("memberBean") Member mem, Authentication logger) {
		//??????
		String encodePassword = encoder.encode(mem.getMemberPassword());
		mem.setMemberPassword(encodePassword);
		
		
		Member update = dao.save(mem);
		//????????????????????????ID
		Integer id = update.getId();
				
		service.editLog(logger, "??????", "??????", id, new Date());
		return "redirect:getAllMember";
		
	}
	
	@GetMapping("/Backstage/deleteMember")
	public String deleteMember(@RequestParam("id") Integer id,Authentication logger) {
		//??????
		service.editLog(logger, "??????", "??????", id, new Date());
		//??????
		dao.deleteById(id);
		
		return "redirect:getAllMember";
	}
	
	
	@GetMapping("/Backstage/goAddMember")
	public String addMemberPage(Model model) {
		model.addAttribute("memberBean",new Member());
		
		return "Backstage/jsp/addMemberPage";
		
	}
	
	@PostMapping("/addMember")
	public String addMember(@ModelAttribute("memberBean") Member addMem, Authentication logger) {
		Member addMember = service.addMember(addMem);
		Integer id = addMember.getId();
		
		service.editLog(logger, "??????", "??????", id, new Date());
		
		return "redirect:/Backstage/getAllMember";
	}
	
	
	
	@GetMapping("Backstage/searchMember")
	public String searchMemberByIdOrName(@RequestParam("id") Integer id,Model model) {

		Member mem = service.searchMemberById(id);

		model.addAttribute("memById",mem);
		return "Backstage/jsp/searchPages/searchMember";
	}
	
	
	
	
}
//SecurityContextHolder.getContext().getAuthentication().getName();
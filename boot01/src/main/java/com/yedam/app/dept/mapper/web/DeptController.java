package com.yedam.app.dept.mapper.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;
import com.yedam.app.emp.service.EmpVO;

@Controller
public class DeptController {
	private DeptService deptService;
	
	@Autowired
	DeptController(DeptService deptService) {
		this.deptService = deptService;
	}
	
	// 전체조회
	@GetMapping("deptList")
	public String deptList(Model model) {
		List<DeptVO> list = deptService.deptList();
		// 페이지 전달
		model.addAttribute("dept", list); 
		return "dept/list";
		// classpath:/templates/dept/list.html
	}
	
	// 단건조회
	@GetMapping("deptInfo")
	public String deptInfo(DeptVO deptVO, Model model) {
		DeptVO findVO = deptService.deptInfo(deptVO);
		model.addAttribute("dept", findVO);
		return "dept/info";
	}
	
	// 등록 - 페이지
	@GetMapping("deptInsert")
	public String deptInsertForm() {
		return "dept/insert";
	}
	
	// 등록 - 처리
	@PostMapping("deptInsert")
	public String deptInsertProcess(DeptVO deptVO) {
		int deptId = deptService.deptInsert(deptVO);
		
		String url = null;
		
		if(deptId > -1) {
			url = "redirect:deptInfo?departmentId=" + deptId;
		} else {
			url = "redirect:deptList";
		}
		return url;
	}
	
	// 수정 - 페이지 : Get, 조건이 필요 <=> 단건조회
	@GetMapping("deptUpdate")
	public String deptUpdateForm(DeptVO deptVO, Model model) {
		DeptVO findVO = deptService.deptInfo(deptVO);
		model.addAttribute("dept", findVO);
			
		return "dept/update";
	}
	
	// 수정 - 처리 : Post, AJAX => QueryString
	@ResponseBody
	public Map<String, Object>
		deptUpdateAJAXQueryString(DeptVO deptVO) {
		return deptService.deptUpdate(deptVO);
	}
	
	// 수정 - 처리 : JSON
	@PostMapping("deptUpdate")
	@ResponseBody
	public Map<String, Object>
		deptUpdateAJAXJSON(@RequestBody DeptVO deptVO) {
		return deptService.deptUpdate(deptVO);
	}
	
	// 삭제 - 처리
	@GetMapping("deptDelete")
	public String deptDelete(Integer departmentId) {
		deptService.deptDelete(departmentId);
		return "redirect:deptList";
	}
}

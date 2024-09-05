package com.yedam.app.dept.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.dept.mapper.DeptMapper;

@Service
public class DeptServiceImpl implements DeptService {
	private DeptMapper deptMapper;
	
	@Autowired
	DeptServiceImpl(DeptMapper deptMapper) {
		this.deptMapper = deptMapper;
	}
	
	@Override
	public List<DeptVO> deptList() {
		return deptMapper.selectDeptAll();
	}

}

package com.spring_security_one.demo.service;

import java.util.List;

import com.spring_security_one.demo.model.Staff;
import com.spring_security_one.demo.model.StaffDto;

import jakarta.validation.Valid;

public interface StaffService {
	List<Staff> getAllStaffs();

	void createStaff(StaffDto staffDto);

	Staff getStaffById(Long id);

	void updateStaff(Long id, StaffDto staffDto);

	void deleteStaff(Long id);

	StaffDto findById(Long id);

	void update(StaffDto staffDto);

	void editStaff(Long id, @Valid StaffDto staffDto);

	long staffCount();

}

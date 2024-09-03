package com.spring_security_one.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring_security_one.demo.model.Staff;
import com.spring_security_one.demo.model.StaffDto;
import com.spring_security_one.demo.repository.StaffRepository;

import jakarta.validation.Valid;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffRepository staffRepository;

	@Override
	public List<Staff> getAllStaffs() {
		return staffRepository.findAll();
	}

	public void setStaffRepository(StaffRepository staffRepository) {
		this.staffRepository = staffRepository;
	}

	@Override
	public void createStaff(StaffDto staffDto) {
		Staff staff = new Staff();
		staff.setName(staffDto.getName());
		staff.setAddress(staffDto.getAddress());
		staff.setPhno(staffDto.getPhno());
		staff.setEmail(staffDto.getEmail());
		staff.setPassword(staffDto.getPassword());
		staffRepository.save(staff);
	}

	@Override
	public Staff getStaffById(Long id) {
		return staffRepository.findById(id).orElse(null);
	}

	@Override
	public void editStaff(Long id, @Valid StaffDto staffDto) {
		Optional<Staff> staffOptional = staffRepository.findById(id);
		if (staffOptional.isPresent()) {
			Staff staff =  staffOptional.get();
			staff.setName(staffDto.getName());
			staff.setAddress(staffDto.getAddress());
			staff.setPhno(staffDto.getPhno());
			staff.setEmail(staffDto.getEmail());
			staff.setPassword(staffDto.getPassword());

			staffRepository.save(staff);

		}
	}

	@Override
	public void deleteStaff(Long id) {
		staffRepository.deleteById(id);
	}

	@Override
	public StaffDto findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStaff(Long id, StaffDto staffDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(StaffDto staffDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public long staffCount() {
		// TODO Auto-generated method stub
		return staffRepository.count();
	}

}

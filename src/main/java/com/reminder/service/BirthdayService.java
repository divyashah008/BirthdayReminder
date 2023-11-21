package com.reminder.service;

import java.util.List;

import com.reminder.entity.Birthday;

public interface BirthdayService {

	List<Birthday> getAllBirthdays();
	
	Birthday saveBirthday(Birthday birth);
	
	boolean deleteBirthday(Long id);
	
	Birthday getBirthdayById(Long id);
	
	Birthday updateBirthday(Long id,Birthday birth);
}

package com.reminder.service;

import java.util.List;

import com.reminder.entity.Birthday;

public interface BirthdayService {

	List<Birthday> getAllBirthdays();
<<<<<<< HEAD
	
	Birthday saveBirthday(Birthday birth);
	
	boolean deleteBirthday(Long id);
	
	Birthday getBirthdayById(Long id);
	
	Birthday updateBirthday(Long id,Birthday birth);
	
=======

	Birthday saveBirthday(Birthday birth);

	boolean deleteBirthday(Long id);

	Birthday getBirthdayById(Long id);

	Birthday updateBirthday(Long id, Birthday birth);

>>>>>>> 7bb25be4a820302945ec003995dd6fe9f92937fe
}

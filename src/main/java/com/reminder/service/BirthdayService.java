package com.reminder.service;

import java.util.List;

import com.reminder.entity.Birthday;

public interface BirthdayService {

	List<Birthday> getAllBirthdays();

	void saveBirthday(Birthday birth);

	void deleteBirthday(Long id);

	Birthday getBirthdayById(Long id);

	Birthday updateBirthday(Birthday birth);

}

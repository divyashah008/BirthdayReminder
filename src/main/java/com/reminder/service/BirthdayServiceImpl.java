package com.reminder.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.reminder.entity.Birthday;
import com.reminder.repository.BirthdayRepository;

@Service
public class BirthdayServiceImpl implements BirthdayService {

	private BirthdayRepository birthdayrepo;

	public BirthdayServiceImpl(BirthdayRepository birthdayrepo) {
		super();
		this.birthdayrepo = birthdayrepo;
	}

	@Override
	public List<Birthday> getAllBirthdays() {
		// TODO Auto-generated method stub
		return birthdayrepo.findAll();
	}

	@Override
	public void saveBirthday(Birthday birth) {
		// TODO Auto-generated method stub
		this.birthdayrepo.save(birth);
	}

	@Override
	public void deleteBirthday(Long id) { // TODO Auto-generated method
		this.birthdayrepo.deleteById(id);
	}

	@Override
	public Birthday getBirthdayById(Long id) {
		return birthdayrepo.findById(id).orElse(null);
	}

	@Override
	public Birthday updateBirthday(Birthday updatedBirthday) {
		return birthdayrepo.save(updatedBirthday);
	}

	
}

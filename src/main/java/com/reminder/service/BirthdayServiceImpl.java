package com.reminder.service;

import java.util.List;
import java.util.Optional;

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
	public Birthday saveBirthday(Birthday birth) {
		// TODO Auto-generated method stub
		return birthdayrepo.save(birth);
	}

	@Override
	public boolean deleteBirthday(Long id) {
		if (birthdayrepo.existsById(id)) {
			birthdayrepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Birthday getBirthdayById(Long id) {
		return birthdayrepo.findById(id).orElse(null);
	}

	@Override
	public Birthday updateBirthday(Long id, Birthday updatedBirthday) {
		Optional<Birthday> optionalExistingBirthday = birthdayrepo.findById(id);
		if (optionalExistingBirthday.isPresent()) {
			Birthday existingBirthday = optionalExistingBirthday.get();
			existingBirthday.setName(updatedBirthday.getName());
			existingBirthday.setDate(updatedBirthday.getDate());
			existingBirthday.setEmail(updatedBirthday.getEmail());
			return birthdayrepo.save(existingBirthday);
		}
		return null;
	}
}

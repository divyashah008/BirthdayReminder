package com.reminder.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reminder.emailService.EmailService;
import com.reminder.entity.Birthday;
import com.reminder.repository.BirthdayRepository;
import com.reminder.service.BirthdayService;

@RestController
@RequestMapping("/birthdays")
public class BirthdayReminderSchController {

	BirthdayService birthdayService;

	public BirthdayReminderSchController(BirthdayService birthdayService) {
		super();
		this.birthdayService = birthdayService;
	}

	@Autowired
	private BirthdayRepository birthdayrepo;

	@Autowired
	private EmailService emailService;

	// Add Birthday Reminder
	@PostMapping("/add")
	public ResponseEntity<Birthday> addBirthday(@RequestBody Birthday birthday) {
		Birthday newBirthday = birthdayService.saveBirthday(birthday);
		return ResponseEntity.status(HttpStatus.CREATED).body(newBirthday);
	}

	// Delete Birthday Reminder
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteBirthday(@PathVariable Long id) {
		boolean deleted = birthdayService.deleteBirthday(id);
		if (deleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// List of all Birthday Reminder
	@GetMapping("/list")
	public ResponseEntity<List<Birthday>> listBirthdays() {
		List<Birthday> birthdays = birthdayService.getAllBirthdays();
		return ResponseEntity.ok(birthdays);
	}

	// UPDATE BIRTHDAY
	@PutMapping("update/{id}")
	public ResponseEntity<Birthday> updateBirthday(@PathVariable Long id, @RequestBody Birthday updatedBirthday) {
		// Save the updated birthday
		Birthday birthday = birthdayService.updateBirthday(id, updatedBirthday);
		if (birthday != null) {
			return ResponseEntity.ok(birthday);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// SEND REMINDER MAIL
	@GetMapping("/send-reminders")
	public ResponseEntity<String> sendReminders() {
		List<Birthday> upcomingBirthdays = birthdayrepo.findUpcomingBirthdays();
		try {
			for (Birthday birthday : upcomingBirthdays) {
				String to = birthday.getEmail(); // Email recipient
				String subject = "Birthday Reminder";
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
				String strDate = formatter.format(birthday.getDate());
				String text = "Don't forget, " + birthday.getName() + "'s birthday is on " + strDate;
				emailService.sendBirthdayReminderEmail(to, subject, text);
			}
			return ResponseEntity.ok("Birthday reminders sent.");
		} catch (Exception e) {
			e.printStackTrace();
			// return ResponseEntity.notFound("Birthday reminders sent.");
			return ResponseEntity.notFound().build();
		}
	}
}

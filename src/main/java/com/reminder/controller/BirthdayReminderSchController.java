package com.reminder.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
	public ResponseEntity<String> addBirthday(@RequestBody Birthday birthday) {
		birthdayService.saveBirthday(birthday);
		return ResponseEntity.status(HttpStatus.CREATED).body("added");

	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteBirthday(@PathVariable Long id) {
		birthdayService.deleteBirthday(id);
		return ResponseEntity.noContent().build();
	}

	// List of all Birthday Reminder
	@GetMapping("/list")
	public ResponseEntity<List<Birthday>> listBirthdays(Model model) {
		List<Birthday> birthdays = birthdayService.getAllBirthdays();
		return ResponseEntity.ok(birthdays);
	}

	// UPDATE BIRTHDAY
	@PutMapping("update/{id}")
	public ResponseEntity<Birthday> updateBirthday(@PathVariable Long id, @RequestBody Birthday updatedBirthday) {
		Birthday existingBirthday = birthdayService.getBirthdayById(id);

		if (existingBirthday == null) {
			// Handle the case where the birthday with the given ID doesn't exist.
			return ResponseEntity.notFound().build();
		}

		// Update the existing birthday with the new data
		existingBirthday.setName(updatedBirthday.getName());
		existingBirthday.setDate(updatedBirthday.getDate());
		existingBirthday.setEmail(updatedBirthday.getEmail());

		// Save the updated birthday
		birthdayService.updateBirthday(existingBirthday);
		return ResponseEntity.ok(existingBirthday);
	}

	@GetMapping("/send-reminders")
	public String sendReminders() {
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
			return "Reminders sent successfully.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed to send reminders.";
		}
	}
}

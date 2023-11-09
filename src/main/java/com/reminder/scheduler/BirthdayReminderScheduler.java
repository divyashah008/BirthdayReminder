package com.reminder.scheduler;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reminder.emailService.EmailService;
import com.reminder.entity.Birthday;
import com.reminder.repository.BirthdayRepository;

@Component
public class BirthdayReminderScheduler {
	@Autowired
	private EmailService emailService;

	@Autowired
	private BirthdayRepository birthdayRepository;

     //	@Scheduled(cron = "0 0 0 * * *") // Run daily at midnight
	// @Scheduled(cron = "* * * * * *") // Run per second
	public void sendBirthdayReminders() {

		List<Birthday> upcomingBirthdays = birthdayRepository.findUpcomingBirthdays();
		for (Birthday birthday : upcomingBirthdays) {
			String to = birthday.getEmail(); // Email recipient
			String subject = "Birthday Reminder";
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate = formatter.format(birthday.getDate());
			String text = "Don't forget, " + birthday.getName() + "'s birthday is on " + strDate;
			emailService.sendBirthdayReminderEmail(to, subject, text);

		}
	}
}

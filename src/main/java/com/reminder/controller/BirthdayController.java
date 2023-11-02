package com.reminder.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.reminder.entity.Birthday;
import com.reminder.service.BirthdayService;

@Controller
public class BirthdayController {

	BirthdayService birthdayService;

	public BirthdayController(BirthdayService birthdayService) {
		super();
		this.birthdayService = birthdayService;
	}

	// Home Page
	@GetMapping("/index")
	public String homePage() {
		return "index";
	}

	// List of all Birthday Reminder
	@GetMapping("/birthdays")
	public String listBirthdays(Model model) {
		List<Birthday> birthdays = birthdayService.getAllBirthdays();
		model.addAttribute("birthdays", birthdays);
		return "list";
	}

	// Show Add Form Birthday Reminder
	@GetMapping("/birthdays/add")
	public String showAddReminderForm(Model model) {
		model.addAttribute("birthday", new Birthday());
		return "add";
	}

	// Add Birthday Reminder
	@PostMapping("/birthdays/add")
	public String addBirthday(@ModelAttribute Birthday birthday) throws ParseException {
		birthdayService.saveBirthday(birthday);
		return "redirect:/birthdays";
	}

	// Delete Birthday Reminder
	@GetMapping("/birthdays/delete/{id}")
	public String deleteBirthday(@PathVariable Long id) {
		birthdayService.deleteBirthday(id);
		return "redirect:/birthdays/";
	}

	@GetMapping("/birthdays/edit/{id}")
	public String editBirthdayForm(@PathVariable Long id, Model model) {
		Birthday birthday = birthdayService.getBirthdayById(id);
		model.addAttribute("birthday", birthday);
		return "edit";
	}

	@PostMapping("/birthdays/update/{id}")
	public String updateBirthday(@PathVariable Long id, @ModelAttribute("birthday") Birthday updatedBirthday) {
		Birthday existingBirthday = birthdayService.getBirthdayById(id);

		if (existingBirthday == null) {
			// Handle the case where the birthday with the given ID doesn't exist.
			return "redirect:/birthdays";
		}

		// Update the existing birthday with the new data
		existingBirthday.setName(updatedBirthday.getName());
		existingBirthday.setDate(updatedBirthday.getDate());
		existingBirthday.setEmail(updatedBirthday.getEmail());

		// Save the updated birthday
		birthdayService.updateBirthday(existingBirthday);

		return "redirect:/birthdays";
	}

}

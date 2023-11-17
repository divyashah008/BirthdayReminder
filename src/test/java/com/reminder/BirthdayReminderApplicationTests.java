package com.reminder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.reminder.entity.Birthday;
import com.reminder.repository.BirthdayRepository;
import com.reminder.service.BirthdayService;

@SpringBootTest
class BirthdayReminderApplicationTests {

	@Autowired
	private BirthdayService birthdayService;

	@MockBean
	private BirthdayRepository birthdayRepository;

	//Test Get all Birthday
	@Test
	public void testGetAllBirthdays() throws Exception {

		// Arrange
		List<Birthday> birthdayList = Arrays.asList(new Birthday(), new Birthday());
		when(birthdayRepository.findAll()).thenReturn(birthdayList);

		// Act
		List<Birthday> allBirthdays = birthdayService.getAllBirthdays();

		// Assert
		assertNotNull(allBirthdays);
		assertEquals(birthdayList.size(), allBirthdays.size());
		verify(birthdayRepository, times(1)).findAll();

	}

	//Test Add Birthday
	@Test
	public void testAddBirthday() throws Exception {
		// Arrange
		Birthday birthdayToAdd = new Birthday();
		when(birthdayRepository.save(any(Birthday.class))).thenReturn(birthdayToAdd);

		// Act
		Birthday addedBirthday = birthdayService.saveBirthday(birthdayToAdd);

		// Assert
		assertNotNull(addedBirthday);
		verify(birthdayRepository, times(1)).save(birthdayToAdd);
	}

	//Test Update Birthday
	@Test
	public void testUpdateBirthday() { // Arrange
		// Arrange
		Long birthdayId = 1L;
		Birthday existingBirthday = new Birthday();
		when(birthdayRepository.findById(birthdayId)).thenReturn(Optional.of(existingBirthday));
		when(birthdayRepository.save(any(Birthday.class))).thenAnswer(invocation -> invocation.getArgument(0));

		Birthday updatedBirthday = new Birthday();
		updatedBirthday.setName("Updated Name");

		// Act
		Birthday result = birthdayService.updateBirthday(birthdayId, updatedBirthday);

		// Assert
		assertNotNull(result);
		assertEquals(updatedBirthday.getName(), result.getName());
		verify(birthdayRepository, times(1)).findById(birthdayId);
		verify(birthdayRepository, times(1)).save(existingBirthday);
	}

	//Test Delete Birthday
	@Test
	public void testDeleteBirthday() { // Arrange
		// Arrange
		Long birthdayId = 1L;
		when(birthdayRepository.existsById(birthdayId)).thenReturn(true);

		// Act
		boolean result = birthdayService.deleteBirthday(birthdayId);

		// Assert
		assertTrue(result);
		verify(birthdayRepository, times(1)).existsById(birthdayId);
		verify(birthdayRepository, times(1)).deleteById(birthdayId);
	}
}

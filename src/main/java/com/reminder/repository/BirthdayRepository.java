package com.reminder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.reminder.entity.Birthday;

@Repository
public interface BirthdayRepository extends JpaRepository<Birthday, Long> {

	@Query(value = "SELECT b.* FROM birthdays b WHERE MONTH(b.date) = MONTH(current_date()) AND DAY(b.date) = DAY(current_date() )", nativeQuery = true)
	List<Birthday> findUpcomingBirthdays();

}

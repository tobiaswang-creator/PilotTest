package com.objectiva.pilot;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.util.StringUtils;

import com.objectiva.pilot.model.dto.SearchDto;
import com.objectiva.pilot.util.SysStatementUtil;

public class SysStatementUtilTest {

	@Test
	public void isWithinLastThreeMonth() {
		LocalDate date1 =LocalDate.of(2020, 10, 7);
		LocalDate date2 =LocalDate.of(2019, 10, 7);
		assertTrue(withinLastThreeMonth(date1));
		assertFalse(withinLastThreeMonth(date2));
	}
	
	private boolean withinLastThreeMonth(LocalDate dateField) {
		return LocalDate.now().minusMonths(3).isAfter(dateField)? false : true;
	}
	
	@Test
	public void isNotEmpty() {
		SearchDto dto = new SearchDto(null, null, "100", "300");
		System.out.print(true||false);
	}
	

}

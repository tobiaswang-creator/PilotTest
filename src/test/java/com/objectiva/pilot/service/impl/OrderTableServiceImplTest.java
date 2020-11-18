package com.objectiva.pilot.service.impl;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class OrderTableServiceImplTest {

	@Test
	public void test() {
		String str = "01.07.2012";
		String[] number = str.split("\\.");
		System.out.println(number[1]);
	}
}
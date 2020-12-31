package it.univpm.progettoOOP.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.progettoOOP.model.Period;

@RestController
public class PeriodController {

	@GetMapping("/period")
	public Period exampleMethode (@RequestParam (name = "param1", defaultValue = "World") String param1) {
		return new Period(21,5,2019,30,7,2019);
	}

	@PostMapping("/period")
	public Period exampleMethode2 (@RequestBody Period body) {
		return body;
	}
}

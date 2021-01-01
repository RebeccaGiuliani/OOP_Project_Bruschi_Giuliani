package it.univpm.progettoOOP.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.progettoOOP.filter.APICall;
import it.univpm.progettoOOP.filter.APICallService;
import it.univpm.progettoOOP.model.Period;

@RestController
public class APICallController {
	
	APICallService apicall_service;

	@RequestMapping(value = "/data", method = RequestMethod.GET)   // il request body è da testare
	public APICall chiamata_api(@RequestBody APICall apiCall){
		return new APICall(new Period(1,1,2019,31,12,2019), 43.5991, 13.511);	
	}

	/*@PostMapping("/period")
	public Period exampleMethode2 (@RequestBody Period body) {
		return body;
	}*/
}

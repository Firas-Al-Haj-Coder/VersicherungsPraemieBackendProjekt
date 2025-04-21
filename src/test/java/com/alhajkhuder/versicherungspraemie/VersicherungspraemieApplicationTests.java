package com.alhajkhuder.versicherungspraemie;

import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.assertj.MockMvcTester.MockMvcRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;

import com.alhajkhuder.versicherungspraemie.enitity.Versicherungsantrag;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
class VersicherungspraemieApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testValidAntragCreation() throws Exception {

		// create an Antrag 
		Versicherungsantrag versicherungsantrag = new Versicherungsantrag(1000, "Sedan", "53129");

		// mock a post Request 
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/antrag")
		.contentType(MediaType.APPLICATION_JSON) // the type we'll be sending is in JSON
		.content(objectMapper.writeValueAsString(versicherungsantrag)); // serialize the JO in JSON String

		// perform the request and assert the ResponseEntity
		mockMvc.perform(requestBuilder)
		.andExpect(status().isCreated()) // assert the status of creation 204
		.andExpect(content().contentType("application/json"))
		.andExpect(jsonPath("$.kilometerLeistung").value(1000))
		.andExpect(jsonPath("$.fahrzeugTyp").value("Sedan"))
		.andExpect(jsonPath("$.plz").value("53129"))
		.andExpect(jsonPath("$.praemie").value(0.5));

	}

	@Test
	void testInvalidAntragCreation() throws Exception {


		Versicherungsantrag versicherungsantrag = new Versicherungsantrag( 0 , "   ", "  ");

		// mock a post request 
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/antrag")
		.contentType(MediaType.APPLICATION_JSON) // the type we'll be sending is in JSON 
		.content(objectMapper.writeValueAsString(versicherungsantrag)); // serialize the JO in JSON String - we need an input as the user input in JSON to the backend

		mockMvc.perform(requestBuilder)
		.andExpect(status().isBadRequest()); // expects a BadRequest
	}
	
}

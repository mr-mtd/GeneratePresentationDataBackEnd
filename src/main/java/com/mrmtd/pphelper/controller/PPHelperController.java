package com.mrmtd.pphelper.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mrmtd.pphelper.client.OpenAIClient;
import com.mrmtd.pphelper.common.ChatGptRequest;
import com.mrmtd.pphelper.common.ChatGptResponse;

@RestController
public class PPHelperController {
	
	@Autowired
	Gson gson;
	@Autowired
	OpenAIClient openAiClient;


	@PostMapping("/generatePPT")
	public ResponseEntity<?> generatePPT( @RequestBody ChatGptRequest req) {

		ChatGptRequest requestFirst = new ChatGptRequest("text-davinci-003", "As a doctor, write me a speech on: "
				+ req.getPrompt() + "in plain text no line breaks", req.getMaxTokens(), req.getTemperature());
		
		
		String resp = (openAiClient.sendCompletionRequest(
				requestFirst.getPrompt(),
				req.getMaxTokens(),
				req.getTemperature()));
		
		System.out.println("ppt resp after first call is: " + resp);

		ChatGptResponse resp1obj = gson.fromJson(resp, ChatGptResponse.class);
		
		String pptPromt = "extract the core points from the followig text. then take and enhance that data to form into text for power point slides:" +
				resp1obj.getChoices().get(0).getText();
		
		pptPromt = pptPromt.replace("\n", "");
		
	
		
		System.out.println("pptPromt after first call is: \n" +
		pptPromt + " about to call to make into slide topics");
		
		String finalResp = openAiClient.sendCompletionRequest(pptPromt, 1000, 0.0);
		
		System.out.println("respSecond, final, is: \n" + finalResp);

		
		ChatGptResponse respSecond = gson.fromJson(finalResp, ChatGptResponse.class);

				
		return new ResponseEntity<ChatGptResponse>(respSecond, HttpStatus.OK);
	}
	
	
}

	
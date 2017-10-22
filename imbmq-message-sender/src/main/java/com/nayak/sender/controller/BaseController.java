package com.nayak.sender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BaseController {

	@Autowired
	JmsOperations jmsOperations;

	@Value("${ibmmq.queue}")
	private String queue;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String getRoot() {
		return "home";
	}

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public String getHome(@RequestParam("xmlData") String xmlData) {

		jmsOperations.convertAndSend(queue, xmlData.trim().replaceAll("\n", ""));

		return "redirect:/";
	}
}

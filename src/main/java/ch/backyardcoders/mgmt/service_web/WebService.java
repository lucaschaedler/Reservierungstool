package ch.backyardcoders.mgmt.service_web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebService {
	
	//Spring wird gezeigt wo der web entry point ist
	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
	
	

}

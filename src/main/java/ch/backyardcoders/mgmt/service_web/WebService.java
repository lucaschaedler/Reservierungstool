package ch.backyardcoders.mgmt.service_web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebService {
	
	//bei jedem aufruf wird template/index.html(root) aufgerufen
	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
	
	

}

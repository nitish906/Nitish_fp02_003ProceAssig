package com.masai.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.masai.Exceptation.InvidProceEntitesExceptation;
import com.masai.Model.ProceEntites;
import com.masai.Model.ProceEntityDTO;
import com.masai.Services.ProceEntitesServices;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.Data;



@RestController
public class MyController {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ProceEntitesServices eService;
	
	
	// http:localhost:8080/entries
	
	@GetMapping("/entries")
	public ResponseEntity<List<ProceEntityDTO>> getentryHandler(){
		Data data = restTemplate.getForObject("https://api.publicapis.org/entries", Data.class);
		List<ProceEntites> apientries = ((Object) data).getProceEntites();
		List<ProceEntityDTO> totalresult = new ArrayList<>();
		for(ProceEntites e : apientries) {
			ProceEntityDTO EDTO = new ProceEntityDTO();
			EDTO.setTitle(e.getApi());
			EDTO.setDescription(e.getDescription());
				totalresult.add(EDTO);	
		}
		return new ResponseEntity<List<ProceEntityDTO>>(totalresult, HttpStatus.ACCEPTED);
	}
	
	

	
	@GetMapping("/entries/{category}")
	public ResponseEntity<List<ProceEntityDTO>> getHandler(@PathVariable("category") String category) {

		Data data = restTemplate.getForObject("https://api.publicapis.org/entries", Data.class);
		List<ProceEntites> entries = data.getProceEntites();

		List<ProceEntites> ans = new ArrayList<>();

		for (ProceEntites e : entries) {
			if(e.getCategories().equals(category)) {
				ProceEntityDTO EDTO = new ProceEntityDTO();
				EDTO.setTitle(e.getApi());
				EDTO.setDescription(e.getDescription());
			    ans.add(e);
			}
		}
		return new ResponseEntity<List<ProceEntityDTO>>(HttpStatus.ACCEPTED);
	}
	
	
	
	//http:localhost:8080/entries
	
	@PostMapping("/entries")
	public ResponseEntity<String> saveEntryHandler(@RequestBody ProceEntites entry)throws InvidProceEntitesExceptation{	
		return new ResponseEntity<String>(eService.saveProEntity(entry),HttpStatus.CREATED);	
	}



}

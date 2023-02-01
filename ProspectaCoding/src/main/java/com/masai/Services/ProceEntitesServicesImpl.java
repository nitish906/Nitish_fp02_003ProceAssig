package com.masai.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.masai.Exceptation.InvidProceEntitesExceptation;
import com.masai.Model.ProceEntites;
import com.masai.Repositery.ProceEntiesDAO;



@Service
public class ProceEntitesServicesImpl implements ProceEntitesServices{
	@Autowired
	private ProceEntiesDAO dao;

	@Override
	public List<ProceEntites> getAllProcEnties() {
		return dao.findAll();
	}

	@Override
	public String saveProEntity(ProceEntites proceEntites) throws InvidProceEntitesExceptation {
	
		ProceEntites en =   dao.findByApi(proceEntites.getApi());
			
			if(en!=null) {
				 throw new InvidProceEntitesExceptation("Invalid Entry") ;
			}else {
				dao.save(proceEntites);
				return "success";
			}
		}
		

}

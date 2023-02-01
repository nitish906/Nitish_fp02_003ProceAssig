package com.masai.Services;
import java.util.*;

import com.masai.Exceptation.InvidProceEntitesExceptation;
import com.masai.Model.ProceEntites;

public interface ProceEntitesServices {
	
	public List<ProceEntites> getAllProcEnties();
	
	public String saveProEntity(ProceEntites proceEntites)throws InvidProceEntitesExceptation;

}

package com.masai.Repositery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.Model.ProceEntites;

public interface ProceEntiesDAO extends JpaRepository<ProceEntites, Integer> {
	
	
	public ProceEntites findByApi(String api);

}

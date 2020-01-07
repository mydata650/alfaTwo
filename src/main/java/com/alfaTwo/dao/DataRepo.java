package com.alfaTwo.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alfaTwo.model.Game;

@Repository
public interface DataRepo extends CrudRepository<Game, Integer> {
	Game findBytokenValue(String tokenValue);				//-Find an entity with a property other than primary key 
}

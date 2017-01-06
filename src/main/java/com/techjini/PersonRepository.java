package com.techjini;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by techjini on 6/1/17.
 */
@Repository
public interface PersonRepository extends CrudRepository<Person,Long> {
}

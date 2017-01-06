package com.techjini;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by techjini on 4/1/17.
 */
@RequestMapping("/home")
@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    Error error = new Error();
    Success success = new Success();


    @RequestMapping("/persons")
    public List<Person> getAllPerson(){
        List<Person> personsList = new ArrayList<>();
        personsList = (List<Person>) personRepository.findAll();
        return personsList;
    }

    @RequestMapping("/person/{id}")
    public Person getPerson(@PathVariable Long id){
        return personRepository.findOne(id);
    }

    @RequestMapping(value = "/person/add",method = RequestMethod.POST)
    @ResponseBody
    public Object getPersonData(@Valid Person p, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            List<String> message = new ArrayList<>();
            error.setCode(-1);
            for (FieldError e : errors){
                message.add("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage());
            }
            error.setMessage("Validation Failed");
            error.setCause(message.toString());
            return error;
        }
        else{
            personRepository.save(p);
            success.setCode(1);
            success.setMessage("Data saved Successfully");
            return success;
        }
    }
}

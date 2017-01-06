package com.techjini;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by techjini on 4/1/17.
 */

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    Error error = new Error();
    Success success = new Success();

    @RequestMapping("/")
    public String greetUser(){
        return "Hello,Java Lover <3";
    }
    @PreAuthorize("hasRole('ROLE_USER')")
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

    @RequestMapping(value = "/person/{id}",method = RequestMethod.DELETE)
    public Object deletePerson(@PathVariable Long id){
        personRepository.delete(id);
        success.setCode(3);
        success.setMessage("Deleted Successfully");
        return success;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/persons",method = RequestMethod.DELETE)
    public Object deleteAllPersons(){
        personRepository.deleteAll();
        success.setCode(3);
        success.setMessage("Deleted Successfully");
        return success;
    }

    @RequestMapping(value = "/person/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Object updatePerson(@PathVariable Long id,@Valid Person p,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            List<String> message = new ArrayList<>();
            error.setCode(-2);
            for (FieldError e : errors){
                message.add("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage());
            }
            error.setMessage("Update Failed");
            error.setCause(message.toString());
            return error;
        }
        else
        {
            Person person = personRepository.findOne(id);
            person = p;
            personRepository.save(person);
            success.setMessage("Updated Successfully");
            success.setCode(2);
            return success;
        }

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

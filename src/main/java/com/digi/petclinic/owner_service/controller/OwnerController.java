/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.digi.petclinic.owner_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.digi.petclinic.owner_service.dao.OwnerRepository;
import com.digi.petclinic.owner_service.entity.Owner;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@RestController
@RequestMapping("/owner")
class OwnerController {

    @Autowired
    public OwnerRepository ownerRepository;
    
    @GetMapping("/ownersByLastName")
    public Collection<Owner> getOwnersByLastName(@RequestParam String lastName) {
    	
        // allow parameterless GET request for /ownersByLastName to return all records
        if (lastName == null) {
        	lastName = ""; // empty string signifies broadest possible search
        } 
        System.out.println("#### lastName is : " + lastName );
        // find owners by last name
        Collection<Owner> listOfowners = ownerRepository.findByLastName(lastName);
        return listOfowners;
       
    } 
    
    @GetMapping("/ownerById")
    public Owner getOwnerById(@RequestParam Integer ownerId) {
    	
    	System.out.println("#### ownerId is : " + ownerId );
        // find owners by id
        Owner owner = ownerRepository.findByOwnerId(ownerId);
        
        return owner;
       
    } 
    
    @GetMapping("/getAllOwners")
    public List<Owner> showOwnerList(Map<String, Object> model) {

        List<Owner> owners = ownerRepository.findAll();
        return owners;
    }
    
    
    @PostMapping("/saveOwner")
    public Owner saveOwner(@RequestBody Owner owner) {
    	
    	System.out.println("#### owner : " + owner );
        // save owner
        return ownerRepository.save(owner);
          
    } 

}

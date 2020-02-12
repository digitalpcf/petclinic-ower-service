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
import com.digi.petclinic.owner_service.dao.PetRepository;
import com.digi.petclinic.owner_service.dto.PetDTORequest;
import com.digi.petclinic.owner_service.dto.PetDTOResponse;
import com.digi.petclinic.owner_service.entity.Owner;
import com.digi.petclinic.owner_service.entity.Pet;
import com.digi.petclinic.owner_service.entity.PetType;

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
@RequestMapping("/pet")
class PetController {

    @Autowired
    public PetRepository petRepository;
    @Autowired
    public OwnerRepository ownerRepository;
    
   
    @GetMapping("/petType")
    public Collection<PetType> getPetType() {
    	// fetch list of petType
    	Collection<PetType> petTypeList = petRepository.findPetTypes();
        return petTypeList;
       
    } 
    
    @GetMapping("/petById")
    public PetDTOResponse getPetById(@RequestParam Integer petId) {

    	System.out.println("#### petById petId is : " + petId );
    	// find pet by id
    	Pet pet = petRepository.findByPetId(petId);
    	
    	System.out.println("#### petById owner is : " + pet.getOwner() );
    	PetDTOResponse petDTOResponse = new PetDTOResponse();
    	petDTOResponse.setPet(pet);
    	petDTOResponse.setOwner(pet.getOwner());
    	
    	return petDTOResponse;

    } 

    
    @PostMapping("/savePet")
    public Pet savePet(@RequestBody PetDTORequest petDTORequest) {
    	
    	System.out.println("#### savePet Controller Pet : " + petDTORequest.getPet().getName() + 
    			" :Id " + petDTORequest.getPet().getId() + 
    			" birth:" + petDTORequest.getPet().getBirthDate() );
        // save Pet
    	
    	Owner owner = petDTORequest.getOwner();
    	Pet pet = petDTORequest.getPet();
    	if(pet.getOwner() == null) {
    		pet.setOwner(owner);
    	}
    	
    	try {
        pet = petRepository.save(pet);
    	}catch( Exception ex) {
    		ex.printStackTrace();
    	}
    	
         return pet; 
    } 

}

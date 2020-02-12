package com.digi.petclinic.owner_service.dto;

import com.digi.petclinic.owner_service.entity.Owner;
import com.digi.petclinic.owner_service.entity.Pet;

public class PetDTORequest {
		
	public Pet pet;
	public Owner owner;
	
	public Pet getPet() {
		return pet;
	}
	public void setPet(Pet pet) {
		this.pet = pet;
	}
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	

}

package com.bilel.SpringBoot_TP01.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.bilel.SpringBoot_TP01.entities.Speciality;
import com.bilel.SpringBoot_TP01.services.SpecialityService;

@RequestMapping("/api/sepcialities")
@RestController
@CrossOrigin
public class SpecialityRestController {
	@Autowired
	private SpecialityService specialityService;

	@GetMapping("/all")
	List<Speciality> getAllSpecialities() {
		return this.specialityService.getSpecialities();
	}

	@GetMapping
	Page<Speciality> getSpecialitiesPageByPage(@RequestParam("page") int page, @RequestParam("size") int size) {
		return this.specialityService.getSpecialitiesByPage(page, size);
	}

	@GetMapping("/{specialityId}")
	Speciality getSpecialityById(@PathVariable("specialityId") Long specialityId) {
		return this.specialityService.getSpecialityById(specialityId);
	}

	@PostMapping
	Speciality createNewSpeciality(@RequestBody Speciality speciality) {
		return this.specialityService.createSpeciality(speciality);
	}

	@PatchMapping("/{specialityId}")
	Speciality updateSpeciality(@PathVariable("specialityId") Long specialityId, @RequestBody Speciality speciality) {
		speciality.setSpecialityId(specialityId);
		return this.specialityService.updateSpeciality(speciality);
	}

	@DeleteMapping("/{specialityId}")
	void deleteSpeciality(@PathVariable("specialityId") Long specialityId) {
		this.specialityService.deleteSpecialityById(specialityId);
	}
}

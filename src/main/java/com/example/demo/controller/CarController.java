package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Car;
import com.example.demo.services.CarServiceImpl;

@Controller
@RequestMapping(value = "/car")
public class CarController {
	@Autowired
	CarServiceImpl carServiceImpl;

	@GetMapping(value = "/carview")
	public String carview(Model model) {
		// allCars.add(new Car("Diesel", 200, 2019, "Black"));
		// allCars.add(new Car("Gas", 100, 1985, "Beige"));
		// allCars.add(new Car("Gasoline", 150, 2013, "Brown"));
		model.addAttribute("object", carServiceImpl.selectAll());
		return "carview"; // html page carview.html
	}

	@GetMapping(value = "/carview/{id}")
	public String carviewById(@PathVariable(name = "id") int id, Model model) {
		model.addAttribute("object", carServiceImpl.selectById(id));
		return "carview";
	}

	// TODO CREATE, UPDATE, DELETE

	@GetMapping(value = "/addcar") // localhost:8080/addcar
	public String addcarGet(Car car)
	// method
	{
		return "addcar"; // addcar.html
	}

	@PostMapping(value = "/addcar") // after submit button pressed
	public String addcarPost(@Valid Car car, BindingResult result) // filled car object
	{
		if(result.hasErrors())
			return "carview";
		else
		{	
		carServiceImpl.insertNewCar(car);
		return "redirect:/car/carview";
	}
}
	@GetMapping(value = "/updatecar/{id}") // localhost:8080/updatecar
	public String updatecarGet(@PathVariable(name = "id") int id, Model model) {
		model.addAttribute("car", carServiceImpl.selectById(id));
		return "updatecar"; // updatecar.html
	}

	@PostMapping(value = "/updatecar{id}") // after submit button pressed
	public String updatecarPost(@PathVariable(name = "id") int id, Car car) // filled car object
	{
		carServiceImpl.updateCarById(car, id);
		return "redirect:/car/carview";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteCarGet(@PathVariable(name = "id") int id) {
		carServiceImpl.deleteCarById(id);
		return "redirect:/car/carview";
	}
}
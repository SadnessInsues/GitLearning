package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.repo.CarRepo;
import com.example.demo.model.Car;

@Controller
public class SimpleController {

	@Autowired
	CarRepo carRepo;
	
	@GetMapping(value="/testdata")
	public String testdata()
	{
		/**INSERT*/
		//add new cars
		Car car1 = new Car("Fuel", 123, 1999, "Brown");
		Car car2 = new Car("Gas", 123, 2000, "Red");
		//save in the database using repo
		carRepo.save(car1);
		carRepo.save(car2);
		
		/**SELECT ALL*/
		for(Car c:carRepo.findAll())
		{
			System.out.println(c.getEngine() + " " + c.getSpeed());
		}
		
		/**SELECT ONE*/
		
		Car carTemp = carRepo.findById(1).get(); //get car with id 1
		if(carTemp!=null)
			System.out.println(carTemp.getEngine() + "" + carTemp.getId()); //print it
		
		/**UPDATE*/
		//TODO get car by id
		//TODO update color
		//TODO save it
		
		Car carTempUpd = carRepo.findById(2).get();
			if(carTempUpd!=null) 
			{
				carTempUpd.setColor("Black");
				carRepo.save(carTempUpd);
			}
		//*DELETE*/
		//TODO get car by id
		//TODO delete
		Car carTempDel =carRepo.findById(1).get();
			if(carTempDel!=null)
			carRepo.delete(carTempDel);	
				
		ArrayList<Car> carsYear2000 = carRepo.findByYear(2000);
			for(int i=0; i < carsYear2000.size();i++)
				System.out.println(carsYear2000.get(i));
		return "testdata";
	}
}

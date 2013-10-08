/** 
 * @author Aditya Mahajan <aditya.mahajan@mcgill.ca>
 * @version 2013.10.06
 * Unit Testing Temperature class
 */

import org.junit.* ;
import static org.junit.Assert.* ;

public class TemperatureTest {

	// Add multiple tests to check all functions of
	// {@Code Temperature} class.

	//1. Check the getUnits() method
	@Test
	public void testGetUnits (){
		//For testing this method, we only need to test 1 unit, and both constructors.
		/* REASONING:
		 * The three units are all part of an enum, and dont actually change anything apart from 
		 * the calculation during the conversion. Since we are only looking at getUnits(), which 
		 * essentially is a getter method, as long as the argument to the constructor is correct, 
		 * i.e. one of the enum values, the outcome will be the same for every unit. 
		 * 
		 * However, we should test the 2 different constructors to ensure that the units 
		 * do not change when the second constructor calls upon the first constructor. 
		 */
		
		//Here we will be testing in Kelvin (arbitrary choice)
		
		//First constructor, where we input the value and the units. 
		Temperature kelvinTemperature = new Temperature(100, Temperature.Units.KELVIN);
		assertTrue("First Constructor: getUnits = Kelvin", kelvinTemperature.getUnits().equals(Temperature.Units.KELVIN));
		
		//Second constructor, where we input a previously created temperature.
		Temperature secondKelvinTemperature = new Temperature(kelvinTemperature);
		assertTrue("Second Constrictor: getUnits = Kelvin", secondKelvinTemperature.getUnits().equals(Temperature.Units.KELVIN));	
	}

	// @Test
	// public void .... (){
	//    ...
	// }
}

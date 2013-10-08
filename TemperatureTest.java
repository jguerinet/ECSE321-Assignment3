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
	public void testGetUnits(){
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
		assertTrue("Second Constructor: getUnits = Kelvin", secondKelvinTemperature.getUnits().equals(Temperature.Units.KELVIN));	
	}

	//1. Check the getValue() method. 
	 @Test
	 public void testGetValue(){
		 //For testing this method, we need to test all 3 units and both constructors. 
		 /* REASONING: 
		  * In both constructors, we are storing the actual value in Kelvins, regardless of the 
		  * initial unit. When we call getValue(), this value is then re-converted to the original unit 
		  * Therefore, we need to make sure that during the conversions to and from Kelvins the value was not 
		  * compromised. 
		  * The 2 constructors do not need to be tested here, since we can clearly see that the value is passed on 
		  * from 1 constructor to another, therefore making the testing unnecessary. 
		  * We are also going to test decimal values, since the value argument in the temperature constructor 
		  * is a double, and we need to make sure that the same precision is kept during the conversions. 
		  * Finally, we are also going to test 
		  */
		 
		 //Note, I made doubles here instead of hardcoding the values to be able to quickly change them in case multiple values needed to be tested. 
		 
		 double roundValue = 78;
		 double decimalValue = 98.546245;
		 double negativeValue = -25.654654;
		 
		 /* Kelvin Testing */
		 //Round Number
		 Temperature roundKelvinTemperature = new Temperature(roundValue, Temperature.Units.KELVIN);
		 assertTrue("getValue : Round Kelvin", roundKelvinTemperature.getValue() == roundValue);
		 
		 //Decimal Number
		 Temperature decimalKelvinTemperature = new Temperature(decimalValue, Temperature.Units.KELVIN);
		 assertTrue("getValue : Decimal Kelvin", decimalKelvinTemperature.getValue() == decimalValue);
		 
		 //Negative Number
		 Temperature negativeKelvinTemperature = new Temperature(negativeValue, Temperature.Units.KELVIN);
		 assertTrue("getValue : Negative Kelvin", negativeKelvinTemperature.getValue() == negativeValue);
		 
		 /*Celsius Testing*/
		 //Round Number
		 Temperature roundCelsiusTemperature = new Temperature(roundValue, Temperature.Units.CELSIUS);
		 assertTrue("getValue : Round Celsius", roundCelsiusTemperature.getValue() == roundValue);
		 
		 //Decimal Number
		 Temperature decimalCelsiusTemperature = new Temperature(decimalValue, Temperature.Units.CELSIUS);
		 assertTrue("getValue : Decimal Celsius", decimalCelsiusTemperature.getValue() == decimalValue);
		 
		 //Negative Number
		 Temperature negativeCelsiusTemperature = new Temperature(negativeValue, Temperature.Units.CELSIUS);
		 assertTrue("getValue : Negative Celsius", negativeCelsiusTemperature.getValue() == negativeValue);
		 
		 /*Fahrenheit Testing*/
		 //Round Number
		 Temperature roundFahrenheitTemperature = new Temperature(roundValue, Temperature.Units.FAHRENHEIT);
		 assertTrue("getValue : Round Fahrenheit", roundFahrenheitTemperature.getValue() == roundValue);
		 
		 //Decimal Number
		 Temperature decimalFahrenheitTemperature = new Temperature(decimalValue, Temperature.Units.FAHRENHEIT);
		 assertTrue("getValue : Decimal Fahrenheit", decimalFahrenheitTemperature.getValue() == decimalValue);
		 
		 //Negative Number
		 Temperature negativeFahrenheitTemperature = new Temperature(negativeValue, Temperature.Units.FAHRENHEIT);
		 assertTrue("getValue : Negative Fahrenheit", negativeFahrenheitTemperature.getValue() == negativeValue);
	 }
}

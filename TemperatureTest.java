/** 
 * @author Aditya Mahajan <aditya.mahajan@mcgill.ca>
 * @version 2013.10.06
 * Unit Testing Temperature class
 */

import org.junit.* ;
import static org.junit.Assert.* ;

public class TemperatureTest {
	static double roundValue, negativeValue, decimalValue; 
	static double[] values1, values2, values3, values4; 
	 
	//This is to set up the double arrays of values. The 0th position will have the celsius value, the 1st will have the
	//Fahrenheit value,and the second will have the Kelvin value.
	//It is also to set up the values used to test getValue().
	@BeforeClass 
	public static void setUpValues(){
		roundValue = 78;
		decimalValue = 98.546245;
		negativeValue = -25.654654;
		
		values1 = new double[3];
		values1[0] = 100; 
		values1[1] = 212;
		values1[2] = 373.15;
		
	 	values2 = new double[3];
	 	values2[0] = -273.15; 
	 	values2[1] = -459.67;
	 	values2[2] = 0;
		
	 	values3 = new double[3];
	 	values3[0] = 1.111111; 
	 	values3[1] = 34;
	 	values3[2] = 274.261111;
		
	 	values4 = new double[3];
	 	values4[0] = 281.85; 
	 	values4[1] = 539.33;
	 	values4[2] = 555;
	}
	
	/* Note : The test cases were chosen assuming that we were doing white-box testing, and therefore I could read and access the code. 
	 * This is important because I have chosen to omit some trivial tests to keep my testing precise and useful.
	 * For example, for the testing of the units, if this were black box testing i would have tested every unit, but since I read through
	 * the temperature class I clearly saw that the unit was directly stored and never tampered with, and therefore did not feel obliged
	 * to test every different scenario. More details on the choosing of each test case are included in each method.
	 */

	// Add multiple tests to check all functions of
	// {@Code Temperature} class.

	//1. Check the getUnits() method
	@Test
	public void testGetUnits(){
		//For testing this method, we only need to test 1 unit, and 1 constructor.
		/* REASONING:
		 * The three units are all part of an enum, and don't actually change anything apart from 
		 * the calculation during the conversion. Since we are only looking at getUnits(), which 
		 * essentially is a getter method, as long as the argument to the constructor is correct, 
		 * i.e. one of the enum values, the outcome will be the same for every unit. 
		 * We only need to test 1 constructor as well because the second constructor literally takes the units
		 * of the first constructor and stores it. Nothing is done to the unit at any moment.
		 */
		
		//Here we will be testing in Kelvin (arbitrary choice)
		
		//First constructor, where we input the value and the units. 
		Temperature kelvinTemperature = new Temperature(100, Temperature.Units.KELVIN);
		assertTrue("First Constructor: getUnits = Kelvin", kelvinTemperature.getUnits().equals(Temperature.Units.KELVIN));	
	}

	//1. Check the getValue() method. 
	 //For testing this method, we need to test all 3 units.
	 /* REASONING: 
	  * In both constructors, we are storing the actual value in Kelvins, regardless of the 
	  * initial unit. When we call getValue(), this value is then re-converted to the original unit 
	  * Therefore, we need to make sure that during the conversions to and from Kelvins the value was not 
	  * compromised. 
	  * The 2 constructors do not need to be tested here, since we can clearly see that the value is passed on 
	  * from 1 constructor to another, therefore making the testing unnecessary. 
	  * We are also going to test decimal values, since the value argument in the temperature constructor 
	  * is a double, and we need to make sure that the same precision is kept during the conversions. 
	  */
	
	@Test
	public void testGetValueKelvin(){	 
		 //Round Number
		 Temperature roundKelvinTemperature = new Temperature(roundValue, Temperature.Units.KELVIN);
		 assertTrue("getValue : Round Kelvin", roundKelvinTemperature.getValue() == roundValue);
		 
		 //Decimal Number
		 Temperature decimalKelvinTemperature = new Temperature(decimalValue, Temperature.Units.KELVIN);
		 assertTrue("getValue : Decimal Kelvin", decimalKelvinTemperature.getValue() == decimalValue);
	}
	
	@Test
	public void testGetValueCelsius(){			 
		 //Round Number
		 Temperature roundCelsiusTemperature = new Temperature(roundValue, Temperature.Units.CELSIUS);
		 assertTrue("getValue : Round Celsius", roundCelsiusTemperature.getValue() == roundValue);
		 
		 //Decimal Number
		 Temperature decimalCelsiusTemperature = new Temperature(decimalValue, Temperature.Units.CELSIUS);
		 assertTrue("getValue : Decimal Celsius", decimalCelsiusTemperature.getValue() == decimalValue);
		 
		 //Negative Number
		 Temperature negativeCelsiusTemperature = new Temperature(negativeValue, Temperature.Units.CELSIUS);
		 assertTrue("getValue : Negative Celsius", negativeCelsiusTemperature.getValue() == negativeValue);
	}
		 
	@Test
	public void testGetValueFahrenheit(){		 
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
	 
	//Testing values that are below absolute 0 
	@Test(expected = Exception.class)
	public void testBelowAbsoluteZero(){
		 Temperature negativeTemperature = new Temperature(-800, Temperature.Units.FAHRENHEIT);
		 fail();
	}
	 
	 //2. Check the changeUnits() function for all six possible conversions between Celsius, Fahrenheit, and Kelvin.
	 
	 /* A couple of testing conversion values : 
	  * 100 C = 212 F = 373.15
	  * -273.15 = -459.67 F = 0 K
	  * 1.111111 C = 34 F = 274.261111 K
	  * 281.85 C = 539.33 F = 555 K
	  * 
	  * Values were chosen at random, and chosen to involve negative, decimal, and round numbers for every temperature unit
	  * (except negative kelvins, because we cannot be below absolute 0).
	  * These values are accurate up to 1e-6, with rounding done for the last number.  
	  */
	 
	//Test 1: Storing them in Celsius, getting them in Kelvin. 
	@Test
	public void testChangeUnitsFromCelsiusToKelvin(){
		 //Temperature initially in Celsius
		Temperature celsiusTemperature1 = new Temperature(values1[0], Temperature.Units.CELSIUS);
		celsiusTemperature1.changeUnits(Temperature.Units.KELVIN);
		Temperature celsiusTemperature2 = new Temperature(values2[0], Temperature.Units.CELSIUS);
		celsiusTemperature2.changeUnits(Temperature.Units.KELVIN);
		Temperature celsiusTemperature3 = new Temperature(values3[0], Temperature.Units.CELSIUS);
		celsiusTemperature3.changeUnits(Temperature.Units.KELVIN);
		Temperature celsiusTemperature4 = new Temperature(values4[0], Temperature.Units.CELSIUS);
		celsiusTemperature4.changeUnits(Temperature.Units.KELVIN);
		
		//Test Celsius -> Kelvin conversion
		assertTrue("changeUnits : Celsius 1 -> Kelvin", celsiusTemperature1.getValue() == values1[2]);
		assertTrue("changeUnits : Celsius 2 -> Kelvin", celsiusTemperature2.getValue() == values2[2]);
		assertTrue("changeUnits : Celsius 3 -> Kelvin", celsiusTemperature3.getValue() == values3[2]);
		assertTrue("changeUnits : Celsius 4 -> Kelvin", celsiusTemperature4.getValue() == values4[2]);
	}

	//Test 2: Storing them in Fahrenheit, getting them in Kelvin. 
	@Test
	public void testChangeUnitsFromFahrenheitToKelvin(){
		//Temperature initially in Fahrenheit
		Temperature fahrenheitTemperature1 = new Temperature(values1[1], Temperature.Units.FAHRENHEIT);
		fahrenheitTemperature1.changeUnits(Temperature.Units.KELVIN);
		Temperature fahrenheitTemperature2 = new Temperature(values2[1], Temperature.Units.FAHRENHEIT);
		fahrenheitTemperature2.changeUnits(Temperature.Units.KELVIN);
		Temperature fahrenheitTemperature3 = new Temperature(values3[1], Temperature.Units.FAHRENHEIT);
		fahrenheitTemperature3.changeUnits(Temperature.Units.KELVIN);
		Temperature fahrenheitTemperature4 = new Temperature(values4[1], Temperature.Units.FAHRENHEIT);
		fahrenheitTemperature4.changeUnits(Temperature.Units.KELVIN);
		
		//Test Fahrenheit -> Kelvin conversion. 
		assertTrue("changeUnits : Fahrenheit 1 -> Kelvin", fahrenheitTemperature1.getValue() == values1[2]);
		assertTrue("changeUnits : Fahrenheit 2 -> Kelvin", fahrenheitTemperature2.getValue() == values2[2]);
		assertTrue("changeUnits : Fahrenheit 3 -> Kelvin", fahrenheitTemperature3.getValue() == values3[2]);
		assertTrue("changeUnits : Fahrenheit 4 -> Kelvin", fahrenheitTemperature4.getValue() == values4[2]);
	}
	
	//Test 3: Storing them in Kelvin, getting them in Celsius
	@Test
	public void testChangeUnitsFromKelvinToCelsius(){
		 //Temperature initially in Kelvin
		Temperature kelvinTemperature1 = new Temperature(values1[2], Temperature.Units.KELVIN);
		kelvinTemperature1.changeUnits(Temperature.Units.CELSIUS);
		Temperature kelvinTemperature2 = new Temperature(values2[2], Temperature.Units.KELVIN);
		kelvinTemperature2.changeUnits(Temperature.Units.CELSIUS);
		Temperature kelvinTemperature3 = new Temperature(values3[2], Temperature.Units.KELVIN);
		kelvinTemperature3.changeUnits(Temperature.Units.CELSIUS);
		Temperature kelvinTemperature4 = new Temperature(values4[2], Temperature.Units.KELVIN);
		kelvinTemperature4.changeUnits(Temperature.Units.CELSIUS);
		
		//Test Kelvin -> Celsius conversion
		assertTrue("changeUnits : Kelvin 1 -> Celsius", kelvinTemperature1.getValue() == values1[0]);
		assertTrue("changeUnits : Kelvin 2 -> Celsius", kelvinTemperature2.getValue() == values2[0]);
		assertTrue("changeUnits : Kelvin 3 -> Celsius", kelvinTemperature3.getValue() == values3[0]);
		assertTrue("changeUnits : Kelvin 4 -> Celsius", kelvinTemperature4.getValue() == values4[0]);
	}
	
	//Test 4: Storing them in Fahrenheit, getting them in Celsius
	@Test
	public void testChangeUnitsFromFahrenheitToCelsius(){
		//Temperature initially in Fahrenheit
		Temperature fahrenheitTemperature1 = new Temperature(values1[1], Temperature.Units.FAHRENHEIT);
		fahrenheitTemperature1.changeUnits(Temperature.Units.CELSIUS);
		Temperature fahrenheitTemperature2 = new Temperature(values2[1], Temperature.Units.FAHRENHEIT);
		fahrenheitTemperature2.changeUnits(Temperature.Units.CELSIUS);
		Temperature fahrenheitTemperature3 = new Temperature(values3[1], Temperature.Units.FAHRENHEIT);
		fahrenheitTemperature3.changeUnits(Temperature.Units.CELSIUS);
		Temperature fahrenheitTemperature4 = new Temperature(values4[1], Temperature.Units.FAHRENHEIT);
		fahrenheitTemperature4.changeUnits(Temperature.Units.CELSIUS);
		
		//Test Fahrenheit -> Celsius conversion
		assertTrue("changeUnits : Fahrenheit 1 -> Celsius", fahrenheitTemperature1.getValue() == values1[0]);
		assertTrue("changeUnits : Fahrenheit 2 -> Celsius", fahrenheitTemperature2.getValue() == values2[0]);
		assertTrue("changeUnits : Fahrenheit 3 -> Celsius", fahrenheitTemperature3.getValue() == values3[0]);
		assertTrue("changeUnits : Fahrenheit 4 -> Celsius", fahrenheitTemperature4.getValue() == values4[0]);
	}
	
	//Test 5:Storing them in Kelvin, getting them in Fahrenheit
	@Test
	public void testChangeUnitsFromKelvinToFahrenheit(){
		//Temperature initially in Kelvin
		Temperature kelvinTemperature1 = new Temperature(values1[2], Temperature.Units.KELVIN);
		kelvinTemperature1.changeUnits(Temperature.Units.FAHRENHEIT);
		Temperature kelvinTemperature2 = new Temperature(values2[2], Temperature.Units.KELVIN);
		kelvinTemperature2.changeUnits(Temperature.Units.FAHRENHEIT);
		Temperature kelvinTemperature3 = new Temperature(values3[2], Temperature.Units.KELVIN);
		kelvinTemperature3.changeUnits(Temperature.Units.FAHRENHEIT);
		Temperature kelvinTemperature4 = new Temperature(values4[2], Temperature.Units.KELVIN);
		kelvinTemperature4.changeUnits(Temperature.Units.FAHRENHEIT);
		
		//Test Kelvin -> Fahrenheit Conversion
		assertTrue("changeUnits : Kelvin 1 -> Fahrenheit", kelvinTemperature1.getValue() == values1[1]);
		assertTrue("changeUnits : Kelvin 2 -> Fahrenheit", kelvinTemperature2.getValue() == values2[1]);
		assertTrue("changeUnits : Kelvin 3 -> Fahrenheit", kelvinTemperature3.getValue() == values3[1]);
		assertTrue("changeUnits : Kelvin 4 -> Fahrenheit", kelvinTemperature4.getValue() == values4[1]);
	}
	
	//Test 6:Storing them in Celsius, getting them in Fahrenheit
	@Test
	public void testChangeUnitsFromCelsiusToFahrenheit(){
		 //Temperature initially in Celsius
		Temperature celsiusTemperature1 = new Temperature(values1[0], Temperature.Units.CELSIUS);
		celsiusTemperature1.changeUnits(Temperature.Units.FAHRENHEIT);
		Temperature celsiusTemperature2 = new Temperature(values2[0], Temperature.Units.CELSIUS);
		celsiusTemperature2.changeUnits(Temperature.Units.FAHRENHEIT);
		Temperature celsiusTemperature3 = new Temperature(values3[0], Temperature.Units.CELSIUS);
		celsiusTemperature3.changeUnits(Temperature.Units.FAHRENHEIT);
		Temperature celsiusTemperature4 = new Temperature(values4[0], Temperature.Units.CELSIUS);
		celsiusTemperature4.changeUnits(Temperature.Units.FAHRENHEIT);
		
		//Test Celsius -> Fahrenheit conversion.
		assertTrue("changeUnits : Celsius 1 -> Fahrenheit", celsiusTemperature1.getValue() == values1[1]);
		assertTrue("changeUnits : Celsius 2 -> Fahrenheit", celsiusTemperature2.getValue() == values2[1]);
		assertTrue("changeUnits : Celsius 3 -> Fahrenheit", celsiusTemperature3.getValue() == values3[1]);
		assertTrue("changeUnits : Celsius 4 -> Fahrenheit", celsiusTemperature4.getValue() == values4[1]);
	}
}

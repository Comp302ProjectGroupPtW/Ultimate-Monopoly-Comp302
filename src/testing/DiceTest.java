package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.Dice;

public class DiceTest {
	Dice dice = Dice.getInstance();
	
	@Test
	public void testGetInstance(){
		Dice test = Dice.getInstance();
		assertTrue("repOk is false.", test.repOk());
	}
	@Test
	public void testRoll() {
		int sum = dice.roll(); // I will roll the dice at least once at the beginning of every test to ensure that there are values set to proper fields.
		assertTrue("faceValue1 is less than 1!", 1 <= dice.getFaceValue1() );
		assertTrue("faceValue1 is greater than 6!", 6 >= dice.getFaceValue1() );

		assertTrue("faceValue2 is less than 1!", 1 <= dice.getFaceValue2() );
		assertTrue("faceValue2 is greater than 6!", 6 >= dice.getFaceValue2() );

		assertTrue("faceValueSpeed is less than 0!", 0 <= dice.getFaceValueSpeed() );
		assertTrue("faceValueSpeed is greater than 3!", 4 >= dice.getFaceValueSpeed() );
		
		if(dice.getFaceValueSpeed() != 4){
		assertTrue("Returned value is not equal to the sum of the face values!", // returned value of roll should be equal to the sum of individual dice. If not, there is an error.
				sum == dice.getFaceValue1() + dice.getFaceValue2() + dice.getFaceValueSpeed());
		} else assertTrue("Returned value is not equal to the sum of the face values!", // if faceValueSpeed is 4, it is bus so it shouldn't count it as pips.
				sum == dice.getFaceValue1() + dice.getFaceValue2());
		
		dice.roll();
		int firstFaceVal1 = dice.getFaceValue1();
		int firstFaceVal2 = dice.getFaceValue2();
		int firstFaceValSpeed = dice.getFaceValueSpeed();
		dice.roll();
		int secondFaceVal1 = dice.getFaceValue1();
		int secondFaceVal2 = dice.getFaceValue2();
		int secondFaceValSpeed = dice.getFaceValueSpeed();
		dice.roll();
		int thirdFaceVal1 = dice.getFaceValue1();
		int thirdFaceVal2 = dice.getFaceValue2();
		int thirdFaceValSpeed = dice.getFaceValueSpeed();
		boolean isRandom = true;
		if((firstFaceVal1 == secondFaceVal1) && (secondFaceVal1 == thirdFaceVal1) && (firstFaceVal2 == secondFaceVal2) && (secondFaceVal2 == thirdFaceVal2) &&
				(firstFaceValSpeed == secondFaceValSpeed) && (secondFaceValSpeed == thirdFaceValSpeed)){
			isRandom = false;
		} 
		assertTrue("Dices are not random as they should be.", isRandom);
	}

	@Test
	public void testBasicRoll(){
		boolean isDouble = dice.basicRoll();
		assertTrue("faceValue1 is less than 1!", 1 <= dice.getFaceValue1() );
		assertTrue("faceValue1 is greater than 6!", 6 >= dice.getFaceValue1() );

		assertTrue("faceValue2 is less than 1!", 1 <= dice.getFaceValue2() );
		assertTrue("faceValue2 is greater than 6!", 6 >= dice.getFaceValue2());

		assertTrue("Returned value doesn't show correctly whether the dice is double or not.", dice.isDouble() == isDouble);
		
		dice.roll();
		int firstFaceVal1 = dice.getFaceValue1();
		int firstFaceVal2 = dice.getFaceValue2();
		dice.roll();
		int secondFaceVal1 = dice.getFaceValue1();
		int secondFaceVal2 = dice.getFaceValue2();
		dice.roll();
		int thirdFaceVal1 = dice.getFaceValue1();
		int thirdFaceVal2 = dice.getFaceValue2();
		boolean isRandom = true;
		if((firstFaceVal1 == secondFaceVal1) && (secondFaceVal1 == thirdFaceVal1) && (firstFaceVal2 == secondFaceVal2) && (secondFaceVal2 == thirdFaceVal2)){
			isRandom = false;
		} 
		assertTrue("Dices are not random as they should be.", isRandom);
	}
	@Test
	public void testIsDouble(){
		dice.roll();
		
		dice.setFaceValue1(3);
		dice.setFaceValue2(3);
		dice.setFaceValueSpeed(2);
		assertTrue("isDouble returns false even though FaceValue1 and FaceValue2 are same and FaceValueSpeed is different.", dice.isDouble());
		
		dice.setFaceValueSpeed(3);
		assertFalse("isDouble returns true, even though it's triple, not double.", dice.isDouble()); //faceValueSpeed should be different
		
		dice.setFaceValue1(1);
		dice.setFaceValue2(2);
		dice.setFaceValueSpeed(2);
		assertFalse("isDouble returns true, efen though FaceValue1 and FaceValue2 are not same.", dice.isDouble());		
	}
	@Test
	public void testIsMM(){
		dice.roll();
		
		dice.setFaceValueSpeed(0);
		boolean isMM = dice.isMM();
		assertTrue("Shows that Mr. Monopoly is not rolled, but it is", isMM); //isMM should be true because FaceValueSpeed = 0 means it's Mr Monopoly.
		
		dice.setFaceValueSpeed(3);
		isMM = dice.isMM();
		assertFalse("Shows that Mr. Monopoly is rolled, but it isn't.", isMM);		
	}
	@Test
	public void testIsBus(){
		dice.roll();
		
		dice.setFaceValueSpeed(4);
		boolean isBus = dice.isBus();
		assertTrue("Shows that Bus is not rolled, but it is", isBus); ////isMM should be true because FaceValueSpeed = 4 means it's Bus.
		
		dice.setFaceValueSpeed(2);
		isBus = dice.isBus();
		assertFalse("Shows that Bus is rolled, but it isn't.", isBus);				
	}
	@Test
	public void testFlush(){
		dice.roll();
		
		dice.setDoubleCount(2);
		dice.flush();
		assertFalse("Doesn't reset the doubleCount", dice.getDoubleCount() == 2); //doubleCount should be zero, shouldn't remain at 2.		
		dice.flush();
		assertFalse("Using flush consecutively results in error; doubleCount is not 0.", dice.getDoubleCount() == 2);
		dice.setDoubleCount(0);
		dice.flush();
		assertTrue("Using flush while double count is already zero results in error.", dice.getDoubleCount() == 0);
	}
	@Test
	public void testIsThirdDouble(){
		dice.roll();
		
		dice.setDoubleCount(3);
		boolean isThirdDouble = dice.isThirdDouble();
		assertTrue("Doesn't show that user rolled three doubles in a row", isThirdDouble);
		assertTrue("Doesn't reset the double count after three double rolls", dice.getDoubleCount() == 0);
		
		dice.setDoubleCount(2);
		isThirdDouble = dice.isThirdDouble();
		assertFalse("Shows that user rolled three doubles in a row even if it's false", isThirdDouble);	
		assertFalse("isThirdDouble resets the double count even if it isn't the third double roll.", dice.getDoubleCount() == 0);
	}
	@Test
	public void testGetSpeedDieString(){
		dice.roll();
		
		dice.setFaceValueSpeed(0);
		String str = dice.getSpeedDieString();
		assertTrue("Doesn't show the name of Mr. Monopoly", str.equals("Mr. Monopoly"));
		
		dice.setFaceValueSpeed(4);
		str = dice.getSpeedDieString();
		assertTrue("Doesn't show the name of Bus", str.equals("Bus"));
	}
	@Test
	public void testIsEven(){
		dice.roll();
		
		dice.setFaceValue1(1);
		dice.setFaceValue2(1);
		assertTrue("isEven returned false even though sum of pips of regular dices was even.", dice.isEven());
		
		dice.setFaceValue1(6);
		dice.setFaceValue2(4);
		assertTrue("isEven returned false even though sum of pips of regular dices was even.", dice.isEven()); //same test with different numbers.
		
		dice.setFaceValue1(1);
		dice.setFaceValue2(2);
		assertFalse("isEven returned true even though sum of pips of regular dices was odd.", dice.isEven());
	}
	@Test
	public void testGetSum(){
		dice.roll();
		
		dice.setFaceValue1(1);
		dice.setFaceValue2(2);
		dice.setFaceValueSpeed(2);
		assertTrue("Sum didn't work in standard case.", dice.getSum() == 5); //Standard case where there is no Mr. Monopoly/bus
		
		dice.setFaceValueSpeed(0);
		assertTrue("Sum didn't work in Mr. Monopoly case.", dice.getSum() == 3); //Mr. monopoly case, where 0 on speed die means Mr. Monopoly.
		
		dice.setFaceValueSpeed(4);
		assertTrue("Sum didn't work in Bus case.", dice.getSum() == 3); //Bus case, where 4 on speed die means Bus.
	}

}


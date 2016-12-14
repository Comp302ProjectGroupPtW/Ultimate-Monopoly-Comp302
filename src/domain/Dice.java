package domain;
import java.util.Random;

/**
 * 
 * @author Eray Yirik
 *
 */
public class Dice {

	private static Dice instance = new Dice();

	private int faceValue1;
	private int faceValue2;
	private int faceValueSpeed;
	private int doubleCount;
	Random rd;

	public boolean debug=false;
	public int debug1;
	public int debug2;
	public int debug3;

	/**
	 * @effects constructs the singleton dice, initializes private variables.
	 * @modifies faceValue1
	 * @modifies faceValue2
	 * @modifies faceValueSpeed
	 * @modifies doubleCount
	 * @modifies rd
	 */
	protected Dice(){
		faceValue1 = 1;
		faceValue2 = 1;
		faceValueSpeed = 1;
		doubleCount = 0;
		rd = new Random();
	}
	/**
	 * Shows if representation invariant is correct.
	 * @return boolean value of whether the representation invariant is correct.
	 */
	public boolean repOk(){
		if(faceValue1<1 || faceValue1>6 || faceValue2<1 || faceValue2>6 || faceValueSpeed<0 || faceValueSpeed>4 || doubleCount<0 || doubleCount>3 || rd ==null) return false;
		return true;
	}


	/**
	 * Returns Dice instance.
	 * @effects Returns the instance of a singleton Dice.
	 * @return instance
	 */
	public static Dice getInstance(){
		return instance;
	}
	/**
	 * Sets doubleCount with given value.
	 * @param count the value to be replaced with doubleCount
	 * @effects Changes the private variable doubleCount with the parameter count. If count&lt;0 sets doubleCount to 0, if count&gt;3 sets doubleCount to 3.
	 * @modifies doubleCount
	 */
	public void setDoubleCount(int count){ //only for testing and debugging
		if(count<0) count = 0;
		else if(count>3) count = 3;
		else this.doubleCount = count;
	}
	/**
	 * Returns doubleCount.
	 * @effects Returns the doubleCount field.
	 * @return doubleCount
	 */
	public int getDoubleCount(){ //only for testing and debugging
		return this.doubleCount;
	}
	/**
	 * Sets a new value to FaceValue1.
	 * @param val the value to be replaced with faceValue1.
	 * @effects Changes the private variable FaceValue1 with val.
	 * @modifies faceValue1
	 */
	public void setFaceValue1(int val){
		this.faceValue1 = val;
	}
	/**
	 * Sets a new value to FaceValue2.
	 * @param val the value to be replaced with faceValue2.
	 * @effects Changes the private variable FaceValue2 with val.
	 * @modifies faceValue2
	 */
	public void setFaceValue2(int val){
		this.faceValue2 = val;
	}
	/**
	 * Sets a new value to FaceValueSpeed.
	 * @param val the value to be replaced with faceValueSpeed.
	 * @effects Changes the private variable faceValueSpeed with val.
	 * @modifies faceValueSpeed
	 */
	public void setFaceValueSpeed(int val){
		this.faceValueSpeed = val;
	}
	/**
	 * Rolls the dice.
	 * @effects Assigns random int values to faces of the dice and increases the doubleCount if
	 * faceValue1 and faceValue2 are same and faceValueSpeed is different from them.
	 * For faceValue1 and faceValue2 assigned values are between 1 and 6 (inclusive).
	 * For faceValueSpeed assigned value is between 0 and 4 (inclusive).
	 * @modifies	faceValue1, faceValue2, faceValueSpeed, doubleCount
	 * @return		faceValue1 + faceValue2 + faceValueSpeed
	 */
	public int roll(){ 
		int val1,val2,val3;
		if(debug){
			val1 = debug1;
			val2 = debug2;
			val3 = debug3;
			debug=false;
		}
		else{
			val1 = rd.nextInt(6)+1;
			val2 = rd.nextInt(6)+1;
			val3 = rd.nextInt(5); //0 means Mr. Monopoly and 4 means Bus.
		}
		setFaceValue1(val1);
		setFaceValue2(val2);
		setFaceValueSpeed(val3);
		if((val1 == val2) && (val1 != val3)){
			doubleCount++;
		}
		else{
			doubleCount = 0;
		}
		GuiHandler.getInstance().showMessage("You rolled "+getFaceValue1()+", "+getFaceValue2()+", "+getSpeedDieString(), "Dice");

		return getSum();
	}
	
	/**
	 * Returns the sum of pips from last roll.
	 * @return sum of pips
	 */
	public int getSum(){
		if(faceValueSpeed == 4) return faceValue1 + faceValue2;
		return faceValue1 + faceValue2 + faceValueSpeed;
	}

	/**
	 * Assigns random integers to faceValue 1 and faceValue2.
	 * @effects Assigns random int values to faceValue1 and faceValue2. The assigned faceValue1 and faceValue2 values are between 1 and 6 (inclusive).
	 * @modifies faceValue1
	 * @modifies faceValue2
	 * @return if the last roll is double or not. 
	 */
	public boolean basicRoll(){ //used in get out of jail and roll once
		int val1 = rd.nextInt(6)+1;
		int val2 = rd.nextInt(6)+1;
		faceValue1 = val1;
		faceValue2 = val2;
		GuiHandler.getInstance().showMessage("You rolled "+getFaceValue1()+", "+getFaceValue2(), "Dice");
		return isDouble();
	}

	/**
	 * Shows if Mr. Monopoly is rolled.
	 * @effects Shows if Mr. Monopoly is rolled or not at last dice roll. Mr. Monopoly is represented by faceValueSpeed = 0.
	 * @return if faceValueSpeed=0 or not.
	 */
	public boolean isMM(){
		return (faceValueSpeed == 0);
	}

	/**
	 * Shows if Bus is rolled.
	 * @effects Shows if bus is rolled or not at last dice roll. Bus is represented by faceValueSpeed = 4.
	 * @return if faceValueSpeed=4 or not.
	 */
	public boolean isBus(){
		return (faceValueSpeed == 4);
	}

	/**
	 * Resets the double count.
	 * @effects Sets the double count to 0 which is used every time a player rolls double three times in a row or a players turn ends.
	 * @modifies doubleCount
	 */
	public void flush(){
		doubleCount = 0;
	}

	/**
	 * Shows if dice is rolled double.
	 * @effects Returns if the last dice is double, which means the faceValue1 and faceValue2 are same and they are different from faceValueSpeed.
	 * @return if faceValue1 and faceValue2 is equal and they are different from faceValueSpeed.
	 */
	public boolean isDouble(){
		return ((faceValue1 == faceValue2) && (faceValue1 != faceValueSpeed));
	}

	/**
	 * Shows if user rolled double three times in a row.
	 * @effects Returns user rolled double three times in a row and reset the double count in case if this is true. 
	 * @modifies doubleCount (if return value of isThirdDouble() is true)
	 * @return if doubleCount is equal or greater than 3.
	 */
	public boolean isThirdDouble(){
		if(doubleCount >= 3){
			flush();
			return true;
		}
		return false;
	}
	/**
	 * Returns faceValue1.
	 * @return faceValue1
	 */
	public int getFaceValue1(){
		return faceValue1;
	}
	/**
	 * Returns faceValue2.
	 * @return faceValue2
	 */
	public int getFaceValue2(){
		return faceValue2;
	}
	/**
	 * Returns faceValueSpeed.
	 * @return faceValueSpeed
	 */
	public int getFaceValueSpeed(){ // call only if isMM() or isBus() = true
		return faceValueSpeed;
	}
	/**
	 * Calculates and returns if the sum of pips of regular dices is even.
	 * @effects the return value determines if transit tunnel will be used.
	 * @return if faceValue1 + faceValue2 is even.
	 */
	public boolean isEven(){ 
		return (faceValue1 + faceValue2) % 2 == 0; 
	}

	/**
	 * Shows the dice value as String.
	 * @effects Shows the name of faceValueSpeed as String for gui.
	 * @return "Mr. Monopoly, "Bus" or faceValueSpeed
	 */
	public String getSpeedDieString(){
		switch(faceValueSpeed){
		case 0:
			return "Mr. Monopoly";
		case 4:
			return "Bus";
		default:
			return String.valueOf(faceValueSpeed);

		}
	}	
}

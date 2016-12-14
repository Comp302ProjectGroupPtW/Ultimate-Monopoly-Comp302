package domain;

import java.awt.Color;

public class ColorFactory {
	private static ColorFactory instance;
	
	private ColorFactory(){
		
	}
	
	public static ColorFactory getInstance(){
		if(instance == null)
			instance = new ColorFactory();
		return instance;
		
	}
	
	public Color stringToColor(String name){
		if(name.equalsIgnoreCase("Blue"))
			return Color.BLUE;
		if(name.equalsIgnoreCase("Green"))
			return Color.GREEN;
		if(name.equalsIgnoreCase("Pink"))
			return Color.PINK;
		if(name.equalsIgnoreCase("Orange"))
			return Color.ORANGE;
		if(name.equalsIgnoreCase("Purple"))
			return new Color(255,0,255);
		if(name.equalsIgnoreCase("Light Blue"))
			return new Color(153,204,255);
		if(name.equalsIgnoreCase("Red"))
			return Color.RED;
		if(name.equalsIgnoreCase("Yellow"))
			return Color.YELLOW;
		if(name.equalsIgnoreCase("Green"))
			return Color.GREEN;
		if(name.equalsIgnoreCase("White"))
			return Color.WHITE;
		if(name.equalsIgnoreCase("Black"))
			return Color.BLACK;
		if(name.equalsIgnoreCase("Silver"))
			return new Color(224,224,224);
		if(name.equalsIgnoreCase("Brown"))
			return new Color(153,76,0);
		if(name.equalsIgnoreCase("Light Pink"))
			return new Color(255,182,193);
		if(name.equalsIgnoreCase("Light Green"))
			return new Color(144,238,144);
		if(name.equalsIgnoreCase("Cream"))
			return new Color(245,245,220);
		if(name.equalsIgnoreCase("Teal"))
			return new Color(0,128,128);
		if(name.equalsIgnoreCase("Wine"))
			return new Color(220,20,60);
		if(name.equalsIgnoreCase("Gold"))
			return new Color(255,215,0);
		if(name.equalsIgnoreCase("Peach"))
			return new Color(255,218,185);
		if(name.equalsIgnoreCase("Maroon"))
			return new Color(128,0,0);

		return null;
	}
}

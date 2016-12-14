package domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.persistence.jaxb.JAXBContextFactory;

public class SaveLoadHandler {

	private OutputStream outputStream;


	private static SaveLoadHandler instance = new SaveLoadHandler();
	private static HashMap<Integer, Object> map = new HashMap<Integer, Object>();

	public static SaveLoadHandler getInstance(){
		return instance;
	}

	public static void main(String[] args) {
		/*for (int i = 0; i < 100; i++) {
		Toolkit.getDefaultToolkit().beep();
		long c = System.currentTimeMillis();
		while(System.currentTimeMillis() < c + 1000L){
			System.out.println(System.currentTimeMillis());
		}
		Toolkit.getDefaultToolkit().beep();
		}*/
		Game.getInstance(); new SaveLoadHandler().save(true);
		new SaveLoadHandler().load("saved_game.xml");
	}

	public void save(boolean outputStreamIsSet) {
		//TODO Revise
		try{
			outputStream = new FileOutputStream("saved_game.xml");
			if(!outputStreamIsSet)
				throw new Exception("An error occured when setting the Output Stream");

			JAXBContext jc = JAXBContext.newInstance("domain");
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(Game.getInstance(), outputStream);
			GuiHandler.getInstance().showMessage("Game saved to saved_game.xml", "Save Successful");

		} catch (Exception e) {
			e.printStackTrace();
			GuiHandler.getInstance().showMessage(e.getMessage(), "Save Error");
		}

	}

	public void load(String fileName){

		try{
			JAXBContext jc = JAXBContext.newInstance( "domain" );
			Unmarshaller m = jc.createUnmarshaller();
			//Game.setGame((Game) m.unmarshal(findFile(fileName))); // TODO Check if really needed
			System.out.println(((Game) m.unmarshal(findFile(fileName))).getCurrentPlayer());
			GuiHandler.getInstance().setGame(Game.getInstance());
			//calibrateBoard();
			GuiHandler.getInstance().init(Game.getInstance().getPlayers());
			GuiHandler.getInstance().updateOwners();
			GuiHandler.getInstance().showMessage("Game loaded.", "Load Successful");

		} catch (Exception e) {
			e.printStackTrace();
			GuiHandler.getInstance().showMessage(e.getMessage(), "Load Error");
		}

	}

	private File findFile(String fileName) throws FileNotFoundException{
		File file = new File(fileName);
		if(!file.exists())
			throw new FileNotFoundException(fileName + " does not exist!");
		return file;
	}

	/*private boolean setOutputStream(String fileName){
		if(!fileName.equals(""))
			try {
				outputStream = new FileOutputStream(fileName);
				return true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		return false;
	}*/

	/*private void calibrateBoard(){
		for (Object obj : map.values()) {
			if(obj instanceof Square){
				Board b = Board.getInstance();
				
			}
		}
	}*/

	public static class ReferencedObject{
		@XmlAttribute
		private int ref;
		@XmlElement
		private Object obj;

	}
	public static class RefAdapter extends XmlAdapter<ReferencedObject, Object>{




		@Override
		public ReferencedObject marshal(Object t) throws Exception {
			if(t == null)
				return null;
			ReferencedObject refObj = new ReferencedObject();
			refObj.ref = t.hashCode();
			refObj.obj = t;
			return refObj;
		}

		@Override
		public Object unmarshal(ReferencedObject refObj) throws Exception {
			if(refObj == null)
				return null;
			System.out.println(refObj.obj);
			if(map.containsKey(refObj.ref))
				return map.get(refObj.ref);

			map.put(refObj.ref, refObj.obj);
			return refObj.obj;
		}

	}
	/*public static class Square2DArrayAdapter extends XmlAdapter<ArrayList<ArrayList<Square>>, Square[][]>{

		@Override
		public ArrayList<ArrayList<Square>> marshal(Square[][] layers) throws Exception {
			ArrayList<ArrayList<Square>> al = new ArrayList<ArrayList<Square>>();
			for (Square[] layer : layers) {
				al.add(new ArrayList<>(Arrays.asList(layer)));
			}
			System.out.println(al);
			return al;
		}

		@Override
		public Square[][] unmarshal(ArrayList<ArrayList<Square>> al) throws Exception {
			Square[][] squares = new Square[3][];
			for (int i = 0; i < squares.length; i++) {
				squares[i] = al.get(i).toArray(new Square[0]);
			}
			return squares;
		}
	}*/

	public static class Square2DArrayAdapter2 extends XmlAdapter<ANewHope, Square[][]>{

		@Override
		public ANewHope marshal(Square[][] squares) throws Exception {
			ANewHope result = new ANewHope();
			result.squares0 = squares[0];
			result.squares1 = squares[1];
			result.squares2 = squares[2];
			return result;
		}

		@Override
		public Square[][] unmarshal(ANewHope v) throws Exception {
			System.out.println(((ReferencedObject) v.squares1[1]).obj);
			Square[][] squares = new Square[3][];
			System.out.println("Test"+v);
			squares[0] = (Square[]) v.squares0;
			squares[1] = (Square[]) v.squares1;
			squares[2] = (Square[]) v.squares2;
			System.out.println("Test");
			return squares;
		}

	}

	public static class ANewHope{
		@XmlElementWrapper(name = "layer-0")
		@XmlElement(name = "square", type = Square.class)
		private Object[] squares0;

		@XmlElementWrapper(name = "layer-1")
		@XmlElement(name = "square", type = Square.class)
		private Object[] squares1;

		@XmlElementWrapper(name = "layer-2")
		@XmlElement(name = "square", type = Square.class)
		private Object[] squares2;

		private ANewHope(){
			//For JAXB
			System.out.println();
		}
	}

}

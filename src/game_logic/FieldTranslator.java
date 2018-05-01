package game_logic;

import java.util.HashMap;
import java.util.Map.Entry;

public class FieldTranslator {
	
	private HashMap<Integer,String> fieldCoordinates;
	
	public FieldTranslator() {
		fieldCoordinates = new HashMap<Integer, String>();
		String[] tmp = {"A", "B", "C", "D", "E", "F", "G", "H"};
		
		int row = 1;
		for (int i = 0; i < tmp.length; i++) {
			int coord = 0;
			switch(row) {
			case 1: coord = i; break;
			case 2: coord = i+16; break;
			case 3: coord = i+32; break;
			case 4: coord = i+48; break;
			case 5: coord = i+64; break;
			case 6: coord = i+80; break;
			case 7: coord = i+96; break;
			case 8: coord = i+112; break;
			}
			
			String field = tmp[i] + row; 
			fieldCoordinates.put(coord, field);
			
			if (i == 7 && row < 8) {
				row++;
				i = -1;
			}
		}
	}
	
	// Print entire board with keys and values
	public String toString() {
		return fieldCoordinates.toString();
	}
	
	public String getFieldName(int fieldCoord) {
		return fieldCoordinates.get(fieldCoord);
	}
	
	public int getFieldCoordinate(String fieldName) {
		for (Entry<Integer, String> entry : fieldCoordinates.entrySet()) {
            if (entry.getValue().equals(fieldName)) {
                return entry.getKey();
            }
		}
		return -1;
	}
	
	
	// Main class for testing with examples - Shall be deleted upon usage
//	public static void main(String[] args) {
//		FieldTranslator translator = new FieldTranslator();
//		String inputString = "A5";
//		int inputInteger = 83;
//		System.out.println("Coordinate: " + inputString + " has index " + translator.getFieldCoordinate(inputString));
//		System.out.println("Field: " + inputInteger + " has field name " + translator.getFieldName(inputInteger));
//	}
}

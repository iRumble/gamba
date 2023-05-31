package igra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.HashMap;

public class Mreza extends Panel {
	
	ArrayList<Polje> Polja = new ArrayList<>();
	protected HashMap<Polje, String> listaPolja = new HashMap<>();
	private int rowNumber = 4;
	private int colNumber = 5;
	
	public Mreza(int row, int col) {
		super();
	//	int dimW = (getWidth()/2)/this.getCols() * this.getCols();
	//	int dimH = (getHeight()/2)/this.getRows() * this.getRows();
	//	this.setPreferredSize(new Dimension(dimW, dimH));
		setBackground(Color.BLACK);
		rowNumber = row;
		colNumber = col;
		this.setLayout(new GridLayout(row, col, 5, 5));
		for(int i=0; i<rowNumber*colNumber; i++) {
			Polje polje = new Polje(this, i);
			add(polje);
			Polja.add(polje);
			listaPolja.put(polje, polje.getStatus());

		}
		
	}
	
	public int getRows() {
		return rowNumber;
	}
	
	public int getCols() {
		return colNumber;
	}
	
	public int getBrojIzabranih() {
		int izabrani = 0;
		for (String value : listaPolja.values()) {
            if (value == "IZABRANO") {
            	izabrani++;
            }
		}
		return izabrani;
	}
	
	/* public static void main(String[] args) {
		Frame f = new Frame();
		f.setSize(500, 500);
		Mreza m = new Mreza(4,5);
		f.add(m);
		f.setVisible(true);*
	} */
}

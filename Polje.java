package igra;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Polje extends Canvas {
	
	private Mreza owner;
	private Label natpis = new Label("");
	protected String status = "SLOBODNO";
	
	public void adjust(MouseEvent e) {
	//	owner.AzurirajKvotu();
		if (status == "SLOBODNO") {
			setStatusIZABRANO();
			owner.listaPolja.put(this, this.getStatus());
		//	System.out.println("Promenjeno u izabrano");
			
		//	owner.listaPolja.forEach((k,v) -> System.out.println("Key = "
	    //           + k + ", Value = " + v)); 
			
		//	System.out.println("natpis: " + getNatpis());
			
		} else {
			setStatusSLOBODNO();
			owner.listaPolja.put(this, this.getStatus());
		//	System.out.println("Promenjeno u slobodno");
		//	System.out.println("natpis: " + getNatpis());
		}
	}
	
	public Polje(Mreza owner, int broj) {
		this.owner=owner;
		Dimension dim = new Dimension(75, 75);
		setPreferredSize(dim);
		setBackground(Color.ORANGE);
		natpis.setText(Integer.toString(broj));
		natpis.setBackground(Color.BLACK);
		natpis.setFont(new Font("Arial", Font.BOLD, 16));
		Panel panel = new Panel();
		natpis.setPreferredSize(new Dimension(this.getWidth()/3, this.getHeight()/3));
		panel.add(natpis);
		panel.add(this);
		
		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				adjust(e);
				repaint();
				Igra.igra.azuriraj();
			} 
		});
	}
	
	public int getNatpis() {
		return Integer.parseInt(natpis.getText());
	}
	public String getStatus() {
		return status;
	}
	public void setStatusIZABRANO() {
		this.status = "IZABRANO";
	}
	public void setStatusSLOBODNO() {
		this.status = "SLOBODNO";
	}
	
	public void paint(Graphics g) {
//		drawNumber();
		if (status == "SLOBODNO") {
			drawSlobodno(g);
		} else {
			drawIzabran(g);
		}
		
	}

	public void drawSlobodno(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString(natpis.getText(), this.getWidth()/2 - 4, this.getHeight()/2 + 4);
		
	}
	public void drawIzabran(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.WHITE);
		g.drawString(natpis.getText(), this.getWidth()/2 - 4, this.getHeight()/2 + 4);
		
	}

}

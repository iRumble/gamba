package igra;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Igra extends Frame {
	
	private Mreza mreza;
	private Panel controlPanel= new Panel();
	private Panel centerPanel = new Panel();
	protected Label status = new Label();
	double dobitak = 0;
	double kvota = 0;
	double balans = 1000;
	double ulog = 0;
	
	Label kvota2 = new Label(String.format("%.2f", kvota));
	
	Label balans1 = new Label("Balans: ");
	Label balans2 = new Label("1000");
	Label ulog1 = new Label("Ulog:");
	TextField ulogField = new TextField(5);
	
	
	Label kvota1 = new Label("Kvota: ");	
	Label dobitak1 = new Label("Dobitak: ");
	Label dobitak2 = new Label(String.format("%.2f", dobitak));
	Button igraj = new Button("Igraj");
	private void populateWindow() {
		
		
		
		mreza = new Mreza(4,5);
		controlPanel.setBackground(Color.LIGHT_GRAY);
		ulogField.setText("100");
		ulogField.addTextListener((te) ->{
			azuriraj();
		});
		
		igraj.addActionListener(ae -> {
			Generator g = new Generator();
			int generisani;
			generisani = g.Generate(0, mreza.getCols()*mreza.getRows() - 1);
			   for (Polje p : mreza.listaPolja.keySet()) {
				   if(p.getStatus() == "SLOBODNO") {
					   if(p.getNatpis() == generisani) {
						   status.setBackground(Color.RED);
						   status.setText("" + generisani);	
						   balans = balans - ulog;
						   break;
					   	}
					   continue;
				   }
			//	   if ((p.getStatus() == "IZABRANO") && (generisani == p.getNatpis())) {
				   if(p.getNatpis() == generisani) {
					   status.setBackground(Color.GREEN);
					   status.setText("" + generisani);
					   balans = balans + dobitak - ulog;
					   break;
				   }
			//	   } else {
			//		   status.setBackground(Color.RED);
			//		   status.setText("" + generisani);	
			//		   balans = balans - dobitak;
			//		   balans2.setText("" + balans);
			//		   break;
			//	   }
			   }		
			azuriraj();
		}); 
		
		status.setBackground(Color.DARK_GRAY);
		Panel balansp = new Panel(new FlowLayout(FlowLayout.LEFT));
		Panel ulogp = new Panel(new FlowLayout(FlowLayout.LEFT));
		Panel kvotap = new Panel(new FlowLayout(FlowLayout.LEFT));
		Panel dobitakp = new Panel(new FlowLayout(FlowLayout.LEFT));
		Panel buttonp = new Panel(new FlowLayout(FlowLayout.RIGHT));
		controlPanel.setLayout(new GridLayout(5,2));
		balansp.add(balans1);
		balansp.add(balans2);
		ulogp.add(ulog1);
		ulogp.add(ulogField);
		kvotap.add(kvota1);
		kvotap.add(kvota2);
		dobitakp.add(dobitak1);
		dobitakp.add(dobitak2);
		buttonp.add(igraj);
		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				
				if (mreza.getBrojIzabranih() == 0) {
					kvota = 0;
				} else {
					kvota = mreza.getCols()*mreza.getRows()/mreza.getBrojIzabranih();
				} 
				kvota2.setText(String.format("%.2f", kvota));
			}
		});
		
// Button for testing purposes
		
//	Button ispisiKvotu = new Button("Ispisi Kvotu");
//	ispisiKvotu.addActionListener(ae -> {
//	
//		if (mreza.getBrojIzabranih() == 0) {
//			kvota = 0;
//		} else {
//			kvota = mreza.getCols()*mreza.getRows()/mreza.getBrojIzabranih();
//		}
//		System.out.println("kvota: " + kvota);
//	});
//	buttonp.add(ispisiKvotu); 
		controlPanel.setPreferredSize(new Dimension(130, 300));
		controlPanel.add(balansp);
		controlPanel.add(ulogp);
		controlPanel.add(kvotap);
		controlPanel.add(dobitakp);
		controlPanel.add(buttonp);
		add(mreza, BorderLayout.CENTER);
		add(controlPanel, BorderLayout.EAST);
		add(status, BorderLayout.SOUTH);

		
	}
	
	public Igra() {
		setBounds(700, 200, 500, 300);
		setTitle("Igra");
		populateWindow();
		pack();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				dispose();
			}
		});
		setVisible(true);
	}
	public void azuriraj() {
		if (mreza.getBrojIzabranih() == 0) {
			kvota = 0;
		} else {
			kvota = (double)mreza.getCols()*mreza.getRows()/mreza.getBrojIzabranih();
		} 
		kvota2.setText(String.format("%.2f", kvota));
		
		ulog = 0;
		try {
			ulog = Integer.parseInt(ulogField.getText());
		} catch (NumberFormatException e) {
			
		}
	//	int duzina = ulogField.getText().length();
		dobitak = ulog*kvota;
		dobitak2.setText(String.format("%.2f", dobitak));
		balans2.setText(String.format("%.2f", (balans - ulog)));
		kvota2.revalidate();
		dobitak2.revalidate();
		balans2.revalidate();
	}
	public static Igra igra;
	public static void main(String[] args) {
		igra = new Igra();
	}

}

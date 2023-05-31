package igra;

import java.util.Random;

public class Generator {

	private int slucajan_broj;
	public Generator() {
	}
	public int Generate(int donja_granica, int gornja_granica) {
		return slucajan_broj = donja_granica + new Random().nextInt(gornja_granica - donja_granica + 1);
	}
	
	public static void main(String[] args) {
		// Test
		Generator g = new Generator();
		System.out.println(g.Generate(3, 5));
		
	}
}

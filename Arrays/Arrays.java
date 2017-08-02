import java.util.*;

public class Arrays {
	public static void main(final String[] args) {
		// test array for the water collection, should produce 4
		int[] water_collect = {3, 2, 4, 1, 5};

		System.out.println("The amount of water collected from the given array of elevations is: " 
			+ waterCollect(water_collect));		

		// test array for the water collection, should produce 8
		int[] water_collect_2 = {6, 2, 4, 1, 5};
		System.out.println("The amount of water collected from the given array of elevations is: " 
			+ waterCollect(water_collect_2));	
	}


	/** 
	 * Method which finds the amount of water that can be trapped between certain elevations
	 *
	 * @param a: An array of elevations
	 * @return The amount of water trapped between the elevations */
	private static int waterCollect(final int a[]) {
		int length = a.length, waterCollected = 0;
		int[] leftMaxima = new int[length];
		int[] rightMaxima = new int[length];
		leftMaxima[0] = a[0]; rightMaxima[length - 1] = a[length - 1]; 
		int i = 1;
		// find the left maxima
		for(; i < length; i++) {
			leftMaxima[i] = Math.max(leftMaxima[i - 1], a[i]);
		}
		
		i = length - 2;
		// find the right maxima
		for(; i >= 0; i--) {
			rightMaxima[i] = Math.max(rightMaxima[i + 1], a[i]);
		}

		// find the amount of water collected
		for(i = 0; i < length; i++) {
			waterCollected += Math.min(leftMaxima[i], rightMaxima[i]) - a[i];
		}
		return waterCollected;
	}
}
public class BitEncoding {
	private static String[] counties = { "Fairfield", "New Haven", "Hartford",
			"Windham", "Tolland", "Middlesex", "New London", "Litchfield" };
	private static long data;
	private static long county;

	public static void main(String[] args) {
		for (int i = 0; i < counties.length; i++) {
			System.out.print("Encoding " + counties[i]);
			enocodeCounty(i);
			System.out.println("\tYou encoded " + deocodeCounty()
					+ " county.\n");
		}
	}

	// county 3 bits will be encoded into the high
	// bits of the data property
	public static void enocodeCounty(long county) {
		long mask1 = 0x00000000000007L;
		county = county & mask1;
		long mask2 = 0x1000000000000000L;
		mask2 = mask2 | county << 61;
		// clear county data bits
		data = data & 0x1FFFFFFFFFFFFFFFL;
		// now encode the county
		data = data | mask2;
	} // BitEncoding

	public static String deocodeCounty() {
		int myCounty = 0;
		myCounty = (int) (data >>> 61);
		String county = "VOID";
		switch (myCounty) {
		case 0:
			county = "Fairfield";
			break;
		case 1:
			county = "New Haven";
			break;
		case 2:
			county = "Hartford";
			break;
		case 3:
			county = "Windham";
			break;
		case 4:
			county = "Tolland";
			break;
		case 5:
			county = "Middlesex";
			break;
		case 6:
			county = "New London";
			break;
		case 7:
			county = "Litchfield";
		}
		return county;
	}
} // BitEncoding

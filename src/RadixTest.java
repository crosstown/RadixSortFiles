import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RadixTest {

	/**
	 * @param args
	 * @throws IOException
	 * @throws NoSuchFieldException
	 */
	public static void main(String[] args) throws IOException,
			NoSuchFieldException {
		// TODO Auto-generated method stub
		Random rand = new Random();
		ArrayList<Integer> obj = new ArrayList<Integer>();
		obj.add(rand.nextInt(7));
		obj.add(rand.nextInt(7));
		obj.add(rand.nextInt(7));
		obj.add(rand.nextInt(7));
		obj.add(rand.nextInt(7));
		obj.add(rand.nextInt(7));
		obj.add(rand.nextInt(7));
		obj.add(rand.nextInt(7));
		obj.add(rand.nextInt(7));
		obj.add(rand.nextInt(7));
		// for (int i = 0; i < obj.size(); i++) {
		// System.out.println(obj.get(i));
		// }

		System.out.println();
		System.out.println();

		RadixSort rs = new RadixSort();

	//	rs.createFiles();

		// rs.createFiles();

		RandomAccessFile F = new RandomAccessFile("main.dat", "rw");

		System.out.println();
		System.out.println();
		try {
			F.seek(0L);
			System.out.println("From file....");
			while (true) {

				System.out.println(F.readInt() + " ");

			}

		} catch (EOFException eof) {
			// TODO: handle exception
		}

		System.out.println("..........");
		System.out.println();

		long startTime = System.nanoTime();
		
		rs.sortFile(F);
		
		long endTime = System.nanoTime();

		
		long totalNanos = endTime-startTime;
		long minutes = totalNanos/1000000000/60;
		totalNanos -= minutes*60000000000L;
		long seconds = (int) (totalNanos/1000000000.0);
		totalNanos -= seconds*1000000000L;
		long milliSeconds = (int) (totalNanos/1000000.0);
		
		System.out.println();
		System.out.println("Sorted file.....");
		
		try {
			F.seek(0L);
			while (true) {

				System.out.println(F.readInt() + " ");

			}

		} catch (EOFException eof) {
			System.out.println("End of file reached!");
			// TODO: handle exception
		}
		System.out.printf("%nTime: %02d:%02d:%03d %n%n", minutes,seconds,milliSeconds);
	}

}

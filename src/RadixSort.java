import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RadixSort {

	public RadixSort() throws IOException {
		 createFiles();
	}

	// private RandomAccessFile F;

	private static RandomAccessFile tmp;

	private RandomAccessFile B;

	private int size = 10;
	private int maxPass;
	long j;

	List<Integer> un = new ArrayList<Integer>();
	List<Integer> ce = new ArrayList<Integer>();

	// //////////////////////////////////
	// //////////////////////////////////

	public ArrayList<Integer> sort(ArrayList<Integer> old) {
		int num;

		for (int shift = 1; shift < 5; shift++) {
			List<Integer> unos = new ArrayList<Integer>();
			List<Integer> ceros = new ArrayList<Integer>();

			for (int i = 0; i < old.size(); i++) {
				num = old.get(i) & shift;
				if (num == shift)
					unos.add(old.get(i));
				else
					ceros.add(old.get(i));
			}

			int counter = 0;
			// Put zeros on old
			for (int i = 0; i < ceros.size(); i++) {
				// System.out.println(ceros.get(i));
				// System.out.println(i);
				old.set(i, ceros.get(i));
				// counter++;
			}
			// put ones on old
			for (int i = ceros.size(); i < old.size(); i++) {

				old.set(i, unos.get(counter));
				// System.out.print(unos.get(counter));
				counter++;
			}
			unos.clear();
			ceros.clear();

		}
		return old;
	}

	// /////////////////////////////////////////////
	// ////////////////////////////////////////////

	public void sortFile(RandomAccessFile F) throws IOException,
			NoSuchFieldException {
		int num;
		int numFromFile = 0;
		RandomAccessFile unos = new RandomAccessFile("unos.bin", "rw");
		RandomAccessFile ceros = new RandomAccessFile("ceros.bin", "rw");

		for (int shift = 0; shift < 7; shift++) {

			// RandomAccessFile unos = new RandomAccessFile("unos.bin","rw");
			// RandomAccessFile ceros = new RandomAccessFile("ceros.bin","rw");

			// int i = 0;

			long mask = 1L << shift;
			int v = -1;
			int a=0, b =0;
			try {
				// System.out.println("we enter");
				F.seek(0L);
				while (true) {
					numFromFile = F.readInt();
					// i++;

					num = numFromFile & shift;

					if ((numFromFile & mask) != 0) {
						// System.out.println("Mask:" + num + " Number: "+
						// numFromFile);
						// if (num == shift) {
						unos.writeInt(numFromFile);
						a++;
						// un.add(numFromFile);
						// System.out.println("uno");
					} else {
						// System.out.println("cero");
						ceros.writeInt(numFromFile);
						b++;
						// ce.add(numFromFile);
					}

				}
				
			} catch (EOFException eof) {
				ceros.writeInt(-1);
				unos.writeInt(-1);
				System.out.println("Round: " + shift + " unos: " + a +" ceros: " + b);

			}
			// put zeros onto ceros files

			// File fi = new File("pancho1.bin");
			// if (fi.delete())
			// System.out.println("File  F deleted");

			// F = new RandomAccessFile("pancho1.bin", "rw");

			F.seek(0L);
			ceros.seek(0L);
			try {

				// while(true) {
				int cero;
				cero = ceros.readInt();
				while (cero != -1) {
					F.writeInt(cero);
					cero = ceros.readInt();
					// System.out.println("Writing cero");
				}
			} catch (EOFException eof) {
				// TODO: handle exception
				System.out.println("Paso por aqui");

			}

			// put ones onto unos file
			unos.seek(0L);
			try {

				int uno;
				uno = unos.readInt();
				// while(true) {
				while (uno != -1) {
					F.writeInt(uno);
					uno = unos.readInt();
					// System.out.println("Writing unos");
				}
			} catch (EOFException eof) {
				System.out.println("tambien por aqui");

			}

			// printList(ce);
			// printList(un);

			// ce.clear();
			// un.clear();

			// File f = new File("unos.bin");
			// f.delete();
			//
			// File f2 = new File("ceros.bin");
			// f2.delete();

		}

		unos.close();
		ceros.close();
		
		
		//
	}

	public void createFiles() throws IOException {

		Random rand = new Random();
		RandomAccessFile F = new RandomAccessFile("main.dat", "rw");
		// tmp = new RandomAccessFile("unos.bin", "rw");
		// B = new RandomAccessFile("ceros.bin", "rw");
		System.out.println(size);
		for (int i = 0; i < 10; i++)
			F.writeInt(rand.nextInt(8));
		
		F.close();

	}

	private void printList(List<Integer> l) {
		for (int i = 0; i < l.size(); i++) {
			System.out.println("From memory: " + l.get(i));
		}
	}

}

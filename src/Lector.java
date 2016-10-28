import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class Lector {

	
	public static void main(String[] args) throws IOException, NoSuchFieldException {
	
		RandomAccessFile F = new RandomAccessFile("main.dat", "r");
		F.seek(0L);
		try {
			
			System.out.println("From file....");
			while (true) {
			
				
				System.out.println(F.readInt());

				 
			}
			
	} catch (EOFException eof) {
		System.out.println("Neber");
	}
	
	
}
}
  
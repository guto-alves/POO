package memoryerror;
import java.util.ArrayList;
import java.util.List;

public class MemoryError {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		
		while(true) {
			list.add("hello");
		}
	}

}

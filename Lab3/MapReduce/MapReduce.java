import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class MapReduce {
static Map<String,Integer> myMap = new HashMap<String, Integer>();
static ValueComparator bvc ;
static TreeMap<String,Integer> sorted_map ;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		bvc  =  new ValueComparator(myMap);
		sorted_map = new TreeMap<String,Integer>(bvc);
		ReadFile();
		sorted_map.putAll(myMap);
		System.out.println(sorted_map);
	}
	private static void MapFile(String[] words) {
		// TODO Auto-generated method stub
		Integer count =0;
		for(String word:words){
			if(!myMap.containsKey(word)){
				myMap.put(word, 1);
			}else{
				count = myMap.get(word);;
				count++;
				myMap.put(word, count);
			}
		}
	}
	private static void ReadFile() {
		// TODO Auto-generated method stub
		try {
			BufferedReader reader = new BufferedReader(new FileReader("words.txt"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] words = line.split(" ");
				MapFile(words);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
   
class ValueComparator implements Comparator<String> {

    Map<String, Integer> base;
    public ValueComparator(Map<String, Integer> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.    
    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}

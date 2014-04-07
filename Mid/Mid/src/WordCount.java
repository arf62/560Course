import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.json.JSONException;
import org.json.JSONObject;

public class WordCount {


	

  public static class Map 
            extends Mapper<LongWritable, Text, Text, IntWritable>{

    private final static IntWritable one = new IntWritable(1); // type of output value
    private Text word = new Text();   // type of output key
      
    ArrayList<String> goodList = new ArrayList<String>();
    ArrayList<String> badList = new ArrayList<String>();
    public void map(LongWritable key, Text value, Context context
                    ) throws IOException, InterruptedException {
     
    	    goodList.add("win");
    	    goodList.add("won");
    	    goodList.add("good");
    	    goodList.add("happy");
    	    goodList.add("spirit");
    	    goodList.add("victory");
    	    goodList.add("victorious");
    	    goodList.add("unbeaten");
    	    goodList.add("    acceptable   ");  
    	    goodList.add("    bad   ");  
    	    goodList.add("    excellent   ");  
    	    goodList.add("    exceptional   ");  
    	    goodList.add("    favorable   ");  
    	    goodList.add("    great   ");  
    	    goodList.add("    marvelous   ");  
    	    goodList.add("    positive   ");  
    	    goodList.add("    satisfactory   ");  
    	    goodList.add("    satisfying   ");  
    	    goodList.add("    superb    ");  
    	    goodList.add("    valuable   ");  
    	    goodList.add("    wonderful   ");  
    	    goodList.add("    ace   ");  
    	    goodList.add("    boss   ");  
    	    goodList.add("    bully   ");  
    	    goodList.add("    capital   ");  
    	    goodList.add("    choice   ");  
    	    goodList.add("    crack   ");  
    	    goodList.add("    nice   ");  
    	    goodList.add("    pleasing   ");  
    	    goodList.add("    prime    ");  
    	    goodList.add("    rad   ");  
    	    goodList.add("    sound   ");  
    	    goodList.add("    spanking   ");  
    	    goodList.add("    sterling   ");  
    	    goodList.add("    super   ");  
    	    goodList.add("    superior   ");  
    	    goodList.add("    welcome   ");  
    	    goodList.add("    worthy   ");  
    	    goodList.add("    admirable   ");  
    	    goodList.add("    agreeable   ");  
    	    goodList.add("    commendable    ");  
    	    goodList.add("    congenial   ");  
    	    goodList.add("    deluxe   ");  
    	    goodList.add("    first-class   ");  
    	    goodList.add("    first-rate   ");  
    	    goodList.add("    gnarly   ");  
    	    goodList.add("    gratifying   ");  
    	    goodList.add("    honorable   ");  
    	    goodList.add("    neat   ");  
    	    goodList.add("    precious   ");  
    	    goodList.add("    recherché   ");  
    	    goodList.add("    reputable    ");  
    	    goodList.add("    select   ");  
    	    goodList.add("    shipshape   ");  
    	    goodList.add("    splendid   ");  
    	    goodList.add("    stupendous   ");  
    	    goodList.add("    super-eminent   ");  
    	    goodList.add("    super-excellent   ");  
    	    goodList.add("    tip-top   ");  
    	    goodList.add("up to snuf"); 
    	    goodList.add("open");
    	    goodList.add("lol");
    	    goodList.add("rofl");
    	    goodList.add(":)");
    	    goodList.add(":-)");
    	    goodList.add(":=)");
    	    goodList.add(":D");
    	    
    	    badList.add("bad");
    	    badList.add("     disagreeable");  
    	    badList.add("    expected");  
    	    badList.add("    inferior");  
    	    badList.add("    insignificant");  
    	    badList.add("    OK");  
    	    badList.add("    ordinary");  
    	    badList.add("    poor");  
    	    badList.add("    second-rate ");  
    	    badList.add("    unacceptable");  
    	    badList.add("    unhelpful");  
    	    badList.add("    unimportant");  
    	    badList.add("    unnoteworthy");  
    	    badList.add("    unsatisfactory");  
    	    badList.add("    worthless");  
    	    badList.add("    minor");  
    	    badList.add("    detestable ");  
    	    badList.add("    evil");  
    	    badList.add("    fake");  
    	    badList.add("    forged");  
    	    badList.add("    immoral");  
    	    badList.add("    inadequate");  
    	    badList.add("    incompetent");  
    	    badList.add("    inconsequential");  
    	    badList.add("    inconsiderable ");  
    	    badList.add("    mean");  
    	    badList.add("    misbehaving");  
    	    badList.add("    noxious");  
    	    badList.add("    rotten");  
    	    badList.add("    sinful");  
    	    badList.add("    tainted");  
    	    badList.add("    unpleasant");  
    	    badList.add("    unreal ");  
    	    badList.add("    unreliable");  
    	    badList.add("    unskilled");  
    	    badList.add("    unsuitable");  
    	    badList.add("    unvirtuous");  
    	    badList.add("    vicious");  
    	    badList.add("    vile");  
    	    badList.add("    wicked ");  

    	    
    	    
    
      
    	try {
     	 JSONObject obj = new JSONObject(value.toString());
  	     String tweet = (String) obj.get("text");
  	     JSONObject user = obj.getJSONObject("user");
  	     if(obj.get("id")!=null && user.get("time_zone")!=null){
  	     Long id = (Long)obj.get("id");
  	     String userID = id.toString();
  	     String timeZone = (String)user.get("time_zone");
  	     StringTokenizer itr = new StringTokenizer(tweet);
	  	   Boolean good = false;
	       Boolean cricket = false;
	       Boolean bad = false;
	       
	       while (itr.hasMoreTokens()) {
	          	    	   
	       String wordString = itr.nextToken().toString();
	       if(goodList.contains(wordString))
	    	 {
	    		 good=true;
	    	 }else if (badList.contains(wordString)) {
				bad =true;
			}
	       
	    	
	       }
	      String temp;
	       if (good) {
			temp="good";
	       }
	       else if(bad){
	    	   temp="bad";
	       }
	       else{
	    	   temp="neutral";
	       }
	        ;
			Text tweetData = new Text();
			tweetData.set(timeZone.replaceAll("\\s+", "")+"_"+temp);
			System.out.println("tweet"+"\t" +tweetData.toString());
	    	context.write(tweetData, one);
  	     }
     	} catch (Exception e) {
    			e.printStackTrace();
   		}
    	
    	
    	//String out = this.executeCommand("curl http://134.193.136.127:8983/solr/collection1_shard1_replica1/select?q=*%3A*&wt=json&indent=true");
    	//System.out.println(out);
      }
      
      
      
   	 
   	 
    	
    
    
  }
  
  
  public static class Reduce
       extends Reducer<Text,IntWritable,Text,IntWritable> {

    private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values, 
                       Context context
                       ) throws IOException, InterruptedException {
      int sum = 0; // initialize the sum for each keyword
      for (IntWritable val : values) {
        sum += val.get();  
      }
      result.set(sum);

      String s = "curl http://localhost:8983/solr/collection1_shard1_replica1/update/json?overwrite=true -H Content-type:application/json --data [";
      
      JSONObject data = new JSONObject();
      try {
		data.put("id",key.toString());
		data.put("numberOfTweet_s", result.toString());
	} catch (Exception e) {
		e.printStackTrace();
	}
     
      s+=data.toString()+"]";
      System.out.println(s);
		String out = this.executeCommand(s);
		System.out.println(out);
      context.write(key, result); // create a pair <keyword, number of occurences>
      s="";
    }
    private String executeCommand(String command) {
   	 
		StringBuffer output = new StringBuffer();
 
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));
 
                        String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		return output.toString();
 
	}
  }

  // Driver program
  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration(); 
    String[] otherArgs = args;//new GenericOptionsParser(conf, args).getRemainingArgs(); // get all args
    if (otherArgs.length != 2) {
      System.err.println("Usage: WordCount <in> <out>");
      System.exit(2);
    }

    // create a job with name "wordcount"
    Job job = new Job(conf, "wordcount");
    job.setJarByClass(WordCount.class);
    job.setMapperClass(Map.class);
    job.setReducerClass(Reduce.class);
   
    // uncomment the following line to add the Combiner
    job.setCombinerClass(Reduce.class);
     

    // set output key type   
    job.setOutputKeyClass(Text.class);
    // set output value type
    job.setOutputValueClass(IntWritable.class);
    
    Path outputPath = new Path(otherArgs[1]);
    //set the HDFS path of the input data
    FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
    // set the HDFS path for the output
    FileOutputFormat.setOutputPath(job, outputPath);

    outputPath.getFileSystem(conf).delete(outputPath, true);
    
    //Wait till job completion
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}

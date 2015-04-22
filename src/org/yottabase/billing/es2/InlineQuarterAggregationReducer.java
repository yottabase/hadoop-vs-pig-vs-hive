package org.yottabase.billing.es2;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class InlineQuarterAggregationReducer extends
		Reducer<Text, CountByMounth, Text, Text> {
	
	
	public void reduce(Text key, Iterable<CountByMounth> values, Context context) 
			throws IOException, InterruptedException {
		
		Map<Integer, Integer> groupByMounth = new TreeMap<Integer, Integer>();
		
		for(CountByMounth cm : values){
			Integer count = groupByMounth.remove(cm.getMounth());
			
			if(count == null){
				count = cm.getCount();
			}else{
				count = cm.getCount() + count;
			}
			
			groupByMounth.put(cm.getMounth(), count);
		}
		
		String out = "";
		for(Entry<Integer, Integer> entry: groupByMounth.entrySet()){
			
			out += (entry.getKey() + 1) + "/2015:" + entry.getValue() + " ";
			
		}	
		
		out = out.trim();
		
		context.write(key, new Text(out));
	}
			
}

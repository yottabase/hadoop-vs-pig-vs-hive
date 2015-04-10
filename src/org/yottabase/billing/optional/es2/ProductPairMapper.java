package org.yottabase.billing.optional.es2;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ProductPairMapper extends
		Mapper<LongWritable, Text, Text, IntWritable> {

	private static final IntWritable ONE = new IntWritable(1);

	private static final int MAX_SUBSET_SIZE = 5;

	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		String line = value.toString();

		StringTokenizer tokenizer = new StringTokenizer(line, ",");
		tokenizer.nextToken();

		List<Text> products = new LinkedList<Text>();
		while (tokenizer.hasMoreTokens())
			products.add(new Text(tokenizer.nextToken()));

		for (int i = 0; i < products.size(); i++) {
			int limit = Math.min(products.size() - i, MAX_SUBSET_SIZE);

			for (int j = 1; j <= limit; j++) {
				String subseq = "";
				List<Text> subset = products.subList(i, i + j);

				for (Text t : subset)
					subseq += t.toString() + " ";

				// TODO: La chiave dovrebbe essere un set per evitare permutazioni
				// TODO: Sarebbe meglio riuscire ad ordinare decrescentemente per valore (considerare)
				context.write(new Text(subseq), ONE);
			}
		}

	}

}
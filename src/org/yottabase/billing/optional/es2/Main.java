package org.yottabase.billing.optional.es2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Main {

	public static final String JOB_NAME = "Optional exercise 2: Subsets Frequencies";

	public static void main(String[] args) throws Exception {

		String inputJob = args[0];
		String outputJob = args[1];

		runJob(inputJob, outputJob);
	}

	public static void runJob(String inputPath, String outputPath)
			throws Exception {
		
		outputPath += "/" + JOB_NAME;
		
		String inputJob1 = inputPath;
		String outputJob1 = outputPath + "/job1";
		
		runJob1(inputJob1, outputJob1);
	}
	
	public static void runJob1(String inputPath, String outputPath)
			throws Exception {
		Job job = new Job(new Configuration(), JOB_NAME + "job1");

		FileInputFormat.addInputPath(job, new Path(inputPath));
		FileOutputFormat.setOutputPath(job, new Path(outputPath));

		job.setJarByClass(Main.class);
		job.setMapperClass(ProductPairMapper.class);
//		job.setCombinerClass(ProductPairReducer.class);
		job.setReducerClass(ProductPairReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.waitForCompletion(true);
	}
	
}
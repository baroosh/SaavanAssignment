package com.bits.sparkassignment1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class SparkSingleRecordLookup {

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setAppName("SparkSingleRecordLookup").setMaster("local[*]");
		JavaSparkContext sparkContext = new JavaSparkContext(conf);
		String inputFile = args[0];
		String outputFile = args[1];
		
		JavaRDD<String> userData = sparkContext.textFile(inputFile);
		System.out.println(userData.top(5));
		int i = 0;
		
		JavaRDD<String> filterRDDData = (JavaRDD) userData.filter(
		x -> {

			List<String> dataList = new ArrayList<String>();
			dataList = Arrays.asList(x.split(","));
	
			boolean isMatch = false;
			if (dataList.get(0).equals("2") && dataList.get(1).equals("2017-10-01 00:15:30")
					&& dataList.get(2).equals("2017-10-01 00:25:11")) {
				
				isMatch = true;
			}
			
			return isMatch;
		});
		
		filterRDDData.saveAsTextFile(outputFile);
		//
//		List<String> top5User = filterRDDData.top(10);
//		for(String t : top5User) {
//			System.out.println(t);
//		}

	}
}

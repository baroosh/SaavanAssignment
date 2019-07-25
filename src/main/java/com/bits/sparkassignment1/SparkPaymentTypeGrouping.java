package com.bits.sparkassignment1;

import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;


public class SparkPaymentTypeGrouping {
	
	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setAppName("SparkSingleRecordLookup").setMaster("local[*]");
		JavaSparkContext context = new JavaSparkContext(conf);
		String inputFile = args[0];
		String outputFile = args[1];
		
		JavaRDD<String> taxiFile = context.textFile(inputFile);
		
		JavaPairRDD<String, Integer> paymentType = (JavaPairRDD) taxiFile.mapToPair(
			x -> {
				String[] vals = x.split(",");
				String paymentGroup = "";
				
				if (vals != null && vals.length > 10) {
					
					paymentGroup = vals[9];
				}
				return new Tuple2<String, Integer>(paymentGroup, 1);
			}
		);
	
		JavaPairRDD<Integer, Integer> movieRatingsCount = (JavaPairRDD) paymentType.reduceByKey(
				
			(tuple1, tuple2) -> {
				return tuple1 + tuple2;
			}
		);

		movieRatingsCount.saveAsTextFile(outputFile);
		
//		List<Tuple2<Integer, Integer>> sampleRatings = movieRatingsCount.top(10, new TupleIntSorter());
//		for(Tuple2<Integer, Integer> t : sampleRatings) {
//			System.out.println("Payment Type " + t._1 + "  has count Of :- " + t._2);
//		}
	}
}


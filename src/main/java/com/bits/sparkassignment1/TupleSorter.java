package com.bits.sparkassignment1;

import java.io.Serializable;
import java.util.Comparator;

import scala.Tuple2;

public class TupleSorter 
	implements Comparator<Tuple2<String,Double>>, Serializable {

	/**@Override
	public int compare(Tuple2<String, Double> o1, Tuple2<String, Double> o2) {
		
		if (o1._2 > o2._2) {
			return 1;
		} else if (o1._2 < o2._2) {
			return -1;
		} else {
			return 0;
		}
		
	}*/

	/**@Override
	public int compare(Tuple2<Integer, Integer> o1, Tuple2<Integer, Integer> o2) {
		
		if (o1._2 > o2._2) {
			return 1;
		} else if (o1._2 < o2._2) {
			return -1;
		} else {
			return 0;
		}
		
	}*/

	public int compare(Tuple2<String, Double> o1, Tuple2<String, Double> o2) {
		if (o1._2 > o2._2) {
			return 1;
		} else if (o1._2 < o2._2) {
			return -1;
		} else {
			return 0;
		}
	}
	
}

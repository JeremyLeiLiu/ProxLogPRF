package common;

import java.util.*;
/**
 * This class implements a series of basic statistical functions.
 */
public class StaTools {
	public static int sum(int[] data){
		int sum = 0;
		for (int i = 0; i < data.length; i++)
			sum+=data[i];
		return sum;
	}

	public static double sum(double[] data){
		int sum = 0;
		for (int i = 0; i < data.length; i++)
			sum+=data[i];
		return sum;
	}   

	public static HashMap<String, Double> uniformarray(HashMap<String, Double> topTermWeightVector) {
		double temp = 0;
		HashMap<String, Double> newVector = new HashMap<String, Double>();
		for (String term : topTermWeightVector.keySet()) {
			temp += topTermWeightVector.get(term);
		}

		for (String term : topTermWeightVector.keySet()) {
			if(temp!=0){
				newVector.put(term, topTermWeightVector.get(term)/temp);
			}else{
				newVector.put(term, 0d);
			}
		}
		return newVector;
	}    

	public static HashMap<String, Double> softmax(HashMap<String, Double> topTermWeightVector) {
		double temp = 0;
		HashMap<String, Double> newVector = new HashMap<String, Double>();
		for (String term : topTermWeightVector.keySet()) {
			temp += Math.exp(topTermWeightVector.get(term));
		}

		for (String term : topTermWeightVector.keySet()) {
			if(temp!=0){
				newVector.put(term, Math.exp(topTermWeightVector.get(term))/temp);
			}else{
				newVector.put(term, 0d);
			}
		}
		return newVector;
	}  

	public static HashMap<String, Double> Divide(HashMap<String, Double> topTermWeightVector, double temp) {
		HashMap<String, Double> newVector = new HashMap<String, Double>();

		for (String term : topTermWeightVector.keySet()) {
			newVector.put(term, topTermWeightVector.get(term)/temp);
		}
		return newVector;
	} 

	public static HashMap<String, Double> MAXNormalizedarray(HashMap<String, Double> topTermWeightVector) {
		double maxV = Double.MIN_VALUE;
		HashMap<String, Double> newVector = new HashMap<String, Double>();
		for (String term : topTermWeightVector.keySet()) {
			if (maxV<topTermWeightVector.get(term)){
				maxV = topTermWeightVector.get(term);
			}
		}
		for (String term : topTermWeightVector.keySet()) {
			if(maxV!=0){
				newVector.put(term, topTermWeightVector.get(term)/maxV);
			}else{
				newVector.put(term, 0d);
			}
		}
		return newVector;
	} 

	public static HashMap<String, Double> Normalizedarray(HashMap<String, Double> topTermWeightVector) {
		double maxV = Double.MIN_VALUE;
		double minV = Double.MAX_VALUE;
		HashMap<String, Double> newVector = new HashMap<String, Double>();
		for (String term : topTermWeightVector.keySet()) {
			if (maxV<topTermWeightVector.get(term)){
				maxV = topTermWeightVector.get(term);
			}
			if (minV>topTermWeightVector.get(term)){
				minV = topTermWeightVector.get(term);
			}
		}
		for (String term : topTermWeightVector.keySet()) {
			if(maxV!=minV){
				newVector.put(term, (topTermWeightVector.get(term)-minV)/(maxV-minV));
			}else{
				newVector.put(term, 0d);
			}
		}
		return newVector;
	} 

	public static double[] uniformarray(double[] data) {
		double temp = sum(data);
		if(temp!=0){
			for (int i=0; i<data.length; i++)
				data[i] = data[i]/temp;
		}
		return data;
	}    

	public static double[] softmax(double[] data) {
		double temp = 0d;
		for (int i=0; i<data.length; i++){
			temp += Math.exp(data[i]);
		}
		if(temp!=0){
			for (int i=0; i<data.length; i++)
				data[i] = Math.exp(data[i])/temp;
		}
		return data;
	}  

	/**
	 * The mean of an array of double values.
	 * @param data The double values.
	 * @return The mean.
	 */
	public static double mean(double[] data) {
		double mean = 0d;
		for (int i=0; i<data.length; i++)
			mean+=data[i];
		mean/=data.length;
		return mean;
	}

	/**
	 * The mean of a sub-array of an array of double values.
	 * @param data The array of double values.
	 * @param start The starting index of the sub-array.
	 * @param length The length of the sub-array.
	 * @param ascending Is the starting index the left (true) or 
	 * right (false) end of the sub-array?
	 * @return The mean of the sub-array.
	 */
	public static double mean(double[] data, int start, int length, boolean ascending) {
		double mean = 0d;
		if (ascending)
			for (int i = start; i < length; i++)
				mean += data[i];
		else
			for (int i = 0; i < length; i++)
				mean += data[start - i];
		mean /= length;
		return mean;
	}

	/**
	 * The mean of an array of integers.
	 * @param data The array of integers.
	 * @return The mean.
	 */
	public static double mean(int[] data) {
		double mean = 0d;
		for (int i=0; i<data.length; i++)
			mean+=data[i];
		mean/=data.length;
		return mean;
	}

	/**
	 * The median of an array of double values.
	 * @param data The array of double values.
	 * @return The median.
	 */
	public static double median(double[] data) {
		double[] copy = (double[])data.clone();
		Arrays.sort(copy);
		return data[(copy.length-1)/2];
	}

	/**
	 * The standard deviation of an array of double values.
	 * @param data The array of double values.
	 * @return The standrad deviation.
	 */
	public static double standardDeviation(double[] data) {	
		return Math.sqrt(variance(data));
	}

	/**
	 * The variance of an array of double values. 
	 * @param data The array of double values.
	 * @return The variance.
	 */
	public static double variance(double[] data) {
		double var = 0d;
		int n = data.length;
		double mean =mean(data);
		for (int i=0; i<n; i++)
			var+=(data[i]-mean)*(data[i]-mean);
		var /= n;

		return var;
	}

	public static double max(double[] data) {
		double amax=0;
		if (data.length>0){
			amax = data[0];
			int ldata = data.length;
			for(int i = 0; i < ldata; i++){
				amax = Math.max(amax,data[i]);
			}
		}
		return amax;
	}

	public static double min(double[] data) {
		double amin;
		amin=10000;
		if (data.length>0){
			amin = data[0];
			int ldata = data.length;
			for(int i = 0; i < ldata; i++){
				amin = Math.min(amin,data[i]);
			}
		}
		return amin;
	}

	public static double[] normalizedarray(double[] data){
		int data_length = data.length;
		double[] normalizedarray = new double[data_length];
		double dmin = min(data);
		double dmax = max(data);

		for (int i=0; i<data_length; i++){
			if (dmin == dmax || Double.isNaN(dmin) || Double.isNaN(dmax)){
				normalizedarray[i]=0;
			}else{
				normalizedarray[i] = (data[i]-dmin)/(dmax-dmin); 
			}
		}

		return normalizedarray;
	}

	/**
	 * The add of two arrays of double values with same length. 
	 * @param data1,data2 The two arrays of double values
	 * @return The mean.
	 */
	public static double[] add(double[] data1, double[] data2 ) {
		double []add=new double[data1.length];
		for (int i=0; i<data1.length; i++)
			add[i]=data1[i]+data2[i];
		return add;
	}
	public static double[] add(double[] data1, double[] data2, double alpha, double beta) {
		double []add=new double[data1.length];
		for (int i=0; i<data1.length; i++)
			add[i]=alpha*data1[i]+beta*data2[i];
		return add;
	}
}

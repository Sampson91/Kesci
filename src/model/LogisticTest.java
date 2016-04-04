package model;

import java.util.Random;

import util.Util;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LibLINEAR;
import weka.classifiers.functions.LibSVM;
import weka.core.Instances;

public class LogisticTest {
	public static void main(String[] args) throws Exception {
		String in = "G:\\比赛\\魔镜杯风控算法大赛\\data\\train\\all_feature.csv";
		Instances train = Util.getInstances(in);
		train=Util.discLabel(train);
//		Logistic logistic = new Logistic();
		LibSVM linear=new LibSVM();
//		String[] options=weka.core.Utils.splitOptions("-S 0 -K 2 -D 3 -G 0.0 -R 0.0 -N 0.5 -M 800.0 -C 0.2 -E 0.0010 -P 0.7 -B 0");
//		linear.setOptions(options);
		Evaluation eval = new Evaluation(train);
		eval.crossValidateModel(linear, train, 5, new Random(1234));
		System.out.println(eval.areaUnderROC(0));
	}

}

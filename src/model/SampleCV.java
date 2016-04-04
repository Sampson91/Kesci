package model;

import java.io.PrintWriter;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dmlc.xgboost4j.Booster;
import org.dmlc.xgboost4j.DMatrix;
import org.dmlc.xgboost4j.demo.util.Params;
import org.dmlc.xgboost4j.util.Trainer;

import sample.MY0;
import util.Csv2LibsvmTool;
import util.Evaluation;
import util.Util;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSink;

public class SampleCV {
	public Instances data;
	public ArrayList<Instances> cvdata = new ArrayList<>();

	public SampleCV(Instances data, int n) {
		this.data = data;
		Instances ins = new Instances(data);
		ins.delete();
		for (int i = 0; i < n; i++) {
			Instances t = new Instances(ins);
			cvdata.add(t);
		}
		for (int i = 0; i < data.numInstances(); i++) {
			int index = i % n;
			cvdata.get(index).add(data.instance(i));
		}
	}

	public void sample(int nthread) throws Exception {
		PrintWriter pt=Util.writeFile("data/result.txt");
		for(int k=50;k<=1000;k+=50){
			double w=0.01;
			while(w<=0.05)
			{
				double auc = 0;
				for (int i = 0; i < cvdata.size(); i++) {
					Instances train = new Instances(data);
					train.delete();
					Instances test = new Instances(data);
					test.delete();
					test = cvdata.get(i);
					for (int j = 0; j < cvdata.size(); j++) {
						if (i != j) {
							train = addAll(train, cvdata.get(j));
						}
					}
					// 采样
					System.err.println("start sample " + i);
					MY0 my0 = new MY0(train, k, w,nthread);
					train.setClassIndex(train.numAttributes() - 1);
					System.out.println(train.numInstances());
					train = my0.getSampleData();
					System.out.println(train.numInstances());
					my0 = null;
					// 采样 
					Util.deleteFile("data\\sample_train.csv");
					Util.deleteFile("data\\sample_test.csv");
					DataSink.write("data\\sample_train.csv", train);
					DataSink.write("data\\sample_test.csv", test);
					Csv2LibsvmTool.transform("data\\sample_train.csv", "data\\sample_train.libsvm", "last");
					Csv2LibsvmTool.transform("data\\sample_test.csv", "data\\sample_test.libsvm", "last");
					auc += (test("data\\sample_train.libsvm", "data\\sample_test.libsvm"));
				}
				pt.println(k+","+w+","+auc / cvdata.size());
				System.out.println(k+","+w+","+auc / cvdata.size());
				w+=0.01;
			}
		}
		pt.close();
	}

	public static double test(String train, String test) throws Exception {
		DMatrix trainMat = new DMatrix(train);
		DMatrix testMat = new DMatrix(test);
		Params param = new Params() {
			{
				put("eta", 0.02);
				put("booster", "gbtree");
				put("lambda", 700);
				put("subsample", 0.7);
				put("colsample_bytree", 0.30);
				put("max_depth", 8);
				put("scale_pos_weight", 10);
				put("silent", 1);
				put("nthread", 8);
				put("objective", "binary:logistic");
				put("gamma", 1.0);
				put("eval_metric", "auc");
			}
		};
		List<Map.Entry<String, DMatrix>> watchs = new ArrayList<>();
		// watchs.add(new AbstractMap.SimpleEntry<>("train", trainMat));
		watchs.add(new AbstractMap.SimpleEntry<>("test", testMat));

		// train a booster
		int round = 1060;
		Booster booster = Trainer.train(param, trainMat, round, watchs, null, null);
		float[][] predicts = booster.predict(testMat);

		return Evaluation.auc(predicts, testMat);
	}

	public Instances addAll(Instances ins1, Instances ins2) {
		for (int i = 0; i < ins2.numInstances(); i++) {
			ins1.add(ins2.instance(i));
		}
		return ins1;
	}

	/*
	 * 1 把数据分成5份 2合并其中4份并作过采样 3交叉验证
	 */
	public static void main(String[] args) throws Exception {
		// String in="G:\\比赛\\魔镜杯风控算法大赛\\data\\train\\all_feature.csv";
		String in = "G:\\比赛\\魔镜杯风控算法大赛\\data\\train\\test.csv";
		Instances data = Util.getInstances(in);
		SampleCV sampleCV = new SampleCV(data, 5);
		sampleCV.sample(8);
	}
}

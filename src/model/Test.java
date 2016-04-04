package model;

import java.util.HashMap;

import org.dmlc.xgboost4j.DMatrix;
import org.dmlc.xgboost4j.demo.util.Params;
import org.dmlc.xgboost4j.util.Trainer;

public class Test {
	public static void main(String[] args) throws Exception {
//		DMatrix trainMat = new DMatrix("./data/agaricus.txt.train");
		DMatrix trainMat = new DMatrix("D:\\temp\\train_tfidf.libsvm");
		// set params
		Params param = new Params() {
			{
				put("eta", 0.1);
                put("max_depth", 10);
                put("silent", 1);
                put("nthread", 8);
                put("objective", "multi:softmax");
                put("gamma", 1.0);
                put("eval_metric", "merror");
                put("num_class", 51);
			}
		};

		// do 5-fold cross validation
		int round = 200;
		int nfold = 5;
		// set additional eval_metrics
		String[] metrics = null;

		String[] evalHist = Trainer.crossValiation(param, trainMat, round, nfold, metrics, null, null);
	}
}

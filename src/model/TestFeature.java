package model;

import org.dmlc.xgboost4j.DMatrix;
import org.dmlc.xgboost4j.demo.util.Params;
import org.dmlc.xgboost4j.util.Trainer;

import util.Csv2LibsvmTool;
//log 0.739
//org 0.739762
//log_nor 0.741233 
//org_nor 0.741151
public class TestFeature {
	public static void main(String[] args)throws Exception{
		String in="G:\\比赛\\魔镜杯风控算法大赛\\data\\train\\nor_all_feature.csv";
		String out="data\\nor_all_feature.libsvm";
		Csv2LibsvmTool.transform(in, out, "last");
		DMatrix trainMat = new DMatrix(out);
		// set params
		Params param = new Params() {
			{
				put("eta", 0.02);
				put("booster", "gbtree");
				put("lambda", 700);
				put("subsample", 0.7);
				put("colsample_bytree", 0.30);
                put("max_depth", 8);
                put("scale_pos_weight", 15);
                put("silent", 1);
                put("nthread", 8);
                put("objective", "binary:logistic");
                put("gamma", 1.0);
                put("eval_metric", "auc");
			}
		};

		// do 5-fold cross validation
		int round = 2000;
		int nfold = 5;
		// set additional eval_metrics
		String[] metrics = null;

		String[] evalHist = Trainer.crossValiation(param, trainMat, round, nfold, metrics, null, null);
	}
}

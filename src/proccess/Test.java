package proccess;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import util.Util;

public class Test {
	public static void main(String[] args) throws Exception{
		fun1();
	}
	
	//SQL创建训练集表
	public static void fun1()
	{
		String head="Idx,UserInfo_1_1,UserInfo_1_2,UserInfo_1_3,UserInfo_1_4,UserInfo_1_5,UserInfo_1_6,UserInfo_1_7,UserInfo_1_8,UserInfo_1_9,UserInfo_3_1,UserInfo_3_2,UserInfo_3_3,UserInfo_3_4,UserInfo_3_5,UserInfo_3_6,UserInfo_3_7,UserInfo_3_8,UserInfo_3_9,UserInfo_5_1,UserInfo_5_2,UserInfo_5_3,UserInfo_6_1,UserInfo_6_2,UserInfo_6_3,UserInfo_7_1,UserInfo_7_2,UserInfo_7_3,UserInfo_7_4,UserInfo_7_5,UserInfo_7_6,UserInfo_7_7,UserInfo_7_8,UserInfo_7_9,UserInfo_7_10,UserInfo_7_11,UserInfo_7_12,UserInfo_7_13,UserInfo_7_14,UserInfo_7_15,UserInfo_7_16,UserInfo_7_17,UserInfo_7_18,UserInfo_7_19,UserInfo_7_20,UserInfo_7_21,UserInfo_7_22,UserInfo_7_23,UserInfo_7_24,UserInfo_7_25,UserInfo_7_26,UserInfo_7_27,UserInfo_7_28,UserInfo_7_29,UserInfo_7_30,UserInfo_7_31,UserInfo_7_32,UserInfo_9_1,UserInfo_9_2,UserInfo_9_3,UserInfo_9_4,UserInfo_9_5,UserInfo_9_6,UserInfo_9_7,UserInfo_11_1,UserInfo_11_2,UserInfo_11_3,UserInfo_12_1,UserInfo_12_2,UserInfo_12_3,UserInfo_13_1,UserInfo_13_2,UserInfo_13_3,UserInfo_14_1,UserInfo_14_2,UserInfo_14_3,UserInfo_14_4,UserInfo_14_5,UserInfo_14_6,UserInfo_14_7,UserInfo_15_1,UserInfo_15_2,UserInfo_15_3,UserInfo_15_4,UserInfo_15_5,UserInfo_15_6,UserInfo_15_7,UserInfo_16_1,UserInfo_16_2,UserInfo_16_3,UserInfo_16_4,UserInfo_16_5,UserInfo_16_6,UserInfo_17_1,UserInfo_17_2,UserInfo_19_1,UserInfo_19_2,UserInfo_19_3,UserInfo_19_4,UserInfo_19_5,UserInfo_19_6,UserInfo_19_7,UserInfo_19_8,UserInfo_19_9,UserInfo_19_10,UserInfo_19_11,UserInfo_19_12,UserInfo_19_13,UserInfo_19_14,UserInfo_19_15,UserInfo_19_16,UserInfo_19_17,UserInfo_19_18,UserInfo_19_19,UserInfo_19_20,UserInfo_19_21,UserInfo_19_22,UserInfo_19_23,UserInfo_19_24,UserInfo_19_25,UserInfo_19_26,UserInfo_19_27,UserInfo_19_28,UserInfo_19_29,UserInfo_19_30,UserInfo_19_31,UserInfo_21_1,UserInfo_21_2,UserInfo_22_1,UserInfo_22_2,UserInfo_22_3,UserInfo_22_4,UserInfo_22_5,UserInfo_22_6,UserInfo_22_7,UserInfo_23_1,UserInfo_23_2,UserInfo_23_3,UserInfo_23_4,UserInfo_23_5,UserInfo_23_6,UserInfo_23_7,UserInfo_23_8,UserInfo_23_9,UserInfo_23_10,UserInfo_23_11,UserInfo_23_12,UserInfo_23_13,UserInfo_23_14,UserInfo_23_15,UserInfo_23_16,UserInfo_23_17,UserInfo_23_18,UserInfo_23_19,UserInfo_23_20,UserInfo_23_21,UserInfo_23_22,UserInfo_23_23,UserInfo_23_24,UserInfo_23_25,UserInfo_23_26,UserInfo_23_27,UserInfo_23_28,Education_Info1_1,Education_Info1_2,Education_Info2_1,Education_Info2_2,Education_Info2_3,Education_Info2_4,Education_Info2_5,Education_Info2_6,Education_Info2_7,Education_Info3_1,Education_Info3_2,Education_Info3_3,Education_Info4_1,Education_Info4_2,Education_Info4_3,Education_Info4_4,Education_Info4_5,Education_Info4_6,Education_Info5_1,Education_Info5_2,Education_Info6_1,Education_Info6_2,Education_Info6_3,Education_Info6_4,Education_Info6_5,Education_Info6_6,Education_Info7_1,Education_Info7_2,Education_Info8_1,Education_Info8_2,Education_Info8_3,Education_Info8_4,Education_Info8_5,Education_Info8_6,Education_Info8_7,WeblogInfo_19_1,WeblogInfo_19_2,WeblogInfo_19_3,WeblogInfo_19_4,WeblogInfo_19_5,WeblogInfo_19_6,WeblogInfo_19_7,WeblogInfo_19_8,WeblogInfo_21_1,WeblogInfo_21_2,WeblogInfo_21_3,WeblogInfo_21_4,WeblogInfo_21_5,SocialNetwork_1_1,SocialNetwork_1_2,SocialNetwork_1_3,SocialNetwork_2_1,SocialNetwork_2_2,SocialNetwork_2_3,SocialNetwork_7_1,SocialNetwork_7_2,SocialNetwork_7_3,SocialNetwork_12_1,SocialNetwork_12_2,SocialNetwork_12_3";
		String feature[]=head.split(",");
		String type=" int";
		StringBuffer sb=new StringBuffer();
		for(String f:feature)
		{
			sb.append(","+f+type);
//			sb.append(",log(1+"+f+")");
		}
		System.out.println(sb.substring(1));
	}
	//把测试集的label填充为-1
	public static void fun2()throws Exception
	{
		BufferedReader br=Util.readFile("D:\\temp\\PPD_Master_GBK_2_Test_Set.csv");
		PrintWriter pt=Util.writeFile("D:\\temp\\test.csv");
		String line="";
		while((line=br.readLine())!=null)
		{
			String features[]=line.split(",");
			StringBuffer sb=new StringBuffer();
			for(int i=0;i<features.length-1;i++)
			{
				sb.append(","+features[i]);
			}
			sb.append(",-1");
			sb.append(","+datareverse(features[features.length-1]));
			pt.println(sb.substring(1));
		}
		pt.close();
	}
	//日期反转
	public static String datareverse(String date)
	{
		String temp[]=date.split("/");
		StringBuffer sb=new StringBuffer();
		for(int i=temp.length-1;i>=0;i--)
		{
			sb.append("/"+temp[i]);
		}
		return sb.substring(1);
	}
	//拼接SQL语句，有哪些操作以及次数的向量
	public static void fun3()throws Exception
	{
		BufferedReader br=Util.readFile("G:\\比赛\\魔镜杯风控算法大赛\\log_feature\\LogInfo2.csv");
		String line="";
		while((line=br.readLine())!=null)
		{
			System.out.println(",CASE WHEN LogInfo2='"+line+"' THEN num ELSE 0 END AS LogInfo2_"+line);
		}
	}
	//拼接SQL语句，更新次数最多的项目
	public static void fun4()throws Exception
	{
		BufferedReader br=Util.readFile("G:\\比赛\\魔镜杯风控算法大赛\\log_feature\\max_upd_items.csv");
		String line="";
		while((line=br.readLine())!=null)
		{
			System.out.println(",CASE WHEN UserupdateInfo1='"+line+"' THEN 1 ELSE 0 END AS is"+line+"_max_upd_item");
		}
	}
	//拼接SQL语句,选取子表所有属性
	public static void fun5()
	{
		for(int i=1;i<=28;i++)
		{
			System.out.print(",a"+i+".*");
		}
	}
	//中国城市人口密度排名
	public static void fun6()throws Exception
	{
		BufferedReader br=Util.readFile("G:\\比赛\\魔镜杯风控算法大赛\\data\\中国各城市人口密度排名.txt");
		PrintWriter pt=Util.writeFile("G:\\比赛\\魔镜杯风控算法大赛\\data\\城市人口密度.csv");
		PrintWriter pt2=Util.writeFile("G:\\比赛\\魔镜杯风控算法大赛\\data\\省份人口密度.csv");
		String line=br.readLine();
		String t[]=line.split(" ");
		HashMap<String, String> map=new HashMap<>();
		HashMap<String, ArrayList<Double>> city=new HashMap<>();
		for(int i=0;i<t.length;i++)
		{
			i++;
			map.put(t[i++], t[i+1]);
			i++;
		}
		for(int i=0;i<t.length;i++)
		{
			i++;
			i++;
			if (city.containsKey(t[i])) {
				city.get(t[i]).add(Double.parseDouble(t[i+1]));
			}else {
				ArrayList<Double> list=new ArrayList<>();
				list.add(Double.parseDouble(t[i+1]));
				city.put(t[i], list);
			}
//			System.out.println(t[i]+","+ t[i+1]);
			i++;
		}
		System.out.println(city.size());
		for(String key:city.keySet())
		{
			ArrayList<Double> list=city.get(key);
			double sum=0;
			for(double d:list)
			{
				sum+=d;
			}
			pt2.println(key+","+sum/list.size());
			System.out.println(key+","+sum/list.size());
		}
		pt2.close();
		for(String key:map.keySet())
		{
			pt.println(key+","+map.get(key));
//			System.out.println(key+":"+map.get(key));
		}
		pt.close();
	}
	//城市人口排名
	public static void fun7()throws Exception
	{
		BufferedReader br=Util.readFile("G:\\比赛\\魔镜杯风控算法大赛\\data\\城市人口排名.csv");
		PrintWriter pt=Util.writeFile("G:\\比赛\\魔镜杯风控算法大赛\\data\\城市人口.csv");
		PrintWriter pt2=Util.writeFile("G:\\比赛\\魔镜杯风控算法大赛\\data\\省份平均人口.csv");
		String line="";
		HashMap<String, String> city=new HashMap<>();
		HashMap<String, ArrayList<Double>> shen=new HashMap<>();
		while((line=br.readLine())!=null)
		{
			String t[]=line.split(" ");
			String key=t[1].replace("辖区", "").replace("市", "");
//			System.out.println(t[1].replace("辖区", "")+","+t[4]);
			city.put(key, t[4]);
			String sheng=t[2];
			if (shen.containsKey(sheng)) {
				shen.get(sheng).add(Double.parseDouble(t[4]));
			}else {
				ArrayList<Double> list=new ArrayList<>();
				list.add(Double.parseDouble(t[4]));
				shen.put(sheng, list);
			}
		}
		for(String key:city.keySet())
		{
			pt.println(key+","+city.get(key));
		}
		for(String key:shen.keySet())
		{
			ArrayList<Double> list=shen.get(key);
			double sum=0;
			for(double d:list)
			{
				sum+=d;
			}
			pt2.println(key+","+sum/list.size());
			System.out.println(key+","+sum/list.size());
		}
		pt.close();
		pt2.close();
	}
	//生成城市人口密度特征，城市人口特征
	public static void fun8()throws Exception
	{
		BufferedReader train=Util.readFile("G:\\比赛\\魔镜杯风控算法大赛\\PPD-First-Round-Data-Update\\Training Set\\PPD_Training_Master_GBK_3_1_Training_Set.csv");
		BufferedReader test=Util.readFile("G:\\比赛\\魔镜杯风控算法大赛\\PPD-First-Round-Data-Update\\Test Set\\PPD_Master_GBK_2_Test_Set.csv");
		String line=train.readLine();
		BufferedReader density=Util.readFile("G:\\比赛\\魔镜杯风控算法大赛\\data\\城市人口密度.csv");
		BufferedReader human_num=Util.readFile("G:\\比赛\\魔镜杯风控算法大赛\\data\\城市人口.csv");
		BufferedReader density_sheng=Util.readFile("G:\\比赛\\魔镜杯风控算法大赛\\data\\省份人口密度.csv");
		HashMap<String, String> density_sheng_map=new HashMap<>();
		while((line=density_sheng.readLine())!=null)
		{
			String t[]=line.split(",");
			density_sheng_map.put(t[0].replace("省", ""), t[1]);
//			System.out.println(t[0].replace("省", "")+","+t[1]);
		}
		BufferedReader human_num_sheng=Util.readFile("G:\\比赛\\魔镜杯风控算法大赛\\data\\省份平均人口.csv");
		HashMap<String, String> human_num_sheng_map=new HashMap<>();
		while((line=human_num_sheng.readLine())!=null)
		{
			String t[]=line.split(",");
			human_num_sheng_map.put(t[0].replace("省", ""), t[1]);
			System.out.println(t[0].replace("省", "")+","+t[1]);
		}
		HashMap<String, String> density_map=new HashMap<>();
		while((line=density.readLine())!=null)
		{
			String t[]=line.split(",");
			density_map.put(t[0], t[1]);
		}
		HashMap<String, String> human_num_map=new HashMap<>();
		while((line=human_num.readLine())!=null)
		{
			String t[]=line.split(",");
			human_num_map.put(t[0], t[1]);
		}
		HashMap<String, String> city2sheng=fun9();
		PrintWriter pt1=Util.writeFile("G:\\比赛\\魔镜杯风控算法大赛\\data\\bryan_city_feature.csv");
//		PrintWriter pt2=Util.writeFile("");
		while((line=train.readLine())!=null)
		{
			String t[]=line.split(",");
			String Idx=t[0];
			String city1=t[2];
			String city2=t[4];
			String d11=(density_map.get(city1)!=null?density_map.get(city1):density_sheng_map.get(city2sheng.get(city1)));
			String d12=human_num_map.get(city1)!=null?human_num_map.get(city1):human_num_sheng_map.get(city2sheng.get(city1));
			String d21=density_map.get(city2)!=null?density_map.get(city2):density_sheng_map.get(city2sheng.get(city2));
			String d22=human_num_map.get(city2)!=null ? human_num_map.get(city2):human_num_sheng_map.get(city2sheng.get(city2));
			pt1.println(Idx+","+d11+","+d12+","+d21+","+d22);
			
//			pt1.println(Idx+","+(density_map.get(city1)!=null?density_map.get(city1):density_sheng_map.get(city2sheng.get(city1)))+","
//			+human_num_map.get(city1)!=null?human_num_map.get(city1):human_num_sheng_map.get(city2sheng.get(city1))+","
//			+density_map.get(city2)!=null?density_map.get(city2):density_sheng_map.get(city2sheng.get(city2))+","
//			+human_num_map.get(city2)!=null ? human_num_map.get(city2):human_num_sheng_map.get(city2sheng.get(city2)));
//			System.out.println(Idx+","+density_map.get(city1)!=null?density_map.get(city1):density_sheng_map.get(city2sheng.get(city1))+","
//					+human_num_map.get(city1)!=null?human_num_map.get(city1):human_num_sheng_map.get(city2sheng.get(city1))+","
//							+density_map.get(city2)!=null?density_map.get(city2):density_sheng_map.get(city2sheng.get(city2))+","
//							+human_num_map.get(city2)!=null ? human_num_map.get(city2):human_num_sheng_map.get(city2sheng.get(city2)));
		}
		pt1.close();
	}
	
	//生成城市对应的省map
	public static HashMap<String, String> fun9()throws Exception
	{
		HashMap<String,String> map=new HashMap<>();
		BufferedReader br=Util.readFile("G:\\比赛\\魔镜杯风控算法大赛\\data\\城市人口排名.csv");
		String line="";
		while((line=br.readLine())!=null)
		{
			String t[]=line.split(" ");
			String city=t[1].replace("辖区", "").replace("市", "");
			String sheng=t[2].replace("省", "").replace("市", "");
			System.out.println(city+","+sheng);
		}
		return map;
	}
	//将
}

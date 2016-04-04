package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.unsupervised.attribute.Remove;

public class Util {
	public static BufferedReader readFile(String src)throws Exception
	{
//		return new BufferedReader(new FileReader(new File(src)));
		return new BufferedReader(new InputStreamReader(new FileInputStream(new File(src)), "UTF-8"));
	}
	
	public static PrintWriter writeFile(String dst)throws Exception
	{
		return new PrintWriter(new FileWriter(dst));
	}
	
	public static boolean saveArrayList(ArrayList<String> list,String dst)throws Exception
	{
		PrintWriter pt=writeFile(dst);
		int i=0;
		while(i<list.size())
		{
			pt.println(list.get(i));
			i++;
		}
		pt.close();
		return true;
	}
	
	public static ArrayList<String> readToArrayList(String src)throws Exception
	{
		BufferedReader br=readFile(src);
		ArrayList<String> list=new ArrayList<>();
		String line="";
		while((line=br.readLine())!=null)
		{
			list.add(line.trim());
		}
		br.close();
		return list;
	}
	
	public static HashMap<String,Integer> readToHashMap(String src)throws Exception
	{
		BufferedReader br=readFile(src);
		HashMap<String,Integer> map=new HashMap<>();
		String line="";
		while((line=br.readLine())!=null)
		{
			map.put(line, 0);
		}
		br.close();
		return map;
	}
	
	//�������ݵ�ʵ��
	public static Instances getInstances(String in)throws Exception
	{
//		DataSource source = new DataSource(in);
//		Instances ins = source.getDataSet();
//		return ins;
		return DataSource.read(in);
	}
	//ɾ����ǩ����
	public static Instances deleteClass(Instances ins)throws Exception
	{
		Remove filter = new Remove();
		String options[] = new String[2];
		options[0] = "-R";
		options[1] = "last";
		filter.setOptions(options);
		filter.setInputFormat(ins);
		return Filter.useFilter(ins, filter);
	}
	//����ǩ����ɢ��
	public static Instances discLabel(Instances ins)throws Exception
	{
		NumericToNominal filter = new NumericToNominal();
        String option[] = new String[2];
        option[0] = "-R";
        option[1] = "last";
        filter.setOptions(option);
        filter.setInputFormat(ins);
        ins=Filter.useFilter(ins, filter);
        ins.setClassIndex(ins.numAttributes()-1);
        return ins;
	}
	//�����һ��
	public static double[] normalize(double data[])
	{
		double max=0;
		double min=data[0];
		for(double d:data)
		{
			if (max<d) {
				max=d;
			}
			if (min>d) {
				min=d;
			}
		}
		double grap=max-min;
		double result[]=new double[data.length];
		for(int i=0;i<result.length;i++)
		{
			result[i]=(data[i]-min)/grap;
		}
		return result;
	}
	public static ArrayList<Double> normalize(ArrayList<Double> data)
	{
		double max=0;
		double min=data.get(0);
		for(double d:data)
		{
			if (max<d) {
				max=d;
			}
			if (min>d) {
				min=d;
			}
		}
		double grap=max-min;
		ArrayList<Double> result=new ArrayList<>();
		for(int i=0;i<data.size();i++)
		{
			result.add((data.get(i)-min)/grap);
		}
		return result;
	}
	
    /**
     * ɾ�������ļ�
     * @param   sPath    ��ɾ���ļ����ļ���
     * @return �����ļ�ɾ���ɹ�����true�����򷵻�false
     */
    public static boolean deleteFile(String sPath) {
    	boolean flag = false;
    	File file = new File(sPath);
        // ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
	
	public static void main(String[] args) {
		for(int i=0;i<65;i++)
		{
			System.out.print("x"+i+",");
		}
	}
}

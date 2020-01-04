package com.hive.wash;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataWash {
    public static class DMap extends Mapper<LongWritable, Text,Text,IntWritable>{
        private String jobname;
        private String money;
        private String company;
        private String area;
        private String exprience;
        private String edu;
        private String mans;
        private String time;
        private String skill;
        private String profession;
        private String welfare;
        private String comptype;
        private String peoples;
        private String trade;


   //  运维工程师（微服务/Linux）,0.7-1万/月,南京壹证通信息科技有限公司,南京-雨花台区 , 5-7年经验 , 大专 , 招1人 , 09-25发布 , 计算机网络 计算机信息管理,,五险一金|餐饮补贴|带薪年假|周末双休|节日福利|定期体检|年终奖金,民营公司,50-150人,计算机软件 计算机服务(系统、数据服务、维修)
        /**
         * map清洗数据
         * anthor:bb
         */
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //读一行数据
            String[] words = value.toString().split("`");
            if(words.length<14){
                return;
            }
            jobname=words[0];
            money=words[1];
            company=words[2];
            area=words[3].replace(" ","");
            // 将字符 C2A0、制表符、换行、回车"\t|\r|\n|" +去掉
            // 下面并没有把空格去掉
            byte bytes[] = {(byte) 0xC2,(byte) 0xA0};
            String UTFSpace = new String(bytes,"utf-8");
            area = area.replace(UTFSpace,"");

            exprience=words[4].replace(" ","");
            edu=words[5].replace(" ","");
            mans=words[6];
            time=words[7].replace(" ","");
            skill=words[8].replace(" ","");
            profession=words[9].replace(" ","");
            welfare=words[10];
            comptype=words[11];
            peoples=words[12];
            trade=words[13];
            if(jobname.equals("")){
                return;
            }

            if(edu.contains("区")||edu.contains("司")||edu.contains("验")||edu.contains("null")){
                return;
            }

            Double min;
            Double max;
            if(money.equals("")||money.contains("面")){
                return;
            }else {
                if(money.length()<4){
                    return;
                }
                String danw=money.substring(money.length()-3,money.length());
                if("万/月".equals(danw)){
                    String xxs=money.substring(0,money.length()-3);
                    String[] split = xxs.split("-");
                    min=Double.parseDouble(split[0])*10000;
                    max=Double.parseDouble(split[1])*10000;
                }else if("千/月".equals(danw)){
                    String xxs=money.substring(0,money.length()-3);
                    String[] split = xxs.split("-");
                    min=Double.parseDouble(split[0])*1000;
                    max=Double.parseDouble(split[1])*1000;
                }else if("元/天".equals(danw)){
                    max=min=Double.parseDouble(money)*30;
                }else if("万/年".equals(danw)){
                    String xxs=money.substring(0,money.length()-3);
                    String[] split = xxs.split("-");
                    min=Double.parseDouble(split[0])*10000/12;
                    max=Double.parseDouble(split[1])*10000/12;
                }else {
                    return;
                }
            }

            String[] split = area.split("-");
            int s=split.length;
            String city;
            String qu;
            if(s==1){
                city=split[0];
                qu="null";
            }else if(s>=2){
                city=split[0];
                qu=split[1];
            }else {
                city="";
                qu="";
            }

            String exp="";
            if(exprience.contains("无")||exprience.equals("")){
                exp="0";
            }else {
                String reg="(\\d)";
                Pattern pattern=Pattern.compile(reg);
                Matcher matcher=pattern.matcher(exprience);
                if(matcher.find()){
                    exp=matcher.group(1);
                }
            }
            String man="";
            String reg="(\\d)";
            Pattern pattern=Pattern.compile(reg);
            Matcher matcher=pattern.matcher(mans);
            if(mans.contains("若干")){
                man="10";
            }else if(matcher.find()){
                man=matcher.group(1);
            }else {
                man="0";
            }
            if(skill.contains("语")||skill.contains("话")){

            }else {
                profession=skill;
                skill="";
            }
            String str=jobname+"`"+min+"`"+max+"`"+company+"`"+city+"`"+qu+"`"+exp+"`"+
                    edu+"`"+man+"`"+time+"`"+skill+"`"+profession+"`"+welfare+"`"+comptype+"`"+
                    peoples+"`"+trade;
            context.write(new Text(str),new IntWritable(1));
        }
    }


    public static class DMapReduce extends Reducer<Text,IntWritable,Text,Text>{
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            context.write(key,new Text(""));
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "wash data");
        job.setJarByClass(DataWash.class);
        job.setMapperClass(DMap.class);
        job.setReducerClass(DMapReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}

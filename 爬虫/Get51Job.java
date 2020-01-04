package com.jsoup.gethtml;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Get51Job {
    public static void getHtml(String url){
        try {
            Document doc = Jsoup.connect(url).get();
            Elements spans = doc.select("p.t1 a");
            int i=1;
            for(Element span:spans){
                String joburl=span.attr("href");
                getDetails(joburl);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void getDetails(String joburl){
        //https://jobs.51job.com/shenzhen-nsq/117347710.html?s=01&t=0
        //https://jobs.51job.com/hangzhou/112946708.html?s=01&t=0
        //https://jobs.51job.com/guangzhou/114204332.html?s=01&t=0
        try {
            String txt="";
            Document doc = Jsoup.connect(joburl).get();
            //维保销售主任 10-15万/年
            Elements h1s = doc.select("div.cn h1");
            Elements money = doc.select("div.cn strong");
            for (Element e:h1s){
                txt+=e.text()+"`";
            }

            for (Element e:money){
                txt+=money.text()+"`";
            }
            //通力电梯有限公司
            Elements comp = doc.select("a.catn");
            for (Element e:comp){
                txt+=e.text()+"`";
            }
            //合肥  |  无工作经验  |  招1人  |  09-24发布
            Elements addrs = doc.select("p.msg");
            int y=0;
            for (Element e:addrs){
                String[] split = e.text().split("\\|");
                for (String s:split){
                    y++;
                    txt+=s+"`";
                }
            }
            if(y<7){
                for (int i=0;i<7-y;i++){
                    txt+= "`";
                }
            }
            //五险一金补充医疗保险免费班车员工旅游餐饮补贴绩效奖金定期体检
            Elements  welfares= doc.select("span.sp4");
            for (int i=0;i<welfares.size();i++){
                if(i==welfares.size()-1){
                    txt+=welfares.get(i).text();
                    continue;
                }
                txt+=welfares.get(i).text()+"|";
            }
            txt+=",";
            //公司
            Elements comp1 = doc.select("p.at");
            for (int i=0;i<comp1.size();i++){
                if(i==comp1.size()-1){
                    txt+=comp1.get(i).text();
                    continue;
                }
                txt+=comp1.get(i).text()+"`";
            }
            if(y<=4){

            }else {
                txt+="\n";
                writeTxt(txt,"D://reptile/51jobs_new2.txt");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeTxt(String str,String path) throws IOException {
        File txt=new File(path);
        if(!txt.exists()){
            txt.createNewFile();
        }
        byte bytes[];
        bytes=str.getBytes();
        int b=bytes.length;   //是字节的长度，不是字符串的长度
        FileOutputStream fos=new FileOutputStream(path,true);
        fos.write(bytes,0,b);
        fos.close();
    }


    public static void main(String[] args) {
        /*//大数据,1177页，共58848条职位
        for (int i=1;i<=1177;i++){
            String url="https://search.51job.com/list/000000,000000,0000,01,9,99,%25E5%25A4%25A7%25E6%2595%25B0%25E6%258D%25AE,2,"+i+".html?lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=";
            getHtml(url);
        }
        //Java，共78668条职位
        for (int i=1;i<=1574;i++){
            String url="https://search.51job.com/list/000000,000000,0000,00,9,99,Java,2,"+i+".html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
            getHtml(url);
        }

        //前端共52372条职位
        for (int i=1;i<=1048;i++){
            String url="https://search.51job.com/list/000000,000000,0000,00,9,99,%25E5%2589%258D%25E7%25AB%25AF,2,"+i+".html?lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=";
            getHtml(url);
        }
        //运维共55435条职位
        for (int i=1;i<=1109;i++){
            String url="https://search.51job.com/list/000000,000000,0000,00,9,99,%25E8%25BF%2590%25E7%25BB%25B4,2,"+i+".html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
            getHtml(url);
        }*/

        //网络100000
        for (int i=1;i<=2000;i++){
            String url="https://search.51job.com/list/000000,000000,0000,00,9,99,%25E7%25BD%2591%25E7%25BB%259C,2,"+i+".html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
            getHtml(url);
        }

        //软件100000
        for (int i=1;i<=2000;i++){
            String url="https://search.51job.com/list/000000,000000,0000,00,9,99,%25E8%25BD%25AF%25E4%25BB%25B6,2,"+i+".html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
            getHtml(url);
        }
        //C++ 4.9w
        for (int i=1;i<=967;i++){
            String url="https://search.51job.com/list/000000,000000,0000,00,9,99,c%252B%252B,2,"+i+".html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
            getHtml(url);
        }

        /*//linux 共72277条职位
        for (int i=1;i<=1446;i++){
            String url="https://search.51job.com/list/000000,000000,0000,00,9,99,Linux,2,"+i+".html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
            getHtml(url);
        }

        //移动 8w
        for (int i=1;i<=1598;i++){
            String url="https://search.51job.com/list/000000,000000,0000,00,9,99,%25E7%25A7%25BB%25E5%258A%25A8,2,"+i+".html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
            getHtml(url);
        }

        //通信10W
        for (int i=1;i<=2000;i++){
            String url="https://search.51job.com/list/000000,000000,0000,00,9,99,%25E9%2580%259A%25E4%25BF%25A1,2,"+i+".html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
            getHtml(url);
        }*/
        /*//安全10w
        for (int i=1;i<=2;i++){
            String url="https://search.51job.com/list/000000,000000,0000,00,9,99,%25E5%25AE%2589%25E5%2585%25A8,2,"+i+".html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
            getHtml(url);
        }

        //z质量10w
        for (int i=1;i<=2000;i++){
            String url="https://search.51job.com/list/000000,000000,0000,00,9,99,%25E8%25B4%25A8%25E9%2587%258F,2,"+i+".html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
            getHtml(url);
        }
        //销售10W
        for (int i=1;i<=2000;i++){
            String url="https://search.51job.com/list/000000,000000,0000,00,9,99,%25E9%2594%2580%25E5%2594%25AE,2,"+i+".html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
            getHtml(url);
        }
        //计算机
        for (int i=1;i<=2000;i++){
            String url="https://search.51job.com/list/000000,000000,0000,00,9,99,%25E8%25AE%25A1%25E7%25AE%2597%25E6%259C%25BA,2,"+i+".html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
            getHtml(url);
        }*/


    }
}

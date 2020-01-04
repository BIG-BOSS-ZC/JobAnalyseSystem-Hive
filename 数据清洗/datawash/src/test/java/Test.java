import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
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

    public  void wash() {
        String key="前端开发工程师,1-1.5万/月,上海邦移出入境服务有限公司,上海 , 3-4年经验 , 大专 , 招1人 , 09-25发布,,,五险一金|员工旅游|交通补贴|餐饮补贴|专业培训|出国机会|通讯补贴|绩效奖金|年终奖金|定期体检,民营公司,150-500人,互联网/电子商务 中介服务";
        String[] words = key.toString().split(",");
        jobname=words[0];
        money=words[1];
        company=words[2];
        area=words[3];
        exprience=words[4];
        edu=words[5];
        mans=words[6];
        time=words[7];
        skill=words[8];
        profession=words[9];
        welfare=words[10];
        comptype=words[11];
        peoples=words[12];
        trade=words[13];

        Double min;
        Double max;
        if(money.equals("")||money.contains("面")){
            return ;
        }else {
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
            }else if("元/月".equals(danw)){
                max=min=Double.parseDouble(money)*30;
            }else {
                max=min=0.0;
            }
        }

        String[] split = area.split("-");
        int s=split.length;
        String city;
        String qu;
        if(s<=1){
            city=split[0];
            qu="null";
        }else {
            city=split[0];
            qu=split[1];
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
        System.out.println("sss"+skill);
        System.out.println("ppp"+profession);
        if(skill.contains("语")||skill.contains("话")){

        }else {
            profession=skill;
            skill="";
        }
        String str=jobname+"#"+min+"#"+max+'#'+company+"#"+city+"#"+qu+"#"+exp+"#"+
                edu+"#"+man+"#"+time+"#"+skill+"#"+profession+"#"+welfare+"#"+comptype+"#"+
                peoples+"#"+trade;
        System.out.println(str);
    }

    public static void main(String[] args) {
        /*Test test=new Test();
        test.wash();*/
        /*String time="9-25发布";
        String reg="(\\d*\\-\\d*)";
        Pattern pattern=Pattern.compile(reg);
        Matcher matcher=pattern.matcher(time);
        if (matcher.find()){
            time=matcher.group(1);
            System.out.println(time);
        }*/

    }
}

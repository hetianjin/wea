package com.example.app1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btn[] = new Button[30];
    private StringBuilder str1 = new StringBuilder();
    private StringBuilder str2 = new StringBuilder();
    private double huansuanbeilv;
    private EditText edit1;
    private EditText edit2;
    private String string1 = "";
    //private String string2 = "";
    private boolean dianvalues = true;
    private boolean caozuofu = true;
    private boolean boldian = true;
    public double[] a;
    public int num = 1;
    private double result = 0;


    //获取数的个数
    public int num1(StringBuilder str1) {
        int num = 1;
        for (int i = 0; i < str1.length(); i++) {
            if (panduanyunsfu(str1, i) == false) {
                num++;
            }
             if(str1.charAt(i)=='^'){
                num++;
            }
        }
        return num;
    }


    public double[] huoqushu(StringBuilder str1) {
        int n = num1(str1);
        int t = 0;

        double beishu1 = 10;
        double beishu2 = 1;
        double[] b = new double[n];
        double[] a=new double[n];
        for (int i = 0; i < n; i++) {
            b[i] = 0;
            a[i]=0;
        }
        for (int i = 0; i < n; i++) {
            beishu1 = 10;
            beishu2 = 1;
            int vall=0;
            for (int j = t; j < str1.length() - 1; j++) {
                if (str1.charAt(j) == '.') {
                    j++;
                    beishu1 = 1;
                    beishu2 = 0.1;
                    vall=1;
                }
                else if(panduanhanshu(str1,j)==true){
                    j=j+2;
                    continue;
                }
                else if(str1.charAt(j)=='l'){
                    j++;
                    continue;
                }

                else if(
                        str1.charAt(j)=='('||
                        str1.charAt(j)==')'||
                        str1.charAt(j)=='√') {
                    continue;
                }
                else if(str1.charAt(j)=='π'){
                    b[i]=Math.PI;
                    t = j + 2;
                    break;
                }
                else if(str1.charAt(j)=='e'){
                    b[i]= Math.E;
                    t = j + 2;
                    break;
                }
                else{
                }
                b[i] = b[i] * beishu1 + (str1.charAt(j) - 48) * beishu2;
                if(vall==1){
                    beishu2 =beishu2*0.1;
                }
                if (panduanyunsfu(str1, j + 1) == false) {
                    t = j + 2;
                    break;
                }
                 if(str1.charAt(j+1)=='^'){
                    t=j+2;
                    break;
                }

            }
        }

        return b;
    }

    public char[] huoqufuhao(StringBuilder str1) {
        StringBuilder strr = new StringBuilder();
        for (int i = 0; i < str1.length() - 1; i++) {
            if (panduanyunsfu(str1, i) == false) {
                strr.append(str1.charAt(i));
            }
            if(panduanhanshu(str1, i) == true){
                strr.append(str1.charAt(i));
                i=i+2;
            }
            if(str1.charAt(i)=='l'){
                strr.append(str1.charAt(i));
                 i++;
            }
             if(str1.charAt(i)=='^'||
                     str1.charAt(i)=='('||
                     str1.charAt(i)==')'||
                     str1.charAt(i)=='√'){
                 strr.append(str1.charAt(i));
             }

        }
        String str = strr.toString();
        char[] char1 = str.toCharArray();
        return char1;
    }

    public double Calculator() {//计算
        double result = 0;
        double[] b = huoqushu(str1);
        char[] chars1 = huoqufuhao(str1);
        double Htj_res = 0.0;
//        for(int i=0;i<b.length;i++){
//            System.out.print(" "+b[i]);
//        }
//        System.out.println();
//        for(int i=0;i<chars1.length;i++){
//            System.out.println(" "+chars1[i]);
//        }

        result = res(b, chars1);
        return result;
    }
    public double[] shanchushu(double[] a) {
        double[] c= a;
        int numc = c.length;
        for(int i=0;i<numc;i++){
            if(i<num-1){
                c[i] = c[i+1];
            }
            else {
                c[i] = 0;
            }
        }
        return c;
    }
    public char[] shanchufuhao(char[] a){
        char[] c= a;
        int numc = c.length;
        for(int i=0;i<numc;i++){
            if(i<num-1){
                c[i] = c[i+1];
            }
            else {
                c[i] = 0;
            }
        }
        return c;
    }

    public double res(double[] a, char[] b) {
        double Htj_res = 0.0;
//        for(int i=0;i<a.length;i++){
//            System.out.print(" "+a[i]);
//        }
//        System.out.println();
//        for(int i=0;i<b.length;i++){
//            System.out.println(" "+b[i]);
//        }

        int numm=0;
        int numm1=0;
        int numm11=0;


        for(int i=0;i<b.length;i++){
            if(b[i]=='+'||b[i]=='-'||b[i]=='*'||b[i]=='/'||b[i]=='%'){
                numm1++;
                continue;
            }
            if (b[i] == '^') {
                a[numm1] = Math.pow(a[numm1],a[numm1+1]);
                for (int j = numm1+1 ; j < a.length; j++) {
                    //考虑尾号
                    if ((j + 1) != a.length) {
                        a[j] = a[j + 1];
                    } else
                        a[j] = 0.0;
                }
                for (int j = i; j < b.length; j++) {
                    //考虑尾号
                    if ((j + 1) != b.length) {
                        b[j] = b[j + 1];
                    }
                    if ((j + 1) == b.length) {
                        b[j] = '0';
                    }
                }
                if (i == 0) {
                    i--;
                    continue;
                }
            }

        }

        for(int i=0;i<b.length;i++){
            if(b[i]=='+'||b[i]=='-'||b[i]=='*'||b[i]=='/'||b[i]=='%'){
                numm++;
                continue;
            }
            if(b[i]=='s'||b[i]=='c'||b[i]=='t'||b[i]=='l'||b[i]=='√'){
                if(i<b.length-1){
                    if(b[i+1]=='s'||b[i+1]=='c'||b[i+1]=='t'||b[i+1]=='l'||b[i+1]=='√') {
                        i = i + 1;
                    }
                }
                if(b[i]=='s'){
                    a[numm]=Math.sin(a[numm]);
                    for (int j = i; j < b.length; j++) {
                        //考虑尾号
                        if ((j + 1) != b.length) {
                            b[j] = b[j + 1];
                        }
                        if ((j + 1) == b.length) {
                            b[j] = '0';
                        }
                    }
                    if(i>0){
                        if(b[i-1]=='s'||b[i-1]=='c'||b[i-1]=='t'||b[i-1]=='l'||b[i-1]=='√'){
                            i=i-2;
                            continue;
                        }
                    }
                    if (i == 0) {
                        i--;
                        continue;
                    }
                    else{
                        i--;
                    }
                }
                if(b[i]=='c'){
                    a[numm]=Math.cos(a[numm]);
                    for (int j = i; j < b.length; j++) {
                        //考虑尾号
                        if ((j + 1) != b.length) {
                            b[j] = b[j + 1];
                        }
                        if ((j + 1) == b.length) {
                            b[j] = '0';
                        }
                    }
                    if(i>0){
                        if(b[i-1]=='s'||b[i-1]=='c'||b[i-1]=='t'||b[i-1]=='l'||b[i-1]=='√'){
                            i=i-2;
                            continue;
                        }
                    }
                    if (i == 0) {
                        i--;
                        continue;
                    }
                    else{
                        i--;
                    }
                }
                if(b[i]=='t'){
                    a[numm]=Math.tan(a[numm]);
                    for (int j = i; j < b.length; j++) {
                        //考虑尾号
                        if ((j + 1) != b.length) {
                            b[j] = b[j + 1];
                        }
                        if ((j + 1) == b.length) {
                            b[j] = '0';
                        }
                    }

                    if(i>0){
                        if(b[i-1]=='s'||b[i-1]=='c'||b[i-1]=='t'||b[i-1]=='l'||b[i-1]=='√'){
                            i=i-2;
                            continue;
                        }
                    }
                    if (i == 0) {
                        i--;
                        continue;
                    }
                    else{
                        i--;
                    }
                }
                if(b[i]=='l'){
                    a[numm]=Math.log10(a[numm]);
                    for (int j = i; j < b.length; j++) {
                        //考虑尾号
                        if ((j + 1) != b.length) {
                            b[j] = b[j + 1];
                        }
                        if ((j + 1) == b.length) {
                            b[j] = '0';
                        }
                    }

                    if(i>0){
                        if(b[i-1]=='s'||b[i-1]=='c'||b[i-1]=='t'||b[i-1]=='l'||b[i-1]=='√'){
                            i=i-2;
                            continue;
                        }
                    }

                    if (i == 0) {
                        i--;
                        continue;
                    }
                    else{
                        i--;
                    }
                }
                if(b[i]=='√'){
                    a[numm]=Math.sqrt(a[numm]);
                    for (int j = i; j < b.length; j++) {
                        //考虑尾号
                        if ((j + 1) != b.length) {
                            b[j] = b[j + 1];
                        }
                        if ((j + 1) == b.length) {
                            b[j] = '0';
                        }
                    }

                    if(i>0){
                        if(b[i-1]=='s'||b[i-1]=='c'||b[i-1]=='t'||b[i-1]=='l'||b[i-1]=='√'){
                            i=i-2;
                            continue;
                        }
                    }

                    if (i == 0) {
                        i--;
                        continue;
                    }
                    else{
                        i--;
                    }
                }
            }

        }
//        for(int i=0;i<a.length;i++){
//            System.out.print(" "+a[i]);
//        }
//        System.out.println();
//        for(int i=0;i<b.length;i++){
//            System.out.println(" "+b[i]);
//        }

        for (int i = 0; i < b.length; i++) {

            if (b[i] == '*') {
                a[i] = a[i] * a[i + 1];
                for (int j = i + 1; j < a.length; j++) {
                    //考虑尾号
                    if ((j + 1) != a.length) {
                        a[j] = a[j + 1];
                    } else
                        a[j] = 0.0;
                }
                for (int j = i; j < b.length; j++) {
                    //考虑尾号
                    if ((j + 1) != b.length) {
                        b[j] = b[j + 1];
                    }
                    if ((j + 1) == b.length) {
                        b[j] = '0';
                    }
                }
                if (i == 0) {
                    i--;
                    continue;
                }
            }
            if (b[i] == '/') {
                a[i] = a[i] / a[i + 1];
                for (int j = i + 1; j < a.length; j++) {
                    //考虑尾号
                    if ((j + 1) != a.length) {
                        a[j] = a[j + 1];
                    } else
                        a[j] = 0.0;
                }
                for (int j = i; j < b.length; j++) {
                    //考虑尾号
                    if ((j + 1) != b.length) {
                        b[j] = b[j + 1];
                    }
                    if ((j + 1) == b.length) {
                        b[j] = ' ';
                    }
                }

                i--;
                continue;
            }
            if (b[i] == '%') {
                a[i] = a[i] % a[i + 1];
                for (int j = i + 1; j < a.length; j++) {
                    //考虑尾号
                    if ((j + 1) != a.length) {
                        a[j] = a[j + 1];
                    } else
                        a[j] = 0.0;
                }
                for (int j = i; j < b.length; j++) {
                    //考虑尾号
                    if ((j + 1) != b.length) {
                        b[j] = b[j + 1];
                    }
                    if ((j + 1) == b.length) {
                        b[j] = ' ';
                    }
                }

                i--;
                continue;
            }

        }
        Htj_res = a[0];
        for (int i = 0; i < b.length; i++) {
            if (b[i] == '+') {
                Htj_res = Htj_res + a[i + 1];
            }
            if (b[i] == '-') {
                Htj_res = Htj_res - a[i + 1];
            }
        }


        return Htj_res;
    }

    //右括号不能多余左括号个数
    public boolean panduankuohao(StringBuilder str1){
        boolean kuohao =true;
        int num11 =0;
        for(int i=0;i<str1.length();i++) {
            if (str1.charAt(i) == '(')
                num11++;
            if (str1.charAt(i) == ')')
                num11--;
        }
        if(num11>0){
            kuohao = true;
        }
        else
            kuohao = false;
        return kuohao;
    }
    public boolean panduandian2(StringBuilder str1) {//点
        if (str1.charAt(str1.length() - 1) == '.') {
            return true;
        } else
            return false;
    }

    public boolean panduandenyu(StringBuilder str1) {
        //点
        int aaa=0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(str1.length() - 1) == '=') {
                aaa=1;
                break;
            } else
                aaa=0;
            break;
        }
        if(aaa==1){
            return true;
        }
        else
            return false;
    }


    public boolean panduandian(StringBuilder str1){
        int num1= str1.length();
        int value1=1;
        for(int i=0;i<num1;i++){
            if(str1.charAt(i)=='.'){
                value1 = -value1;//遇到点操作符，记录，value1为负
            }
            boolean czfu = panduanyunsfu(str1,i);
                if(czfu==false){
                    value1=1;//遇到操作符+-*/重置values
                }
            }
            if(value1>0){
                dianvalues=true;
            }
            if(value1<0){
                dianvalues=false;
            }
            return dianvalues;
        }


    public boolean panduanyunsfu(StringBuilder str1,int a){
        boolean val = true;

        if(

                        str1.charAt(a)=='+'||
                        str1.charAt(a)=='-'||
                        str1.charAt(a)=='*'||
                        str1.charAt(a)=='/'||
                        str1.charAt(a)=='%'){
            val = false;
        }
        else
            val = true;
        return val;
    }
    //判断汇率换算问题
    public boolean puanduanhuilv(StringBuilder str1){
        boolean bool = false;
        if(str1.charAt(0)=='￥')
            bool = true;
        else
            bool = false;

        return  bool;
    }
    //判断运算符前有没有￥
    public boolean puanduanhuilv2(StringBuilder str2){
        boolean boole = false;
        if(str1.charAt(str1.length()-1)=='￥')
            boole = true;
        else
            boole = false;
        return  boole;
    }
   //判断函数
    public boolean panduanhanshu(StringBuilder str1,int a){
        boolean pdhs =false;
        if(str1.charAt(a)=='s'){
            pdhs =true;
        }
        else if(str1.charAt(a)=='c'){
            pdhs=true;
        }
       else if(str1.charAt(a)=='t'){
            pdhs=true;
        }
        else
            pdhs = false;
        return  pdhs;
    }
    public boolean panduanhanshu1(StringBuilder str1,int a){
        boolean pdhs =false;
        if(str1.charAt(a)=='s'){
            pdhs =true;
        }
       else if(str1.charAt(a)=='n'){
            pdhs=true;
        }

        else
            pdhs = false;
        return  pdhs;
    }
    //判断前一位是否为数
    public boolean panduanshu(StringBuilder str1){
        boolean boolshu=false;
        if(str1.charAt(str1.length()-1)=='0')
            boolshu =true;
        if(str1.charAt(str1.length()-1)=='1')
            boolshu =true;
        if(str1.charAt(str1.length()-1)=='2')
            boolshu =true;
        if(str1.charAt(str1.length()-1)=='3')
            boolshu =true;
        if(str1.charAt(str1.length()-1)=='4')
            boolshu =true;
        if(str1.charAt(str1.length()-1)=='5')
            boolshu =true;
        if(str1.charAt(str1.length()-1)=='6')
            boolshu =true;
        if(str1.charAt(str1.length()-1)=='7')
            boolshu =true;
        if(str1.charAt(str1.length()-1)=='8')
            boolshu =true;
        if(str1.charAt(str1.length()-1)=='9')
            boolshu =true;
        if(str1.charAt(str1.length()-1)=='π')
            boolshu =true;
        if(str1.charAt(str1.length()-1)=='e')
            boolshu =true;
        return  boolshu;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btn[0] = (Button) findViewById(R.id.Button_0);
        btn[1] = (Button) findViewById(R.id.Button_1);
        btn[2] = (Button) findViewById(R.id.Button_2);
        btn[3] = (Button) findViewById(R.id.Button_3);
        btn[4] = (Button) findViewById(R.id.Button4);
        btn[5] = (Button) findViewById(R.id.Button5);
        btn[6] = (Button) findViewById(R.id.Button6);
        btn[7] = (Button) findViewById(R.id.Button7);
        btn[8] = (Button) findViewById(R.id.Button8);
        btn[9] = (Button) findViewById(R.id.Button9);
        btn[10] = (Button) findViewById(R.id.Button_C);
        btn[11] = (Button) findViewById(R.id.Button_delete);
        btn[12] = (Button) findViewById(R.id.button_divided);
        btn[13] = (Button) findViewById(R.id.button_multiply);
        btn[14] = (Button) findViewById(R.id.Button_minus);
        btn[15] = (Button) findViewById(R.id.Button_plus);
        btn[16] = (Button) findViewById(R.id.Button_js);
        btn[17] = (Button) findViewById(R.id.Button_yu);
        btn[18] = (Button) findViewById(R.id.Button_dian);
        //btn[19] = (Button) findViewById(R.id.Button_huilv);
        btn[20] = (Button) findViewById(R.id.button_cos);
        btn[21] = (Button) findViewById(R.id.button_sin);
        btn[22] = (Button) findViewById(R.id.button_tan);
//        btn[23] = (Button) findViewById(R.id.button_zuokuohao);
//        btn[24] = (Button) findViewById(R.id.button_youkuohao);
        btn[25] = (Button) findViewById(R.id.button_genhao);
        btn[26] = (Button) findViewById(R.id.button_zhishucimi);
        btn[27] = (Button) findViewById(R.id.button_luoge);
        btn[28] = (Button) findViewById(R.id.button_pai);
        btn[29] = (Button) findViewById(R.id.button_e);

        edit1 = (EditText) this.findViewById(R.id.EditText1);
        edit1.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        //文本显示的位置在EditText的最上方
        edit1.setGravity(Gravity.TOP);

        edit1.setSingleLine(false);
        //水平滚动设置为False
        edit1.setHorizontallyScrolling(false);
        edit2 = (EditText) this.findViewById(R.id.EditText2);
        btn[0].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(str1.length()>0) {
                    if (str1.charAt(str1.length() - 1) == 'π') {
                        str1.append("*");
                    }
                    if (str1.charAt(str1.length() - 1) == 'e') {
                        str1.append("*");
                    }
                }
                str1 = str1.append("0");
                edit1.setText(str1);
            }

        });
        btn[1].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(str1.length()>0) {
                    if (str1.charAt(str1.length() - 1) == 'π') {
                        str1.append("*");
                    }
                    if (str1.charAt(str1.length() - 1) == 'e') {
                        str1.append("*");
                    }
                }
                str1 = str1.append("1");
                edit1.setText(str1);
            }

        });
        btn[2].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(str1.length()>0) {
                    if (str1.charAt(str1.length() - 1) == 'π') {
                        str1.append("*");
                    }
                    if (str1.charAt(str1.length() - 1) == 'e') {
                        str1.append("*");
                    }
                }
                str1 = str1.append("2");
                edit1.setText(str1);
            }

        });
        btn[3].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(str1.length()>0) {
                    if (str1.charAt(str1.length() - 1) == 'π') {
                        str1.append("*");
                    }
                    if (str1.charAt(str1.length() - 1) == 'e') {
                        str1.append("*");
                    }
                }
                str1 = str1.append("3");
                edit1.setText(str1);
            }

        });
        btn[4].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(str1.length()>0) {
                    if (str1.charAt(str1.length() - 1) == 'π') {
                        str1.append("*");
                    }
                    if (str1.charAt(str1.length() - 1) == 'e') {
                        str1.append("*");
                    }
                }
                str1 = str1.append("4");
                edit1.setText(str1);
            }

        });
        btn[5].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(str1.length()>0) {
                    if (str1.charAt(str1.length() - 1) == 'π') {
                        str1.append("*");
                    }
                    if (str1.charAt(str1.length() - 1) == 'e') {
                        str1.append("*");
                    }
                }
                str1 = str1.append("5");
                edit1.setText(str1);
            }

        });
        btn[6].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(str1.length()>0) {
                    if (str1.charAt(str1.length() - 1) == 'π') {
                        str1.append("*");
                    }
                    if (str1.charAt(str1.length() - 1) == 'e') {
                        str1.append("*");
                    }
                }
                str1 = str1.append("6");
                edit1.setText(str1);
            }

        });
        btn[7].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(str1.length()>0) {
                    if (str1.charAt(str1.length() - 1) == 'π') {
                        str1.append("*");
                    }
                    if (str1.charAt(str1.length() - 1) == 'e') {
                        str1.append("*");
                    }
                }
                str1 = str1.append("7");
                edit1.setText(str1);
            }

        });
        btn[8].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(str1.length()>0) {
                    if (str1.charAt(str1.length() - 1) == 'π') {
                        str1.append("*");
                    }
                    if (str1.charAt(str1.length() - 1) == 'e') {
                        str1.append("*");
                    }
                }
                str1 = str1.append("8");
                edit1.setText(str1);
            }

        });
        btn[9].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(str1.length()>0) {
                    if (str1.charAt(str1.length() - 1) == 'π') {
                        str1.append("*");
                    }
                    if (str1.charAt(str1.length() - 1) == 'e') {
                        str1.append("*");
                    }
                }
                str1 = str1.append("9");
                edit1.setText(str1);
            }

        });
        btn[10].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str1.delete(0, str1.length());
                edit2.setText(str1);
                edit1.setText(str1);
            }

        });
        btn[11].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (str1.length() > 0) {
                    if (panduanhanshu1(str1, str1.length() - 1) == true && str1.length() > 2)
                        str1.delete(str1.length() - 3, str1.length());
                    else if (str1.charAt(str1.length() - 1) == 'g') {
                        str1.delete(str1.length() - 2, str1.length());
                    } else
                        str1.delete(str1.length() - 1, str1.length());
                    edit1.setText(str1);

                }
            }

        });
        btn[12].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (str1.length() > 0) {
                    StringBuilder strrr = new StringBuilder();
                    strrr.append(str1.charAt(str1.length() - 1));
                    String str = strrr.toString();
                    char[] char11 = str.toCharArray();
                    switch (char11[0]) {
                        case '*':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '/':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '+':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '-':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '%':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '￥':
                            str1.append('0');
                            break;
                        case 'g':
                            str1.append('1');
                            break;
                        case 'n':
                            str1.append('0');
                            break;
                        case 's':
                            str1.append('0');
                            break;
                        case '(':
                            str1.append('0');
                            break;
                        case '.':
                            str1.append('0');
                            break;
                        case '√':
                            str1.append('0');
                            break;
                        case '^':
                            str1.append('1');
                            break;
                        case 'π':
                            break;
                        case 'e':
                            break;
                        case ')':
                            break;
                    }
                    str1.append('/');
                    edit1.setText(str1);
                }
            }

        });
        btn[13].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (str1.length() > 0) {
                    StringBuilder strrr = new StringBuilder();
                    strrr.append(str1.charAt(str1.length() - 1));
                    String str = strrr.toString();
                    char[] char11 = str.toCharArray();
                    switch (char11[0]) {
                        case '*':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '/':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '+':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '-':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '%':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '￥':
                            str1.append('0');
                            break;
                        case 'g':
                            str1.append('1');
                            break;
                        case 'n':
                            str1.append('0');
                            break;
                        case 's':
                            str1.append('0');
                            break;
                        case '(':
                            str1.append('0');
                            break;
                        case '.':
                            str1.append('0');
                            break;
                        case '√':
                            str1.append('0');
                            break;
                        case '^':
                            str1.append('1');
                            break;
                        case 'π':
                            break;
                        case 'e':
                            break;
                        case ')':
                            break;
                    }
                    str1.append('*');
                    edit1.setText(str1);
                }
            }
        });
        btn[14].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (str1.length() > 0) {
                    StringBuilder strrr = new StringBuilder();
                    strrr.append(str1.charAt(str1.length() - 1));
                    String str = strrr.toString();
                    char[] char11 = str.toCharArray();
                    switch (char11[0]) {
                        case '*':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '/':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '+':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '-':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '%':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '￥':
                            str1.append('0');
                            break;
                        case 'g':
                            str1.append('1');
                            break;
                        case 'n':
                            str1.append('0');
                            break;
                        case 's':
                            str1.append('0');
                            break;
                        case '(':
                            str1.append('0');
                            break;
                        case '.':
                            str1.append('0');
                            break;
                        case '√':
                            str1.append('0');
                            break;
                        case '^':
                            str1.append('1');
                            break;
                        case 'π':
                            break;
                        case 'e':
                            break;
                        case ')':
                            break;
                    }
                    str1.append('-');
                    edit1.setText(str1);
                }
            }

        });
        btn[15].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (str1.length() > 0) {
                    StringBuilder strrr = new StringBuilder();
                    strrr.append(str1.charAt(str1.length() - 1));
                    String str = strrr.toString();
                    char[] char11 = str.toCharArray();
                    switch (char11[0]) {
                        case '*':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '/':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '+':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '-':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '%':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '￥':
                            str1.append('0');
                            break;
                        case 'g':
                            str1.append('1');
                            break;
                        case 'n':
                            str1.append('0');
                            break;
                        case 's':
                            str1.append('0');
                            break;
                        case '(':
                            str1.append('0');
                            break;
                        case '.':
                            str1.append('0');
                            break;
                        case '√':
                            str1.append('0');
                            break;
                        case '^':
                            str1.append('1');
                            break;
                        case 'π':
                            break;
                        case 'e':
                            break;
                        case ')':
                            break;
                    }
                    str1.append('+');
                    edit1.setText(str1);
                }
            }


        });
        btn[16].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if (str1.length() > 0) {

                    if (panduanhanshu1(str1, str1.length() - 1) == true && str1.length() > 2) {
                        str1.delete(str1.length() - 3, str1.length());
                    }
                    if (puanduanhuilv2(str1) == true) {
                        str1.append("0.0=");
                        string1 = "0.0";
                        edit1.setText(str1);
                        edit2.setText("$" + string1);
                    } else {
                        boldian = panduandian2(str1);
                        double result = 0;
                        caozuofu = panduanyunsfu(str1, str1.length() - 1);
                        if (caozuofu == false || boldian == true)
                            str1.delete(str1.length() - 1, str1.length());
                        if (str1.charAt(str1.length() - 1) == '=') {
                        } else
                            str1 = str1.append("=");
                        str2 = str1;
                        if (puanduanhuilv(str1) == true) {
                            str1.delete(0, 1);
                            result = Calculator();

                            string1 = Double.toString(result);
                            edit1.setText("￥" + str2);
                            edit2.setText("$" + string1);
                            str1.delete(0, str1.length());
                            if(string1.charAt(0)!='N')
                            str1.append("￥" + string1);
                        } else {
                            result = Calculator();
                            string1 = Double.toString(result);
                            edit1.setText(str2);
                            edit2.setText("" + string1);
                            str1.delete(0, str1.length());
                            if(string1.charAt(0)!='N')
                                str1.append(string1);
                        }

                    }

                }

            }

        });
        btn[17].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (str1.length() > 0) {
                    StringBuilder strrr = new StringBuilder();
                    strrr.append(str1.charAt(str1.length() - 1));
                    String str = strrr.toString();
                    char[] char11 = str.toCharArray();
                    switch (char11[0]) {
                        case '*':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '/':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '+':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '-':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '%':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '￥':
                            str1.append('0');
                            break;
                        case 'g':
                            str1.append('1');
                            break;
                        case 'n':
                            str1.append('0');
                            break;
                        case 's':
                            str1.append('0');
                            break;
                        case '(':
                            str1.append('0');
                            break;
                        case '.':
                            str1.append('0');
                            break;
                        case '√':
                            str1.append('0');
                            break;
                        case '^':
                            str1.append('1');
                            break;
                        case 'π':
                            break;
                        case 'e':
                            break;
                        case ')':
                            break;
                    }
                    str1.append('%');
                    edit1.setText(str1);
                }
            }

        });
        btn[18].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (str1.length() == 0 || (puanduanhuilv2(str1) == true)) {
                    str1.append('0');
                }
                if (str1.length() > 0) {
                    StringBuilder strrr = new StringBuilder();
                    strrr.append(str1.charAt(str1.length() - 1));

                    String str = strrr.toString();
                    char[] char11 = str.toCharArray();
                    switch (char11[0]) {
                        case '*':
                            str1.append('0');
                            break;
                        case '/':
                            str1.append('0');
                            break;
                        case '+':
                            str1.append('0');
                            break;
                        case '-':
                            str1.append('0');
                            break;
                        case '%':
                            str1.append('0');
                            break;
                        case '￥':
                            str1.append('0');
                            break;
                        case 'g':
                            str1.append('0');
                            break;
                        case 'n':
                            str1.append('0');
                            break;
                        case 's':
                            str1.append('0');
                            break;
                        case '(':
                            str1.append('0');
                            break;
                        case '.':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '√':
                            str1.append('0');
                            break;
                        case '^':
                            str1.append('1');
                            break;
                        case 'π':
                            str1.append("+0");
                            break;
                        case 'e':
                            str1.append("+0");
                            break;
                        case ')':
                            str1.append("+0");
                            break;
                    }
                    if (panduandian(str1) == false) {
                        str1.append("+0");
                    }
                    str1.append('.');
                    edit1.setText(str1);
                }
            }

        });
//        btn[19].setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                str1.delete(0, str1.length());
//                str1 = str1.append("￥");
//                edit1.setText(str1);
//            }
//
//
//        });
        btn[20].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (str1.length() > 0) {
                    StringBuilder strrr = new StringBuilder();
                    strrr.append(str1.charAt(str1.length() - 1));
                    String str = strrr.toString();
                    char[] char11 = str.toCharArray();
                    switch (char11[0]) {
                        case '*':
                            break;
                        case '/':
                            break;
                        case '+':
                            break;
                        case '-':
                            break;
                        case '%':
                            break;
                        case '￥':
                            break;
                        case 'g':
                            break;
                        case 'n':
                            break;
                        case 's':
                            break;
                        case '(':
                            break;
                        case '.':
                            str1.append('0' + '+');
                            break;
                        case '√':
                            break;
                        case '^':
                            break;
                        case 'π':
                            str1.append('+');
                            break;
                        case 'e':
                            str1.append('+');
                            break;
                        case ')':
                            str1.append('+');
                            break;
                    }
                    if (panduanshu(str1) == true) {
                        str1.append("*");
                    }
                    str1.append("cos");
                    edit1.setText(str1);
                } else {
                    str1.append("cos");
                    edit1.setText(str1);
                }
            }
        });
        btn[21].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (str1.length() > 0) {
                    StringBuilder strrr = new StringBuilder();
                    strrr.append(str1.charAt(str1.length() - 1));
                    String str = strrr.toString();
                    char[] char11 = str.toCharArray();
                    switch (char11[0]) {
                        case '*':
                            break;
                        case '/':
                            break;
                        case '+':
                            break;
                        case '-':
                            break;
                        case '%':
                            break;
                        case '￥':
                            break;
                        case 'g':
                            break;
                        case 'n':
                            break;
                        case 's':
                            break;
                        case '(':
                            break;
                        case '.':
                            str1.append('0' + '+');
                            break;
                        case '√':
                            break;
                        case '^':
                            break;
                        case 'π':
                            str1.append('+');
                            break;
                        case 'e':
                            str1.append('+');
                            break;
                        case ')':
                            str1.append('+');
                            break;
                    }
                    if (panduanshu(str1) == true) {
                        str1.append("*");
                    }
                    str1.append("sin");
                    edit1.setText(str1);
                } else {
                    str1.append("sin");
                    edit1.setText(str1);
                }


            }


        });
        btn[22].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (str1.length() > 0) {
                    StringBuilder strrr = new StringBuilder();
                    strrr.append(str1.charAt(str1.length() - 1));
                    String str = strrr.toString();
                    char[] char11 = str.toCharArray();
                    switch (char11[0]) {
                        case '*':
                            break;
                        case '/':
                            break;
                        case '+':
                            break;
                        case '-':
                            break;
                        case '%':
                            break;
                        case '￥':
                            break;
                        case 'g':
                            break;
                        case 'n':
                            break;
                        case 's':
                            break;
                        case '(':
                            break;
                        case '.':
                            str1.append('0' + '+');
                            break;
                        case '√':
                            break;
                        case '^':
                            break;
                        case 'π':
                            str1.append('+');
                            break;
                        case 'e':
                            str1.append('+');
                            break;
                        case ')':
                            str1.append('+');
                            break;
                    }
                    if (panduanshu(str1) == true) {
                        str1.append("*");
                    }
                    str1.append("tan");
                    edit1.setText(str1);
                } else {

                    str1.append("tan");
                    edit1.setText(str1);
                }
            }
        });

        //()√^lgπe
        /*
        btn[23].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (str1.length() > 0) {
                    StringBuilder strrr = new StringBuilder();
                    strrr.append(str1.charAt(str1.length() - 1));
                    String str = strrr.toString();
                    char[] char11 = str.toCharArray();
                    switch (char11[0]) {

                        case '.':
                            str1.append('0' + '+');
                            break;
                        case '√':
                            break;
                        case '^':
                            break;
                        case 'π':
                            str1.append('+');
                            break;
                        case 'e':
                            str1.append('+');
                            break;
                        case ')':
                            str1.append('+');
                            break;
                    }
                    if (panduanshu(str1) == true) {
                        str1.append("*");
                    }
                    str1.append("(");
                    edit1.setText(str1);
                }
                else {
                    str1.append("(");
                    edit1.setText(str1);
                }
            }


        });
        btn[24].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (str1.length() > 0) {
                    StringBuilder strrr = new StringBuilder();
                    strrr.append(str1.charAt(str1.length() - 1));
                    String str = strrr.toString();
                    char[] char11 = str.toCharArray();
                    switch (char11[0]) {
                        case '+':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '-':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '*':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '/':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '%':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case 's':
                            str1.delete(str1.length() - 3, str1.length());
                            break;
                        case 'n':
                            str1.delete(str1.length() - 3, str1.length());
                            break;
                        case 'g':
                            str1.delete(str1.length() - 2, str1.length());
                            break;
                        case '.':
                            str1.append('0');
                            break;
                        case '√':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case '^':
                            str1.delete(str1.length() - 1, str1.length());
                            break;
                        case 'π':
                            break;
                        case 'e':
                            break;
                        case ')':
                            break;
                    }
                    if (panduankuohao(str1) == true) {
                        if (panduanshu(str1) == true||str1.charAt(str1.length()-1)==')')
                            str1.append(")");
                    }
                    edit1.setText(str1);
                }

            }


        });
        */
        //√
        btn[25].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (str1.length() > 0) {
                    StringBuilder strrr = new StringBuilder();
                    strrr.append(str1.charAt(str1.length() - 1));
                    String str = strrr.toString();
                    char[] char11 = str.toCharArray();
                    switch (char11[0]) {
                        case '+':
                            break;
                        case '-':
                            break;
                        case '*':
                            break;
                        case '/':
                            break;
                        case '%':
                            break;
                        case 's':
                            break;
                        case 'n':
                            break;
                        case 'g':
                            break;
                        case '(':
                            break;
                        case '.':
                            str1.append("0");
                            break;
                        case '√':

                            break;
                        case '^':

                            break;
                        case 'π':
                            str1.append('*');
                            break;
                        case 'e':
                            str1.append('*');
                            break;
                        case ')':
                            str1.append('*');
                            break;
                    }
                    if (panduanshu(str1) == true) {
                        str1.append("*");
                    }
                    str1.append("√");

                }
                else
                    str1.append('√');
                edit1.setText(str1);
            }
        });
        btn[26].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (str1.length() > 0) {
                    StringBuilder strrr = new StringBuilder();
                    strrr.append(str1.charAt(str1.length() - 1));
                    String str = strrr.toString();
                    char[] char11 = str.toCharArray();
                    switch (char11[0]) {
                        case '+':
                            str1.append('1');
                            break;
                        case '-':
                            str1.append('1');
                            break;
                        case '*':
                            str1.append('1');
                            break;
                        case '/':
                            str1.append('1');
                            break;
                        case '%':
                            str1.append('1');
                            break;
                        case 's':str1.append('1');
                            break;
                        case 'n':
                            str1.append('1');
                            break;
                        case 'g':
                            str1.append('1');
                            break;
                        case '(':
                            str1.append('1');
                            break;
                        case '.':

                            str1.append("0");
                            break;
                        case '√':
                            str1.append('1');
                            break;
                        case '^':
                            str1.delete(str1.length()-1,str1.length());
                            break;
                        case 'π':
                            break;
                        case 'e':
                            break;
                        case ')':
                            break;
                    }

                    str1.append("^");
                    edit1.setText(str1);
                }
            }
        });
        btn[27].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (str1.length() > 0) {
                    StringBuilder strrr = new StringBuilder();
                    strrr.append(str1.charAt(str1.length() - 1));
                    String str = strrr.toString();
                    char[] char11 = str.toCharArray();
                    switch (char11[0]) {
                        case '.':
                            str1.append("0*");
                            break;
                        case 'π':
                            str1.append("*");
                            break;
                        case 'e':
                            str1.append("*");
                            break;
                        case ')':
                            str1.append("*");
                            break;
                    }
                    if(panduanshu(str1)==true){
                        str1.append("*");
                    }
                    str1.append("lg");
                }
                else
                    str1.append("lg");
                edit1.setText(str1);
            }
        });
        btn[28].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (str1.length() > 0) {
                    StringBuilder strrr = new StringBuilder();
                    strrr.append(str1.charAt(str1.length() - 1));
                    String str = strrr.toString();
                    char[] char11 = str.toCharArray();
                    switch (char11[0]) {
                        case '.':
                            str1.delete(str1.length()-1,str1.length());
                            break;
                        case')':
                            str1.append("*");
                            break;
                    }
                    if(panduanshu(str1)==true){
                        str1.append("*");
                    }
                    str1.append("π");
                }
                else
                    str1.append("π");
                edit1.setText(str1);
            }
        });
        btn[29].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (str1.length() > 0) {
                    StringBuilder strrr = new StringBuilder();
                    strrr.append(str1.charAt(str1.length() - 1));
                    String str = strrr.toString();
                    char[] char11 = str.toCharArray();
                    switch (char11[0]) {
                        case '.':
                            str1.delete(str1.length()-1,str1.length());
                            break;
                        case')':
                            str1.append("*");
                            break;
                    }
                    if(panduanshu(str1)==true){
                        str1.append("*");
                    }
                    str1.append("e");
                }
                else
                    str1.append("e");
                edit1.setText(str1);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
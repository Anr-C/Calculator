package com.lckiss.calculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RadioButton RTHREE, RFOUR;

    //结果
    private TextView RS1, RS2, RS3, RS4;

    //按钮
    private Button DoRs, ToClean, Exit;

    //菜头
    private double doubleCaiTou;

    //数据定义 菜头，胡子，活鸟，拖鸟
    private EditText caoTou, huZhi1, huZhi2, huZhi3, huZhi4, huoNiao1, huoNiao2, huoNiao3, huoNiao4, tuoNiao1, tuoNiao2, tuoNiao3, tuoNiao4;

    //积分，活鸟，拖鸟
    private int intHuZi1, intHuZi2, intHuZi3, intHuZi4, intHuoNiao1, intHuoNiao2, intHuoNiao3, intHuoNiao4, intTuoNiao1, intTuoNiao2, intTuoNiao3, intTuoNiao4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "TIL-MICE 2017 ©CopyRight", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        CheckFirst();

        //隐藏玩家
        final TextView D = (TextView) findViewById(R.id.D);

        //菜头
        caoTou = (EditText) findViewById(R.id.ct);

        //胡子定义
        huZhi1 = (EditText) findViewById(R.id.hz1);
        huZhi2 = (EditText) findViewById(R.id.hz2);
        huZhi3 = (EditText) findViewById(R.id.hz3);
        huZhi4 = (EditText) findViewById(R.id.hz4);

        //活鸟定义
        huoNiao1 = (EditText) findViewById(R.id.hn1);
        huoNiao2 = (EditText) findViewById(R.id.hn2);
        huoNiao3 = (EditText) findViewById(R.id.hn3);
        huoNiao4 = (EditText) findViewById(R.id.hn4);

        //拖鸟定义
        tuoNiao1 = (EditText) findViewById(R.id.tn1);
        tuoNiao2 = (EditText) findViewById(R.id.tn2);
        tuoNiao3 = (EditText) findViewById(R.id.tn3);
        tuoNiao4 = (EditText) findViewById(R.id.tn4);

        //结果
        RS1 = (TextView) findViewById(R.id.rs1);
        RS2 = (TextView) findViewById(R.id.rs2);
        RS3 = (TextView) findViewById(R.id.rs3);
        RS4 = (TextView) findViewById(R.id.rs4);

        DoRs = (Button) findViewById(R.id.doRs);
        DoRs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RS1.setText(null);
                RS2.setText(null);
                RS3.setText(null);
                RS4.setText(null);
                if (RTHREE.isChecked()) {
                    ThreePerson();
                } else if (RFOUR.isChecked()) {
                    FourPerson();
                } else {
                    Toast.makeText(getApplication(), "不打还算个球啊", Toast.LENGTH_LONG).show();
                }
            }
        });
        //按钮定义
        ToClean = (Button) findViewById(R.id.toClean);
        ToClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CleanALl();
            }
        });
        Exit = (Button) findViewById(R.id.exit);
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
            }
        });

        //游戏规则按钮定义
        RTHREE = (RadioButton) findViewById(R.id.three);
        RTHREE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                D.setVisibility(View.INVISIBLE);
                huZhi4.setVisibility(View.INVISIBLE);
                huoNiao4.setVisibility(View.INVISIBLE);
                tuoNiao4.setVisibility(View.INVISIBLE);
                RS4.setVisibility(View.INVISIBLE);
//                CleanALl();
            }
        });

        RFOUR = (RadioButton) findViewById(R.id.four);
        RFOUR.setChecked(true);
        RFOUR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                D.setVisibility(View.VISIBLE);
                huZhi4.setVisibility(View.VISIBLE);
                huoNiao4.setVisibility(View.VISIBLE);
                tuoNiao4.setVisibility(View.VISIBLE);
                RS4.setVisibility(View.VISIBLE);
//                CleanALl();
            }
        });

    }

    public void CleanALl(){
        huZhi1.setText(null);
        huZhi2.setText(null);
        huZhi3.setText(null);
        huZhi4.setText(null);
        huoNiao1.setText(null);
        huoNiao2.setText(null);
        huoNiao3.setText(null);
        huoNiao4.setText(null);
        tuoNiao1.setText(null);
        tuoNiao2.setText(null);
        tuoNiao3.setText(null);
        tuoNiao4.setText(null);
    }

    public double Mathrint(double x){
        if(x<0){
            double xd=x-(int)(x);
            double	tmp=0.0;
            xd =-xd;
            if(xd>0.4){
                tmp=	Math.round(xd);
            }
            x=(int)x-tmp;
        }else{
            x=	Math.round(x);
        }
        return x*10.0;

    }
    public void ThreePerson() {
        try {
            //菜头
            doubleCaiTou = Double.parseDouble(caoTou.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            doubleCaiTou = 0;
        }
        //积分
        try {
            intHuZi1 = Integer.parseInt(huZhi1.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            intHuZi1 = 0;
        }
        try {
            intHuZi2 = Integer.parseInt(huZhi2.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            intHuZi2 = 0;
        }
        try {
            intHuZi3 = Integer.parseInt(huZhi3.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            intHuZi3 = 0;
        }

        //拖鸟
        try {
            intTuoNiao1 = Integer.parseInt(tuoNiao1.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            intTuoNiao1 = 0;
        }
        try {
            intTuoNiao2 = Integer.parseInt(tuoNiao2.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            intTuoNiao2 = 0;
        }
        try {
            intTuoNiao3 = Integer.parseInt(tuoNiao3.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            intTuoNiao3 = 0;
        }

        //活鸟
        try {
            intHuoNiao1 = Integer.parseInt(huoNiao1.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            intHuoNiao1 = 0;
        }
        try {
            intHuoNiao2 = Integer.parseInt(huoNiao2.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            intHuoNiao2 = 0;
        }
        try {
            intHuoNiao3 = Integer.parseInt(huoNiao3.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            intHuoNiao3 = 0;
        }


        //拖鸟结果值
        int[] Rtn = {0,0,0};

        int[] hz = {intHuZi1, intHuZi2, intHuZi3};
        int[] tn = {intTuoNiao1, intTuoNiao2, intTuoNiao3};
        int rs = 0;
        for (int i = 0; i < tn.length; i++) {
            for (int j = 0; j < tn.length; j++) {
                if (i != j) {
                    if (hz[i] - hz[j] > 0) {
                        rs += tn[i] + tn[j];
                    } else if(hz[i] - hz[j]==0){
                        rs+=0;
                    }else{
                        rs += (-tn[i] - tn[j]);
                    }
                }
            }

            Rtn[i]=rs;
            rs = 0;
        }

        intHuZi1 = (int) Mathrint(intHuZi1/10.0);
        intHuZi2 = (int) Mathrint(intHuZi2 / 10.0);
        intHuZi3 = (int) Mathrint(intHuZi3 / 10.0);

        //胡子的重新赋值
        hz[0] = intHuZi1;
        hz[1] = intHuZi2;
        hz[2] = intHuZi3;

        int[] hn = {intHuoNiao1, intHuoNiao2, intHuoNiao3};
        //活鸟结果值
        int[] Rhn = {0,0,0};
        rs = 0;
        for (int i = 0; i < tn.length; i++) {
            for (int j = 0; j < tn.length; j++) {
                if (i != j) {
                    rs += ((hz[i] - hz[j]) * doubleCaiTou * (hn[i] + 1) * (hn[j] + 1));
                }

            }
            Rhn[i]=rs;
            rs = 0;
        }

        int Srs11=Rhn[0] + Rtn[0];
        String Srs1 = Integer.toString(Srs11);
        RS1.setText(Srs1);
        int Srs22=Rhn[1] + Rtn[1];
        String Srs2 = Integer.toString(Srs22);
        RS2.setText(Srs2);
        int Srs33=Rhn[2] + Rtn[2];
        String Srs3 =  Integer.toString(Srs33);
        RS3.setText(Srs3);
//        for(int i=0;i<3;i++){
//            hn[i]=0;
//            hz[i]=0;
//            tn[i]=0;
//        }

    }

    public void FourPerson() {
        try {
            //菜头
            doubleCaiTou = Double.parseDouble(caoTou.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            doubleCaiTou = 0;
        }
        //积分
        try {
            intHuZi1 = Integer.parseInt(huZhi1.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            intHuZi1 = 0;
        }
        try {
            intHuZi2 = Integer.parseInt(huZhi2.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            intHuZi2 = 0;
        }
        try {
            intHuZi3 = Integer.parseInt(huZhi3.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            intHuZi3 = 0;
        }
        try {
            intHuZi4 = Integer.parseInt(huZhi4.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            intHuZi4 = 0;
        }
        //活鸟
        try {
            intHuoNiao1 = Integer.parseInt(huoNiao1.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            intHuoNiao1 = 0;
        }
        try {
            intHuoNiao2 = Integer.parseInt(huoNiao2.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            intHuoNiao2 = 0;
        }
        try {
            intHuoNiao3 = Integer.parseInt(huoNiao3.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            intHuoNiao3 = 0;
        }
        try {
            intHuoNiao4 = Integer.parseInt(huoNiao4.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            intHuoNiao4 = 0;
        }
        //拖鸟
        try {
            intTuoNiao1 = Integer.parseInt(tuoNiao1.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            intTuoNiao1 = 0;
        }
        try {
            intTuoNiao2 = Integer.parseInt(tuoNiao2.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            intTuoNiao2 = 0;
        }
        try {
            intTuoNiao3 = Integer.parseInt(tuoNiao3.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            intTuoNiao3 = 0;
        }
        try {
            intTuoNiao4 = Integer.parseInt(tuoNiao4.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            intTuoNiao4 = 0;
        }


        //拖鸟结果值
        int[] Rtn = {0,0,0,0};

        int[] hz = {intHuZi1, intHuZi2, intHuZi3,intHuZi4};
        int[] tn = {intTuoNiao1, intTuoNiao2, intTuoNiao3,intTuoNiao4};
        int rs = 0;
        for (int i = 0; i < tn.length; i++) {
            for (int j = 0; j < tn.length; j++) {
                if (i != j) {
                    if (hz[i] - hz[j] > 0) {
                        rs += tn[i] + tn[j];
                    } else if(hz[i] - hz[j]==0){
                        rs+=0;
                    }else{
                        rs += (-tn[i] - tn[j]);
                    }
                }
            }

            Rtn[i]=rs;
            rs = 0;
        }

        intHuZi1 = (int) Mathrint(intHuZi1 / 10.0);
        intHuZi2 = (int) Mathrint(intHuZi2 / 10.0);
        intHuZi3 = (int) Mathrint(intHuZi3 / 10.0);
        intHuZi4 = (int) Mathrint(intHuZi4 / 10.0);

        //胡子的重新赋值
        hz[0] = intHuZi1;
        hz[1] = intHuZi2;
        hz[2] = intHuZi3;
        hz[3] = intHuZi4;

        int[] hn = {intHuoNiao1, intHuoNiao2, intHuoNiao3,intHuoNiao4};
        //活鸟结果值
        int[] Rhn = {0,0,0,0};
        rs = 0;
        for (int i = 0; i < tn.length; i++) {
            for (int j = 0; j < tn.length; j++) {
                if (i != j) {
                    rs += ((hz[i] - hz[j]) * doubleCaiTou * (hn[i] + 1) * (hn[j] + 1));
                }

            }
            Rhn[i]=rs;
            rs = 0;
        }

        int Srs11=Rhn[0] + Rtn[0];
        String Srs1 = Integer.toString(Srs11);
        RS1.setText(Srs1);
        int Srs22=Rhn[1] + Rtn[1];
        String Srs2 = Integer.toString(Srs22);
        RS2.setText(Srs2);
        int Srs33=Rhn[2] + Rtn[2];
        String Srs3 =  Integer.toString(Srs33);
        RS3.setText(Srs3);
        int Srs44=Rhn[3] + Rtn[3];
        String Srs4 =  Integer.toString(Srs44);
        RS4.setText(Srs4);

//        for(int i=0;i<4;i++){
//            hn[i]=0;
//            hz[i]=0;
//            tn[i]=0;
//        }

    }

    public void CheckFirst() {
        String M = "CheckFirst";
        SharedPreferences setting = getSharedPreferences(M, 0);
        Boolean user_first = setting.getBoolean("FIRST", true);
        if (user_first) {//第一次
            setting.edit().putBoolean("FIRST", false).commit();

            new AlertDialog.Builder(this)
                    .// 图标
                    setTitle("骚年，第一次吧！")
                    .// 标题
                    setMessage("由于游戏规则实在太复杂，开发者无力进行功能开发\n" +
                    "如若需要开发者再加把力.请捐赠支付宝：18692878812\n")
                    .setNegativeButton("好的",
                            new DialogInterface.OnClickListener() {
                                // 取消
                                @Override
                                public void onClick(DialogInterface arg1,
                                                    int witch) {
                                    // no to do
//                                    Intent ToAliPay=new Intent("com.eg.android.AlipayGphone");
                                    Intent ToAliPay = getPackageManager().getLaunchIntentForPackage("com.eg.android.AlipayGphone");
                                    startActivity(ToAliPay);
                                }
                            })
                    .// 提示内容
                    setPositiveButton("我没钱，不会捐赠你的",

                    new DialogInterface.OnClickListener() {// 确定
                        @Override
                        public void onClick(DialogInterface arg0,
                                            int arg1) {
                            // yes to do
                            for (int i = 0; i <= 2; i++)
                                Toast.makeText(MainActivity.this, "你好残忍啊！一毛钱都不给！ 给你6秒自责", Toast.LENGTH_LONG).show();
                        }
                    }

            ).create().show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

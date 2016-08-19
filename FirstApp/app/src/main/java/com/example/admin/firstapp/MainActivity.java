package com.example.admin.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.accessibility.CaptioningManager;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // onCreate 호출 시 반드시 써야하는 규칙
        setContentView(R.layout.activity_main); // 리소스 파일인 activity_main.xml을 호출해 화면으로 사용
        TextView textView = (TextView)findViewById(R.id.text);


        String s = Calc(100);
        s += Calc(500);
        s += Calc(1000);
        s += Calc(10000);
        s += Calc(-500);


        textView.setText(s);


        }
    public String Calc(int n) {

        int a = 0;
        String s = "";
        if(n>0) {
            for (int i = 1; i <= n; i++) {
                a += i;
            }
        }else{
            for (int i = 1; i>=n; i--){
                a += i;
            }
        }
        s += "1부터 "+ n +"을 더 한 값은 " + String.valueOf(a) + '\n';
        return s;



    }
}

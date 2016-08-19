package com.example.debugapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DebugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        TextView textView = (TextView)findViewById(R.id.DebugText);
        textView.setText(Calc(10));



    }

    private int Calc(int x){
        int result = 0;
        for(int i = 1 ; i <= x; i++){
            result += i;
            Log.d("result", String.valueOf(i)+":"+String.valueOf(result));
        }
        return result;

    }
}

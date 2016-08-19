package com.example.chatapplication;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Test;

import java.util.Calendar;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mInputMessage;
    private Button mSendMessage;
    private LinearLayout mMessageLog;
    private TextView mCpuMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        mInputMessage = (EditText) findViewById(R.id.input_message); // 사용자 입력 필드

        mSendMessage = (Button) findViewById(R.id.send_message); // SEND 버튼
        mMessageLog = (LinearLayout) findViewById(R.id.message_log); // 입력 이력을 표시할 레이아웃

        mCpuMessage = (TextView) findViewById(R.id.cpu_message); // 컴퓨터의 응답

        mSendMessage.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
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

    @Override
    public void onClick(View v) {
            if(v.equals(mSendMessage)){
                //send 버튼이 눌렸을 때의 처리
                String inputText = mInputMessage.getText().toString();
                String answer ;
                final TextView userMessage =  new TextView(this); // 새 TextView 생성
                int messageColor = getResources().getColor(R.color.message_color);
                userMessage.setTextColor(messageColor);
                userMessage.setBackgroundResource(R.drawable.user_message);
                userMessage.setText(inputText); // TextView에 입력한 텍스트를 설정
                //메세지 크기에 맞춘다
                LinearLayout.LayoutParams userMessageLayout = new LinearLayout.
                        LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                userMessageLayout.gravity = Gravity.END; // 오른쪽 맞춤
                final int marginSize =
                        getResources().getDimensionPixelSize(R.dimen.message_margin);
                // 간격을 설정
                userMessageLayout.setMargins(0,marginSize,0,marginSize);
                mMessageLog.addView(userMessage,0,userMessageLayout);
                // TextView를 View의 맨 위에 설정한다

                if(inputText.contains("안녕")){
                    answer  = "안녕하세요.";
                }else if(inputText.contains("피곤")){
                    answer = "고생하셨어요.";
                }else if(inputText.contains("운세")) {
                    double random = Math.random() * 5d;

                    if(random < 1d){
                        answer = "몹시 나쁨";
                    }else if(random < 2d){
                        answer = "나쁨" ;
                    }else if(random < 3d){
                        answer = "보통";
                    }else if(random <3d){
                        answer = "좋음";
                    }else if(random <4d){
                        answer = "행운";
                    }else {
                        answer = "대박";
                    }

                }else if(inputText.contains("시간")){
                    Calendar cal = Calendar.getInstance();

                    int hour = cal.get(Calendar.HOUR);
                    int minute = cal.get(Calendar.MINUTE);
                    int second = cal.get(Calendar.SECOND);

                    answer = String.format("현재 시간은 %1d시 %2d분 %3d초 입니다.", hour, minute, second);
                }
                else
                 {
                    answer = "그거 괜찮네요.";
                }

                final TextView cpuMessage = new TextView(this);
                // 내부 클래스에서 참조하기 위해 final 선언
                cpuMessage.setTextColor(messageColor);
                cpuMessage.setBackgroundResource(R.drawable.cpu_message);
                cpuMessage.setText(answer);
                cpuMessage.setGravity(Gravity.START);

                mInputMessage.setText("");
                TranslateAnimation userMessageAnimation =
                        new TranslateAnimation(mMessageLog.getWidth(),0,0,0);
                userMessageAnimation.setDuration(1000);
                userMessageAnimation.setAnimationListener(new Animation.AnimationListener() {
                    //클래스 이름이 선언되지 않은 내부 클래스를 '익명 내부 클래스'라고 한다.

                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // 메세지 크기 맞춘다
                        LinearLayout.LayoutParams cpuMessageLayout = new LinearLayout.
                                LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        cpuMessageLayout.gravity = Gravity.START ;
                        // 간격을 설정한다.
                        cpuMessageLayout.setMargins(marginSize,marginSize,marginSize,marginSize);
                        mMessageLog.addView(cpuMessage,0,cpuMessageLayout);
                        TranslateAnimation cpuAnimation =
                                new TranslateAnimation(-mMessageLog.getWidth(),0,0,0);
                        cpuAnimation.setDuration(1000);
                        cpuMessage.setAnimation(cpuAnimation);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                userMessage.startAnimation(userMessageAnimation);

            }


    }
}

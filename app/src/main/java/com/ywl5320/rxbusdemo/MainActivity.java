package com.ywl5320.rxbusdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ywl5320.rxbusdemo.RxBus.RxBus;
import com.ywl5320.rxbusdemo.RxBus.RxBusResult;

public class MainActivity extends AppCompatActivity {

    private Button btnsend;
    private Button btnsecond;
    private TextView mtvmsg;

    private RxBus rxBus = RxBus.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnsend = (Button) findViewById(R.id.btn_send);
        btnsecond = (Button) findViewById(R.id.btn_second);
        mtvmsg = (TextView) findViewById(R.id.tv_msg);

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxBus.post("first", new String("hello rxbus"));
            }
        });

        btnsecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        rxBus.toObserverableOnMainThread("first", new RxBusResult() {
            @Override
            public void onRxBusResult(Object o) {
                final String msg = (String)o;
                mtvmsg.setText("first收到消息;" + msg);
                Toast.makeText(MainActivity.this, "收到消息;" + msg, Toast.LENGTH_SHORT).show();
            }
        });

        rxBus.toObserverableOnMainThread("second", new RxBusResult() {
            @Override
            public void onRxBusResult(Object o) {
                String msg = (String)o;
                mtvmsg.setText("second收到消息;" + msg);
                Toast.makeText(MainActivity.this, "second收到消息;" + msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 退出时，释放rxbus
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        rxBus.release();
    }
}

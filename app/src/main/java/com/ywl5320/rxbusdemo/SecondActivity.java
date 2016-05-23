package com.ywl5320.rxbusdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ywl5320.rxbusdemo.RxBus.RxBus;

/**
 * Created by ywl on 2016/5/20.
 */
public class SecondActivity extends AppCompatActivity {

    private Button mbtnsend;
    private RxBus rxBus = RxBus.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        mbtnsend = (Button) findViewById(R.id.btn_send);
        mbtnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxBus.post("second", new String("第二个页面的数据"));
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        rxBus.removeObserverable("second");
    }
}

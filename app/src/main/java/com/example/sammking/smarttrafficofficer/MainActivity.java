package com.example.sammking.smarttrafficofficer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private CardView ReportCard,FineCard,StatusCard,RulesCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ReportCard=(CardView) findViewById(R.id.report_card);
        FineCard=(CardView) findViewById(R.id.fine_card);
        StatusCard=(CardView) findViewById(R.id.status_card);
        RulesCard=(CardView) findViewById(R.id.rules_card);

        ReportCard.setOnClickListener(this);
        FineCard.setOnClickListener(this);
        StatusCard.setOnClickListener(this);
        RulesCard.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Intent i ;
        switch (view.getId()){
            case R.id.report_card: i =new Intent(this,Report.class);startActivity(i);break;
            case R.id.fine_card:i=new Intent(this,FinePayment.class);startActivity(i);break;
            case R.id.status_card: i=new Intent(this,Status.class);startActivity(i);break;
            case R.id.rules_card: i =new Intent(this,Rules.class);startActivity(i);break;
            default:break;
        }





    }
}

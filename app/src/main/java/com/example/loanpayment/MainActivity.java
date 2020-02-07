package com.example.loanpayment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText costText;
    private EditText paymentText;
    private EditText aprText;
    private RadioButton loanButton;
    private RadioButton leaseButton;
    private SeekBar loanLength;
    private Button calcButton;
    private EditText monthlyText;
    private int length = 0;
    private TextView sliderText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        costText = findViewById(R.id.costText);
        paymentText = findViewById(R.id.paymentText);
        aprText = findViewById(R.id.aprText);
        loanButton = findViewById(R.id.loanButton);
        leaseButton = findViewById(R.id.leaseButton);
        loanLength = findViewById(R.id.loanLength);
        calcButton = findViewById(R.id.calcButton);
        monthlyText = findViewById(R.id.monthlyText);
        sliderText = findViewById(R.id.sliderText);
        loanLength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                length = loanLength.getProgress();
                sliderText.setText(loanLength.getProgress() + "");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void calculate(View v) {
        if (costText.getText().length() == 0 || paymentText.getText().length() == 0 || aprText.getText().length() == 0 ){
            Toast.makeText(this, "There is a missing field", Toast.LENGTH_LONG).show();
        } else {
            String input = costText.getText().toString();
            String dpay = paymentText.getText().toString();
            String apr = aprText.getText().toString();
            double total = Double.parseDouble(input);
            double down = Double.parseDouble(dpay);
            double interest = Double.parseDouble(apr);
            double monthly;
            interest = interest / 100;
            interest = interest / 12;
            if (leaseButton.isChecked()) {
                if(total == 0 || interest == 0){
                    Toast.makeText(this,"Cost/APR cannot be 0",Toast.LENGTH_LONG).show();
                }else {
                    length = 36;
                    loanLength.setProgress(36);
                    total = total / 3;
                    monthly = interest * (total - down) / (1 - Math.pow((1 + interest), -length));
                    monthlyText.setText(monthly + "");
                }
            } else {
                if(length == 0  || total == 0){
                    Toast.makeText(this,"Length/Cost/APR cannot be 0",Toast.LENGTH_LONG).show();
                }else {
                    monthly = interest * (total - down) / (1 - Math.pow((1 + interest), -length));
                    monthlyText.setText(monthly + "");
                }
            }
        }
    }
}


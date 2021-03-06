package com.example.vatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    //declaring variables
    private EditText prices, units;
    private TextView vat, exVat, inVat;
    ConstraintLayout mainView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializing variables
        prices = findViewById(R.id.etPrice);
        units= findViewById(R.id.etNumUnits);
        vat=findViewById(R.id.txVat);
        exVat=findViewById(R.id.txExVat);
        inVat=findViewById(R.id.btnInVat);
        mainView =findViewById(R.id.mainView);

    }

    public void onCalculate(View view) {
        try {
            double price = Double.parseDouble(prices.getText().toString());
            double unit = Double.parseDouble(units.getText().toString());

            if(price >0 && unit > 0){
                double exAmount = price * unit;
                double VAT = exAmount * 0.2;
                double inAmount = exAmount + VAT;

                if (((RadioButton) findViewById(R.id.rbtnInvat)). isChecked()){
                    VAT =exAmount*0.2;
                    inAmount=exAmount;
                    exAmount -=VAT;
                }
                vat.setText(NumberFormat.getInstance().format(VAT));
                exVat.setText(NumberFormat.getInstance().format(exAmount));
                inVat.setText(NumberFormat.getInstance().format(inAmount));
            }


        } catch (IllegalArgumentException ex){
            //Toast and snackbar used to inform user with massages
            //Toast.makeText(this,"",Toast.LENGTH_SHORT).show();
            Snackbar snackbar=Snackbar.make(mainView, ex.getMessage(), Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    public void onClear(View view) {
        prices.setText("");
        units.setText("");
        vat.setText("");
        exVat.setText("");
        inVat.setText("");
        //focus on the first field
        prices.requestFocus();
    }

}
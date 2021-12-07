package com.example.td3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button rollButton = (Button) findViewById(R.id.button1);

        rollButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int Min = 1;

                EditText maximum = (EditText) findViewById(R.id.maximum);
                String value = maximum.getText().toString();
                int Max;
                if(! value.contentEquals("")){
                    Log.i("My activity :", value);
                    Max = new Integer (value);
                }
                else{
                    maximum.setText("6", TextView.BufferType.EDITABLE);
                    Max = 6;
                }
                Log.i("My activity :", String.valueOf(Max));

                int nombreAleatoire = Min + (int)(Math.random() * ((Max - Min) + 1));
                TextView helloTextView = (TextView) findViewById(R.id.textView1);
                helloTextView.setText(Integer.toString(nombreAleatoire));

                int nombreAleatoire2 = Min + (int)(Math.random() * ((Max - Min) + 1));
                TextView helloTextView2 = (TextView) findViewById(R.id.textView2);
                helloTextView2.setText(Integer.toString(nombreAleatoire2));
            }
        });
    }
}
package com.example.jbt.simplecalculator;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textResult;
    private EditText editNum1, editNum2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResult = findViewById(R.id.textResult);
        editNum1 = findViewById(R.id.editNum1);
        editNum2 = findViewById(R.id.editNum2);

        // event clicks!
        findViewById(R.id.btnPlus).setOnClickListener(this);
        findViewById(R.id.btnMinus).setOnClickListener(this);
        findViewById(R.id.btnMult).setOnClickListener(this);
        findViewById(R.id.btnDiv).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        try {
            //int num1 = Integer.parseInt(editNum1.getText().toString());
            // read the TEXT in EditText editNum1 and parse it into a double!
            double num1 = Double.parseDouble(editNum1.getText().toString());
            // read the TEXT in EditText editNum2 and parse it into a double!
            double num2 = Double.parseDouble(editNum2.getText().toString());

            double result = 0;

            switch (v.getId()) {
                case R.id.btnPlus:
                    result = num1 + num2;
                    break;

                case R.id.btnMinus:
                    result = num1 - num2;
                    break;

                case R.id.btnMult:
                    result = num1 * num2;
                    break;

                case R.id.btnDiv:
                    result = num1 / num2;
                    break;
            }
            textResult.setText(String.format(getString(R.string.result), result));
        } catch (NumberFormatException ex) {
            textResult.setText(R.string.badinput);
        } catch (ArithmeticException ex) {
            textResult.setText(R.string.divzero);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                return true;
            case R.id.item2:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    final static  String key = "RESULT";

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        // Save custom values into the bundle

        savedInstanceState.putString(key, textResult.getText().toString());
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
        // Restore state members from saved instance
        String result = savedInstanceState.getString(key);

        TextView resultText =  findViewById(R.id.textResult);
        resultText.setText(result);
    }

}

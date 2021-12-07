package com.example.lab04_switcheskashitsin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Кашицын,393

    CheckBox[] productsOn = new CheckBox[4];
    EditText[] productsK = new EditText[4];
    EditText[] costs = new EditText[4];
    TextView[] results = new TextView[4];
    float[] price = new float[4];
    Switch diaSw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productsOn[0] = findViewById(R.id.product1);
        productsOn[1] = findViewById(R.id.product2);
        productsOn[2] = findViewById(R.id.product3);
        productsOn[3] = findViewById(R.id.product4);

        productsK[0] = findViewById(R.id.kolvo1);
        productsK[1] = findViewById(R.id.kolvo2);
        productsK[2] = findViewById(R.id.kolvo3);
        productsK[3] = findViewById(R.id.kolvo4);

        costs[0] = findViewById(R.id.buy1);
        costs[1] = findViewById(R.id.buy2);
        costs[2] = findViewById(R.id.buy3);
        costs[3] = findViewById(R.id.buy4);

        results[0] = findViewById(R.id.result1);
        results[1] = findViewById(R.id.result2);
        results[2] = findViewById(R.id.result3);
        results[3] = findViewById(R.id.result4);

        diaSw = findViewById(R.id.dialogSwitch);
    }
    //Кашицын,393
    public void calculate(View v)
    {
        float sum = 0f; float k;
        for (int i = 0; i < productsOn.length; i++)
        {
            if (productsOn[i].isChecked())
            {
                if (productsK[i].getText().toString().isEmpty())
                {
                    messageWrite("Введено пустое значение");
                    return;
                }
                else {
                    try {
                        k = Float.parseFloat(productsK[i].getText().toString());
                    }
                    catch (Exception e)
                    {
                        messageWrite("Введены некорректные данные");
                        return;
                    }
                    if (k >= 0) {
                        results[i].setText(String.format("%.2f", price[i] * k));
                        sum += price[i] * Float.parseFloat(productsK[i].getText().toString());
                    }
                    else
                    {
                        messageWrite("Количество продуктов не может быть отрицательным");
                        return;
                    }
                }
            }
        }
        messageWrite("Итог=" + String.format("%.2f", sum));
    }
    //Кашицын,393
    public  void  onChecked(View v)
    {
        for (int i=0;i<productsOn.length;i++)
        {
            if(productsOn[i].isChecked())
                productsK[i].setEnabled(true);
            else
                productsK[i].setEnabled(false);
        }
    }

    public void savePrices(View v)
    {
        float k = 0f;
        for (int i=0; i < productsOn.length; i++)
        {
            if (productsOn[i].getText().toString().isEmpty())
            {
                messageWrite("Введено пустое значение");
                return;
            }
            else {
                try {
                    k = Float.parseFloat(costs[i].getText().toString());
                } catch (Exception e) {
                    messageWrite("Введены некорректные данные");
                    return;
                }
                if (k > 0) {
                    price[i] = k;
                }
                else
                {
                    messageWrite("Цена продуктов не может быть отрицательным или равным 0");
                    return;
                }
            }
        }
    }

    void messageWrite(String res)
    {
        if (diaSw.isChecked())
            makeDialog(res);
        else
            Toast.makeText(getApplicationContext(),res, Toast.LENGTH_LONG).show();
    }

    void makeDialog(String str)//Кашицын,393
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dlg = builder.create();
        dlg.setIcon(R.drawable.iconshop);
        dlg.setTitle(str);
        dlg.show();
    }

}
package com.example.lenovo.androidjustjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int orderNum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public void incrementOrder(View v){
        orderNum = orderNum +1;
        displayQuantity(orderNum);
    }
    public void decrementOrder(View v){

        if(orderNum>0){
            orderNum = orderNum -1;
            displayQuantity(orderNum);
        }

    }

    public void submitOrder(View v){
        int orderPrice= calculatePrice();
        String summary =createOrderSummary();
        PrintTotal(summary);
    }

    private String createOrderSummary(){
        String summary;
        CheckBox ch = (CheckBox) findViewById(R.id.whipped);
        boolean hasWhipped = ch.isChecked();
        CheckBox cho = (CheckBox) findViewById(R.id.chocolate);
        boolean hasChocolate = cho.isChecked();
        EditText editTextName = (EditText) findViewById(R.id.name_text);
        String name = editTextName.getText().toString();



        if(hasWhipped == true && hasChocolate ==true){
            int orderPrice= calculatePrice();
            summary ="Name:" + name +
                    "\nQuantity "+ orderNum +
                    "\nwe want Chocolate ?? " + hasChocolate+
                    "\nWe want Whipped ?? " + hasWhipped+
                    "\nTotal $: "+ orderPrice +
                    "\nThank you!!";
            return summary ;
        }else{
            int orderPrice= calculatePrice();
            summary = "Name: " + name +
                    "\nQuantity "+ orderNum +
                    "\nwe want Chocolate ?? " + hasChocolate +
                    "\nwe want Whipped ?? " + hasWhipped +
                    "\nTotal $: "+ orderPrice +
                    "\nThank you!!";
            return summary ;
        }

    }

    private int calculatePrice(){
        int price =5 * orderNum;
        return price;
    }




    private void PrintTotal(String total){

        TextView t = (TextView) findViewById(R.id.total);
        t.setText(" " + total);
    }

    private void displayQuantity(int  numberOfCoffees){
        TextView t = (TextView) findViewById(R.id.orderNum);
        t.setText(""+numberOfCoffees);
    }
}

package com.example.lenovo.androidjustjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int orderNum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public void incrementOrder(View v){
        if(orderNum == 100){
            Toast.makeText(this, "you can't get more than 100 cups", Toast.LENGTH_SHORT).show();
        }
        if(orderNum < 100){
            orderNum = orderNum +1;
            displayQuantity(orderNum);
        }
    }
    public void decrementOrder(View v){

        if(orderNum == 1){
            Toast.makeText(this, "can not get less than 1 cup", Toast.LENGTH_SHORT).show();
        }
        if(orderNum>1){
            orderNum = orderNum -1;
            displayQuantity(orderNum);

        }

    }

    public void submitOrder(View v){
        CheckBox ch = (CheckBox) findViewById(R.id.whipped);
        boolean hasWhipped = ch.isChecked();
        CheckBox cho = (CheckBox) findViewById(R.id.chocolate);
        boolean hasChocolate = cho.isChecked();

        EditText editTextName = (EditText) findViewById(R.id.name_text);
        String name = editTextName.getText().toString();



        int orderPrice= calculatePrice(hasWhipped,hasChocolate);

        String priceMessage = createOrderSumary(name,orderPrice,hasWhipped,hasChocolate);


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, "e.josem91@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Cooffe");
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    private int calculatePrice( boolean addWhipperCream, boolean addChocolate){
        int price =5;


        if (addWhipperCream){
            price = price + 1;
        }

        if(addChocolate){
            price = price +2;
        }

       // if(addChocolate && addWhipperCream){
         //   price = price + 3;
        //}

        return price * orderNum;
    }


    private String createOrderSumary(String name, int price,boolean hasWhipper, boolean hasChocolate){
        String summary;

        summary = "\nName: " + name ;
        summary +="\nPrice $: " + price;
        summary +="\nWe have Whipper ?? " + hasWhipper;
        summary +="\nWe have Chocolate ?? " + hasChocolate;
        summary +="\n" + getString( R.string.thank_you);



        return summary;
    }






    private void displayQuantity(int  numberOfCoffees){
        TextView t = (TextView) findViewById(R.id.orderNum);
        t.setText(""+numberOfCoffees);
    }
}

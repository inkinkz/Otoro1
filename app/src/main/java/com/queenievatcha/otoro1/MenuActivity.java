package com.queenievatcha.otoro1;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    static String[] food = {"burger", "crab", "fish", "pizza", "shrimp"};
    static String[] description = {"This is a burger", "This is a crab", "This is a fish", "This is a pizza", "This is a shrimp"};
    static int[] priceForEach = {10, 20, 30, 40, 50};
    static int[] imgID = {R.drawable.burger, R.drawable.crab, R.drawable.fish, R.drawable.pizza, R.drawable.shrimp};
    static String[] butPlus = {"+", "+", "+", "+", "+"};
    static String[] butMinus = {"-", "-", "-", "-", "-"};
    static int[] amount = {0, 0, 0, 0, 0};
    static int[] totalPriceForEach = {0, 0, 0, 0, 0};
    static TextView shrimpText, burgerText, fishText, crabText, pizzaText, textViewPriceTest;
    static Button buttonCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setTitle("Menu");
        ListAdapter myAdapter = new CustomAdapter(this, food, description, priceForEach, imgID, butPlus, butMinus, amount);
        ListView myListView = (ListView) findViewById(R.id.listView);
        myListView.setAdapter(myAdapter);

        shrimpText = (TextView) findViewById(R.id.shrimpText);
        fishText = (TextView) findViewById(R.id.fishText);
        burgerText = (TextView) findViewById(R.id.burgerText);
        pizzaText = (TextView) findViewById(R.id.pizzaText);
        crabText = (TextView) findViewById(R.id.crabText);
        textViewPriceTest = findViewById(R.id.textViewPriceTest);

        buttonCart = findViewById(R.id.buttonCart);

        setAllText();
    }

    public static void setAllText() {
        burgerText.setText("Burger: " + amount[0]);
        crabText.setText("Crab: " + amount[1]);
        fishText.setText("Fish: " + amount[2]);
        pizzaText.setText("Pizza: " + amount[3]);
        shrimpText.setText("Shrimp: " + amount[4]);

        int totalItems = 0;
        for (int i = 0; i < amount.length; i++) {
            totalItems += amount[i];

        }
        int totalValue = 0;
        for (int i = 0; i < totalPriceForEach.length; i++) {
            totalValue += totalPriceForEach[i];
        }

        if(totalItems<0) totalItems=0;
        if(totalValue<0) totalValue=0;

        textViewPriceTest.setText("฿"+totalValue);
        buttonCart.setText("GO TO CART (" + totalItems + " items, ฿"+totalValue+")");
    }

    public void goToCart(View v) {
        Intent in = new Intent(MenuActivity.this, CartActivity.class);
        in.putExtra("list", amount);
        in.putExtra("imgID", imgID);
        in.putExtra("nameList", food);
        startActivity(in);
    }

    public static void addAmount(int position) {
        amount[position]++;
    }

    public static void minusAmount(int position) {
        amount[position]--;
        if(!(amount[position]>0)) amount[position]=0;
    }

    public static void addPrice(int position) {
        totalPriceForEach[position] += priceForEach[position];
    };
    public static void minusPrice(int position){
        totalPriceForEach[position] -= priceForEach[position];
        if(totalPriceForEach[position]<0) totalPriceForEach[position]=0;
    };

}

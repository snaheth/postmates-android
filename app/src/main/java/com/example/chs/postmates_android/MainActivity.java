package com.example.chs.postmates_android;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv = (TextView)findViewById(R.id.tv);
        PostmatesAPI api = new PostmatesAPI();

        /*

        MAKE A DELIVERY REQUEST:

        Delivery testDelivery = new Delivery("del_KSsT9zJdfV3P9k", "A box of gray kittens" ,
                "Kitten Warehouse" , "20 McAllister St, San Francisco, CA", "415-555-4242",
                "Ring the doorbell twice, and only delivery the package if a human answers.","Alice",
                "678 Green St, San Francisco, CA", "415-555-8484", "Tell the security guard " +
                "that you're here to see Alice.");
        testDelivery.setManifestRef("Order #690");
        testDelivery.setPickupBusinessName("Feline Enterprises, Inc.");
        testDelivery.setDropoffBusinessName("Alice's Cat Cafe");
        api.postDelivery(testDelivery, testCallback);
        */

        api.getDeliveryWithId("1", new TestCallback("GET ID DELIVERY", this, tv));
        api.postDeliveryQuote(new DeliveryQuote("20 McAllister St, San Francisco, CA" ,
                "101 Market St, San Francisco, CA"), new TestCallback("POST QUOTE", this, tv));
        api.getAllDeliveries(new TestCallback("GET ALL DELIVERIES", this, tv));
        api.cancelDeliveryWithId("1", new TestCallback("CANCEL DELIVERY", this, tv));
        api.returnDeliveryWithId("1", new TestCallback("RETURN DELIVERY", this, tv));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
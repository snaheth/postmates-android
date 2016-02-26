package com.example.chs.postmates_android;
import android.util.Log;

import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by snaheth on 10/10/15.
 */
public class PostmatesAPI {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String baseUrl = "https://api.postmates.com/v1/";

    private static final String customerId = "";
    private static final String authKey = "";

    private OkHttpClient client;

    public PostmatesAPI(){
        client = new OkHttpClient();
        client.setAuthenticator(new Authenticator() {
            @Override
            public Request authenticate(Proxy proxy, Response response) throws IOException {
                String basicAuth = Credentials.basic(authKey, "");
                return response.request().newBuilder().header("Authorization", basicAuth).build();
            }

            @Override
            public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
                return null;
            }
        });
    }


    //POST https://api.postmates.com/v1/customers/:customer_id/deliveries/:delivery_id/return
    public void returnDeliveryWithId(String id, Callback callback){
        String url = baseUrl + "customers/" + customerId + "/deliveries/" + id + "/return";
        try{
            post(url, null, callback);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    //POST https://api.postmates.com/v1/customers/:customer_id/deliveries/:delivery_id/cancel
    public void cancelDeliveryWithId(String id, Callback callback){
        String url = baseUrl + "customers/" + customerId + "/deliveries/" + id + "/cancel";
        try{
            post(url, null, callback);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    //GET https://api.postmates.com/v1/customers/:customer_id/deliveries/:delivery_id
    public void getDeliveryWithId(String id, Callback callback){
        String url = baseUrl + "customers/" + customerId + "/deliveries/" + id;
        try{
            get(url, callback);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    //POST https://api.postmates.com/v1/customers/:customer_id/delivery_quotes
    public void postDeliveryQuote(DeliveryQuote quote, Callback callback){
        String url = baseUrl + "customers/" + customerId + "/delivery_quotes";
        HashMap<String, String> map = new HashMap<>();
        map.put("dropoff_address", quote.getDropoffAddress());
        map.put("pickup_address" , quote.getPickupAddress());

        try{
            post(url, map, callback);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    //POST https://api.postmates.com/v1/customers/:customer_id/deliveries
    public void postDelivery(Delivery delivery, Callback callback){
       String url = baseUrl + "customers/" + customerId + "/deliveries";
       HashMap<String, String> map = delivery.hashMapRepresentation();
       try{
           post(url, map, callback);
       }
       catch (IOException e){
           e.printStackTrace();
       }
    }



    //GET https://api.postmates.com/v1/customers/:customer_id/deliveries
    public void getAllDeliveries(com.squareup.okhttp.Callback callback){
        String url = baseUrl + "customers/" + customerId + "/deliveries";
        try{
            get(url, callback);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public Call get(String url, com.squareup.okhttp.Callback callback) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }

    public Call post(String url, HashMap<String, String> map, com.squareup.okhttp.Callback callback) throws IOException {

        Request request;
        if(map != null){
            FormEncodingBuilder builder = new FormEncodingBuilder();
            Set<String> allKeys = map.keySet();
            Object[] keysArr = allKeys.toArray();
            for(int i = 0; i < map.size(); i++) {
                String key = (String) keysArr[i];
                builder.add(key, map.get(key));
            }
            RequestBody body = builder.build();
            request = new Request.Builder()
                    .url(url)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .post(body)
                    .build();
        }
        else{
            request = new Request.Builder()
                    .url(url)
                    .build();
        }

        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }
}
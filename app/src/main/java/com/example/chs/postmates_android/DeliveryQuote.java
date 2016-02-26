package com.example.chs.postmates_android;

/**
 * Created by snaheth on 10/10/15.
 */
public class DeliveryQuote {

    private String pickupAddress;
    private String dropoffAddress;

    public DeliveryQuote(String aPickup, String aDropoff){
        pickupAddress = aPickup;
        dropoffAddress = aDropoff;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public String getDropoffAddress() {
        return dropoffAddress;
    }
}

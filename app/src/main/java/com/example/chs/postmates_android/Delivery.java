package com.example.chs.postmates_android;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by snaheth on 10/10/15.
 */
public class Delivery {

    private String quoteId; //optional, but recommended
    private String manifest;
    private String manifestRef; //optional
    private String pickupName;
    private String pickupAdd;
    private String pickupPhone;
    private String pickupBusinessName; //optional
    private String pickupNotes;
    private String dropoffName;
    private String dropoffAddress;
    private String dropoffPhone;
    private String dropoffBusinessName; //optional
    private String dropoffNotes;

    public Delivery(String aQuoteId, String aManifest, String aPickupName,
                    String pickupAddress, String aPickupPhone, String aPickupNotes,
                    String aDropName, String dropAddress, String dropPhone, String dropNotes){

        quoteId = aQuoteId;
        manifest = aManifest;
        pickupName = aPickupName;
        pickupAdd = pickupAddress;
        pickupPhone = aPickupPhone;
        pickupNotes = aPickupNotes;
        dropoffName = aDropName;
        dropoffAddress = dropAddress;
        dropoffPhone = dropPhone;
        dropoffNotes = dropNotes;
    }


    //Assumes dev has entered in all mandatory parameters
    public HashMap<String, String> hashMapRepresentation(){
        HashMap<String, String> map = new HashMap<>();
        map.put("quote_id", quoteId);
        map.put("manifest", manifest);
        map.put("pickup_name", pickupName);
        map.put("pickup_address", pickupAdd);
        map.put("pickup_phone_number", pickupPhone);
        map.put("pickup_notes", pickupNotes);
        map.put("dropoff_name", dropoffName);
        map.put("dropoff_address", dropoffAddress);
        map.put("dropoff_phone_number", dropoffPhone);
        map.put("dropoff_notes", dropoffNotes);

        if(manifestRef != null)
            map.put("manifest_reference", manifestRef);

        if(pickupBusinessName != null)
            map.put("pickup_business_name", pickupBusinessName);

        if(dropoffBusinessName != null)
            map.put("dropoff_business_name", dropoffBusinessName);

        return map;
    }

    public void setDropoffBusinessName(String dropoffBizName) {
        this.dropoffBusinessName = dropoffBizName;
    }

    public void setManifestRef(String manifestRef) {
        this.manifestRef = manifestRef;
    }

    public void setPickupBusinessName(String pickupBusinessName) {
        this.pickupBusinessName = pickupBusinessName;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }
}

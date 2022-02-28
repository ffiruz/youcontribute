package com.ferdi.youcontribute.client;

import com.currencyfair.onesignal.OneSignal;
import com.currencyfair.onesignal.model.notification.Button;
import com.currencyfair.onesignal.model.notification.NotificationRequest;
import com.ferdi.youcontribute.config.ApplicationProperties;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class OneSignalClientTest {




    @Test
    public void it_7() throws Exception {



        NotificationRequest notificationRequest=new NotificationRequest();
        notificationRequest.setTemplateId("939a05ec-ff0b-47f5-a4a8-4ad45755bfb4");
        notificationRequest.setAnyWeb(true);
        notificationRequest.setAppId("3c431a6d-543f-4e13-a368-44329e3fff27");
        notificationRequest.setContents(new HashMap<String,String>() {{
            put("en", String.format("Would you like to solve following issue?\n%s","issueTitle"));
        }});
        Button acceptButtton= new Button();
        acceptButtton.setId("accept");
        acceptButtton.setText("Accept");
        acceptButtton.setUrl(String.format("http://localhost:4200/challenges/%d/accept",1));
        Button rejectButton= new Button();
        rejectButton.setId("reject");
        rejectButton.setText("Reject");
        rejectButton.setUrl(String.format("http://localhost:4200/challenges/%d/reject",1));
        notificationRequest.setWebButtons(Arrays.asList(acceptButtton,rejectButton));
        notificationRequest.setIncludedSegments(new ArrayList<String>(){{add("Subscribed Users");}});

        OneSignal.createNotification("NjgwZGU1YjktN2FiNy00Y2QyLTkzMTEtOGRkZWU4Y2FjZTEx",notificationRequest);
    }

}
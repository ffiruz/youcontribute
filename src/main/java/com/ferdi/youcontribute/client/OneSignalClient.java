package com.ferdi.youcontribute.client;

import com.currencyfair.onesignal.OneSignal;
import com.currencyfair.onesignal.model.notification.Button;
import com.currencyfair.onesignal.model.notification.NotificationRequest;
import com.ferdi.youcontribute.config.ApplicationProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.Notification;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Service
@AllArgsConstructor
public class OneSignalClient {

    private final ApplicationProperties applicationProperties;

    public void sendNotification(Integer challengeId,String issueTitle)  //OneSıgnal ile push notification işlemi için implemente ediyoruz.
    {
        ApplicationProperties.OneSignalProperties oneSignal=this.applicationProperties.getOneSignal();

        NotificationRequest notificationRequest=new NotificationRequest();
        notificationRequest.setTemplateId(oneSignal.getNewChallengeTemplateId());
        notificationRequest.setAnyWeb(true);
        notificationRequest.setAppId(oneSignal.getApiId());
        notificationRequest.setContents(new HashMap<String,String>() {{
                put("en", String.format("Would you like to solve following issue?\n%s",issueTitle));
        }});
        Button acceptButtton= new Button();
        acceptButtton.setId("accept");
        acceptButtton.setText("Accept");
        acceptButtton.setUrl(String.format("http://localhost:4200/challenges/%d/accept",challengeId));
        Button rejectButton= new Button();
        rejectButton.setId("reject");
        rejectButton.setText("Reject");
        rejectButton.setUrl(String.format("http://localhost:4200/challenges/%d/reject",challengeId));
        notificationRequest.setWebButtons(Arrays.asList(acceptButtton,rejectButton));
        notificationRequest.setIncludedSegments(new ArrayList<String>(){{add("Subscribed Users");}});

        OneSignal.createNotification(oneSignal.getApiAuthKey(),notificationRequest);

    }
}

package com.job.scrapper.schuedule;


import com.job.scrapper.constant.Constants;
import com.job.scrapper.service.EmailService;
import com.job.scrapper.service.Scrapper;
import com.job.scrapper.util.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Scheduler {
    @Autowired
    EmailService service;

    @Autowired
    Scrapper scrapper;

    @Scheduled(cron = "0 0/15 * * * ?")
    public void runWebScrapping(){
        log.info("Scheduler invoked");
        var data=scrapper.scrapArogyaSathi();
        String dataToSend= Utility.createData(data);

        var googleData=scrapper.scrapGoogleJob();
        var dataFromGoogle=Utility.createDataForGoogle(googleData);

        dataToSend=Utility.joinString(dataToSend,dataFromGoogle);
        service.sendSimpleEmail(Constants.emailAddressToSent,"Job Opening for you!!!", dataToSend);
        log.info("Job completed successfully");
    }
}

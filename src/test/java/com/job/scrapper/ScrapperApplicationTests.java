package com.job.scrapper;

import com.job.scrapper.schuedule.Scheduler;
import com.job.scrapper.service.Scrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ScrapperApplicationTests {

	@Autowired
	Scrapper scrapper;

	@Autowired
	Scheduler scheduler;
	@Test
	void contextLoads() {
		scrapper.scrapGoogleJob();
	}
@Test
	void runScheduler(){
		scheduler.runWebScrapping();
	}

}

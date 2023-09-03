package com.AllianzTechnologySE.Client.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.AllianzTechnologySE.Client.Entity.Employee;

@Service
public class KafkaMessageListener {

	Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);

	@KafkaListener(topics = "Employee-data", groupId = "germany")
	public void consumeEvents(Employee employee) {
		log.info("consumer consume the events {} ", employee.toString());
	}

//    @KafkaListener(topics = "Employee-data1",groupId = "germany")
//    public void consume2(String message) {
//        log.info("consumer2 consume the message {} ", message);
//    }
//
//    @KafkaListener(topics = "Employee-data1",groupId = "germany")
//    public void consume3(String message) {
//        log.info("consumer3 consume the message {} ", message);
//    }

}

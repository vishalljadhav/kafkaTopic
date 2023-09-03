package com.AllianzTechnologySE.Producer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.AllianzTechnologySE.Producer.Entity.Employee;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

	@Autowired
	private KafkaTemplate<String, Object> template;

	public void sendMessageToTopic(String message) {
		CompletableFuture<SendResult<String, Object>> future = template.send("Employee-data2", message);
		future.whenComplete((result, ex) -> {
			if (ex == null) {
				System.out.println(
						"Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			} else {
				System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
			}
		});

	}

	public void sendEventsToTopic(Employee employee) {
		try {
			CompletableFuture<SendResult<String, Object>> future = template.send("Employee-data", employee);
			future.whenComplete((result, ex) -> {
				if (ex == null) {
					System.out.println("Sent message=[" + employee.toString() + "] with offset=["
							+ result.getRecordMetadata().offset() + "]");
				} else {
					System.out.println(
							"Unable to send message=[" + employee.toString() + "] due to : " + ex.getMessage());
				}
			});

		} catch (Exception ex) {
			System.out.println("ERROR : " + ex.getMessage());
		}
	}
}
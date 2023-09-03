package com.AllianzTechnologySE.Producer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.AllianzTechnologySE.Producer.Entity.Employee;
import com.AllianzTechnologySE.Producer.Service.KafkaMessagePublisher;

@RestController
@RequestMapping("/producer-app")
public class EventController {

	@Autowired
	private KafkaMessagePublisher publisher;

	@GetMapping("/publish/{message}")
	public ResponseEntity<?> publishMessage(@PathVariable String message) {
		try {
			for (int i = 0; i <= 10; i++) {
				publisher.sendMessageToTopic(message + " : " + i);
			}
			return ResponseEntity.ok("message published successfully ..");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/publish")
	public void sendEvents(@RequestBody Employee employee) {
		publisher.sendEventsToTopic(employee);
	}

}
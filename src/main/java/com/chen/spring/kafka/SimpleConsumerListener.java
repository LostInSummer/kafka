package com.chen.spring.kafka;

import java.util.concurrent.CountDownLatch;

import org.springframework.kafka.annotation.KafkaListener;

public class SimpleConsumerListener {

	// private final static Logger logger =
	// LoggerFactory.getLogger(SimpleConsumerListener.class);
	private final CountDownLatch latch1 = new CountDownLatch(1);

	@KafkaListener(id = "foo", topics = "topic-test")
	public void listen(String records) {
		// do something here
		this.latch1.countDown();
		System.out.println(records);
	}

}

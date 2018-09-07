package com.chen.kafka.consumer;

import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

public class Consumer {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", "192.168.1.5:9092");
		properties.put("group.id", "test");
		properties.put("enable.auto.commit", "true");
		properties.put("auto.commit.interval.ms", "1000");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		final KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		consumer.subscribe(Arrays.asList("topic-test"), new ConsumerRebalanceListener() {
			public void onPartitionsRevoked(Collection<TopicPartition> collection) {
			}

			public void onPartitionsAssigned(Collection<TopicPartition> collection) {
				// 将偏移设置到最开始
				consumer.seekToBeginning(collection);
			}
		});

		System.out.println("Suceess");

		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records)
				System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
		}
	}

}

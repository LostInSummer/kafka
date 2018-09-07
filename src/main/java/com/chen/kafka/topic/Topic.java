package com.chen.kafka.topic;

import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

public class Topic {

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", "192.168.1.5:9092");
		AdminClient adminClient = AdminClient.create(properties);
		ArrayList<NewTopic> topics = new ArrayList<NewTopic>();
		NewTopic newTopic = new NewTopic("topic-test", 1, (short) 1);
		topics.add(newTopic);
		CreateTopicsResult result = adminClient.createTopics(topics);
		try {
			result.all().get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("Suceess");
	}

}

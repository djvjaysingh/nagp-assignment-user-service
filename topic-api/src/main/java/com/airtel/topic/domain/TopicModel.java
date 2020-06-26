package com.airtel.topic.domain;

/**
 * This class is main domain model which is used to displaying only relevant
 * data into frontend.
 * 
 * @author digvijayadhikari
 *
 */
public class TopicModel {

	private Integer topicId;

	private String topicName;

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

}


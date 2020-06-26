package com.airtel.topic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airtel.topic.domain.TopicModel;
import com.airtel.topic.service.TopicService;

/**
 * This Class is act as main Controller for the application.
 * 
 * @author digvijayadhikari
 */
@RestController
@RequestMapping("/topic")
public class TopicController {

	@Autowired
	private TopicService service;

	/**
	 * Request API call to find all Topic which are linked with at least {linkCount}
	 * Topics i.e. linkCount 3 Topics
	 * 
	 * @param linkCount
	 * @return modelList
	 */
	@RequestMapping("/topicLinked/{linkCount}")
	public List<TopicModel> findTopicsLinkedToOtherTopics(@PathVariable("linkCount") int linkCount) {
		List<TopicModel> modelList = service.findTopicsLinkedToOtherTopics(linkCount);
		return modelList;
	}

	/**
	 * Request API call to find all Topic which are having one child and one parent
	 * 
	 * @return modelList
	 */
	@RequestMapping("/topicWithOneParentChild")
	public List<TopicModel> findTopicsWithOneParentAndOneChild() {
		List<TopicModel> modelList = service.findTopicsWithOneParentAndOneChild();
		return modelList;
	}

	/**
	 * Request API call to find all Topic Topic which are placed at certain level in
	 * the hierarchy i.e. level = 3
	 * 
	 * @param level
	 * @return modelList
	 */
	@RequestMapping("/topicLevel/{level}")
	public List<TopicModel> findAllTopicsAtLevel(@PathVariable("level") int level) {
		List<TopicModel> modelList = service.findAllTopicsAtLevel(level);
		return modelList;
	}

}

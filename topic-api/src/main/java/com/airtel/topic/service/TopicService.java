package com.airtel.topic.service;

import java.util.List;

import com.airtel.topic.domain.TopicModel;
import com.airtel.topic.entity.TopicEntity;

/**
 * Service layer of the application which routes all the incoming requests.
 * 
 * @author digvijayadhikari
 *
 */
public interface TopicService {

	/**
	 * This will find all Topic which are linked with other topics and updating
	 * domain model
	 * 
	 * @param linkCount
	 * @return List<TopicModel>
	 */
	public List<TopicModel> findTopicsLinkedToOtherTopics(int linkCount);

	/**
	 * This will find all Topic which are having one child and one parent and
	 * updating domain model
	 * 
	 * @return List<TopicModel>
	 */
	public List<TopicModel> findTopicsWithOneParentAndOneChild();

	/**
	 * This will find all Topic Topic which are placed at certain level in the
	 * hierarchy and updating domain model
	 * 
	 * @param level
	 * @return List<TopicModel>
	 */
	public List<TopicModel> findAllTopicsAtLevel(int level);

}

/**
 * 
 */
package com.airtel.topic.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airtel.topic.domain.TopicModel;
import com.airtel.topic.entity.TopicEntity;
import com.airtel.topic.repository.TopicRepository;
import com.airtel.topic.service.TopicService;

/**
 * @author digvijayadhikari
 *
 */
@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicRepository repo;
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Implementation to find all Topic which are linked with other topics and
	 * updating domain model
	 * 
	 * @param linkCount
	 * @return modelList
	 */
	public List<TopicModel> findTopicsLinkedToOtherTopics(int linkCount) {
		List<TopicEntity> topicList = repo.findTopicsLinkedToOtherTopics(linkCount-1);
		List<TopicModel> modelList = updateModeldata(topicList);
		return modelList;
	}

	/**
	 * Implementation to find all Topic which are having one child and one parent
	 * and updating domain model
	 * 
	 * @return modelList
	 */
	public List<TopicModel> findTopicsWithOneParentAndOneChild() {
		List<TopicEntity> topicList = repo.findTopicsWithOneParentAndOneChild();
		List<TopicModel> modelList = updateModeldata(topicList);
		return modelList;
	}

	/**
	 * Implementation to find all Topic Topic which are placed at certain level in
	 * the hierarchy and updating domain model
	 * 
	 * @param level
	 * @return modelList
	 */
	public List<TopicModel> findAllTopicsAtLevel(int level) {
		List<TopicEntity> topicList = repo.findAllTopicsAtLevel(level);
		List<TopicModel> modelList = updateModeldata(topicList);
		return modelList;
	}

	/**
	 * This method is used to update Final Model Object which needs to display on
	 * frontend
	 * 
	 * @param topicList
	 * @return modelList
	 */
	private List<TopicModel> updateModeldata(List<TopicEntity> topicList) {
		List<TopicModel> modelList = new ArrayList<>();
		for (TopicEntity topicEntity : topicList) {
			TopicModel model = new TopicModel();
			model.setTopicId(topicEntity.getTopicId());
			model.setTopicName(topicEntity.getTopicName());
			modelList.add(model);
		}
		return modelList;
	}

}

/**
 * 
 */
package com.airtel.topic.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Parent Topic Entity class which is linked to "tbl_topic" table in database
 * and discriminated by Column name "topic_type" as value "P".
 * 
 * This class is also responsible to creating/updating another intermediate
 * joined table for ManyToMany relationship named as "tbl_topic_linking" with
 * Column names "topic_id" and "linked_topic_id"
 * 
 * @author digvijayadhikari
 */
@Entity
@Table(name = "tbl_topic")
public class TopicEntity {

	/**
	 * The primary key column. It is automatically generated.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "topic_id", updatable = false, nullable = false)
	private Integer topicId;

	/**
	 * Stores the name of the topic.
	 */
	@Column(name = "topic_name")
	private String topicName;
	
	/**
	 * The column to map to the parent topic. Many topics can have a single parent.
	 */
	@ManyToOne
	private TopicEntity parent;
	
	/**
	 * A One to Many relationship representing mapping between one topics to
	 * multiple subTopics.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
	@Column(name = "parent_topic_id")
	private List<TopicEntity> subTopics;

	/**
	 * A Many to Many relationship between topics
	 */
	@ManyToMany()
	@JoinTable(name = "tbl_topic_linking", joinColumns = {
			@JoinColumn(referencedColumnName = "topic_id", name = "topic_id") }, inverseJoinColumns = {
					@JoinColumn(referencedColumnName = "topic_id", name = "linked_topic_id") })
	private List<TopicEntity> linkedTopics;
	
	
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

	public List<TopicEntity> getLinkedTopics() {
		return linkedTopics;
	}

	public void setLinkedTopics(List<TopicEntity> linkedTopics) {
		this.linkedTopics = linkedTopics;
	}

	public TopicEntity getParent() {
		return parent;
	}

	public void setParent(TopicEntity parent) {
		this.parent = parent;
	}
	
	public List<TopicEntity> getSubTopics() {
		return subTopics;
	}

	public void setSubTopics(List<TopicEntity> subTopics) {
		this.subTopics = subTopics;
	}
	
}

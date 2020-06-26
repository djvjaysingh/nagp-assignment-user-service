package com.airtel.topic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.airtel.topic.entity.TopicEntity;

/**
 * Persistance interface to all the requests which is used to querying database
 * 
 * @author digvijayadhikari
 *
 */
@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, Integer> {

	/**
	 * Query to find all Topic which are linked with other topics
	 * 
	 * @param linkcount
	 * @return List<TopicEntity>
	 */
	/**
	 * @Query(value = "SELECT t1 FROM TopicEntity t1 JOIN t1.linkedTopics t2 " +
	 * "GROUP BY  t1.topicId having count(t1.topicId) >:linkcount")
	 */
	@Query("select t from TopicEntity t where size(t.linkedTopics) >:linkcount")
	List<TopicEntity> findTopicsLinkedToOtherTopics(@Param("linkcount") int linkcount);

	/**
	 * Query to find all Topic which are having one child and one parent
	 * 
	 * @return List<TopicEntity>
	 */
	@Query(value = "SELECT t FROM TopicEntity t where t.parent is not null and size(t.subTopics) = 1")
	List<TopicEntity> findTopicsWithOneParentAndOneChild();

	/**
	 * Query to find all Topic Topic which are placed at certain level in the
	 * hierarchy.
	 * 
	 * Limitation :- Recursive CTE Query is not supported by JPQL/HQL. It can be achieved using java by making various HQL queries in loop
	 * but this might give some performance issue, so using native query is the better approach to handle this.
	 * 
	 * @param level
	 * @return List<TopicEntity>
	 */
	@Query(value = "WITH RECURSIVE cte (topic_id, parent_topic_id, topic_name, LEVEL) AS \r\n" + "(\r\n"
			+ "SELECT t.topic_id, t.parent_topic_id, t.topic_name, 1 AS LEVEL\r\n" + "FROM TBL_TOPIC t \r\n"
			+ "WHERE t.parent_topic_id IS NULL\r\n" + "\r\n" + "UNION ALL\r\n" + "\r\n"
			+ "SELECT t1.topic_id, t1.parent_topic_id, t1.topic_name, (cte.LEVEL + 1) AS LEVEL\r\n" + "FROM TBL_TOPIC t1\r\n"
			+ "    JOIN cte ON t1.parent_topic_id = cte.topic_id    \r\n" + ")\r\n" + "\r\n"
			+ "SELECT topic_id, parent_topic_id, topic_name, LEVEL FROM cte WHERE LEVEL =:level", nativeQuery = true)
	List<TopicEntity> findAllTopicsAtLevel(@Param("level") int level);

}

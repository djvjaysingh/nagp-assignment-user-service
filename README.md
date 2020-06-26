# springboot-rest-springdata-jpa-example
Steps to execute the API :-
1)	Import the attached project in eclipse as maven project.
2)	Install MySQL database and Create a new Schema name in database as "topicdb" 
3)	Run application as SpringBootApplication.
4)	Insert the DML data records present in project location
	i.e. SampleProject/src/main/resources/dml_data.sql
5)	Hit the API Urls.

Solution:-
Table Design and sample data  :  

1)	tbl_topic
Topic_id	Topic_name					Parent_topic_id		topic_type
	1			Java						null				P
	2			C							null				P
	3			C++							null				P
	4			Collection					1					S
	5			Multithreading				1					S
	6			OOPS						1					S
	7			OOPS						3					S
	8			Executor Framework			5					S
	9			Future						8					S
	10			Core Java					null				P
	11			Algorithm					null				P
	12			JSP							null				P
	13			JPA							null				P
	14			Pointer						2					S
	15			Generics					4					S

2)	tbl_topic_linking (using Many-To-Many Mapping)

Topic_id	linked_Topic_id
	1			4
	1			5
	1			6
	2			null
	3			4
	3			7

Note :-  
Sample DML Data is placed at project location /SampleProject/src/main/resources/dml_data.sql


Expected Result :- Using Common Table Expression (CTE) Recursive Query
topic_id					topic_name			parent_topic_id			Level
1							Java				null					1
2							C					null					1
3							C++					null					1
4							Collection			1						2
5							Multithreading		1						2
6							OOPS				1						2
7							OOPS				3						1
8							Executor Framework	5						3
9							Future				8						4
10							Core Java			null					1
11							Algorithm			null					1
12							JSP					null					1
13							JPA					null					1
14							Pointer				2						2
15							Generics			4						3


How to call APIs :-
1. Create API find all Topic which are linked with at least three Topics

 API Url :- http://localhost:8080/topic/topicLinked/3

2. Create API find all Topic which are having one child and one parent

API Url :- http://localhost:8080/topic/topicWithOneParentChild

3. Create API find all Topic which are placed at 3rd level in the hierarchy
APU Url :- http://localhost:8080/topic/topicLevel/3

Assumptions:-
1) To find all Topic which are linked with at least three Topics
   - Using ManyToMany relationship, an intermediate joined table(tbl_topic_linking) will be created 
     and sample on that table is given based on the topic which are linked with other topics.
2) To Find all Topics which are having one child and one parent
 Scenario :- A --> B --> C --> D  => Since B and C containing one parent and one child, So both B and C will be fetched in query.
  
Limitation :- 
3) To find all Topic which are placed at 3rd level in the hierarchy
	-Recursive CTE Query is not supported by JPQL/HQL. It can be achieved using java by making various HQL queries in loop
	 but this might give some performance issue, so using native query is the better approach to handle this. 
	 
****************************************************************************************************************************************************
Native SQL Queries for all the APIs:-
****************************************************************************************************************************************************
1) SQL Query to find all Topic which are linked with at least three Topics
SELECT * 
FROM TBL_TOPIC t1 JOIN tbl_topic_linking t2 
ON  t1.topic_id = t2.topic_id 
GROUP BY  t1.topic_id having count(t1.topic_id) > 3-1;

2) SQL Query to find all Topic which are having one child and one parent
SELECT * FROM tbl_topic t WHERE t.parent_topic_id IS NOT NULL 
AND 
(SELECT count(st.parent_topic_id) FROM tbl_topic st WHERE t.topic_id=st.parent_topic_id)=1;

3) SQL Query to find all Topic which are placed at 3rd level in the hierarchy
WITH RECURSIVE cte (topic_id, parent_topic_id, topic_name, LEVEL) AS 
(
SELECT t.topic_id, t.parent_topic_id, t.topic_name, 1 AS LEVEL
FROM TBL_TOPIC t 
WHERE t.parent_topic_id IS NULL

UNION ALL

SELECT t1.topic_id, t1.parent_topic_id, t1.topic_name, (cte.LEVEL + 1) AS LEVEL
FROM TBL_TOPIC t1
    JOIN cte ON t1.parent_topic_id = cte.topic_id    
)

SELECT topic_id, parent_topic_id, topic_name, LEVEL FROM cte WHERE LEVEL =3; 


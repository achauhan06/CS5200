USE `cs5200_spring2020_chauhan`;
DROP procedure IF EXISTS `getUnansweredQuestions`;

DELIMITER $$
USE `cs5200_spring2020_chauhan`$$
CREATE PROCEDURE `cs5200_spring2020_chauhan`.`getUnansweredQuestions`()
BEGIN	 
	SELECT InnerQuery.ModuleName AS ModuleName, InnerQuery.Question AS Question,  
		max(InnerQuery.NumberOfAnswers) AS UnAnsweredQuestion 
	FROM
		(SELECT count(ques.id) as NumberOfAnswers, ques.text as Question, 
			 ques.module as ModuleName
			FROM `cs5200_spring2020_chauhan`.`widget` ans join 
			`cs5200_spring2020_chauhan`.`widget` ques on ans.question_id = ques.id 
			where ans.correct_ans = false group by ques.id
		) as InnerQuery 
		GROUP BY InnerQuery.ModuleName;
END;$$

DELIMITER ;
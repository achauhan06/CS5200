USE `cs5200_spring2020_chauhan`;
DROP procedure IF EXISTS `endorsedUsersForWeek`;

DELIMITER $$
USE `cs5200_spring2020_chauhan`$$
CREATE PROCEDURE `endorsedUsersForWeek` (IN startDate DATE, IN endDate DATE)
BEGIN
SELECT person.firstname as Name
	FROM widget ans JOIN widget ques on ans.question_id = ques.id 
    JOIN user ON user.id = ans.posted_by 
	JOIN person ON person.id = user.person_id
	WHERE ans.correct_ans = true AND ques.posted_on >= startDate 
    AND  ques.posted_on <= endDate GROUP BY user.id 
    HAVING count(ans.id) = (
        SELECT MAX(InnerQuery.CorrectAns) FROM 
			(
				SELECT count(ans.id) As CorrectAns, 
                user.id as UserId FROM widget ans
                JOIN widget ques on ans.question_id = ques.id 
                JOIN user on user.id = ans.posted_by 
				JOIN person on person.id = user.person_id
				WHERE ans.correct_ans = true 
                AND ques.posted_on >= startDate 
                AND  ques.posted_on <= endDate
				Group By user.id
			) AS InnerQuery
        ) LIMIT 5;

END$$

DELIMITER ;


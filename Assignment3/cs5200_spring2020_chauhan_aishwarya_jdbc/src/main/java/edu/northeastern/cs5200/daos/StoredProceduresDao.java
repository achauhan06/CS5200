package edu.northeastern.cs5200.daos;

public interface StoredProceduresDao {

    void getUnansweredQuestions();
    void endorsedUsersForWeek(java.sql.Date startDate, java.sql.Date endDate);
}

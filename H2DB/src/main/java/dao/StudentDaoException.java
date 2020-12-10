package dao;

public class StudentDaoException extends RuntimeException {
  public StudentDaoException(Exception ex) {
    super(ex);
  }
}

package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dao.StudentDao;
import model.*;
import sessionmanager.SessionManager;

import java.util.Optional;

public class DbServiceStudentImpl implements DbServiceStudent {
  private static Logger logger = LoggerFactory.getLogger(DbServiceStudentImpl.class);

  private final StudentDao studentDao;

  public DbServiceStudentImpl(StudentDao studentDao) {
    this.studentDao = studentDao;
  }

  @Override
  public long saveStudent( Student student) {
    try (SessionManager sessionManager = studentDao.getSessionManager()) {
      sessionManager.beginSession();
      try {
        long studentId = studentDao.saveStudent(student);

        sessionManager.commitSession();

        logger.info("Created Student with Id = {}", studentId);
        return studentId;
      } catch (Exception e) {
        logger.error(e.getMessage(), e);
        sessionManager.rollbackSession();
        throw new DbServiceException(e);
      }
    }
  }

  @Override
  public Optional<Student> getStudent(long id) {
    try (SessionManager sessionManager = studentDao.getSessionManager()) {
      sessionManager.beginSession();
      try {
        Optional<Student> studentOptional = studentDao.findById(id);

        logger.info("Got Student via getStudent: {}", studentOptional.orElse(null));
        return studentOptional;
      } catch (Exception e) {
        logger.error(e.getMessage(), e);
        sessionManager.rollbackSession();
      }
      return Optional.empty();
    }
  }
}

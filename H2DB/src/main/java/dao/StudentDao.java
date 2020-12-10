package dao;

import model.*;
import sessionmanager.SessionManager;

import java.util.Optional;

public interface StudentDao {
  Optional<Student> findById( long id);

  long saveStudent(Student student);

  SessionManager getSessionManager();

}

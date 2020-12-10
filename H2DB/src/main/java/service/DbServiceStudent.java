package service;

import model.*;

import java.util.Optional;

public interface DbServiceStudent {

  long saveStudent( Student student);

  Optional<Student> getStudent( long id);

}

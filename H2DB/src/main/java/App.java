import dao.StudentDao;
import hibernate.HibernateUtils;
import hibernate.dao.StudentDaoHibernate;
import hibernate.sessionmanager.SessionManagerHibernate;
import model.*;
import org.hibernate.SessionFactory;
import service.DbServiceStudent;
import service.DbServiceStudentImpl;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("Getting Started...");
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml", Student.class, Avatar.class, Email.class, Course.class );

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);

        StudentDao studentDao = new StudentDaoHibernate(sessionManager);

        DbServiceStudent dbServiceStudent = new DbServiceStudentImpl(studentDao);

        //Наполним таблицы данными
        for (int i = 0; i < 10; i++)
        {
            String name = String.format("Student%sName",i);
            Avatar avatar = new Avatar(String.format("Student%sPhotoUrl",i));
            List<Email> emails = new ArrayList<Email>();
            List<Course> courses = new ArrayList<Course>();

            emails.add(new Email(String.format("Student%sEmail",i)));

            if (i % 2 == 0) {
                courses.add(new Course("Четный курс"));
            } else {
                courses.add(new Course("Нечетный курс"));
            }

            dbServiceStudent.saveStudent(new Student(name, avatar, emails, courses));
        }

        sessionManager.beginSession();

        String courseName = "Четный курс";
        String queryString = String.format("select st.id, st.name from students st, student_courses inter, courses crs " +
                "where st.id = inter.student_id and inter.course_id = crs.id and crs.name = '%s'",courseName);

        //Кверим всех студентов с курса
        List<Object[]> resultSet = sessionManager.getCurrentSession().getHibernateSession().createSQLQuery(queryString).list();

        System.out.println("======= Список студентов с курса 'Четный курс' =======");
        for (Object[] row : resultSet)
        {
            System.out.println(row[1].toString());
        }
        System.out.println("======================================================");

        sessionManager.getCurrentSession().close();
    }
}

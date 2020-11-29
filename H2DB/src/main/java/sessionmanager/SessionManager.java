package sessionmanager;

import hibernate.sessionmanager.DatabaseSessionHibernate;

public interface SessionManager extends AutoCloseable {
    void beginSession();
    void commitSession();
    void rollbackSession();
    void close();

    DatabaseSessionHibernate getCurrentSession();
}

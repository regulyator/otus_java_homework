package ru.otus.data.hibernate.dao;

import org.hibernate.Session;
import ru.otus.data.core.dao.UserDao;
import ru.otus.data.core.model.User;
import ru.otus.data.core.sessionmanager.SessionManager;
import ru.otus.data.hibernate.exceptions.HibernateDaoException;
import ru.otus.data.hibernate.sessionmanager.DatabaseSessionHibernate;
import ru.otus.data.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.List;
import java.util.Optional;

public class UserDaoHibernate implements UserDao<User, Long> {

    private final SessionManagerHibernate sessionManager;

    public UserDaoHibernate(SessionManagerHibernate sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            return Optional.ofNullable(getSession().getHibernateSession().find(User.class, id));
        } catch (Exception ex) {
            throw new HibernateDaoException("Error when try findById!", ex);
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            return Optional.ofNullable(getSession()
                    .getHibernateSession()
                    .createQuery("Select u from User u where u.username = :pUsername", User.class)
                    .setParameter("pUsername", username)
                    .getSingleResult());
        } catch (Exception ex) {
            throw new HibernateDaoException("Error when try findByUsernameAdnPassword!", ex);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return getSession()
                    .getHibernateSession()
                    .createQuery("Select u from User u", User.class)
                    .getResultList();
        } catch (Exception ex) {
            throw new HibernateDaoException("Error when try getAllUsers!", ex);
        }
    }

    @Override
    public Long insert(User user) {
        try {
            Session hibernateSession = getSession().getHibernateSession();
            hibernateSession.persist(user);
            hibernateSession.flush();
            return user.getId();
        } catch (Exception ex) {
            throw new HibernateDaoException("Error when try insert user!", ex);
        }
    }

    @Override
    public void update(User user) {
        try {
            Session hibernateSession = getSession().getHibernateSession();
            hibernateSession.merge(user);
            hibernateSession.flush();
        } catch (Exception ex) {
            throw new HibernateDaoException("Error when try update user!", ex);
        }
    }

    @Override
    public void insertOrUpdate(User user) {
        try {
            Session hibernateSession = getSession().getHibernateSession();
            hibernateSession.saveOrUpdate(user);
            hibernateSession.flush();
        } catch (Exception ex) {
            throw new HibernateDaoException("Error when try insertOrUpdate user!", ex);
        }
    }

    @Override
    public SessionManager getSessionManager() {
        return this.sessionManager;
    }

    private DatabaseSessionHibernate getSession() {
        return sessionManager.getCurrentSession();
    }


}

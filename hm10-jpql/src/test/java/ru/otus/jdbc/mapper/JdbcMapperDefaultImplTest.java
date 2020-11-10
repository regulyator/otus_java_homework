package ru.otus.jdbc.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.core.util.ReflectionUtils;
import ru.otus.jdbc.DbExecutor;
import ru.otus.jdbc.mapper.testclasses.User;
import ru.otus.jdbc.sessionmanager.DatabaseSessionJdbc;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JdbcMapperDefaultImplTest {
    @Mock
    DbExecutor<User> dbExecutor;
    @Mock
    SessionManagerJdbc sessionManagerJdbc;
    @Mock
    DatabaseSessionJdbc databaseSessionJdbc;
    @Mock
    EntitySQLMetaData entitySQLMetaData;
    @Mock
    EntityClassMetaData<User> entityClassMetaData;
    @Mock
    ReflectionUtils reflectionUtils;

    @Test
    void testInsert() {
        when(sessionManagerJdbc.getCurrentSession()).thenReturn(databaseSessionJdbc);
        when(databaseSessionJdbc.getConnection()).thenReturn(null);
        try {
            when(dbExecutor.executeInsert(any(), any(), any())).thenReturn(12L);
        } catch (SQLException throwables) {
            fail();
        }

        JdbcMapper<User> userJdbcMapper = new JdbcMapperDefaultImpl<>(sessionManagerJdbc, dbExecutor, entitySQLMetaData, entityClassMetaData, reflectionUtils);
        userJdbcMapper.insert(new User(1, "dbServiceUser", 22));

        try {
            verify(dbExecutor, times(1)).executeInsert(any(), any(), any());
            verify(reflectionUtils, times(1)).setField(any(), any(), any());
        } catch (SQLException | IllegalAccessException ex) {
            fail();
        }
    }

    @Test
    void testUpdate() {
        when(sessionManagerJdbc.getCurrentSession()).thenReturn(databaseSessionJdbc);
        when(databaseSessionJdbc.getConnection()).thenReturn(null);

        JdbcMapper<User> userJdbcMapper = new JdbcMapperDefaultImpl<>(sessionManagerJdbc, dbExecutor, entitySQLMetaData, entityClassMetaData, reflectionUtils);
        userJdbcMapper.update(new User(1, "dbServiceUser", 22));

        try {
            verify(dbExecutor, times(1)).executeUpdate(any(), any(), any());
        } catch (SQLException ex) {
            fail();
        }
    }

    @Test
    void testInsertOrUpdate() {
        when(sessionManagerJdbc.getCurrentSession()).thenReturn(databaseSessionJdbc);
        when(databaseSessionJdbc.getConnection()).thenReturn(null);
        when(reflectionUtils.checkFieldIsNull(any(), any())).thenReturn(true).thenReturn(false);

        User newUser = new User();


        JdbcMapper<User> userJdbcMapper = new JdbcMapperDefaultImpl<>(sessionManagerJdbc, dbExecutor, entitySQLMetaData, entityClassMetaData, reflectionUtils);
        userJdbcMapper.insertOrUpdate(newUser);

        try {
            verify(dbExecutor, times(1)).executeInsert(any(), any(), any());
            verify(dbExecutor, times(0)).executeUpdate(any(), any(), any());
        } catch (SQLException ex) {
            fail();
        }

        userJdbcMapper.insertOrUpdate(newUser);

        try {
            verify(dbExecutor, times(1)).executeInsert(any(), any(), any());
            verify(dbExecutor, times(1)).executeUpdate(any(), any(), any());
        } catch (SQLException ex) {
            fail();
        }
    }

    @Test
    void testFindById() {
        when(sessionManagerJdbc.getCurrentSession()).thenReturn(databaseSessionJdbc);
        when(databaseSessionJdbc.getConnection()).thenReturn(null);

        JdbcMapper<User> userJdbcMapper = new JdbcMapperDefaultImpl<>(sessionManagerJdbc, dbExecutor, entitySQLMetaData, entityClassMetaData, reflectionUtils);
        userJdbcMapper.findById(1, User.class);

        try {
            verify(dbExecutor, times(1)).executeSelect(any(), any(), any(), any());
        } catch (SQLException ex) {
            fail();
        }
    }
}
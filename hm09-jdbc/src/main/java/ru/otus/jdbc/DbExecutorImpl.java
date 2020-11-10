package ru.otus.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author sergey
 * created on 03.02.19.
 */
public class DbExecutorImpl<T> implements DbExecutor<T> {

    @Override
    public long executeInsert(Connection connection, String sql, List<Object> params) throws SQLException {
        Savepoint savePoint = connection.setSavepoint("savePointName");
        try (var pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            fillParams(pst, params);
            pst.executeUpdate();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            connection.rollback(savePoint);
            throw ex;
        }
    }

    @Override
    public Optional<T> executeSelect(Connection connection, String sql, Object id,
                                     Function<ResultSet, T> rsHandler) throws SQLException {
        try (var pst = connection.prepareStatement(sql)) {
            pst.setObject(1, id);
            try (var rs = pst.executeQuery()) {
                return rs.next() ? Optional.ofNullable(rsHandler.apply(rs)) : Optional.empty(); // тут дергаем next() чтобы не делать это в хэндлере
            }
        }
    }

    @Override
    public void executeUpdate(Connection connection, String sql, List<Object> params) throws SQLException {
        Savepoint savePoint = connection.setSavepoint("savePointName");
        try (var pst = connection.prepareStatement(sql)) {
            fillParams(pst, params);
            pst.executeUpdate();
        } catch (SQLException ex) {
            connection.rollback(savePoint);
            throw ex;
        }
    }

    @Override
    public Collection<? extends T> executeSelectAll(Connection connection, String sql, Function<ResultSet, T> rsHandler) throws SQLException {
        Collection<T> result = new ArrayList<>();
        try (var pst = connection.prepareStatement(sql)) {
            try (var rs = pst.executeQuery()) {
                while (rs.next()) {
                    result.add(rsHandler.apply(rs));
                }
            }
        }
        return result;
    }

    private void fillParams(PreparedStatement pst, List<Object> params) throws SQLException {
        for (int idx = 0; idx < params.size(); idx++) {
            pst.setObject(idx + 1, params.get(idx));
        }
    }
}

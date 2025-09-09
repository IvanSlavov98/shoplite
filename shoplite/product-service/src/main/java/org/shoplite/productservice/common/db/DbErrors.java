package org.shoplite.productservice.common.db;

public final class DbErrors {

    private DbErrors() {}

    public static boolean isUniqueViolation(Throwable ex) {
        for (Throwable t = ex; t != null; t = t.getCause()) {
            if (t instanceof org.hibernate.exception.ConstraintViolationException cve) {
                var sqlEx = cve.getSQLException();
                if (sqlEx != null && "23505".equals(sqlEx.getSQLState())) return true;
            }
            if (t instanceof org.postgresql.util.PSQLException psql) {
                if ("23505".equals(psql.getSQLState())) return true;
            }
            if (t instanceof java.sql.SQLException sql) {
                if ("23505".equals(sql.getSQLState())) return true;
            }
        }
        return false;
    }

}

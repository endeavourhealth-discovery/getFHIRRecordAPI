package org.endeavourhealth.getFHIRRecordAPI.common.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import static java.sql.Types.*;

public class DALHelper {

    public static int getGeneratedKey(PreparedStatement stmt) {
        try (ResultSet rs = stmt.getGeneratedKeys()) {
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new DALException("Error fetching generated key", e);
        }
    }

    public static void setLong(PreparedStatement stmt, int i, Long value) {
        try {
            if (value == null)
                stmt.setNull(i, BIGINT);
            else
                stmt.setLong(i, value);
        } catch (SQLException e) {
            throw new DALException("Error setting LONG value", e);
        }
    }

    public static void setFloat(PreparedStatement stmt, int i, Float value) {
        try {
            if (value == null)
                stmt.setNull(i, FLOAT);
            else
                stmt.setFloat(i, value);
        } catch (SQLException e) {
            throw new DALException("Error setting FLOAT value", e);
        }
    }

    public static void setInt(PreparedStatement stmt, int i, Integer value) {
        try {
            if (value == null)
                stmt.setNull(i, INTEGER);
            else
                stmt.setInt(i, value);
        } catch (SQLException e) {
            throw new DALException("Error setting INT value", e);
        }
    }

    public static void setBool(PreparedStatement stmt, int i, Boolean value) {
        try {
            if (value == null)
                stmt.setNull(i, BOOLEAN);
            else
                stmt.setBoolean(i, value);
        } catch (SQLException e) {
            throw new DALException("Error setting BOOL value", e);
        }
    }

    public static void setByte(PreparedStatement stmt, int i, Byte value) {
        try {
            if (value == null)
                stmt.setNull(i, TINYINT);
            else
                stmt.setByte(i, value);
        } catch (SQLException e) {
            throw new DALException("Error setting BYTE value", e);
        }
    }

    public static void setString(PreparedStatement stmt, int i, String value) {
        try {
            if (value == null)
                stmt.setNull(i, VARCHAR);
            else
                stmt.setString(i, value);
        } catch (SQLException e) {
            throw new DALException("Error setting STRING value", e);
        }
    }

    public static void setTimestamp(PreparedStatement stmt, int i, Timestamp value) {
        try {
            if (value == null)
                stmt.setNull(i, TIMESTAMP);
            else
                stmt.setTimestamp(i, value);
        } catch (SQLException e) {
            throw new DALException("Error setting TIMESTAMP value", e);
        }
    }

    public static String inListParams(int size) {
        List<String> q = Collections.nCopies(size, "?");
        return String.join(",", q);
    }

    public static int setLongArray(PreparedStatement stmt, int i, List<Long> values) {
        try {
            for (Long value : values) {
                stmt.setLong(i++, value);
            }
            return i;
        } catch (SQLException e) {
            throw new DALException("Error setting LONG array", e);
        }
    }
}

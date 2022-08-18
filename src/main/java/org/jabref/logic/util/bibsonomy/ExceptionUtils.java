package org.jabref.logic.util.bibsonomy;

import java.sql.SQLException;

import org.bibsonomy.common.exceptions.QueryTimeoutException;
import org.slf4j.Logger;

/**
 * ToDo: better solution
 * Copied from org.bibsonomy.util.ExceptionUtils to work with Log4J
 * @see <a href="https://bitbucket.org/bibsonomy/bibsonomy/src/master/bibsonomy-common/src/main/java/org/bibsonomy/util/ExceptionUtils.java">ExceptionUtils</a>
 */
@SuppressWarnings("checkstyle:RequireEmptyLineBeforeBlockTagGroup")
public class ExceptionUtils {
    public static void logErrorAndThrowRuntimeException(final Logger log, final Exception ex, final String error) throws RuntimeException {
        log.error(error + " - throwing RuntimeException" + ((ex != null) ? ("\n" + ex.toString()) : ""), ex);
        if (ex != null && ex.getCause() != null && ex.getCause().getClass().equals(SQLException.class)) {
            final SQLException sqlException = ((SQLException) ex);
            log.error("SQL error code: " + sqlException.getErrorCode() + ", SQL state: " + sqlException.getSQLState());
        }
        throw new RuntimeException(error, ex);
    }

    public static void logErrorAndThrowQueryTimeoutException(final Logger log, final Exception ex, final String query) throws QueryTimeoutException {
        log.error("Query timeout for query: " + query);
        throw new QueryTimeoutException(ex, query);
    }
}

package ma.valueit.testingplatform.core.utils.database;

import ma.valueit.testingplatform.core.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yelansari on 5/6/18.
 */
public class MysqlDBUtils{
    private static final Logger LOGGER = LoggerFactory.getLogger(MysqlDBUtils.class);
    private static final String BACKUP_FAILED_MESSAGE = "Could not create the backup";
    private static final String BACKUP_SUCCESS_MESSAGE = "Backup created successfully";
    private static final String RESTORE_FAILED_MESSAGE = "Could not restore the backup";
    private static final String RESTORE_SUCCESS_MESSAGE = "Backup restored successfully";

    public static boolean backup(String database, String user, String password, String path) {
        String today = DateUtils.toSimpleDateFormat(DateUtils.getDate(), "yyyy-MM-dd HH:mm:ss");

        String fileName = "backup-" + today +".sql";

        String cmd = "mysqldump -u " + user + " -p" + password + " --add-drop-database -B " + database + " -r " + path + fileName;

        return executeCmd(BACKUP_SUCCESS_MESSAGE, BACKUP_FAILED_MESSAGE, cmd);
    }

    private static boolean executeCmd(String successMessage, String failedMessage, String ... cmd){
        Process runtimeProcess;

        try {
            runtimeProcess = Runtime.getRuntime().exec(cmd);

            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                LOGGER.info(successMessage);
                return true;
            } else {
                LOGGER.error(failedMessage);
            }
        } catch (Exception e) {
            LOGGER.error(failedMessage, e);
            e.printStackTrace();
        }

        return false;
    }

    public static boolean restore(String user, String password, String source) {
        String[] cmd = new String[]{"mysql", "--user=" + user, "--password=" + password, "-e", "source " + source};

        return executeCmd(RESTORE_SUCCESS_MESSAGE, RESTORE_FAILED_MESSAGE, cmd);
    }

    public static boolean restoreDB(String database, String user, String password, String source) {
        String[] cmd = new String[]{"mysql", "--user=" + user, "--password=" + password, database,"-e", "source " + source};

        return executeCmd(RESTORE_SUCCESS_MESSAGE, RESTORE_FAILED_MESSAGE, cmd);
    }
}

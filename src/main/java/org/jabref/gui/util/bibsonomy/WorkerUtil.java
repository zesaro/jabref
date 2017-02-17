package org.jabref.gui.util.bibsonomy;

import org.jabref.gui.worker.AbstractWorker;
import org.jabref.gui.worker.CallBack;

public class WorkerUtil {

    /**
     * @deprecated Duplicate code.
     * See {@link org.jabref.gui.exporter.SaveDatabaseAction}
     */
    @Deprecated
    public static void performAsynchronously(AbstractWorker worker) throws Throwable {
        //TODO: Duplicate code. See SaveDatabaseAction - zellerdev
        Runnable wrk = worker.getWorker();
        CallBack cb = worker.getCallBack();

        worker.init();

        wrk.run();

        cb.update();
    }
}

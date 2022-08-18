package org.jabref.model.util.bibsonomy;

public class WorkerUtil {

    public static void performAsynchronously(AbstractWorker worker) throws Throwable {
        Worker wrk = worker.getWorker();
        CallBack cb = worker.getCallBack();

        worker.init();

        wrk.run();

        cb.update();
    }
}

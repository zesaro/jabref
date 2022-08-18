package org.bibsonomy.plugin.jabref.util;

import org.bibsonomy.plugin.jabref.worker.AbstractPluginWorker;

import net.sf.jabref.gui.ImportInspectionDialog.CallBack;

/**
 * {@link PluginCallBack} is a util to stop execution of workers
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 *
 */
public class PluginCallBack implements CallBack {

	private AbstractPluginWorker worker;

	public void stopFetching() {

		if(worker != null)
			worker.stopFetching();

	}

	public PluginCallBack(AbstractPluginWorker pluginWorker) {

		this.worker = pluginWorker;
	}
}

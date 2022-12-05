package org.bibsonomy.plugin.jabref.worker;

import net.sf.jabref.AbstractWorker;
import net.sf.jabref.JabRefFrame;

import org.bibsonomy.model.logic.LogicInterface;
import org.bibsonomy.plugin.jabref.PluginProperties;
import org.bibsonomy.plugin.jabref.util.JabRefFileFactory;
import org.bibsonomy.rest.client.RestLogicFactory;
import org.bibsonomy.rest.client.util.FileFactory;

/**
 * {@link AbstractPluginWorker} is the base for all Workers which need to support stopping execution.
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 *
 */
public abstract class AbstractPluginWorker extends AbstractWorker {

	private boolean fetchNext = true;
	private final FileFactory fileFactory;
	protected final JabRefFrame jabRefFrame;

	public AbstractPluginWorker(JabRefFrame jabRefFrame) {
		this.jabRefFrame = jabRefFrame;
		this.fileFactory = new JabRefFileFactory(jabRefFrame);
	}

	public synchronized void stopFetching() {

		fetchNext = false;
	}

	protected synchronized boolean fetchNext() {

		return fetchNext;
	}

	protected LogicInterface getLogic() {
		return new RestLogicFactory(PluginProperties.getApiUrl(), RestLogicFactory.DEFAULT_RENDERING_FORMAT, RestLogicFactory.DEFAULT_CALLBACK_FACTORY, fileFactory).getLogicAccess(PluginProperties.getUsername(), PluginProperties.getApiKey());
	}
}

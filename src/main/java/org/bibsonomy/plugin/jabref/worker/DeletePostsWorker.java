package org.bibsonomy.plugin.jabref.worker;

import java.util.Arrays;

import net.sf.jabref.BibtexEntry;
import net.sf.jabref.JabRefFrame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bibsonomy.plugin.jabref.PluginProperties;

/**
 * Delete a Post from the service
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 *
 */
public class DeletePostsWorker extends AbstractPluginWorker {

	private static final Log LOG = LogFactory.getLog(DeletePostsWorker.class);

	private BibtexEntry[] entries;

	public void run() {
		for (BibtexEntry entry : entries) {
			final String intrahash = entry.getField("intrahash");
			final String username = entry.getField("username");
			if ((intrahash == null) || ("".equals(intrahash)) || ((intrahash != null) && !(PluginProperties.getUsername().equals(username)))) {
				continue;
			}

			try {
				getLogic().deletePosts(PluginProperties.getUsername(), Arrays.asList(intrahash));
				jabRefFrame.output("Deleting post " + intrahash);
				entry.clearField("intrahash");
			} catch (Exception ex) {
				LOG.error("Failed deleting post " + intrahash);
			}
		}
		jabRefFrame.output("Done.");
	}

	public DeletePostsWorker(JabRefFrame jabRefFrame, BibtexEntry[] entries) {
		super(jabRefFrame);
		this.entries = entries;
	}
}

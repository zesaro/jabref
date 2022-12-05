package org.bibsonomy.plugin.jabref.listener;

import java.awt.Component;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.jabref.BasePanel;

/**
 * {@link TabbedPaneChangeListener} add a ChangeListener to the Database.
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 *
 */
public class TabbedPaneChangeListener implements ChangeListener {
	private static final Log log = LogFactory.getLog(TabbedPaneChangeListener.class);

	private PluginDataBaseChangeListener databaseChangeListener;

	public void stateChanged(ChangeEvent e) {
		if(e.getSource() instanceof JTabbedPane) {
			JTabbedPane pane = (JTabbedPane) e.getSource();
			Component[] components = pane.getComponents();
			for (Component c : components) {
				BasePanel bp = (BasePanel) c;
				if (bp.database() != null) {
					bp.database().addDatabaseChangeListener(databaseChangeListener);
				} else {
					log.warn("found tab-component without database");
				}
			}
			if (components.length == 0) {
				log.info("pane has no tab-components");
			}
		}
	}

	public TabbedPaneChangeListener(PluginDataBaseChangeListener l) {
		this.databaseChangeListener = l;
	}

}

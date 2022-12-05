package org.bibsonomy.plugin.jabref;

import javax.swing.JMenuItem;

import org.bibsonomy.plugin.jabref.gui.EntryEditorTabExtender;
import org.bibsonomy.plugin.jabref.gui.PluginMenuItem;
import org.bibsonomy.plugin.jabref.gui.PluginToolBarExtender;
import org.bibsonomy.plugin.jabref.listener.PluginDataBaseChangeListener;
import org.bibsonomy.plugin.jabref.listener.TabbedPaneChangeListener;

import net.sf.jabref.JabRefFrame;
import net.sf.jabref.SidePaneComponent;
import net.sf.jabref.SidePaneManager;
import net.sf.jabref.plugin.SidePanePlugin;
/**
 * PublicationSharingSidePanelPlugin - This is the entry point of the plugin.
 * It defines the MenuItem, the ShortcutKey and the SidePaneComponent.
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 *
 */
public class PublicationSharingSidePanePlugin implements SidePanePlugin {

	/**
	 * The plugins side pane component
	 */
	private PluginSidePaneComponent sidePaneComponent;

	/**
	 * The plugins menu
	 */
	public JMenuItem getMenuItem() {

		return new PluginMenuItem(sidePaneComponent);
	}

	/**
	 * the plugins shortcut key. None defined.
	 */
	public String getShortcutKey() {

		return null;
	}

	/**
	 * the plugins side pane component
	 */
	public SidePaneComponent getSidePaneComponent() {

		return sidePaneComponent;
	}

	/**
	 * This method will be called from JabRef to initialize the plugin.
	 */
	public void init(JabRefFrame jabRefFrame, SidePaneManager manager) {

		// create a ChangeListener to react on newly added entries.
		PluginDataBaseChangeListener l = new PluginDataBaseChangeListener(jabRefFrame);

		// set a ChangeListener of the Tabbed Pane which registers the databasechangelistener to all database tabs that are added later
		jabRefFrame.getTabbedPane().addChangeListener(new TabbedPaneChangeListener(l));
		// ...but maybe we were too late: Tabs are created by another (swing)thread so the initial tab change event after tab(and database) creation may be over already.
		// Therefore add the listener to the database of the current tab if it is already present.
		if (jabRefFrame.basePanel() != null && jabRefFrame.basePanel().database() != null) {
			jabRefFrame.basePanel().database().addDatabaseChangeListener(l);
		}

		this.sidePaneComponent = new PluginSidePaneComponent(manager, jabRefFrame);
		PluginToolBarExtender.extend(jabRefFrame, sidePaneComponent);
		EntryEditorTabExtender.extend();
	}

}

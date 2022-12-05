package org.bibsonomy.plugin.jabref.gui;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JToolBar;


import org.bibsonomy.plugin.jabref.PluginGlobals;
import org.bibsonomy.plugin.jabref.PluginSidePaneComponent;
import org.bibsonomy.plugin.jabref.action.DeleteSelectedEntriesAction;
import org.bibsonomy.plugin.jabref.action.ExportSelectedEntriesAction;
import org.bibsonomy.plugin.jabref.action.ToggleSidePaneComponentAction;

import net.sf.jabref.JabRefFrame;

/**
 * {@link PluginToolBarExtender} add the service specific buttons to the tool bar
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 *
 */
public class PluginToolBarExtender {

	public static void extend(JabRefFrame jabRefFrame, PluginSidePaneComponent sidePaneComponent) {

		for(Component rp : jabRefFrame.getComponents()) {

			if(rp instanceof JRootPane) {

				for(Component lp : ((JRootPane) rp).getComponents()) {

					if(lp instanceof JLayeredPane) {

						for(Component p : ((JLayeredPane)lp).getComponents()) {

							if(p instanceof JPanel) {

								for(Component tb : ((JPanel) p).getComponents()) {

									if(tb instanceof JToolBar) {

										JToolBar toolBar = (JToolBar) tb;

										JButton searchEntries = new JButton(new ToggleSidePaneComponentAction(sidePaneComponent));
										searchEntries.setText(PluginGlobals.PLUGIN_NAME);
										toolBar.add(searchEntries, 5);

										JButton exportEntries = new JButton(new ExportSelectedEntriesAction(jabRefFrame));
										exportEntries.setText(null);
										toolBar.add(exportEntries, 6);

										JButton deleteEntries = new JButton(new DeleteSelectedEntriesAction(jabRefFrame));
										deleteEntries.setText(null);
										toolBar.add(deleteEntries, 7);

									}
								}
							}
						}
					}
				}
			}
		}
	}
}

/*******************************************************************************
 * Copyright (c) 2018 Tobias Ortmayr.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 * 	Tobias Ortmayr - initial API and implementation
 ******************************************************************************/
package at.tortmayr.chillisp.api;

import at.tortmayr.chillisp.api.actions.CollapseExpandAction;
import at.tortmayr.chillisp.api.actions.CollapseExpandAllAction;

public interface IGraphicalModelExpansionListener {
	void expansionChanged(CollapseExpandAction action, IGraphicalLanguageServer server);
	void expansionChanged(CollapseExpandAllAction action, IGraphicalLanguageServer server);
	
	public static class NullImpl implements IGraphicalModelExpansionListener{

		@Override
		public void expansionChanged(CollapseExpandAction action, IGraphicalLanguageServer server) {
		}

		@Override
		public void expansionChanged(CollapseExpandAllAction action, IGraphicalLanguageServer server) {	
		}

		
	}
}

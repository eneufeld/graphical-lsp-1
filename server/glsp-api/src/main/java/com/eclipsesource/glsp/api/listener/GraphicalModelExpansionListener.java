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
package com.eclipsesource.glsp.api.listener;

import com.eclipsesource.glsp.api.action.kind.CollapseExpandAction;
import com.eclipsesource.glsp.api.action.kind.CollapseExpandAllAction;

public interface GraphicalModelExpansionListener {
	void expansionChanged(CollapseExpandAction action);
	void expansionChanged(CollapseExpandAllAction action);
	
	public static class NullImpl implements GraphicalModelExpansionListener{

		@Override
		public void expansionChanged(CollapseExpandAction action) {
		}

		@Override
		public void expansionChanged(CollapseExpandAllAction action) {	
		}

		
	}
}
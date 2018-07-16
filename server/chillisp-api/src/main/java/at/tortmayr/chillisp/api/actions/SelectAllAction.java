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
package at.tortmayr.chillisp.api.actions;

import at.tortmayr.chillisp.api.ActionRegistry;

public class SelectAllAction extends Action {
	private boolean select;

	public SelectAllAction() {
		super(ActionRegistry.Kind.SELECT_ALL_ACTION);
	}

	public SelectAllAction(boolean select) {
		this();
		this.select = select;
	}

	public boolean isSelect() {
		return select;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (select ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SelectAllAction other = (SelectAllAction) obj;
		if (select != other.select)
			return false;
		return true;
	}

}

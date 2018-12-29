/*******************************************************************************
 * Copyright (c) 2018 EclipseSource Services GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *   
 * Contributors:
 * 	Tobias Ortmayr - initial API and implementation
 ******************************************************************************/
package com.eclipsesource.glsp.api.model;

import org.eclipse.sprotty.SEdge;

import com.eclipsesource.glsp.api.types.EdgeTypeHint;

public class EdgeTypeConfiguration extends ModelTypeConfiguration{

	public EdgeTypeConfiguration() {
		super();
	}

	public EdgeTypeConfiguration(String elementTypeId, Class<? extends SEdge> javaClassRepresentation,
			EdgeTypeHint edgeTypeHint) {
		super(elementTypeId, javaClassRepresentation, edgeTypeHint);
		
	}
}

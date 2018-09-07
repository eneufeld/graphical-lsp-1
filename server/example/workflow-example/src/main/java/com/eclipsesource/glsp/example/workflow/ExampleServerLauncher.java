/*******************************************************************************
 * Copyright (c) 2018 EclipseSource Services GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 * 	Tobias Ortmayr - initial API and implementation
 ******************************************************************************/
package com.eclipsesource.glsp.example.workflow;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.eclipsesource.glsp.server.ServerLauncher;

public class ExampleServerLauncher {

	public static void main(String[] args) {
		int port = Integer.MIN_VALUE;
		String host = null;
		if (args.length == 2) {
			host = args[0];
			port = Integer.parseInt(args[1]);
		}

		if (host == null && port == Integer.MIN_VALUE) {
			host = "localhost";
			port = 5007;
		}

		ServerLauncher launcher = new ServerLauncher(host, port, new WorkflowServerRuntimeModule());
		try {
			launcher.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

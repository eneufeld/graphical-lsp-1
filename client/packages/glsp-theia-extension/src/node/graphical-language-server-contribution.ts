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
import { BaseLanguageServerContribution, LanguageServerContribution, IConnection } from '@theia/languages/lib/node';
import { injectable } from 'inversify';
import { MaybePromise } from '@theia/core';
export const GraphicalLanguageServerContribution = Symbol('GraphicalLanguageServerContribution')
import * as net from "net"
import * as cp from 'child_process';
import { createSocketConnection } from 'vscode-ws-jsonrpc/lib/server';
import { RawProcess } from '@theia/process/lib/node/raw-process';
export interface GraphicalLanguageServerContribution extends LanguageServerContribution {
    createConnection(outSocket: MaybePromise<net.Socket>, inSocket: MaybePromise<net.Socket>,
        process: RawProcess): Promise<IConnection>
}

@injectable()
export abstract class BaseGraphicalLanguageServerContribution extends BaseLanguageServerContribution implements GraphicalLanguageServerContribution {

    async createConnection(outSocket: MaybePromise<net.Socket>, inSocket: MaybePromise<net.Socket>,
        process: RawProcess): Promise<IConnection> {



        const [outSock, inSock] = await Promise.all([outSocket, inSocket]);
        return createSocketConnection(outSock, inSock, () => {
            process.kill()
            if (!outSock.destroyed) {
                outSock.destroy()
            }
            if (!inSock.destroyed) {
                inSock.destroy()
            }
        });
    }

}
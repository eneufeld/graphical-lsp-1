/*******************************************************************************
 * Copyright (c) 2018 EclipseSource Services GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 * 	Tobias Ortmayr - initial API and implementation
 ******************************************************************************/
import { IConnection } from "@theia/languages/lib/node";
import { BaseGraphicalLanguageServerContribution } from 'glsp-theia-extension/lib/node';
import { injectable } from "inversify";
import * as path from 'path';
import * as net from 'net';
import { createSocketConnection, createProcessSocketConnection } from 'vscode-ws-jsonrpc/lib/server';
import { WorkflowLanguage } from "../common/workflow-language";
import * as getPort from "get-port"
import PromiseSocket from "promise-socket"

function getSocketPort(): number | undefined {
    let arg = process.argv.filter(arg => arg.startsWith('--WORKFLOW_GLSP='))[0]
    if (!arg) {
        return undefined
    } else {
        return Number.parseInt(arg.substring('--WORKFLOW_GLSP='.length), 10)
    }
}
@injectable()
export class WorkflowGLServerContribution extends BaseGraphicalLanguageServerContribution {
    readonly id = WorkflowLanguage.Id
    readonly name = WorkflowLanguage.Name

    readonly description = {
        id: 'workflow',
        name: 'Workflow',
        documentSelector: ['workflow'],
        fileEvents: [
            '**/*.workflow'
        ]
    }

    start(clientConnection: IConnection): void {
        let debugPort = getSocketPort();

        if (debugPort) {
            const socket = new net.Socket()
            console.log('[WorkflowGL] Create Debug Socket Connection at ' + debugPort + '.')
            const serverConnection = createSocketConnection(socket, socket, () => {
                socket.destroy()
            });
            this.forward(clientConnection, serverConnection)
            socket.connect(debugPort)
        } else {
            getPort().then(port => {
                const command = 'java';
                const jarPath = path.resolve(__dirname, '../../build/workflow-example-0.0.1-SNAPSHOT-glsp.jar');
                const host = "localhost"
                const args: string[] = [];
                args.push(
                    '-jar', jarPath
                    , host, port.toString()
                );
                const process = this.spawnProcess(command, args)
                const socket = new net.Socket()
                const promiseSocket = new PromiseSocket(socket)

                console.log('[WorkflowGL] Create Socket Connection at ' + port + '.')
                this.createConnection(socket, socket, process).then(serverConnection => {
                    this.forward(clientConnection, serverConnection)
                    promiseSocket.connect(port).then(() => console.log('[WorkflowGL] Client Connection Started.'))
                })
            })

        }

    }
}
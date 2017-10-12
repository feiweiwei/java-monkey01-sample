package com.monkey01.reactor;

/**
 * @Author: feiweiwei
 * @Description:
 * @Created Date: 14:25 17/10/12.
 * @Modify by:
 */
public class Server {
	Selector selector = new Selector();
	Dispatcher eventLooper = new Dispatcher(selector);
	Acceptor acceptor;

	Server(int port) {
		acceptor = new Acceptor(selector, port);
	}

	public void start() {
		eventLooper.registEventHandler(EventType.ACCEPT, new AcceptEventHandler(selector));
		new Thread(acceptor, "Acceptor-" + acceptor.getPort()).start();
		eventLooper.handleEvents();
	}
}

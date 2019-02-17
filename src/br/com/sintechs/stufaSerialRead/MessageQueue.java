package br.com.sintechs.stufaSerialRead;

import java.util.ArrayList;
import java.util.List;

public class MessageQueue {
	private  ArrayList<String> msgQueue;
//	private  List<Command> commands;
	
	public MessageQueue(ArrayList<String> arrayList) {
		msgQueue = arrayList;
	}
	
	public  synchronized void add(List<String> readLines) {
		
		for(String cmd : readLines) {
			if(cmd.isEmpty())
				continue;
			msgQueue.add(cmd);
		}
		this.notify();
	}
	
	public  synchronized String getMessage() {
		if(msgQueue.size() > 0 )
			return msgQueue.remove(0);
		else
			return null;
	}
	
	public synchronized List<String> getMessageQueue(){
		return msgQueue;
	}
}

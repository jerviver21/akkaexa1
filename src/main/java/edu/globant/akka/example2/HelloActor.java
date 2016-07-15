package edu.globant.akka.example2;

import akka.actor.UntypedActor;

public class HelloActor extends UntypedActor{

	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof HelloMessage){
			System.out.println("The message is: "+((HelloMessage)msg).getMessage());
			getContext().stop(getSelf());
		}else{
			unhandled(msg);
		}
	}

}

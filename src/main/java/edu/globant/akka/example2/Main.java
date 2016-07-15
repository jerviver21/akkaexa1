package edu.globant.akka.example2;

import edu.globant.akka.config.Terminator;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {

	public static void main(String[] args) {
		ActorSystem as = ActorSystem.create("System1");
		ActorRef ref = as.actorOf(Props.create(HelloActor.class), "Actor1");
		ref.tell(new HelloMessage("Hello akka"), null);
		as.actorOf(Props.create(Terminator.class, ref), "terminator");
	    

	}

}

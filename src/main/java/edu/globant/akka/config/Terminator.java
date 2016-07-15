package edu.globant.akka.config;

import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Terminator extends UntypedActor {

	private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private final ActorRef ref;
	
	public Terminator(ActorRef ref) {
	  this.ref = ref;
	  getContext().watch(ref);
	}
	
	@Override
	public void onReceive(Object msg) {
	  if (msg instanceof Terminated) {
	    log.info("{} has terminated, shutting down system", ref.path());
	    System.out.println(" Terminando Sistema... ");
        getContext().system().terminate();
      } else {
        unhandled(msg);
      }
    }
	
}

package edu.globant.akka.example3;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;

public class PrimeMaster extends UntypedActor{
	private Router router;
	private final ActorRef workerRouter;
	private final ActorRef listener;
	
	private int numberOfWorkers;
	private int numberOfResults;
	
	private Result finalResults = new Result();
	
	public PrimeMaster(final int numberOfWorkers, ActorRef listener){
		this.numberOfWorkers = numberOfWorkers;
		this.listener = listener;
		
		List<Routee> routees = new ArrayList<>();
		workerRouter = getContext().actorOf(Props.create(PrimeWorker.class), "workerRouter");
		getContext().watch(workerRouter);
		routees.add(new ActorRefRoutee(workerRouter));
		router = new Router(new RoundRobinRoutingLogic(), routees);
		
	}

	@Override
	public void onReceive(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}

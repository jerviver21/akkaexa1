package edu.globant.akka.example3;

import akka.actor.UntypedActor;

public class PrimeWorker extends UntypedActor{
	
	private long startNumber;
	private long endNumber;

	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof NumberRangeMessage){
			NumberRangeMessage nrm = (NumberRangeMessage)msg;
			System.out.println("Number Range FROM: "+nrm.getStartNumber()+" to "+nrm.getEndNumber());
			
			// Iterate over the range, compute primes, and return the list of numbers that are prime
            Result result = new Result();
            for( long l = nrm.getStartNumber(); l <= nrm.getEndNumber(); l++ ){
                if( isPrime( l ) )
                    result.getResults().add( l );
            }

            // Send a notification back to the sender
            getSender().tell( result, getSelf() );
		}else{
			unhandled(msg);
		}
	}
	
	private boolean isPrime( long n )
    {
        if( n == 1 || n == 2 || n == 3 )
            return true;

        // Is n an even number?
        if( n % 2 == 0 )
            return false;

        //if not, then just check the odds
        for( long i=3; i*i<=n; i+=2 ){
            if( n % i == 0)
                return false;
        }
        return true;
    }

	public long getStartNumber() {
		return startNumber;
	}

	public long getEndNumber() {
		return endNumber;
	}

}

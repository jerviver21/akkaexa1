package edu.globant.akka.example3;

import java.time.LocalTime;

import akka.actor.UntypedActor;

public class PrimeListener extends UntypedActor
{
    @Override
    public void onReceive( Object message ) throws Exception
    {
        if( message instanceof Result )
        {
            Result result = ( Result )message;
        	System.out.println("Real End Date:"+LocalTime.now());
            System.out.print( result.getResults().size() + "  Primos - First: "+result.getResults().get(0)+", " );

            // Exit
            getContext().stop( getSelf() );
        }
        else
        {
            unhandled( message );
        }
    }
}

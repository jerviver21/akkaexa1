package edu.globant.akka.example3;


import java.time.LocalTime;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class PrimeCalculator
{
    public void calculate( long startNumber, long endNumber )
    {
        // Create our ActorSystem, which owns and configures the classes
        ActorSystem actorSystem = ActorSystem.create( "primeCalculator" );

        // Create our listener
        final ActorRef primeListener = actorSystem.actorOf( Props.create( PrimeListener.class ), "primeListener" );

        ActorRef primeMaster = actorSystem.actorOf( Props.create( PrimeMaster.class,  10, primeListener), "primeMaster" );

        // Start the calculation
        primeMaster.tell( new NumberRangeMessage( startNumber, endNumber ), null );
    }

    
    //Ver Primemaster para ver las diferentes formas de ejecutar la concurrencia, de cualquier forma se obtiene una mejora sustancial en performance.
    public static void main( String[] args )
    {
        if( args.length < 2 )
        {
            System.out.println( "Usage: java com.geekcap.akka.prime.PrimeCalculator <start-number> <end-number>" );
            System.exit( 0 );
        }

        long startNumber = Long.parseLong( args[ 0 ] );
        long endNumber = Long.parseLong( args[ 1 ] );

        PrimeCalculator primeCalculator = new PrimeCalculator();
        System.out.println("Init Date: "+LocalTime.now());
        primeCalculator.calculate( startNumber, endNumber );
        System.out.println("End Date for main Thread: "+LocalTime.now());
    }
}

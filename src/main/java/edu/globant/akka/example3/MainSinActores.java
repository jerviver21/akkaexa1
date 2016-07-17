package edu.globant.akka.example3;

import java.time.LocalTime;

public class MainSinActores {
	
	public static void main( String[] args ){
		
		System.out.println("Init Date: "+LocalTime.now());
		
		long startNumber = Long.parseLong( args[ 0 ] );
        long endNumber = Long.parseLong( args[ 1 ] );
		
		 Result result = new Result();
         for( long l = startNumber; l <= endNumber; l++ ){
             if( isPrime( l ) )
                 result.getResults().add( l );
         }
         System.out.println("Number of primes: "+result.getResults().size());
         System.out.println("End Date: "+LocalTime.now());
		
	}
	
	
	private static boolean isPrime( long n )
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


}

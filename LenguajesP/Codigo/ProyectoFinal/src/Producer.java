//PROYECTO 

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    
	Buffer buffer;
	int rangoMenor;
	int rangoMayor;
	boolean ejecucion;
    
    Producer(Buffer buffer, int rMa, int rMe) {
        this.buffer = buffer;
        this.rangoMayor = rMa;
        this.rangoMenor = rMe;
        this.ejecucion = true;
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        
        //METODO PROFE 
        
        //String products = "AEIOU";
        //Random r = new Random(System.currentTimeMillis());
        //char product;
        
//        for(int i=0 ; i<5 ; i++) {
//            product = products.charAt(r.nextInt(5));
//            this.buffer.produce(product);
//            //System.out.println("Producer produced: " + product);
//            Buffer.print("Producer produced: " + product);
//            
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        
        while(this.ejecucion) {
        	
        	Operations objetoScheme = new Operations(rangoMayor,rangoMenor);
        	int arreglo[]=objetoScheme.getRandomNumbers();
        	String operacionRandom = objetoScheme.getRandomOperation();
        	String formatOperation = objetoScheme.getFormatOperation(arreglo,operacionRandom);
        	
        	//PASAR formatOperation AL BUFFER 
        	
        	//TRY CATCH DEL BUFFER 
        	
        }
        
    }
    
    public void stopThread() {
    	this.ejecucion = false;
    }
    
}

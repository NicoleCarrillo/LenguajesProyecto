import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {

    Buffer buffer;
    boolean ejecucion = true;
    
    Consumer(Buffer buffer) {
        this.buffer = buffer;
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        Operations product;
       
        //MIENTRAS EXISTA ESPACIO RESUELVE LAS OPERACIONES Y LAS MANDA AL BUFFER 
        //Y LA GUI
        while(this.ejecucion) {
            product = this.buffer.consume();

            if(product != null){
                Operations.resultOperation(product);
                Buffer.print("Consumer Consumed ID -> " + product.getID() + " Result -> " + product.getResult());
                GUIFrame.tableComplete(product.getID(), product.getOperation(), product.getResult());
            }
            else{

                try {
                    Thread.sleep(this.buffer.time2);
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                stopThread();
            }
        }
    }
    
    //PARAR EL HILO DE CONSUMER
    public void stopThread(){
        this.ejecucion = false;
    }
}

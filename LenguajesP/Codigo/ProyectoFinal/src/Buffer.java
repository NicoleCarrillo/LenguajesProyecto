
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private String[] buffer; //array list   ---- validaciones 
    private int pos;
    
    Buffer() {
    	this.buffer=new String[10];
    	this.pos=0;
        for(int x=0;x<10;x++) {
        	buffer[x]="0";
        }
    }
    
    synchronized char consume() {
        char product = 0;
        
        if(this.buffer[pos] == "0") {
            try {
                wait(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        product = this.buffer[pos].charAt(0);
        this.buffer[pos] = "0";
        pos--;
        notify();
        
        return product;
    }
    
    synchronized void produce(char product) {
        if(this.buffer[pos] != "0") {
            try {
                wait(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buffer[pos] = String.valueOf(product);
        pos++;
        notify();
    }
    
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}

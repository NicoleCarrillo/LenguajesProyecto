import java.util.*;

//This class is to generate the math operations and gives the Scheme format
public class Operations {
	
	public int ID,
				cont=0,
				rangoMinimo,
				rangoMaximo;
	public String operation;
	public double resultado;
	static Random rand=new Random();
	
	public Operations(int min,int max) {
		this.ID=0;
		this.operation=null;
		this.resultado=0;
		this.rangoMinimo=min;
		this.rangoMaximo=max;
	}
	
	public String getOperation() {
        return operation;
    }
	
	 public void setOperation(String operation) {
		 this.operation = operation;
	  }

	 public void setResult(double result) {
	        this.resultado = result;
	  }
	
	public static Operations resultOperation(Operations op) {
		int result;
		char[] operations=stringToChar(op.getOperation());
		
		int n1=Integer.parseInt(String.valueOf(operations[2]));
		int n2=Integer.parseInt(String.valueOf(operations[3]));
		
		char operator=operations[1];
		
		if(n2==0 && operator=='/') {
			op.setResult(Double.NaN);
			return op;
		}
		
		switch(operator) {
			case '+':
				result=n1+n2;
				op.setResult(result);
				break;
			case '-':
				result=n1-n2;
				op.setResult(result);
				break;
			case '*':
				result=n1*n2;
				op.setResult(result);
				break;
			case '/':
				result=n1/n2;
				op.setResult(result);
				break;
			default:
				break;
		}		
		return op;
	}

	//The numbers to evaluate the operation
	public int[] getRandomNumbers() {
		// TODO Auto-generated method stub
		int[] arr = null;
		arr[0]=rand.nextInt(this.rangoMaximo - this.rangoMinimo) + this.rangoMinimo;
		arr[1]=rand.nextInt(this.rangoMaximo - this.rangoMinimo) + this.rangoMinimo;
		
		return arr;
	}

	//Generates the operation (+,-,*,/)
	public String getRandomOperation() {
		// TODO Auto-generated method stub
		int ran=rand.nextInt(4);
		String op = null;
		switch(ran) {
		case 0:
			op="+";
			break;
		case 1:
			op="-";
			break;
		case 2:
			op="*";
			break;
		case 3:
			op="/";
			break;
		}
		return op;
	}

	//This gives the operation a Scheme Format (+ a b)
	public String getFormatOperation(int[] arreglo, String operacionRandom) {
		// TODO Auto-generated method stub
		String format="("+operacionRandom+arreglo[0]+arreglo[1]+")";
		return format;
	}
	
    public static char[] stringToChar(String str){

        char [] ch = new char[str.length()];

        for ( int i = 0; i < str.length(); i++ ){
            ch[i] = str.charAt(i);
        }

        return ch;
    } 


}

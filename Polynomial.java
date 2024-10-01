import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Polynomial{
    //field
    double[] coefficients;
    int[] exponents;

    public Polynomial(){
        coefficients = new double[] {0.0};
        exponents = new int[] {0};

    }

    public Polynomial(double[] a, int[] n){
        coefficients = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            coefficients[i] = a[i];
        }

        exponents = new int[n.length];
        for (int i = 0; i < n.length; i++)
        {
            exponents[i] = n[i];
        }
    }

    public Polynomial(File file){

        try{

        Scanner input = new Scanner(file);
        String polynomial = input.nextLine();
        input.close();

        polynomial = polynomial.replace("-", "+-");
        String[] monomials = polynomial.split("\\+");
        coefficients = new double[monomials.length];
        exponents = new int[monomials.length];




        } catch (FileNotFoundException exp){


            System.out.println("File not contain");

        }



        
        

    

    

    
}


    public Polynomial add(Polynomial p) {
        int len = this.coefficients.length + p.coefficients.length;
        double[] c1 = new double[len];
        int[] e1 = new int[len];
    
        int num_terms = 0;
        boolean contain;
    
        
        for (int i = 0; i < this.exponents.length; i++) {
            contain = false;
            for (int j = 0; j < p.exponents.length; j++) {
                if (this.exponents[i] == p.exponents[j]) {
                    c1[num_terms] = this.coefficients[i] + p.coefficients[j];
                    e1[num_terms] = this.exponents[i];
                    contain = true;
                    num_terms++;
                  
                }
            }
            if (contain == false) {
                c1[num_terms] = this.coefficients[i];
                e1[num_terms] = this.exponents[i];
                num_terms++;
            }
        }
    
        
        for (int i = 0; i < p.exponents.length; i++) {
            contain = false;
            for (int j = 0; j < this.exponents.length; j++) {
                if (p.exponents[i] == this.exponents[j]) {
                    contain = true;
                    
                }
            }
            if (contain == false) {
                c1[num_terms] = p.coefficients[i];
                e1[num_terms] = p.exponents[i];
                num_terms++;
            }
        }
    
    
        double[] c2 = new double[num_terms];
        int[] e2 = new int[num_terms];
        System.arraycopy(c1, 0, c2, 0, num_terms);
        System.arraycopy(e1, 0, e2, 0, num_terms);
    
        return new Polynomial(c2, e2);
    }
    


    public Polynomial multiply(Polynomial p){

        Polynomial returnPoly = new Polynomial();

        for (int i = 0; i < this.coefficients.length; i++)
        {
            double[] temp_coefs = new double[p.coefficients.length];
            int[] temp_exps = new int[p.exponents.length];

            for (int j = 0; j < p.coefficients.length; j++)
            {
                temp_coefs[j] = this.coefficients[i] * p.coefficients[j];
                temp_exps[j] = this.exponents[i] + p.exponents[j];
            }

            Polynomial temp = new Polynomial(temp_coefs, temp_exps);
            returnPoly = temp.add(returnPoly);
        }

        return returnPoly;
    }

    

    public double evaluate(double x){
        double sum = 0;
        for (int i = 0; i < this.coefficients.length; i++){
            sum += this.coefficients[i] * Math.pow(x, this.exponents[i]);
        }
        return sum;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0.0;
    }


    public void saveToFile(String name){

        try{

            StringBuilder eqn = new StringBuilder();

            PrintWriter write = new PrintWriter(name);

            for (int i = 0; i < this.coefficients.length; i++)
            {
                if (i >= 1 && coefficients[i] >= 0)
                {
                    eqn.append("+");
                }

                if (exponents[i] == 0)  //constant
                {
                    eqn.append(coefficients[i]);
                }

                else{
                    eqn.append(coefficients[i]);
                    eqn.append("x");
                    eqn.append(exponents[i]);
                }
            
            }



            write.write(eqn.toString());
            write.close();


        } catch (FileNotFoundException exp){
            System.out.println("File not found");
        }
        
        
        
        
    }


    
}
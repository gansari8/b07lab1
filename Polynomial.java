public class Polynomial{
    //field
    double[] coefficients;

    public Polynomial(){
        coefficients = new double[] {0};
    }

    public Polynomial(double[] p){
        coefficients = new double[p.length];
        for (int i = 0; i < p.length; i++) {
            coefficients[i] = p[i];
        }
    }

    //methods

    public Polynomial add(Polynomial p){
        Polynomial added = new Polynomial();
        int len1 = Math.max(coefficients.length, p.coefficients.length);
        int len2 = Math.min(coefficients.length, p.coefficients.length);

        added.coefficients = new double[len1];
        for (int i = 0; i < len2; i++){
            added.coefficients[i] = coefficients[i] + p.coefficients[i];
        }
        for (int i = len2; i < len1; i++){
            if (len1 == coefficients.length){
                added.coefficients[i] = coefficients[i];
            }
            else{
                added.coefficients[i] = p.coefficients[i];
            }
        }

        return added;

    }

    public double evaluate(double x){
        double sum = 0;
        for (int i = 0; i < coefficients.length; i++){
            sum += coefficients[i] * (double)Math.pow(x, i);
        }
        return sum;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }
}
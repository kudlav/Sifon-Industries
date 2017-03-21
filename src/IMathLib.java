public interface IMathLib
{
    double idiv(double dividend, double divisor);
    double imul(double a, double b);
    double sub(double a, double b);
    double add(double a, double b);

    double nRoot(int n, double a);
    double exp(int n, double a);
    double fac(int a);
    int mod(int a, int b);
}

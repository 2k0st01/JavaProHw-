import java.util.Scanner;

public class ExchangeRates {
    private static Scanner sc = new Scanner(System.in);
    private ExchangeRates(){

    }

    public static void getExchangeRates() {
        System.out.println("Select with one rates you like to check: ");
        String select = sc.nextLine();
        System.out.println("1: UAH to EUR");
        System.out.println("2: UAH to USD");
        System.out.println("1: USD to EUR");
        System.out.println("4: USD to UAH");
        System.out.println("5: EUR to UAH");
        System.out.println("5: EUR to USD");
        System.out.print("Select sum -> ");

        int sum = sc.nextInt();
        switch (select) {
            case "1":
                System.out.println(uahToEur(sum));
                break;
            case "2":
                System.out.println(uahToUsd(sum));
                break;
            case "3":
                System.out.println(usdToEur(sum));
                break;
            case "4":
                System.out.println(usdToUah(sum));
                break;
            case "5":
                System.out.println(eurToUah(sum));
                break;
            case "6":
                System.out.println(eurToUsd(sum));
            default:
                break;
        }
    }

    public static double uahToEur(double uah) {
        return uah * 0.024;
    }

    public static double uahToUsd(double uah) {
        return uah * 0.026;
    }

    public static double usdToEur(double usd) {
        return usd * 0.91;
    }

    public static double usdToUah(double usd) {
        return usd * 38.23;
    }

    public static double eurToUah(double eur) {
        return eur * 41.93;
    }

    public static double eurToUsd(double eur) {
        return eur * 1.10;
    }
}

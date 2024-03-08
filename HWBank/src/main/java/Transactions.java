import java.util.Scanner;

public class Transactions {
    private static Transactions transactions = new Transactions();
    private static Scanner sc = new Scanner(System.in);

    private Transactions() {

    }

    public static Transactions getTransactions() {
        return transactions;
    }

    public static void transaction(Users users, String from, String to) {
        System.out.println("How many you wood like transfer?");
        double amount = sc.nextDouble();
        switch (from + "_" + to) {
            case "uah_usd":
                if (users.getWallet().getUah() >= amount) {
                    users.getWallet().minusFromUah(amount);
                    users.getWallet().addToUsd(ExchangeRates.uahToUsd(amount));
                } else System.out.println("There is not enough money on the balance sheet.");
                break;
            case "uah_eur":
                if (users.getWallet().getUah() >= amount) {
                    users.getWallet().minusFromUah(amount);
                    users.getWallet().addToEur(ExchangeRates.uahToEur(amount));
                } else System.out.println("There is not enough money on the balance sheet.");
                break;
            case "eur_usd":
                if (users.getWallet().getEur() >= amount) {
                    users.getWallet().minusFromEur(amount);
                    users.getWallet().addToUsd(ExchangeRates.eurToUsd(amount));
                } else System.out.println("There is not enough money on the balance sheet.");
                break;
            case "eur_uah":
                if (users.getWallet().getEur() >= amount) {
                    users.getWallet().minusFromEur(amount);
                    users.getWallet().addToUah(ExchangeRates.eurToUah(amount));
                } else System.out.println("There is not enough money on the balance sheet.");
                break;
            case "usd_eur":
                if (users.getWallet().getUsd() >= amount) {
                    users.getWallet().minusFromUsd(amount);
                    users.getWallet().addToEur(ExchangeRates.usdToEur(amount));
                } else System.out.println("There is not enough money on the balance sheet.");
                break;
            case "usd_uah":
                if (users.getWallet().getUsd() >= amount) {
                    users.getWallet().minusFromUsd(amount);
                    users.getWallet().addToUah(ExchangeRates.usdToUah(amount));
                } else System.out.println("There is not enough money on the balance sheet.");
                break;
            default:
                System.out.println("Invalid currency pair.");
        }
    }
}


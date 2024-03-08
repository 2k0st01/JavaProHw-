import java.util.Scanner;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleUnaryOperator;

public class Transactions {
    private static Transactions transactions = new Transactions();
    private static Scanner sc = new Scanner(System.in);

    private Transactions() {

    }

    public static Transactions getTransactions() {
        return transactions;
    }

    public static void transaction(Users users, String from, String to) {
        System.out.println("How much would you like to transfer?");
        double amount = sc.nextDouble();
        Wallet wallet = users.getWallet();

        switch (from + "_" + to) {
            case "uah_usd":
                performTransfer(wallet::getUah, wallet::minusFromUah, wallet::addToUsd, ExchangeRates::uahToUsd, amount);
                break;
            case "uah_eur":
                performTransfer(wallet::getUah, wallet::minusFromUah, wallet::addToEur, ExchangeRates::uahToEur, amount);
                break;
            case "eur_usd":
                performTransfer(wallet::getEur, wallet::minusFromEur, wallet::addToUsd, ExchangeRates::eurToUsd, amount);
                break;
            case "eur_uah":
                performTransfer(wallet::getEur, wallet::minusFromEur, wallet::addToUah, ExchangeRates::eurToUah, amount);
                break;
            case "usd_eur":
                performTransfer(wallet::getUsd, wallet::minusFromUsd, wallet::addToEur, ExchangeRates::usdToEur, amount);
                break;
            case "usd_uah":
                performTransfer(wallet::getUsd, wallet::minusFromUsd, wallet::addToUah, ExchangeRates::usdToUah, amount);
                break;
            default:
                System.out.println("Invalid currency pair.");
        }
    }
    private static void performTransfer(DoubleSupplier balanceGetter, DoubleConsumer subtractor, DoubleConsumer adder, DoubleUnaryOperator converter, double amount) {
        double balance = balanceGetter.getAsDouble();
        if (balance >= amount) {
            subtractor.accept(amount);
            adder.accept(converter.applyAsDouble(amount));
        } else {
            System.out.println("There is not enough money on the balance sheet.");
        }
    }
}


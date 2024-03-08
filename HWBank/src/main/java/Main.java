import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class Main {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAHomeWorkBan");
    static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
//        Users users = new Users("Stanisalv" , "asdad");
//        Wallet wallet = new Wallet();
//        users.setWallet(wallet);
//        wallet.setUser(users);
//        Transactions.transaction(users, "usd", "uah");

        Scanner sc = new Scanner(System.in);
        try {
            try {
                label:
                while (true) {
                    int userId = 0;
                    System.out.println("1: add user");
                    System.out.println("2: add UAH to wallet");
                    System.out.println("3: transfer money");
                    System.out.println("4: showBalance");
                    System.out.println("5: show all users");
                    System.out.print("-> ");

                    String select = sc.nextLine();
                    List<Users> list;
                    switch (select) {
                        case "1":
                            addUser(sc);
                            break;
                        case "2":
                            System.out.println("Select user ID: ");
                            list = getUsers();
                            for (Users user : list)
                                System.out.println(user);
                            userId = sc.nextInt();
                            addToWalletUAH(sc, userId);
                            sc.nextLine();
                            break;
                        case "3":
                            System.out.println("Select user ID: ");
                            list = getUsers();
                            for (Users user : list)
                                System.out.println(user);
                            userId = sc.nextInt();
                            transaction(sc, userId);
                            sc.nextLine();
                            break;
                        case "4":
                            System.out.println("Select user ID: ");
                            list = getUsers();
                            for (Users user : list)
                                System.out.println(user);
                            userId = sc.nextInt();
                            showBalance(userId);
                            sc.nextLine();
                            break;
                        case "5":
                            list = getUsers();
                            for (Users user : list)
                                System.out.println(user);
                            break;
                        default:
                            break label;
                    }
                }
            } finally {
                sc.close();
                em.close();
                emf.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addUser(Scanner sc) {
        System.out.println("Write name: ");
        String userName = sc.nextLine();

        System.out.println("Write email: ");
        String email = sc.nextLine();

        Users user = new Users(userName, email);
        Wallet wallet = new Wallet();

        user.setWallet(wallet);
        wallet.setUser(user);

        em.getTransaction().begin();
        try {
            em.persist(user);
            em.persist(wallet);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public static void addToWalletUAH(Scanner sc, long userId) {
        em.getTransaction().begin();
        TypedQuery<Users> typedQuery = em.createQuery("SELECT c FROM Users c  WHERE id = :userId", Users.class);
        typedQuery.setParameter("userId", userId);
        Users user = typedQuery.getSingleResult();

        System.out.println("How many you like to add to your Wallet?");
        Double sum = sc.nextDouble();
        user.getWallet().addToUah(sum);
        em.persist(user);
        em.getTransaction().commit();
    }

    public static void transaction(Scanner sc, long userId) {
        em.getTransaction().begin();
        try {
            TypedQuery<Users> typedQuery = em.createQuery("SELECT c FROM Users c  WHERE id = :userId", Users.class);
            typedQuery.setParameter("userId", userId);
            Users user = typedQuery.getSingleResult();
            System.out.println("From with wallet transfer?");
            System.out.println("uah, eur, usd");
            sc.nextLine();
            String from = sc.nextLine();
            System.out.println("uah, eur, usd");
            System.out.println("To with wallet transfer?");
            String to = sc.nextLine();
            user.transaction(from, to);

            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }

    }

    public static void showBalance(long userId) {
        TypedQuery<Users> typedQuery = em.createQuery("SELECT c FROM Users c  WHERE id = :userId", Users.class);
        typedQuery.setParameter("userId", userId);
        Users user = typedQuery.getSingleResult();

        System.out.println("EURO: " + user.getWallet().getEur());
        System.out.println("UAH: " + user.getWallet().getUah());
        System.out.println("USD: " + user.getWallet().getUsd());

        System.out.println("All in UAH: " + user.getAllSumInUAH());
    }

    public static List<Users> getUsers() {
        TypedQuery<Users> typedQuery = em.createQuery("SELECT c FROM Users c", Users.class);
        return typedQuery.getResultList();
    }

}

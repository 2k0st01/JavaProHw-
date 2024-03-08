import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class RestaurantApp {
    static EntityManagerFactory emf;
    static EntityManager em;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            emf = Persistence.createEntityManagerFactory("JPAHomeWork");
            em = emf.createEntityManager();
            try {
                label:
                while (true) {
                    System.out.println("1: add new dish");
                    System.out.println("2: select dish by price (from-to)");
                    System.out.println("3: select dishes by discount");
                    System.out.println("4: collect dishes for 1 kilogram");
                    System.out.println("5: show all dished");
                    System.out.print("-> ");

                    String select = sc.nextLine();

                    switch (select) {
                        case "1":
                            addDish(sc);
                            break;
                        case "2":
                            selectDishByPrice(sc);
                            break;
                        case "3":
                            selectDishesByDiscount();
                            break;
                        case "4":
                            collectDishesForOneKg(1000);
                            break;
                        case "5":
                            showAllDished();
                            break;
                        default:
                            break;
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

    private static void addDish(Scanner sc) {
        System.out.println("Enter dish name: ");
        String name = sc.nextLine();
        System.out.println("Enter dish discount: ");
        int discount = Integer.parseInt(sc.nextLine());
        System.out.println("Enter dish price: ");
        double price = Double.parseDouble(sc.nextLine());
        System.out.println("Enter dish weight: ");
        int weight = Integer.parseInt(sc.nextLine());

        em.getTransaction().begin();
        try {
            RestaurantMenu rm = new RestaurantMenu(name, discount, price, weight);
            em.persist(rm);
            em.getTransaction().commit();

            System.out.println(rm.getId());
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    private static void selectDishByPrice(Scanner sc) {
        System.out.println("Select price from: ");
        double from = Double.parseDouble(sc.nextLine());
        System.out.println("Select price to: ");
        double to = Double.parseDouble(sc.nextLine());
        TypedQuery<RestaurantMenu> typedQuery = em.createQuery
                ("SELECT c FROM RestaurantMenu c WHERE c.price >= :from AND c.price <= :to", RestaurantMenu.class);
        typedQuery.setParameter("from", from);
        typedQuery.setParameter("to", to);

        List<RestaurantMenu> list = typedQuery.getResultList();

        for (RestaurantMenu rm : list) {
            System.out.println(rm);
        }

    }

    private static void selectDishesByDiscount() {
        TypedQuery<RestaurantMenu> typedQuery = em.createQuery
                ("SELECT c FROM RestaurantMenu c WHERE discount > " + 0, RestaurantMenu.class);
        List<RestaurantMenu> list = typedQuery.getResultList();

        System.out.println("All our discounts: ");
        for (RestaurantMenu rm : list) {
            System.out.println(rm);
        }
    }

    private static void collectDishesForOneKg(int weight) {
        Scanner sc = new Scanner(System.in);
        int tempWeight = weight;

        int id = 0;
        List<RestaurantMenu> list = getList(tempWeight);
        System.out.println("Max 1kg");
        for (RestaurantMenu rm : list) {
            System.out.println(rm);
        }

        if (tempWeight != 0 && getList(tempWeight) != null) {
            System.out.println("Select by id: ");
            id = sc.nextInt();
            for (RestaurantMenu rm : list) {
                if (rm.getId() == id) {
                    tempWeight -= rm.getWeight();
                    System.out.println("You can add dish more for weight: " + tempWeight);
                }
            }
            collectDishesForOneKg(tempWeight);
        } else System.out.println("All done ");
    }

    private static void showAllDished() {
        TypedQuery<RestaurantMenu> typedQuery = em.createQuery("SELECT c FROM RestaurantMenu c", RestaurantMenu.class);
        List<RestaurantMenu> list = typedQuery.getResultList();

        for (RestaurantMenu rm : list) {
            System.out.println(rm);
        }
    }

    private static List<RestaurantMenu> getList(int weight) {
        TypedQuery<RestaurantMenu> typedQuery = em.createQuery(
                "SELECT c FROM RestaurantMenu c WHERE c.weight <= :weight", RestaurantMenu.class);
        typedQuery.setParameter("weight", weight);
        return typedQuery.getResultList();
    }

}

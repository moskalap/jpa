import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }
    private static void zad2(Session session) throws IOException {
        System.out.println("Podaj nazwę produktu");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String res = br.readLine();
        System.out.println("Podaj ilosc ");
        Integer units = Integer.parseInt(br.readLine());
        Product product = new Product();
        product.setProductName(res);
        product.setUnitsInStock(units);
        Transaction thx = session.beginTransaction();
        session.save(product);
        System.out.println(String.format("Dodano, nadano id %s", product.getId()));
        thx.commit();

    }
    private static void zad3(Session session) throws IOException {
        System.out.println("Podaj nazwę dostawcy");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String comapnyName = br.readLine();

        System.out.println("Ulica");
        String ulica = br.readLine();


        System.out.println("City");
        String city = br.readLine();

        System.out.println("Podaj ilosc ");
        Supplier supplier = new Supplier(comapnyName,ulica,city);

        Transaction thx = session.beginTransaction();
        Product product = new Product("testwoy", 21);
        session.save(supplier);
        session.save(product);


        System.out.println(String.format("Dodano, nadano id %s", product.getId()));
        thx.commit();

    }

    private static void zad4(Session session
    ){

        Transaction thx = session.beginTransaction();
        Product p1 = new Product("p1", 2);
        Product p2 = new Product("p2", 5);
        Product p3 = new Product("p3", 8);

        Supplier sup = new Supplier("test4", "sadasda", "1asds");
        sup.getProducts().add(p1);
        sup.getProducts().add(p2);
        sup.getProducts().add(p3);
        session.save(sup);
        session.save(p1);
        session.save(p2);
        session.save(p3);



    }
    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {

           zad4(session);


        } finally {
            session.close();
        }
    }
}
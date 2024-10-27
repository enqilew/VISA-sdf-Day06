package lecture;

import java.util.Date;
import java.util.LinkedList;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class App {
    
    public static void main(String[] args) {
        
        //Thread th = new Thread(new Runnable() {
        //    public void run() {
        //        for (int i =0; i <10; i++)
        //        System.out.println(Thread.currentThread().getName() + " -> " +i);
        //    }
        //});
        //th.start();

        MyImplementation my1 = new MyImplementation();
        MyImplementation my2 = new MyImplementation();
        MyImplementation my3 = new MyImplementation();
        MyImplementation my4 = new MyImplementation();
        MyImplementation my5 = new MyImplementation();

        //// Execute the tasks using a single thread
        //ExecutorService es = Executors.newSingleThreadExecutor();
        //es.execute(my1);
        //es.execute(my2);
        //es.shutdown();

        // Execute the tasks using fixed thread pool
        ExecutorService es1 = Executors.newFixedThreadPool(5);
        es1.execute(my1);
        es1.execute(my2);
        es1.execute(my3);
        es1.execute(my4);
        es1.execute(my5);
        es1.shutdown();

        List<Product> products = new ArrayList<Product>();
        products.add(new Product(1L, "Apple iPad", "Apple Tablet", "Computer"));
        products.add(new Product(2L, "Apple MacBook Pro", "Apple Laptop", "Computer"));
        products.add(new Product(3L, "Logitech Mouse", "Mouse", "Computer"));
        products.add(new Product(4L, "32 inch monitor", "Samsung Monitor", "Computer"));
        products.add(new Product(5L, "Huawei 5", "Huawei Phone", "Mobile"));
        products.add(new Product(6L, "Oppo 7 Pro", "Oppo Phone", "Mobile"));
        products.add(new Product(7L, "Galaxy S24", "Samsung Phone", "Mobile"));
        products.add(new Product(8L, "iPhone 16 Pro", "Apple Phone", "Mobile"));
        
        //products.forEach(prod -> {
        //    System.out.println(prod);
        //});

        // Only retrieve products with category 'mobile'
        // Using stream and lambda functions
        List<Product> filteredProducts = new ArrayList<>();
        filteredProducts = products
                .stream()
                .filter(p -> p.getCategory().equals("Mobile"))
                .collect(Collectors.toList());

            filteredProducts.forEach(Product::print);

        LocalDate ld = LocalDate.of(1990, 1, 12);
        Date currentDate = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Person> persons = new LinkedList<>();
        persons.add(new Person(1L, "Bernard", "Tan", 30000, currentDate));
        persons.add(new Person(2L, "Chris", "Tan", 20000, currentDate));
        persons.add(new Person(3L, "Dennis", "Wong", 15000, currentDate));
        persons.add(new Person(4L, "Thomas", "Chia", 5000, currentDate));
        persons.add(new Person(5L, "Rick", "Ng", 35000, currentDate));
        persons.add(new Person(6L, "Michael", "Choy", 12000, currentDate));
        persons.add(new Person(7L, "Darren", "loh", 32000, currentDate));
        persons.add(new Person(8L, "May", "Tan", 28000, currentDate));

        Comparator<Person> compare = Comparator.comparing(p -> p.getFirstName());
        persons.sort(compare.reversed());
            persons.forEach(p -> {
                System.out.println(p.toString());
            });
        
        Comparator<Person> compareMultiple = Comparator.comparing(Person::getFirstName)
            .thenComparing(Person::getLastName);
        persons.sort(compareMultiple);
            persons.forEach(p -> {
                System.out.println(p.toString());
            });

        MyInterface<Integer> addOperation = (a, b) -> {
            return a + b;
        };

        MyInterface<Integer> multiplyOperation = (a, b) -> {
            return a * b;
        };

        System.out.println("add Operation: " + addOperation.process(3, 2));
        System.out.println("multiply Operation: " + multiplyOperation.process(3, 2));

    }
}

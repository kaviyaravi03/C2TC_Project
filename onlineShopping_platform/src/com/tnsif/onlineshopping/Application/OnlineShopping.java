package com.tnsif.onlineshopping.Application;

import java.util.*;

public class OnlineShopping {
    static Scanner sc = new Scanner(System.in);
    static ProductService productService = new ProductService();
    static AdminService adminService = new AdminService();
    static CustomerService customerService = new CustomerService();
    static OrderService orderService = new OrderService();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n1. Admin Menu\n2. Customer Menu\n3. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> adminMenu();
                case 2 -> customerMenu();
                case 3 -> System.out.println("Exiting...");
            }
        } while (choice != 3);
    }

    static void adminMenu() {
        int choice;
        do {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Product\n2. Remove Product\n3. View Products\n4. Create Admin\n5. View Admins\n6. Update Order Status\n7. View Orders\n8. Return");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Product ID: "); int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Product Name: "); String name = sc.nextLine();
                    System.out.print("Enter Price: "); double price = sc.nextDouble();
                    System.out.print("Enter Stock: "); int stock = sc.nextInt();
                    productService.addProduct(new Product(id, name, price, stock));
                    System.out.println("Product added successfully!");
                }
                case 2 -> {
                    System.out.print("Enter Product ID to remove: "); int id = sc.nextInt();
                    productService.removeProduct(id);
                    System.out.println("Product removed!");
                }
                case 3 -> productService.getAllProducts().forEach(System.out::println);
                case 4 -> {
                    System.out.print("Enter Admin ID: "); int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: "); String name = sc.nextLine();
                    System.out.print("Enter Email: "); String email = sc.nextLine();
                    adminService.addAdmin(new Admin(id, name, email));
                    System.out.println("Admin created successfully!");
                }
                case 5 -> adminService.getAllAdmins().forEach(System.out::println);
                case 6 -> {
                    System.out.print("Enter Order ID: "); int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new status: "); String status = sc.nextLine();
                    orderService.updateOrderStatus(id, status);
                }
                case 7 -> orderService.getAllOrders().forEach(System.out::println);
                case 8 -> System.out.println("Returning...");
            }
        } while (choice != 8);
    }

    static void customerMenu() {
        int choice;
        do {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. Create Customer\n2. View Customers\n3. Place Order\n4. View Orders\n5. View Products\n6. Return");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter User ID: "); int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Username: "); String name = sc.nextLine();
                    System.out.print("Enter Email: "); String email = sc.nextLine();
                    System.out.print("Enter Address: "); String address = sc.nextLine();
                    customerService.addCustomer(new Customer(id, name, email, address));
                    System.out.println("Customer created successfully!");
                }
                case 2 -> customerService.getAllCustomers().forEach(System.out::println);
                case 3 -> placeOrder();
                case 4 -> {
                    System.out.print("Enter Customer ID: "); int id = sc.nextInt();
                    Customer c = customerService.findById(id);
                    if (c != null) c.getOrders().forEach(System.out::println);
                    else System.out.println("Customer not found!");
                }
                case 5 -> productService.getAllProducts().forEach(System.out::println);
                case 6 -> System.out.println("Returning...");
            }
        } while (choice != 6);
    }

    static void placeOrder() {
        System.out.print("Enter Customer ID: "); int id = sc.nextInt();
        Customer c = customerService.findById(id);
        if (c == null) { System.out.println("Customer not found!"); return; }

        List<ProductQuantityPair> productList = new ArrayList<>();
        while (true) {
            System.out.print("Enter Product ID to add to order (or -1 to complete): ");
            int pid = sc.nextInt();
            if (pid == -1) break;
            Product p = productService.findProductById(pid);
            if (p == null) {
                System.out.println("Invalid product ID!");
                continue;
            }
            System.out.print("Enter quantity: ");
            int qty = sc.nextInt();
            productList.add(new ProductQuantityPair(p, qty));
        }
        orderService.placeOrder(c, productList);
    }
}
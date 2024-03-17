package org.example;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        // Создаем массивы с данными о фирмах и марках
        String[] firms = {"Breda", "Daniel Wellington", "LORUS"};
        String[][] brands = {
                {"Часы Breda Jane Tethered, 23 мм", "Breda Visser"},
                {"Daniel Wellington Iconic Link Ceramic 32mm", "Daniel Wellington Quadro Pressed Sheffield"},
                {"Ladies Strap Watch SS6101P", "SEIKO"}
        };
        // Создаем список для хранения покупателей
        ArrayList<Customer> customers = new ArrayList<>();
        // Отображаем доступные позиции в магазине
        showAvailablePositions(brands, firms);

        // Функция для добавления заказа
        Scanner scanner = new Scanner(System.in);
        addOrder(customers, firms, brands, scanner);
        addOrder(customers, firms, brands, scanner);
        addOrder(customers, firms, brands, scanner);

        // Выводим информацию о заказчиках и их заказах
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            System.out.println("Информация о заказе " + (i + 1) + ":");
            System.out.println("ФИО покупателя: " + customer.getFullName());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Номер телефона: " + customer.getPhoneNumber());
            System.out.println("Выбранный товар: " + customer.getPosition()[1]);
            System.out.println("Фирма: " + customer.getPosition()[0]);
            System.out.println("Количество: " + customer.getQuantity());
            System.out.println("-----------------------");
        }
    }

    public static void showAvailablePositions(String[][] brands, String[] firms) {
        System.out.println("Список доступных позиций в магазине:");
        for (int i = 0; i < brands.length; i++) {
            System.out.println("Первый товар, Позиция: " + i + ": " + brands[i][0] + " - Второй товар: " + brands[i][1]
                    + " - " + "Фирма: " + firms[i]);
        }
    }
    public static void addOrder(ArrayList<Customer> customers, String[] firms, String[][] brands, Scanner scanner) {
        Customer customer = new Customer();
        System.out.print("Введите ФИО покупателя (только русские буквы): ");
        String fullName = scanner.nextLine();
        while (!fullName.matches("[а-яА-Я]+")) {
            System.out.println("Некорректный ввод. Введите ФИО покупателя (только русские буквы): ");
            fullName = scanner.nextLine();
        }
        customer.setFullName(fullName);

        // Ввод email покупателя
        System.out.print("Введите email покупателя: ");
        String email = scanner.nextLine();
        while (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,4}") || email.length() < 7) {
            System.out.println("Некорректный ввод. Введите email покупателя: ");
            email = scanner.nextLine();
        }
        customer.setEmail(email);
        System.out.print("Введите номер телефона: ");
        String phoneNumber = scanner.nextLine();
        while (!Pattern.matches("^((\\+7|7|8)+([0-9]){10})$", phoneNumber)) {
            System.out.println("Некорректный ввод. Пожалуйста, введите корректный номер телефона");
            phoneNumber = scanner.nextLine();
        }
        customer.setPhoneNumber(phoneNumber);

        System.out.print("Выберите позицию товара (введите номер фирмы-марки): ");
        String positionInput = scanner.nextLine();
        String[] position = positionInput.split("-");
        int firmIndex = Integer.parseInt(position[0]);
        int brandIndex = Integer.parseInt(position[1]);
        customer.setPosition(new String[]{firms[firmIndex], brands[firmIndex][brandIndex]});
        System.out.print("Введите количество товара: ");
        customer.setQuantity(Integer.parseInt(scanner.nextLine()));
        customers.add(customer);

        System.out.println("Выбранный товар: " + customer.getPosition()[1]);
        System.out.println("Количество: " + customer.getQuantity());
        System.out.println("-----------------------");

    }

}
class Customer {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String[] position;
    private int quantity;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String[] getPosition() {
        return position;
    }

    public void setPosition(String[] position) {
        this.position = position;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
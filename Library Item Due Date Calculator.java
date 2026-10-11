import java.time.LocalDate;
import java.util.*;

abstract class LibraryItem {
    String title;

    LibraryItem(String title) {
        this.title = title;
    }

    abstract int getLoanPeriod();

    LocalDate getDueDate() {
        LocalDate currentDate =
                LocalDate.of(2023, 10, 26);
        return currentDate.plusDays(getLoanPeriod());
    }
}

class Book extends LibraryItem {
    Book(String title) {
        super(title);
    }

    int getLoanPeriod() {
        return 14;
    }
}

class DVD extends LibraryItem {
    DVD(String title) {
        super(title);
    }

    int getLoanPeriod() {
        return 7;
    }
}

class Magazine extends LibraryItem {
    Magazine(String title) {
        super(title);
    }

    int getLoanPeriod() {
        return 3;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            int space = line.indexOf(' ');
            String type = line.substring(0, space);
            String title = line.substring(space + 1)
                               .replace("\"", "");

            LibraryItem item;

            switch (type.toUpperCase()) {
                case "BOOK":
                    item = new Book(title);
                    break;
                case "DVD":
                    item = new DVD(title);
                    break;
                case "MAGAZINE":
                    item = new Magazine(title);
                    break;
                default:
                    continue;
            }

            System.out.println(item.title + ": "
                    + item.getDueDate());
        }

        sc.close();
    }
}

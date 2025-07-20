import java.util.*;

class Stock {
    String symbol;
    String name;
    double price;

    Stock(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    public void updatePrice(double newPrice) {
        this.price = newPrice;
    }
}

class User {
    String username;
    double balance;
    Map<String, Integer> portfolio = new HashMap<>();

    User(String username, double balance) {
        this.username = username;
        this.balance = balance;
    }

    public void buyStock(Stock stock, int quantity) {
        double cost = stock.price * quantity;
        if (cost > balance) {
            System.out.println("Insufficient balance!");
            return;
        }

        balance -= cost;
        portfolio.put(stock.symbol, portfolio.getOrDefault(stock.symbol, 0) + quantity);
        System.out.println("Bought " + quantity + " shares of " + stock.symbol);
    }

    public void sellStock(Stock stock, int quantity) {
        int owned = portfolio.getOrDefault(stock.symbol, 0);
        if (quantity > owned) {
            System.out.println("You don't have enough shares to sell!");
            return;
        }

        balance += stock.price * quantity;
        if (quantity == owned) {
            portfolio.remove(stock.symbol);
        } else {
            portfolio.put(stock.symbol, owned - quantity);
        }
        System.out.println("Sold " + quantity + " shares of " + stock.symbol);
    }

    public void viewPortfolio(Map<String, Stock> market) {
        System.out.println("\n--- Portfolio of " + username + " ---");
        double totalValue = balance;
        System.out.println("Cash Balance: $" + balance);
        for (String symbol : portfolio.keySet()) {
            int qty = portfolio.get(symbol);
            Stock stock = market.get(symbol);
            double value = qty * stock.price;
            totalValue += value;
            System.out.printf("%s (%s): %d shares @ $%.2f = $%.2f\n", stock.name, stock.symbol, qty, stock.price, value);
        }
        System.out.println("Total Portfolio Value: $" + totalValue);
    }
}

class StockMarket {
    Map<String, Stock> stocks = new HashMap<>();

    public void addStock(Stock stock) {
        stocks.put(stock.symbol, stock);
    }

    public void displayMarket() {
        System.out.println("\n--- Stock Market ---");
        for (Stock stock : stocks.values()) {
            System.out.printf("%s (%s): $%.2f\n", stock.name, stock.symbol, stock.price);
        }
    }

    public Stock getStock(String symbol) {
        return stocks.get(symbol);
    }

    public void simulateMarketChanges() {
        for (Stock stock : stocks.values()) {
            double change = (Math.random() - 0.5) * 10; // -5 to +5
            double newPrice = Math.max(1, stock.price + change);
            stock.updatePrice(newPrice);
        }
    }
}

public class StockTradingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StockMarket market = new StockMarket();
        market.addStock(new Stock("AAPL", "Apple Inc.", 150));
        market.addStock(new Stock("GOOGL", "Google LLC", 2800));
        market.addStock(new Stock("TSLA", "Tesla Inc.", 700));

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        User user = new User(name, 10000); // $10,000 balance

        while (true) {
            System.out.println("\n1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Simulate Market Change");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    market.displayMarket();
                    break;
                case 2:
                    market.displayMarket();
                    System.out.print("Enter stock symbol to buy: ");
                    String symbolBuy = scanner.nextLine().toUpperCase();
                    System.out.print("Enter quantity: ");
                    int qtyBuy = scanner.nextInt();
                    scanner.nextLine();
                    Stock stockToBuy = market.getStock(symbolBuy);
                    if (stockToBuy != null) {
                        user.buyStock(stockToBuy, qtyBuy);
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;
                case 3:
                    user.viewPortfolio(market.stocks);
                    System.out.print("Enter stock symbol to sell: ");
                    String symbolSell = scanner.nextLine().toUpperCase();
                    System.out.print("Enter quantity: ");
                    int qtySell = scanner.nextInt();
                    scanner.nextLine();
                    Stock stockToSell = market.getStock(symbolSell);
                    if (stockToSell != null) {
                        user.sellStock(stockToSell, qtySell);
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;
                case 4:
                    user.viewPortfolio(market.stocks);
                    break;
                case 5:
                    market.simulateMarketChanges();
                    System.out.println("Market prices updated!");
                    break;
                case 6:
                    System.out.println("Thank you for using the Stock Trading App!");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
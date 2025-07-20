import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// NLP + Rule-based logic
class AIChatbot {
    private final Map<String, String> faqBase;

    public AIChatbot() {
        faqBase = new HashMap<>();
        trainBot();
    }

    private void trainBot() {
        faqBase.put("hi", "Hello! How can I help you today?");
        faqBase.put("hello", "Hi there! How can I assist you?");
        faqBase.put("how are you", "I'm a bot, always running smooth!");
        faqBase.put("what is your name", "I am IntelliBot, your AI assistant.");
        faqBase.put("bye", "Goodbye! Have a great day.");
        faqBase.put("what is artificial intelligence", "Artificial Intelligence is the simulation of human intelligence in machines.");
        faqBase.put("who created java", "Java was created by James Gosling at Sun Microsystems in 1995.");
        faqBase.put("what is java", "Java is a versatile, object-oriented programming language.");
        faqBase.put("help", "You can ask me about AI, Java, or general tech topics.");
    }

    public String getResponse(String input) {
        input = input.toLowerCase().trim();
        for (String key : faqBase.keySet()) {
            if (input.contains(key)) {
                return faqBase.get(key);
            }
        }
        return "I'm not sure how to respond to that. Can you rephrase or ask something else?";
    }
}

public class AIChatbotConsole {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AIChatbot bot = new AIChatbot();

        System.out.println("Welcome to IntelliBot! Type 'bye' to exit.");
        while (true) {
            System.out.print("You: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bot: " + bot.getResponse(input));
                break;
            }
            String response = bot.getResponse(input);
            System.out.println("Bot: " + response);
        }

        scanner.close();
    }
}
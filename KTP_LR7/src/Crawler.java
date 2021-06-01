import java.net.*;
import java.util.*;


// Класс сканирует ссылки
public class Crawler {


    // Хеш-мап для посещенных сайтов
    private HashMap<String, URLDepthPair> visited;
    // Связанный список для не посещенных сайтов
    private LinkedList <URLDepthPair> notVisited;
    // Максимальная глубина
    private int maxDepth;

    // Конструктор класса
    public Crawler(String URL, int maxDepth){
        // Инициализация списков
        visited = new HashMap<>();
        notVisited = new LinkedList<>();
        notVisited.add(new URLDepthPair(URL,0));
        this.maxDepth = maxDepth;
    }

    // Метод запускает сканер сайта
    public void run() {
        while(notVisited.size() > 0) {
            URLDepthPair link = notVisited.pop();
            if(visited.containsKey(link.getURL())) continue;
            visited.put(link.getURL(), link);
            System.out.println(link);
            if(link.getDepth() != maxDepth)
                findLinks(link);
        }

        System.out.println("\nВсего ссылок: " + visited.size());
    }

    // Метод ищет ссылки на сайте
    private void findLinks(URLDepthPair link)
    {
        try {
            // Инициализация объекта класса с сылкой
            URL url = new URL(link.getURL());

            // Открываем соединение с сайтом(сервером)
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Инициализация объекта класса сканирования
            java.util.Scanner scanner = new java.util.Scanner(connection.getInputStream());

            // Выявление ссылок по шаблону 
            while (scanner.findWithinHorizon("<a\\s+(?:[^>]*?\\s+)?href=([\"'])(.*?)\\1", 0) != null) {
                String newURL = scanner.match().group(2);
                createNewLink(newURL, link);
            }
        }
        catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    // Метод формирует ссылку для записи в список
    private void createNewLink(String newURL, URLDepthPair link){
        // Формируем ссылку
        if (newURL.startsWith("/")) {
            newURL = link.getURL() + newURL;
        }
        else if (!newURL.startsWith("https")) return;

        // Создаем новую пару(ссылка и ее глубина)
        URLDepthPair newLink = new URLDepthPair(newURL, link.getDepth() + 1);
        // Записываем пару в список
        notVisited.add(newLink);
    }

    // Запуск программы
    public static void main(String[] args) {
        Crawler crawler = new Crawler("https://vk.com",2);
        crawler.run();
    }
}

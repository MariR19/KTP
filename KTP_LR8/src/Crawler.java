// Класс запускает сканирование
public class Crawler {

    // Хранит ссылку
    private String URL;
    // Максимальная глубина поиска
    private static int maxDepth;
    // Счетчик всех потоков
    public static int CountThreads;
    // Ожидающие потоки
    public static int WaitingThreads = 0;
    // Счетчик ссылок
    public static int CountURLs = 0;

    // Метод возвращает максимальную глубину
    public static int getMaxDepth() { return maxDepth; }

    // Конструктор класса
    public Crawler(String URL, int maxDepth, int countThreads){
        this.URL = URL;
        Crawler.maxDepth = maxDepth;
        Crawler.CountThreads = countThreads;
    }

    // Запуск сканера и создание нового потока
    public void run() {
        CrawlerTask task = new CrawlerTask(new URLDepthPair(URL,0));
        task.start();
    }

    // Вывод результатов
    private static void printResult(){
        System.out.println("\nВсего ссылок: " + CountURLs);
    }

    // Запуск программы
    public static void main(String[] args){
        Crawler crawler = new Crawler("https://mos.ru",2 ,10);
        crawler.run();

        Runtime.getRuntime().addShutdownHook(new Thread(()->printResult()));
    }
}

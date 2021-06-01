import java.net.*;
import java.util.Scanner;


// Класс-наследник выполняющийся в потоке (наследуется от класса реализующего интерфейс Runnable)
public class CrawlerTask extends Thread {

    // Ссылка на пул(список) адресов
    private URLPool pool;

    // Конструктор класса
    public CrawlerTask(URLDepthPair link) {
        this.pool = new URLPool();
        pool.addLink(link);
    }

    // Перезаписанный метод запускает поток
    @Override
    public void run() {
        // Получет ссыку из пула
        URLDepthPair link = pool.getLink();
        // Вывод ссылки в консоль
        System.out.println(link.toString());
        Crawler.CountURLs++;
        // Если ссылка максимальной глубины,то дальше не ищем
        if (link.getDepth() == Crawler.getMaxDepth()) return;

        // Поиск новых ссылок
        findLinks(link);
    }

    // Метод поиска новых ссылок на сайте
    private void findLinks(URLDepthPair link) {
        try {
            // Инициализация объекта класса с сылкой
            URL url = new URL(link.getURL());

            // Открываем соединение с сайтом(сервером)
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Инициализация объекта класса сканирования
            Scanner scanner = new Scanner(connection.getInputStream());

            // Выявление ссылок по шаблону
            while (scanner.findWithinHorizon("<a\\s+(?:[^>]*?\\s+)?href=([\"'])(.*?)\\1", 0) != null) {
                String newURL = scanner.match().group(2);
                URLDepthPair newLink = createNewLink(newURL, link);
                //Если ссылка рабочая, запсукаем новый поток с анализом новой ссылки
                if (newLink == null) continue;
                CreateNewThread(newLink);
            }
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    // Метод формирует ссылку для записи в список (протокол://адрес сайта/адрес страницы сайта/...)
    private URLDepthPair createNewLink(String newURL, URLDepthPair link) {
        // Формируем ссылку
        if (newURL.startsWith("/")) {
            newURL = link.getURL() + newURL;
        }
        else if (!newURL.startsWith("https")) return null;

        // Возвращает новую пару(ссылка и ее глубина) для анализа
        return new URLDepthPair(newURL, link.getDepth() + 1);
    }

    // Создает и запускает новый поток с анализом ссылки
    private void CreateNewThread(URLDepthPair link) {
        CrawlerTask task = new CrawlerTask(link);
        task.start();
    }
}

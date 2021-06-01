import java.util.*;


// Класс хранит список всех ссылок
public class URLPool {

    // Список посещенных ссылок
    private HashMap<String, URLDepthPair> visited;
    // Связанный список не посещенных ссылок
    private LinkedList<URLDepthPair> pool;

    // Констрктор класса
    public URLPool(){
        // Инициализация списков
        visited = new HashMap<>();
        pool = new LinkedList<>();
    }

    // Синхронизированный метод возвращает первую доступную для анализа ссылку
    public synchronized URLDepthPair getLink(){
        boolean isWaiting = false;

        // Если ссылок нет, поток ждет
        if(pool.size() == 0) {
            try {
                Crawler.WaitingThreads++;
                isWaiting = true;
                // Если все потоки заняты, то заавершить программу
                if(Crawler.WaitingThreads == Thread.activeCount()) {
                    System.err.println("Все потоки заняты");
                    System.exit(0);
                };
                // Приостановить поток (--> метод addLink)
                this.wait();
            }
            catch (Exception e) { return null; }
        }

        //Если поток ждал, нужно уменьшить счетчик
        if(isWaiting) Crawler.WaitingThreads--;

        //Извлекаем первую ссылку из списка
        URLDepthPair link = pool.pop();
        //Добавляем ссылку в набор посещенных
        visited.put(link.getURL(),link);
        return link;
    }

    // Синхронизированный метод добавляет ссылку в пул и уведомляет потоки
    public synchronized void addLink(URLDepthPair link){
        if(!visited.containsKey(link.getURL())) {
            pool.add(link);
            // Запустить поток
            this.notify();
        }
    }
}

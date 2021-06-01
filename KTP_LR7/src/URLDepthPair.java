// Класс хранит в себе пару(ссылка и ее глубина)
public class URLDepthPair {
    private String URL;
    private int depth;

    // Метод возвращает глубину
    public int getDepth() {
        return depth;
    }

    // Метод возвращает ссылку
    public String getURL() {
        return URL;
    }

    // Конструктор класса
    public URLDepthPair(String URL, int depth) {
        this.URL = URL;
        this.depth = depth;
    }

    // Перезаписанный метод для текстового представления объекта
    @Override
    public String toString() {
        return "глубина: " + depth + " URL: [" + URL + "]";
    }
}

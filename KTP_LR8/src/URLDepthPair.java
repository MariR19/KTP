// Класс хранит в себе пару(ссылка и ее глубина)
public class URLDepthPair {

    // Адрес
    private String URL;
    // Глубина
    private int depth;

    // Метод возвращает глубину и ссылку
    public int getDepth()  { return depth; }
    public String getURL() { return URL; }

    // Констркутор класса
    public URLDepthPair(String URL, int depth){
        this.URL = URL;
        this.depth = depth;
    }

    // Перезаписанный метод возвращает строковое представление пары
    @Override
    public String toString() {
        return "глубина: " + depth + " URL: ["+ URL + "]";
    }
}

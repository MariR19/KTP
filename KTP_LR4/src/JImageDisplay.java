import java.awt.*;
import java.awt.image.BufferedImage;


// Класс-наследник представляет компонент отображения фрактала
public class JImageDisplay extends javax.swing.JComponent {

    // Объект класса содержит изображение и управляет им
    private java.awt.image.BufferedImage image;

    // Конструктор класса, принимает ширину и высоту изображения и строит его
    public JImageDisplay(int width, int height){

        //Инициализация объекта класса с заданной шириной, высотой и цветовым профилем RGB
        image=new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        //Объект класса, который создает двумерную плоскость
        Dimension dimension = new Dimension(width,height);
        //Метод, устанавливающий размер компонента
        super.setPreferredSize(dimension);
    }

    // Перезаписанный метод отрисовки изображения
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image,0,0,image.getWidth(), image.getHeight(),null);
    }

    // Метод очищает изображение
    public void clearImage(){
        for (int x=0;x<image.getWidth();x++){
            for(int y=0;y<image.getHeight();y++){
                image.setRGB(x,y,0);
            }
        }
    }

    // Метод отрисовывает пиксель по указаным координатам x и y цветом rgbColor(нужный нам цвет)
    public void drawPixel(int x, int y, int rgbColor){
        image.setRGB(x,y,rgbColor);
    }
}

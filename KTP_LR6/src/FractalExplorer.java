import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import javax.swing.filechooser.FileFilter;


// Основной класс (наследник) стоит окно приложения
public class FractalExplorer extends JFrame {


    // *Колличество оставшихся для обработки строк фрактала
    private int rowsRemaining;

    // Размер изображения
    private int screenSize;
    // Объект компонента отображения фрактала
    private JImageDisplay display;

    // Объект генератора фракталов
    private FractalGenerator fractal;

    // Объекты фракталов (объект класса фрактала, приобразованный к типу FractalGenerator)
    private FractalGenerator mandelbrot = new Mandelbrot();
    private FractalGenerator tricorn = new Tricorn();
    private FractalGenerator burningShip = new BurningShip();

    // Объект плоскости отрисовки фрактала
    private Rectangle2D.Double range;

    // *Инициализация элементов управления (кнопок, комбобокса)
    private JButton resetButton = new JButton("Reset");
    private JButton saveButton = new JButton("Save");
    private JComboBox comboBox = new JComboBox();

    // Конструктор, принимающий рамзер окна (длину или ширину)
    public FractalExplorer(int size){
        screenSize = size;
        display = new JImageDisplay(size,size);
        fractal = mandelbrot;
        range = new Rectangle2D.Double();
        fractal.getInitialRange(range);
    }

    // Метод выводит графическое изображение
    public void createAndShowGui(){

        // Установка слоя для компонента отображения
        display.setLayout(new BorderLayout());

        // Инициализация окна
        JFrame frame = new JFrame("Fractal Explorer");

        // Инициализация лейбла
        JLabel label = new JLabel();

        // Инициализация панелей
        JPanel upPanel = new JPanel();
        JPanel downPanel = new JPanel();

        // Установка текста для верхней панели
        label.setText("Fractal:");

        // Установка содержания выпадающего списка
        comboBox.addItem(mandelbrot);
        comboBox.addItem(tricorn);
        comboBox.addItem(burningShip);

        // Установка команд для кнопок
        resetButton.setActionCommand("Reset");
        saveButton.setActionCommand("Save");

        // Установка логики для кнопок
        ButtonHandler handler = new ButtonHandler();
        resetButton.addActionListener(handler);
        saveButton.addActionListener(handler);

        // Установка логики на нажатие кнопки мышки
        MouseHandler click = new MouseHandler();
        display.addMouseListener(click);

        // Установка логики на выбор из списка
        ComboBoxHandler switchHandler = new ComboBoxHandler();
        comboBox.addActionListener(switchHandler);

        // Настройки верхней панели
        upPanel.add(label);
        upPanel.add(comboBox);

        // Настройка нижней панели
        downPanel.add(saveButton);
        downPanel.add(resetButton);

        // Добавление элементов в слой окна (фрактал и кнопка)
        frame.add(display, BorderLayout.CENTER);
        frame.add(downPanel, BorderLayout.SOUTH);
        frame.add(upPanel, BorderLayout.NORTH);

        // Границы и выход по нажатию на кнопку закрытия
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0,0, 1000, 1000);

        // Формирование окна
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    // *Метод рисует фрактал при помощи другого потока
    private void drawFractal(){
        enableUI(false);
        rowsRemaining=screenSize;

        for(int i=0;i<screenSize;i++){
            FractalWorker worker = new FractalWorker(i);
            worker.execute();
        }
    }

    // *Метод включения и выключения элементов управления
    private  void enableUI(boolean enable){
        resetButton.setEnabled(enable);
        saveButton.setEnabled(enable);
        comboBox.setEnabled(enable);
    }

    // Внутренний класс для отработки нажатия на кнопки
    private class ButtonHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // Принимет команду от кнопки
            String command = e.getActionCommand();

            if (command.equals("Save")){
                // Окно для выбора файла
                JFileChooser chooser = new JFileChooser();

                // Фильтры для файла png
                FileFilter filter = new FileNameExtensionFilter("PNG Images",".png");
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);

                // Получение ответа пользователя
                int answer = chooser.showSaveDialog(display);
                if(answer==JFileChooser.APPROVE_OPTION){
                    File file = chooser.getSelectedFile();
                    try {
                        ImageIO.write(display.image,"png",file);
                    }
                    catch (Exception exception){
                        JOptionPane.showMessageDialog(display,exception.getMessage(),"Can't save image!",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            else{
                fractal.getInitialRange(range);
                drawFractal();
            }
        }
    }

    // Внутренний класс для действий с комбобоксом(выподающий список) реализует интерфейс
    private class ComboBoxHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox source = (JComboBox) e.getSource();
            fractal = (FractalGenerator) source.getSelectedItem();
            fractal.getInitialRange(range);
            drawFractal();
        }
    }

    // Внутренний класс для отработки нажатия на кнопку мышки
    private class MouseHandler extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            int x=e.getX();
            int y=e.getY();

            double xCoord = fractal.getCoord(range.x, range.x + range.width, screenSize, x);
            double yCoord = fractal.getCoord(range.y,range.y+range.height, screenSize,y);

            fractal.recenterAndZoomRange(range,xCoord,yCoord,0.5);

            drawFractal();
        }
    }

    // *Внутренний класс для вычисления цвета строки фрактала в отдельном потоке
    private class FractalWorker extends SwingWorker<Object,Object>{

        // Координата y строчки
        public int yCoord;
        // Массив цветов пикселей
        private int[] pixelsRGB;

        // Конструктор
        public FractalWorker(int yCoord){
            this.yCoord = yCoord;
        }

        // Перезаписанный метод рассчитывает цвет пикселей на фоне
        @Override
        protected Object doInBackground() throws Exception {
            // Инициализация массива
            pixelsRGB = new int[screenSize];

            // Перебор строки пикселей
            for (int x = 0; x < screenSize; x++) {
                double xCoord = fractal.getCoord(range.x, range.x + range.width, screenSize, x);
                double yCoord = fractal.getCoord(range.y, range.y + range.height, screenSize, this.yCoord);

                int iteration = fractal.numIterations(xCoord, yCoord);

                if (iteration == -1) {
                    pixelsRGB[x]=0;
                } else {
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    pixelsRGB[x]=rgbColor;
                }
            }
            return null;
        }

        // *Перезаписанный метод вызывается по завершению doInBackground
        @Override
        protected void done(){
            for (int i=0;i<screenSize;i++){
                display.drawPixel(i,yCoord,pixelsRGB[i]);
            }
            display.repaint(0,0,yCoord,screenSize,1);
            rowsRemaining--;
            if(rowsRemaining==0){
                enableUI(true);
            }
        }

    }

    // Метод запуска программы
    public static void main(String[] args){
        FractalExplorer explorer = new FractalExplorer(800);
        explorer.createAndShowGui();
        explorer.drawFractal();
    }

}

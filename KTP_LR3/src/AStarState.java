import java.util.HashMap;
import java.util.Map;

/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState
{
    // Коллекция для хранения открытых и закрытых точек
    private HashMap<Location,Waypoint> openWaypoint=new HashMap<>();
    private HashMap<Location,Waypoint> closeWaypoint=new HashMap<>();
    // <координаты,точки>

    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;


    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    // Метод возвращает точку, путь к которой до конца будет наименьшим
    public Waypoint getMinOpenWaypoint()
    {
        //Если коллекция пустая возвращаем null
        if (openWaypoint.isEmpty()){
            return null;
        }
        else {
            //Для того, чтобы получить первый ключ коллекции
            Map.Entry<Location,Waypoint> entry = openWaypoint.entrySet().iterator().next();
            //Пусть первый элемент - искомый
            Location minKey = entry.getKey();
            float min = openWaypoint.get(minKey).getTotalCost();
            //Временная переменная для хранения пути текущей точки
            float temp;

            //Перебор всех элементов коллекции
            for (Location curLoc : openWaypoint.keySet()) {
                temp = openWaypoint.get(curLoc).getTotalCost();
                if (temp < min) {
                    min = temp;
                    minKey = curLoc;
                }
            }
            return openWaypoint.get(minKey);
        }
    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    // Метод добавляет коллекцию открых вершин
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        //Если такая точка уже есть
        if (openWaypoint.containsValue(newWP)){
            //Ключ для старой вершины
            Location key=new Location();

            //Поиск старой вершины в коллекции
            for(Location curLoc:openWaypoint.keySet()){
                if (openWaypoint.get(curLoc).equals(newWP)){
                    key=curLoc;
                    break;
                }
            }

            //Запись оставшегося пути в переменную для каждой вершины
            float oldPath=openWaypoint.get(key).getRemainingCost();
            float newPath= newWP.getRemainingCost();

            //Если путь через новую вершину короче, записываем ее в коллекцию
            if (oldPath>newPath){
                openWaypoint.put(key,newWP);
                return true;
            }
            else{
                return false;
            }

        }
        else{
            openWaypoint.put(newWP.getLocation(),newWP);
            return true;
        }
    }


    /** Returns the current number of open waypoints. **/
    // Метод возвращает колличество не закрашенных (открытых) точек
    public int numOpenWaypoints()
    {
        return openWaypoint.size();
    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    // Метод для закрашивания (закрытия) точки
    public void closeWaypoint(Location loc)
    {
        // Добавляем точку в коллекцию закрытых вершин
        Waypoint temp=openWaypoint.get(loc);
        closeWaypoint.put(loc,temp);
        // Удаляем эту точку из коллекции открытых вершин
        openWaypoint.remove(loc);
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    // Метод возвращает true, если точка закрашена (закрыта)
    public boolean isLocationClosed(Location loc)
    {
        if (closeWaypoint.containsValue(loc)){
            return true;
        }
        else {
            return false;
        }
    }
}


package blatt04;
import java.util.LinkedList;
import java.util.List;
public class ConvexHull
{
    public static List<Point> simpleConvex(Point[] p){
        boolean valid = false;
        LinkedList<Point> convexhull = new LinkedList<Point>();

        for(Point t : p){
            for(int i = 0; i<p.length;i++){
                valid = true;
                for(int j = 0; j<p.length;j++){
                    //falls der gefundene Punkt einer der beiden Punkte aus pq ist
                    if(p[j].get(0) == t.get(0) && p[j].get(1) == t.get(1) || p[j].get(0) == p[i].get(0) && p[j].get(1) == p[i].get(1)){
                        //Punkt ist einer der beiden Punkte der gerichteten Gerade --> tue nichts
                    }else{
                        //Punkt ist nicht einer der vorhandenen Punkte aus pq
                        //berechne Kreuzprodukt aus: (j-t)X normalenvektor von (t-i)
                    	Point jp = new Point(2, p[j].get(0) - t.get(0),p[j].get(1) - t.get(1));
                    	Point ti = new Point(2, t.get(0) - p[i].get(0),t.get(1) - p[i].get(1));
                        //2d kreuzprodukt
                        double erg = (jp.get(0)*ti.get(1)) - (jp.get(1)*ti.get(0));
                        if(erg <= 0 ){
                            //wenn erg kleiner oder gleich 0 ist, so liegt der Punkt auf der geraden oder rechts davon
                            valid = false;
                            //da valid nun gleich false gesetzt wurde und eine Setzung auf true nur in der naechst
                            //aeusseren schleife erfolgen kann --> break
                            break;
                        }
                    }
                }
                if(valid == true){
                	//fuege den Punkt zu der LinkedList hinzu
                    convexhull.add(p[i]); 
                }
            }
        }
        return convexhull;
    }
}

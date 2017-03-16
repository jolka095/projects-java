// programme which detects collision between points
import java.util.*;
public class Collision implements CollisionInterface {
	
	Point2D[] arrayOfPoints = null;
	
	public void setNumberOfObjects(int value) {
		arrayOfPoints = new Point2D[value];
		for(int i = 0; i<value; i++){
			arrayOfPoints[i] = new Point2D(0,0);
		}
	}

	public void setInitialPosition(int objectID, Point2D ip) {
		for(int i = 0; i<arrayOfPoints.length; i++){
			if(i == objectID){
				arrayOfPoints[objectID] = ip;
				//System.out.println(arrayOfPoints[objectID].toString());
			}
		}
	}

	public void moveObject(int objectID, int stepLength, int angle) {
		Helper moveHelper = new Helper();
		for(int i = 0; i<arrayOfPoints.length; i++){
			if(i == objectID){
				Point2D movePoint = moveHelper.move(arrayOfPoints[objectID], stepLength, angle);
				arrayOfPoints[objectID] = movePoint;
				//System.out.println("Punkt o ID = " + objectID +" przeniesiony na pozycjê: " + movePoint.toString());
			}
		}
	}

	public int[] getCollictionsOfObject(int objectID) {
		Point2D searchPoint = null;
		List<Integer> indexDynamicArr = new ArrayList<Integer>();
		int len = arrayOfPoints.length;
		
		for(int i = 0; i<len; i++){ 	// szukamy punktu o danym ID
			if(i == objectID){
				searchPoint = arrayOfPoints[i];
			}
		}
		

		for(int j = 0; j<len; j++){		// przeszukujemy tablicê punktów w poszukiwaniu kolizji
			if(arrayOfPoints[j].equals(searchPoint) && !(arrayOfPoints[j] == searchPoint)) indexDynamicArr.add((int)j);
		}
		
		int[] idCollisionArr = new int[indexDynamicArr.size()];
		int i = 0;
		for(Integer indexInt : indexDynamicArr){
			idCollisionArr[i] = (int) indexInt;		//przepisujemy indeksy z tablicy dynamicznej do zwyklej tablicy int
			i++;
		}
		return idCollisionArr;
	}

	public Point2D getPosition(int objectID) {
		Point2D p = null;
		for(int i = 0; i<arrayOfPoints.length; i++){
			if(i == objectID){
				p = arrayOfPoints[i];
			}
		}
		return p;
	}
}
// finding treasures by visiting many rooms
import java.util.List;
import java.util.ArrayList;

public class TreasureFinder extends AbstractTreasureFinder{
	
	List<Treasure> boxOfTreasures = new ArrayList<Treasure>();
	
	public void setHall( Room room ){
		if(room != null) {
			if(room.getTreasure() != null){
				boxOfTreasures.add(room.getTreasure());
				System.out.println("Dodano " + room.getTreasure().getValue());
			} // no treasure
			
			if(room.getNumberOfDoors() != 0){
				for(int i = 0; i < room.getNumberOfDoors(); i++){
					//System.out.println(i);
					setHall(room.getDoor(i));
				}
			} // no exit
		} // null input
	}
	
	public int howManyGoldenCoins(){
		int numOfCoins = 0;
		for(Treasure t : boxOfTreasures){
			if(t instanceof GoldenCoin)	numOfCoins++;
		}
		return numOfCoins;
	}

	public int howManyDiamonds(){
		int numOfDiam = 0;
		for(Treasure t : boxOfTreasures){
			if(t instanceof Diamond) numOfDiam++;
		}
		return numOfDiam;
	}

	public Treasure[] getTreasures(){
		Treasure[] arrOfAllTreasures = new Treasure[boxOfTreasures.size()];
		int i = 0;
		for(Treasure t : boxOfTreasures){
			arrOfAllTreasures[i] = t;
			i++;
		}
		return arrOfAllTreasures;
	}

	public int getTotalValue(){
		int allDiamonds = 0;
		int allCoins = 0;
		int allOtherTreasures = 0;
		
		for(Treasure t : boxOfTreasures){
			if(t instanceof Diamond){
				allDiamonds += t.getValue();
			} else if(t instanceof GoldenCoin){
				allCoins += t.getValue();
			} else {
				if(t != null) allOtherTreasures += t.getValue();
			}
		}
		return (allDiamonds + allCoins + allOtherTreasures);		
	}

	public static void main(String[] args){
		
		Room a = new Room(new Room[0], null);
		Room b = new Room(new Room[0], new GoldenCoin(18));
		
		Room[] ab = {a,b}; 
		Room c = new Room(ab, new GoldenCoin(14));
		//Room c = null;
		Room d = new Room(new Room[0], new Diamond(17));
		Room e = new Room(new Room[0], new GoldenCoin(16));
		Room[] ed = {e,d};
		Room f = new Room(ed, new GoldenCoin(12));
		//Room e = null;
		Room g = new Room(new Room[0], new Diamond(13));
		Room[] cfg = {f,g,c};
		Room h = new Room(cfg, null);
		Room i = new Room(new Room[0], new GoldenCoin(15));
		Room[] ii = {i};
		Room j = new Room(ii, new Diamond(10));
		Room[] hj = {h,j};
		Room k = new Room(hj, new Diamond(6));	
		Room l = new Room(new Room[0], new GoldenCoin(5));
		Room[] lk = {l,k};
		Room m = new Room(lk, new GoldenCoin(3));

		Room o = new Room(new Room[0], new GoldenCoin(11));
		Room[] oo = {o};
		Room p = new Room(oo, new GoldenCoin(7));
		Room r = new Room(new Room[0], new GoldenCoin(8));
		Room[] pr = {p,r};
		Room s = new Room(pr, new Diamond(4));
		Room t = new Room(new Room[0], null);
		Room[] st = {t,s};
		Room u = new Room(st, new GoldenCoin(2));
		Room[] mu = {u,m};
		Room n = new Room(mu, new Diamond(1));
		
		TreasureFinder finder = new TreasureFinder();
		//finder.setHall(n);

//		System.out.println("\nilosc diamentow: " + finder.howManyDiamonds());
//		System.out.println("ilosc monet: " + finder.howManyGoldenCoins());

//		System.out.println("wartosc wszystkich skarbow: " + finder.getTotalValue());

		
//		Treasure[] trr = finder.getTreasures();
//		for(int iii = 0; iii<trr.length; iii++){
//			System.out.println(trr[iii].getValue());
//		}
	}
	
}
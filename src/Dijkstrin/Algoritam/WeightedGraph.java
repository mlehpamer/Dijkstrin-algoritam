package Dijkstrin.Algoritam;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javafx.util.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

import javafx.util.Pair;

public class WeightedGraph extends Graph{
	
	private int[][] ucitajListuTezina() {
		int [][] polje=new int[2][10];
		File listaSusjedstva=new File("Dokumentacija/Primjer_oi.tez");
		try{
			Scanner zapis=new Scanner(listaSusjedstva);
			int j=0;
			while(zapis.hasNextLine()) {
				int k=0;
				String redak=zapis.nextLine();
				char znak[]=new char[30];
				char znak1[]=new char[5];
				for(int i=0;i<redak.length();i++) {
					znak[i]=redak.charAt(i);
					if(znak[i]!=':' && znak[i]!=' ') {
						znak1[1]+=znak[i];
						polje[k][j]=Character.getNumericValue(znak1[1]);
					}					
					else {
						znak1[1]=0;		
					}
					if(znak[i]==':') {
						k++;						
					}
				}
				j++;
			}
			zapis.close();
		}catch(IOException e) {
			return null;
		}
		for( int z=0;z<10;z++) {
			for(int h=0;h<2;h++) {
				System.out.print(polje[h][z]+ " ");
			}
			System.out.println("\n");
		}
		return polje;
	}
	
	private String izracunajNajbržiPut() {
		int[][] matricaIncidencije=ucitajMatricuIncidencije();
		int [][] tezineBridova=ucitajListuTezina();
		int [][] matricaSusjedstva=matricaSusjedstva(matricaIncidencije);
		HashMap<Integer,Integer> skupS=new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> skupT=new HashMap<Integer,Integer>();
		skupS.put(1, 0);
		//List <Pair<Integer,Integer>> skupS=new ArrayList<Pair<Integer,Integer>>();
		//List <Pair<Integer,Integer>> skupT=new ArrayList<Pair<Integer,Integer>>();
		//List <Pair> skupT=new ArrayList<Pair>();
		//Pair parS=new Pair(1,0);
		//skupS.add(parS);	
			int cvor=1;
			for(int i=1;i<6;i++) {
				cvor++;
				if(matricaSusjedstva[0][i]==1) {
					int tezina=tezineBridova[1][cvor-2];
					skupT.put(cvor,tezina);
				//	Pair parT=new Pair(cvor,tezina);
					//skupT.add(parT);
				}
				else {
					skupT.put(cvor,999);
				//	Pair parT=new Pair(cvor,null);
				//	skupT.add(parT);
				}
			}
			//while(skupT!=null) {	
				//Pair parS2 =new Pair(skupT.contains(Collections.min(((Pair<Integer, Integer>) skupT).getValue())));
				//Pair parS2=new Pair(Collections.min(skupT.get);//(((Pair) skupT).getValue()));
				//skupS.add(parS2);
			//}
			//System.out.println("Skup S: "+Arrays.deepToString(skupS.toArray()));
			//System.out.println("Skup T: "+Arrays.deepToString(skupT.toArray()));
			//System.out.println(value);
			
			//while(skupT!=null) {
				Integer min=Collections.min(skupT.values());
				int kljuc=0;
				for(int key: skupT.keySet()) {
				    if(skupT.get(key).equals(min)) {
				    	skupS.put( key,min);
				    	kljuc=key;
				    }
				}
				skupT.remove(kljuc,min);
				for(int i=0;i<6;i++) {
					if(i!=kljuc-1 && matricaSusjedstva[kljuc][i]==1) {
						
					}
				}
				
			//}
			System.out.print("Skup S: ");
			Set set = skupS.entrySet();
		      Iterator iterator = set.iterator();
		      while(iterator.hasNext()) {
		         Map.Entry mentry = (Map.Entry)iterator.next();
		         System.out.print(mentry.getKey() + "-");
		         System.out.print(mentry.getValue()+", ");
		      }
		      
		      System.out.print("\nSkup T: ");
			Set set1 = skupT.entrySet();
		      Iterator iterator1 = set1.iterator();
		      while(iterator1.hasNext()) {
		         Map.Entry mentry = (Map.Entry)iterator1.next();
		         System.out.print(mentry.getKey()+"-");
		         System.out.print(mentry.getValue()+", ");
		      }
		return "";
	}
	

	public static void main(String[] args) {
			WeightedGraph tezinskiGraf=new WeightedGraph();
			System.out.println("Tezine grafa");
			tezinskiGraf.ucitajListuTezina();
			tezinskiGraf.izracunajNajbržiPut();
			
	}
}

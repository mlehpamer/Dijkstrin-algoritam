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
import java.util.LinkedHashMap;
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
		return polje;
	}
	
	public void listaTezinaToString(int polje[][]) {
		for( int z=0;z<10;z++) {
			for(int h=0;h<2;h++) {
				System.out.print(polje[h][z]+ " ");
			}
			System.out.println("\n");
		}
	}
	
	private void izracunajNajbržiPut() {
		int[][] matricaIncidencije=ucitajMatricuIncidencije();
		int [][] tezineBridova=ucitajListuTezina();
		int [][] matricaSusjedstva=matricaSusjedstva(matricaIncidencije);
		LinkedHashMap<Integer,Integer> skupS=new LinkedHashMap<Integer,Integer>();
		LinkedHashMap<Integer,Integer> skupT=new LinkedHashMap<Integer,Integer>();
		skupS.put(1, 0);
			int cvor=1;
			for(int i=1;i<6;i++) {
				cvor++;
				if(matricaSusjedstva[0][i]==1) {
					int tezina=tezineBridova[1][cvor-2];
					skupT.put(cvor,tezina);
				}
				else {
					skupT.put(cvor,999);
				}
			}
			 System.out.println("------------------------------------------");
			while(!skupT.isEmpty()) {
				
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
			      System.out.println();
			      System.out.println("------------------------------------------");
			      
				Integer min=Collections.min(skupT.values());
				int kljuc=0;
				for(int key: skupT.keySet()) {
				    if(skupT.get(key).equals(min)) {
				    	skupS.put( key,min);
				    	kljuc=key;
				    }
				}			      
				skupT.remove(kljuc,min);
				int linija=0;
				int tezina=0;

				for(int i=0;i<10;i++) {
					linija++;
					if(matricaIncidencije[i][kljuc-1]==1) {
						for(int j=1;j<6;j++) {
							if(matricaIncidencije[i][j]==1 && j!=kljuc-1 && !skupS.containsKey(j+1)) {
								tezina=tezineBridova[1][linija-1];
								System.out.println("Cvor " +kljuc+ " je povezan sa cvorom "+(j+1)+" putem linije "+linija+ " tezine "+tezina);
								int tezinaCvoraOdIshodista=skupT.get(j+1);
								int tezinaKljuca=skupS.get(kljuc)+tezina;
								skupT.replace(j+1,Math.min(tezinaCvoraOdIshodista, tezinaKljuca));
							}
						}
					}
				}
				
			}
			System.out.println("Put obrnutim redoslijedom");
			System.out.print("6-");
			for(int i=skupS.size();i>0;i--) {
				int tezinaUnutarSkupa=0;
				int direktnaTezina=0;
				for(int j=0;j<6;j++) {
					if(matricaSusjedstva[i-1][j]==1) {
						tezinaUnutarSkupa=skupS.get(i)-skupS.get(j+1);
						for(int k=0;k<10;k++) {
							if(matricaIncidencije[k][j]==1 && matricaIncidencije[k][i-1]==1) {
								direktnaTezina=tezineBridova[1][k];
								if(tezinaUnutarSkupa==direktnaTezina) {
									System.out.print(j+1+"-");								
									i=j+2;
								}
							}
						}
					}
				}
			}
	}
	

	public static void main(String[] args) {
			WeightedGraph tezinskiGraf=new WeightedGraph();
			System.out.println("Tezine grafa");
			tezinskiGraf.listaTezinaToString(tezinskiGraf.ucitajListuTezina());
			System.out.println("Izracun putanje");
			tezinskiGraf.izracunajNajbržiPut();
			
	}
}

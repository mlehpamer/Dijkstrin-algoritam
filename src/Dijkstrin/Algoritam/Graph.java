package Dijkstrin.Algoritam;

import java.io.*;
import java.util.Scanner;

public class Graph {
	
	public Graph() {
		System.out.println("Matrica incidencije: ");
		int [][] matricaIncidencije=ucitajMatricuIncidencije();
		System.out.println("Lista susjedstva: ");
		listaSusjedstva(matricaIncidencije);
		System.out.println("Matrica susjedstva: ");
		matricaSusjedstva(matricaIncidencije);

	}
	
	public int[][] ucitajMatricuIncidencije() {
		int [][] polje=new int[10][6];
		try {
			File matrica=new File("Dokumentacija/Primjer_oi.minc");
			Scanner citac=new Scanner(matrica);
			int j=0;
			while(citac.hasNextLine()) {
				int k=0;
				String zapis=citac.nextLine();
				char [] znak=new char[20];
				for(int i=0; i<zapis.length();i++) {
					znak[i]=zapis.charAt(i);
					if(znak[i] !=',' && znak[i]!= ' '){
						polje[k][j]=Character.getNumericValue(znak[i]);
						System.out.print(polje[k][j]+" ");
						k++;
					}
					if(znak[i]==' ') {
						System.out.println();
					}
				}
				j++;
			}
			citac.close();
		}catch(IOException e){
			System.out.println("Ne radi");
		}
		return polje;
	}
	
	public void listaSusjedstva(int [][] matricaIncidencije) {
		int cvor=0;
		int cvor1;
		for(int i=0;i<6;i++) {
			System.out.print(cvor+1 + ":");
			for(int j=0;j<10;j++) {
				if(matricaIncidencije[j][i]==1) {
					cvor=i+1;
					for(int k=0;k<6;k++) {
						if(cvor!=k+1 && matricaIncidencije[j][k]==1) {
							cvor1=k+1;
							System.out.print(cvor1+",");
						}
					}
				}
				
			}
			System.out.println();
		}
	}
	
	public int[][] matricaSusjedstva(int[][] matricaIncidencije){
		int[][] matricaSusjedstva=new int[6][6];
		int cvor=0;
		for(int i=0;i<6;i++) {
			for(int j=0;j<10;j++) {
				if(matricaIncidencije[j][i]==1) {
					cvor=i+1;
					for(int k=0;k<6;k++) {
						if(cvor!=k+1 && matricaIncidencije[j][k]==1) {
							matricaSusjedstva[i][k]=1;
						}
					}
				}
			}
		}
		for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				System.out.print(matricaSusjedstva[j][i]+ " ");
			}
			System.out.println();
		}
		return matricaSusjedstva;
	}
}
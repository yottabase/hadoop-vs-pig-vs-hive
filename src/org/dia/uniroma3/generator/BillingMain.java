package org.dia.uniroma3.generator;

import java.io.IOException;

public class BillingMain {
	
public static void main(String[] args) throws IOException {
		
		// Comando da usare per generare il dataset
		
		// prima bisogna configurare il generatore di scontrini
		/* al costruttore va passato un file che contiene in chiaro la lista di
		 * cibi da cui pescare
		 * 
		 * tale file è nella cartella billing
		 * il file food può tranquillamente essere editato aggiungendo nuovi
		 * cibi (uno per riga)
		 */

		ItemBillingGenerator IB = new ItemBillingGenerator("data/generator/billing/food.txt");
		
		/* quindi bisogna richiamare la funzione generate in cui bisogna passare:
		 * - il nome del file in cui generare il dataset
		 * - il numero di righe del file (nell'esempio 10)
		 * - il numero massimo di cibi per scontrino (nell'esempio 5)
		 * - la data viene generata in modo randomico nel formato yyyy-mm-dd
		 */
		IB.generate("data/generator/sample/esempio.txt", 10, 10);
		
		// big big big data!
		IB.generate("data/generator/sample/esempio-xs.txt",   1000, 10); 	//   1k
		IB.generate("data/generator/sample/esempio-sm.txt", 100000, 10);	// 100k
		IB.generate("data/generator/sample/esempio-md.txt", 500000, 10);	// 500k
		IB.generate("data/generator/sample/esempio-lg.txt",1000000, 10);	//1000k
		IB.generate("data/generator/sample/esempio-xl.txt",100000000, 10);	//100kk
		System.out.println("done");
	}

}

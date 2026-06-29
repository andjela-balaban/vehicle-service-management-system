package org.unibl.etf.supplier.server;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.unibl.etf.model.Item;
import org.unibl.etf.supplier.parser.ItemScraper;

public class Main {
	public static void main(String[] args) throws MalformedURLException, IOException {
		Item item = ItemScraper.scrape(new URL("https://www.silux.ba/proizvodi/dodatna-oprema/auto-zarulje/led-zarulje/zarulje-led-h4/3623307/led-zarulje-12v-5xsmd-bijela-1-komad?refurl=%2Fproizvodi%2Fdodatna-oprema%2Fauto-zarulje%2Fled-zarulje"));
		System.out.println(item);
	}
}

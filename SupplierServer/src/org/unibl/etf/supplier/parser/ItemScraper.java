package org.unibl.etf.supplier.parser;

import java.io.IOException;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.unibl.etf.model.Item;

public class ItemScraper {

	public static Item scrape(URL url) throws IOException {
		String code = null;
		String name = null;
		String price = null;
		String imageUrl = null;
		
		long startTime = System.currentTimeMillis();
        long maxDuration = 1000; 	// Limiting the sending of requests, if after 1s I don't get a response - return null
		
		do {
			
			String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36";
			String accept = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";
			Document doc = Jsoup.connect(url.toString())
					.userAgent(userAgent)
					.referrer("https://www.google.com")
					.header("Accept", accept)
					.header("Accept-Language", "en-US,en;q=0.9")
					.timeout(15000)
					.ignoreHttpErrors(true)
					.get();
				
			
			if (System.currentTimeMillis() - startTime > maxDuration) {
                System.out.println("Aborted: scraping takes too long.");
                return null;
            }
			
			// Part ID
			Element partIdElement = doc.selectFirst("#app > main > div > article > div > div:nth-child(2) > div.col-xxl-4.order-3.order-xxl-2 > ul > li:nth-child(2) > div > div.col-sm-6.col-value");
			if(partIdElement == null) {
				continue;
			}
			code = partIdElement.text().split(": ")[1];
			
			// Element name
			Element nameElement = doc.selectFirst("#app > main > div > article > div > div:nth-child(1) > div.col-lg-8.ml-auto > div > div.col > h1");
			if(nameElement == null) {
				continue;
			}
			name = (nameElement != null) ? nameElement.text() : "Not found";
			
			// Price
			Element priceElement = doc.selectFirst("#cart-form > ul > li:nth-child(1) > div.offer-part--price > span > span.price");
			if (priceElement != null) {
				price = priceElement.text().split(" ")[0].replace(",", ".");
			}
			
			// Image URL
			Element imageDiv = doc.selectFirst("#app > main > div > article > div > div:nth-child(2) > div.col-xxl-4.col-md-6.order-0.product-single--media.mb-4 > div > div");
			imageUrl = "Not found";
			if (imageDiv == null) {
				continue;
				
			}
			Element img = imageDiv.selectFirst("img");
			if (img == null) {
				continue;
				
			}
			imageUrl = img.absUrl("src");
			
			
		} while(code == null || name == null || price == null || imageUrl == null);
		return new Item(code, name, Double.parseDouble(price), new URL(imageUrl));
	}	

}

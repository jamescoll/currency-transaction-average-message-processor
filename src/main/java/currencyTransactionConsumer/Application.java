package currencyTransactionConsumer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import messageProcessor.MessageProcessorEngine;

import org.springframework.web.client.RestTemplate;

public class Application {

	private static final String filename = "transactionlog.txt";


	public static void main(String args[]) {


		RestTemplate restTemplate = new RestTemplate();
		ArrayList<CurrencyTransaction> transactions = new ArrayList<>();
		String urlString = "http://localhost:8080/currencyTransaction";
		MessageProcessorEngine messageProcessor = new MessageProcessorEngine();

		for (int i = 1; i < 6; i++) {

			CurrencyTransaction transaction = restTemplate.getForObject(urlString + "?transactionNumber=" + i, CurrencyTransaction.class);
			messageProcessor.processMessage(transaction, new Date());
			transactions.add(transaction);

		}

		for(CurrencyTransaction transaction : transactions){
			outputTransaction(transaction);	
		}

		saveTransactions(transactions, filename);

		displayStatsFromProcessor(messageProcessor);


	}

	private static void displayStatsFromProcessor(
			MessageProcessorEngine messageProcessor) {
		displayUserStats(messageProcessor);

		displayCurrencyFromStats(messageProcessor);
		displayCurrencyToStats(messageProcessor);
		showSortedTransactionDates(messageProcessor);

		showTransactionCount(messageProcessor);
	}

	private static void showTransactionCount(MessageProcessorEngine messageProcessor){
		System.out.println("Number of transactions");
		System.out.println(messageProcessor.getTransactionCount());
	}

	private static void displayCurrencyFromStats(
			MessageProcessorEngine messageProcessor) {
		
	
		
		HashMap<String, Integer> stats = messageProcessor.getCurrencyFromStats();

		System.out.println("Currency from Stats");

		for (Entry<String, Integer> entry : stats.entrySet())
		{
			
			System.out.println("Currency: " + entry.getKey() + "/" + "Count: "+ entry.getValue());
		}
	}
	
	private static void displayCurrencyToStats(
			MessageProcessorEngine messageProcessor) {
		
	
		
		HashMap<String, Integer> stats = messageProcessor.getCurrencyToStats();

		System.out.println("Currency to Stats");

		for (Entry<String, Integer> entry : stats.entrySet())
		{
			System.out.println("Currency: " + entry.getKey() + "/" + "Count: "+ entry.getValue());
		}
	}
	

	private static void displayUserStats(MessageProcessorEngine messageProcessor) {
		HashMap<String, Integer> stats = messageProcessor.getUserCountStats();

		System.out.println("User Count Stats");

		for (Entry<String, Integer> entry : stats.entrySet())
		{
			System.out.println("User: " + entry.getKey() + "/" + "Count: "+ entry.getValue());
		}
	}

	private static void saveTransactions(ArrayList<CurrencyTransaction> transactions, String filename){

		try {
			File file = new File(filename);

			if(!file.exists()){
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			writeTransactionRecords(transactions, bw);
			System.out.println("Successfully written file");

			bw.close();

		} catch (IOException e){
			e.printStackTrace();
		} 
	}

	private static void writeTransactionRecords(
			ArrayList<CurrencyTransaction> transactions, BufferedWriter bw)
					throws IOException {
		for(CurrencyTransaction transaction : transactions) {

			bw.write("*Transaction Record*");
			bw.newLine();
			bw.write("UserId: " + transaction.getUserId());
			bw.newLine();
			bw.write("Currency From: " + transaction.getCurrencyFrom());
			bw.newLine();
			bw.write("Currency To: "+ transaction.getCurrencyTo());
			bw.newLine();
			bw.write("Amount Sell: " + transaction.getAmountSell());
			bw.newLine();
			bw.write("Amount Buy: " + transaction.getAmountBuy());
			bw.newLine();
			bw.write("Rate: " + transaction.getRate());
			bw.newLine();
			bw.write("Time Placed: " + transaction.getTimePlaced());
			bw.newLine();
			bw.write("Originating Country: " + transaction.getOriginatingCountry());
			bw.newLine();

		}
	}

	private static void outputTransaction(CurrencyTransaction transaction){

		System.out.println("*Transaction Record*");
		System.out.println("UserId: " + transaction.getUserId());
		System.out.println("Currency From: " + transaction.getCurrencyFrom());
		System.out.println("Currency To: "+ transaction.getCurrencyTo());
		System.out.println("Amount Sell: " + transaction.getAmountSell());
		System.out.println("Amount Buy: " + transaction.getAmountBuy());
		System.out.println("Rate: " + transaction.getRate());
		System.out.println("Time Placed: " + transaction.getTimePlaced());
		System.out.println("Originating Country: " + transaction.getOriginatingCountry());
	}

	private static void showSortedTransactionDates(MessageProcessorEngine engine){

		System.out.println("Ordered Transaction Dates ");
		
		Collections.sort(engine.getTransactionDateList());

		for(Date date : engine.getTransactionDateList()){
			System.out.println(date.toString());
		}
	}

}
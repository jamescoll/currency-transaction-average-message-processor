package messageProcessor;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.text.SimpleDateFormat;

import currencyTransactionConsumer.CurrencyTransaction;

public class MessageProcessorEngine {
	
	private HashMap<String, Integer> userCountStats;
	private HashMap<String, Integer> currencyFromStats;
	private HashMap<String, Integer> currencyToStats;
	private HashMap<String, Integer> originatingCountryStats;
	private List<Date> transactionDateList;
	private List<Date> receivedDateList;
	private int transactionCount;
	
	public MessageProcessorEngine(){
		userCountStats = new HashMap<>();
		currencyFromStats = new HashMap<>();
		currencyToStats = new HashMap<>();
		originatingCountryStats = new HashMap<>();
		transactionDateList = new ArrayList<>();
		receivedDateList = new ArrayList<>();
		transactionCount = 0;
	}
	
	public void processMessage(CurrencyTransaction transaction, Date messageReceivedDateTime){
		
		transactionCount++;
		
		processUserCountStats(transaction);
		processCurrencyFromStats(transaction);
		processCurrencyToStats(transaction);
		processOriginatingCountryStats(transaction);
		processTransactionDate(transaction);
		processReceivedDate(new Date());
		
		
	}

	private void processTransactionDate(CurrencyTransaction transaction){
		
		
		DateFormat format = new SimpleDateFormat("d-MMMM-yyyy HH:mm:ss", Locale.ENGLISH);
		Date date;
		try {
			date = format.parse(transaction.getTimePlaced());
			transactionDateList.add(date);
		} catch (ParseException e) {
			
			System.out.println("Unable to process date");
		}
		
	}
	
private void processReceivedDate(Date receivedDate){
		
		
		receivedDateList.add(receivedDate);
		
	}
	
	private void processUserCountStats(CurrencyTransaction transaction) {
		Integer value = userCountStats.get(String.valueOf(transaction.getUserId()));
		
		if(value!=null)
			userCountStats.put(String.valueOf(transaction.getUserId()), ++value);
		else
			userCountStats.put(String.valueOf(transaction.getUserId()), 1);
	}
	
	private void processCurrencyFromStats(CurrencyTransaction transaction) {
		Integer value = currencyFromStats.get(String.valueOf(transaction.getCurrencyFrom()));
		
		if(value!=null)
			currencyFromStats.put(String.valueOf(transaction.getCurrencyFrom()), ++value);
		else
			currencyFromStats.put(String.valueOf(transaction.getCurrencyFrom()), 1);
	}
	
	private void processCurrencyToStats(CurrencyTransaction transaction) {
		Integer value = currencyToStats.get(String.valueOf(transaction.getCurrencyFrom()));
		
		if(value!=null)
			currencyToStats.put(String.valueOf(transaction.getCurrencyFrom()), ++value);
		else
			currencyToStats.put(String.valueOf(transaction.getCurrencyFrom()), 1);
	}
	
	private void processOriginatingCountryStats(CurrencyTransaction transaction) {
		Integer value = originatingCountryStats.get(String.valueOf(transaction.getCurrencyFrom()));
		
		if(value!=null)
			originatingCountryStats.put(String.valueOf(transaction.getCurrencyFrom()), ++value);
		else
			originatingCountryStats.put(String.valueOf(transaction.getCurrencyFrom()), 1);
	}

	private void resetStats(){
		
		transactionCount = 0;
		
		userCountStats.clear();
		currencyFromStats.clear();
		currencyToStats.clear();
		originatingCountryStats.clear();
		transactionDateList.clear();
		receivedDateList.clear();
	}

	public int getTransactionCount(){
		return transactionCount;
	}

	public HashMap<String, Integer> getUserCountStats() {
		return userCountStats;
	}



	public HashMap<String, Integer> getCurrencyFromStats() {
		return currencyFromStats;
	}





	public HashMap<String, Integer> getCurrencyToStats() {
		return currencyToStats;
	}





	public HashMap<String, Integer> getOriginatingCountryStats() {
		return originatingCountryStats;
	}





	public List<Date> getTransactionDateList() {
		return transactionDateList;
	}





	public List<Date> getReceivedDateList() {
		return receivedDateList;
	}

}

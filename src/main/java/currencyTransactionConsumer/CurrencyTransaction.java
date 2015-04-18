package currencyTransactionConsumer;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyTransaction {

	private long userId;
	private String currencyFrom;
	private String currencyTo;
	private BigDecimal amountSell;
    private BigDecimal amountBuy;
    private double rate;
    private String timePlaced;
    private String originatingCountry;
    
    
    public long getUserId() {
		return userId;
	}
	public String getCurrencyFrom() {
		return currencyFrom;
	}
	public String getCurrencyTo() {
		return currencyTo;
	}
	public BigDecimal getAmountSell() {
		return amountSell;
	}
	public BigDecimal getAmountBuy() {
		return amountBuy;
	}
	public double getRate() {
		return rate;
	}
	public String getTimePlaced() {
		return timePlaced;
	}
	public String getOriginatingCountry() {
		return originatingCountry;
	}
	
}

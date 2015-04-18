package currencyTransactionConsumer;

import java.util.Iterator;

import messageProcessor.MessageProcessorEngine;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GraphingController {
	
		
		@RequestMapping("/output")
	    public String greeting(@RequestParam(value="currency1", defaultValue="null") String currency1, 
	    		@RequestParam(value="currency2", defaultValue="null") String currency2, 
	    		@RequestParam(value="currency3", defaultValue="null") String currency3, 
	    		@RequestParam(value="currency4", defaultValue="null") String currency4,
	    		@RequestParam(value="value1", defaultValue="0") String value1, 
	    		@RequestParam(value="value2", defaultValue="0") String value2, 
	    		@RequestParam(value="value3", defaultValue="0") String value3, 
	    		@RequestParam(value="value4", defaultValue="0") String value4,
	    		Model model) {
	        
			MessageProcessorEngine mEngine = Application.sendMessageProcessor();
			int index = 1;
			
			for(String key : mEngine.getCurrencyFromStats().keySet())
			{				
				model.addAttribute("currency" + index, key);
				model.addAttribute("value" + index, mEngine.getCurrencyFromStats().get(key));
				index++;
				
			}
			
			
			
	        return "index";
	    }

}

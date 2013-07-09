package Utils;

import java.sql.Timestamp;

public class Utils {

	public Timestamp stringToTimestamp(String sts) {
		Timestamp ts = null;
		
		try {
			ts = Timestamp.valueOf(sts);
		} catch (Exception e) {
			ts = new Timestamp(new java.util.Date().getTime());			
		}
		
		return ts;
	}
}

	
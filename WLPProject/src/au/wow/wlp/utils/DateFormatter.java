/**
 * This class formats the current date in the format yyyy-MM-dd 
 */
package au.wow.wlp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter extends Date{
	static final SimpleDateFormat yymmddFormat = new SimpleDateFormat("yyyy-MM-dd");

	  public DateFormatter (String s) throws ParseException {
	    super(yymmddFormat.parse(s).getTime());
	  }

	  public String toString () {
	    return yymmddFormat.format(this);
	  }
}

package application.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * CloseUtil Class defined the method to close Utils.
 * @author Zetian Qin zxq876
 */
public class CloseUtil {
	public static void closeAll(Closeable... io) {
		for(Closeable temp: io) {
			if(null != temp) {
				try {
					temp.close();
				} catch (IOException e) {
					
				}
			}
		}
	}
}

package privateUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import util.ErrorLogUtil;
import vo.Part;
import vo.Symbol;
import vo.Word;

/**
 * 金山词霸的查词API集成
 * @author MaoLG
 *
 * 2018-4-17下午2:31:15
 */
public class ICIBAUtil {

	public static Word findWord(String word){
		Word w = null;
		try{
			URL url = new URL("http://dict-co.iciba.com/api/dictionary.php?type=json&key=48C54DA13E9D473CEDD97C0AC33D4A51&w="+word);
			HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
			//POST Request Define: 
			urlConnection.setRequestMethod("POST"); 
			urlConnection.connect();
			
			//Connection Response From Test Servlet
//			System.out.println("Connection Response From Test Servlet");
			InputStream inputStream = urlConnection.getInputStream();
			
			//Convert Stream to String
			String responseStr = ConvertToString(inputStream);
			
			
			JSONObject jobj = JSONObject.fromObject(responseStr);
			
			JSONObject jsonObject = jobj.getJSONObject("exchange");
			if(!jsonObject.isEmpty()){
				
				if(jsonObject.get("word_pl").equals("")){
					jsonObject.remove("word_pl");
				}
				
				if(jsonObject.get("word_third").equals("")){
					jsonObject.remove("word_third");
				}
				
				if(jsonObject.get("word_past").equals("")){
					jsonObject.remove("word_past");
				}
				
				if(jsonObject.get("word_ing").equals("")){
					jsonObject.remove("word_ing");
				}
				
				if(jsonObject.get("word_done").equals("")){
					jsonObject.remove("word_done");
				}
				
				if(jsonObject.get("word_er").equals("")){
					jsonObject.remove("word_er");
				}
				
				if(jsonObject.get("word_est").equals("")){
					jsonObject.remove("word_est");
				}
				Map<String , Class> map = new HashMap<String, Class>();
				map.put("symbols", Symbol.class);
				map.put("parts", Part.class);
				
				w = (Word)JSONObject.toBean(jobj, Word.class, map);
			}
		}catch(IOException e){
			
		}
		return w;
	}
	
	/**
	 * 字符串拼接
	 * @param inputStream
	 * @return
	 */
	private static String ConvertToString(InputStream inputStream){  
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);  
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
        StringBuilder result = new StringBuilder();  
        String line = null;  
        try {  
            while((line = bufferedReader.readLine()) != null){ 
            	
                result.append(line + "\n");  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try{  
                inputStreamReader.close();  
                inputStream.close();  
                bufferedReader.close();  
            }catch(IOException e){  
                e.printStackTrace();  
            }  
        }  
        return result.toString();  
    }
}

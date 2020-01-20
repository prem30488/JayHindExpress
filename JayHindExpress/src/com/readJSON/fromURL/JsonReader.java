package com.readJSON.fromURL;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dao.ProgramDAO;
import com.dao.ProgramDAOImpl;
import com.models.Program;


public class JsonReader {
	
	private static ProgramDAO programDAO = new ProgramDAOImpl();
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	  }

	  /*public static void main(String[] args) throws IOException, JSONException {
	    JSONObject json = readJsonFromUrl("http://localhost:8045/JSONRestService/getPlanPlus");
	    System.out.println(json.toString());
	    System.out.println(json.get("zpName"));
	    	    
	  }*/
	  
		public static void main(String[] args) throws IOException, JSONException {
			String url="http://localhost:8089/JayHindExpress/getInternational";
			String jsonData = "";
			BufferedReader br = null;
			try {
				String line;
				InputStream is = new URL(url).openStream();
				br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
				while ((line = br.readLine()) != null) {
					jsonData += line + "\n";
					//jsonData=jsonData.replace("[", "").replace("]", "");
					//System.out.println(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)
						br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			
			//JSONObject obj = new JSONObject(jsonData);
			JSONArray obj = new JSONArray(jsonData);
			//System.out.println(jsonData);
			for(int i=0;i<obj.length();i++){
			
			Program p = new Program();
			p.setId(Long.valueOf(obj.getJSONObject(i).get("Id").toString().trim()));
			p.setProgramName(obj.getJSONObject(i).getString("programName"));
			p.setDescription(obj.getJSONObject(i).get("description").toString().trim());
			
			/*try{
				p.setBpCode(Integer.valueOf(obj.getJSONObject(i).get("bpCode").toString().trim()));
				}catch(java.lang.NumberFormatException ex){
					p.setBpCode(null);	
				}
			*/
			
/*			try{
			p.setAssetTypeCode(Integer.valueOf(obj.getJSONObject(i).get("assetTypeCode").toString().trim()));
			}catch(java.lang.NumberFormatException ex){
				p.setAssetTypeCode(null);	
			}
*/			
			
			programDAO.save(p);
			}
		}
}

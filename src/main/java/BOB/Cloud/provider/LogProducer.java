package BOB.Cloud.provider;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import BOB.Cloud.provider.manager.PropertiesManager;


/**
 * @author  Loup_
 */
public class LogProducer{
	/**
	 * @uml.property  name="propertiesManager"
	 * @uml.associationEnd  
	 */
	private PropertiesManager propertiesManager;
	private String modelDataFileName;
	
	public String ModelParser(Boolean isNormal, String logFormat){
		propertiesManager = new PropertiesManager();
		modelDataFileName = propertiesManager.getProperty("modelFile");
		Map<String, Object> modelData = getModelData(logFormat);
		JSONArray modelItemList = (JSONArray) modelData.get("items");
		String format = (String) modelData.get("format");
		
		ModelItemParser modelItemParser = new ModelItemParser(modelItemList, isNormal);
		ArrayList<String> itemValueList = modelItemParser.parser();
		return String.format(format, itemValueList.toArray());
	}
	
	private Map<String, Object> getModelData(String logformat){
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject model = getModel(logformat);
		try{
			JSONArray itemList = model.getJSONArray("items");
			String format = model.getString("format");
			map.put("items", itemList);
			map.put("format", format);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	private JSONObject getModel(String format){
		JSONObject models = null, model = null;
		ArrayList<String> keyList = new ArrayList<String>();
		try{
			models = new JSONObject(getModelFileData());

			if(format.equals("random") || format.length() <= 0){
				Iterator<?> keys = models.keys();
				while(keys.hasNext()){
					keyList.add(keys.next().toString());
				}
				String key = keyList.get(new Util().getRandomArrayNumber(keyList.size()));
				model = models.getJSONObject(key);
			}else{
				model = models.getJSONObject(format);
			}
		} catch (JSONException e){
			e.printStackTrace();
		}
		return model;
	}
	
	private String getModelFileData(){
		String modelData = null;
        FileInputStream fis = null;
        BufferedReader br = null;
        try{
            fis = new FileInputStream(modelDataFileName);
            br = new BufferedReader(new InputStreamReader(fis , "UTF-8"));
            modelData =fileReader(br);
        }catch ( FileNotFoundException e) {
            e.printStackTrace( );
        }catch ( UnsupportedEncodingException e ){
            e.printStackTrace( );
        }
        return modelData;
	}
	
	private String fileReader(BufferedReader br){
		StringBuffer resultData = new StringBuffer();
		try{
			String data = null;
            while ((data = br.readLine()) != null){
                resultData.append(data);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
		return resultData.toString();
	}
}

package BOB.Cloud.provider;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import BOB.Cloud.provider.manager.PropertiesManager;


public class LogProducer
{
	private PropertiesManager propertiesManager;
	private String modelDataFileName;
	
	public LogProducer()
	{
		propertiesManager = new PropertiesManager();
		modelDataFileName = propertiesManager.getProperty("modelFile");
	}
	
	public String ModelParser(Boolean isNormal){
		
		Map<String, Object> modelData = getModelData();
		JSONArray modelItemList = (JSONArray) modelData.get("items");
		String format = (String) modelData.get("format");
		
		ModelItemParser modelItemParser = new ModelItemParser(modelItemList, isNormal);
		ArrayList<String> itemValueList = modelItemParser.parser();
		return String.format(format, itemValueList.toArray());
	}
	
	private Map<String, Object> getModelData(){
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject model = getRandomModel();
		try{
			JSONArray itemList = model.getJSONObject(model.keys().next().toString()).getJSONArray("items");
			String format = model.getJSONObject(model.keys().next().toString()).getString("format");
			map.put("items", itemList);
			map.put("format", format);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	private JSONObject getRandomModel(){
		JSONArray modelList = null;
		JSONObject randomModel = null;
		try{
			modelList = new JSONArray(getModelFileData());
			randomModel = new JSONObject(modelList.get(new Util().getRandomArrayNumber(modelList.length())).toString());
		} catch (JSONException e){
			e.printStackTrace();
		}
		return randomModel;
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

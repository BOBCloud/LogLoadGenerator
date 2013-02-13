package BOB.Cloud.provider;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author  syncc
 */
public class ModelItemParser
{
	/**
	 * @uml.property  name="util"
	 * @uml.associationEnd  
	 */
	private Util util;
	private boolean isNormal = false; 
	private JSONArray itemList;
	
	public ModelItemParser(JSONArray itemList, boolean isNormal){
		util = new Util();
		this.itemList = itemList;
		this.isNormal = isNormal;
	}
	
	public ArrayList<String> parser(){
		ArrayList<String> result = new ArrayList<String>();
		for(int i = 0; i < this.itemList.length(); i++){
			String itemValue = null;
			try{
				JSONObject itemJSObject = this.itemList.getJSONObject(i);
				String type = itemJSObject.getString("type");
				if(type.equals("str")){
					itemValue = parseStrData(itemJSObject);
				}else if(type.equals("int")){
					itemValue = String.valueOf(parseIntData(itemJSObject));
				}else if(type.equals("ip")){
					itemValue = parseIpData(itemJSObject);
				}else if(type.equals("date")){
					itemValue = parseDateData(itemJSObject);
				}
			}catch(JSONException e){
				e.printStackTrace();
			}
			result.add(itemValue);
		}
		return result;
	}
	
	private String parseStrData(JSONObject item){
		String data = null;
		try{
			boolean isRandom = item.getBoolean("is_random");
			if(isRandom){
				data = util.getRandomString();
			}else{
				JSONArray randomList = getRandomList(item);
				data = randomList.getString(util.getRandomArrayNumber(randomList.length()));
			}
		}catch(JSONException e){
			e.printStackTrace();
		}
		return data;
	}
	
	private int parseIntData(JSONObject item){
		int data = 0;
		try{
			boolean isRandom = item.getBoolean("is_random");
			if(isRandom){
				data = util.getRandomNumber(item.getInt("range"));
			}else{
				JSONArray randomList = getRandomList(item);
				data = randomList.getInt(util.getRandomArrayNumber(randomList.length()));
			}
		}catch(JSONException e){
			e.printStackTrace();
		}
		return data;
	}

	private String parseIpData(JSONObject item){
		String data = null;
		try{
			boolean isRandom = item.getBoolean("is_random");
			if(isRandom){
				data = util.getRandomIP();
			}else{
				JSONArray randomList = getRandomList(item);
				data = randomList.getString(util.getRandomArrayNumber(randomList.length()));
			}
		}catch(JSONException e){
			e.printStackTrace();
		}
		return data;
	}
	
	private String parseDateData(JSONObject item){
		String data = null;
		try{
			boolean isRandom = item.getBoolean("is_random");
			if(isRandom){
				data = util.getRandomDate(item.getString("format"));
			}else{
				JSONArray randomList = getRandomList(item);
				data = randomList.getString(util.getRandomArrayNumber(randomList.length()));
			}
		}catch(JSONException e){
			e.printStackTrace();
		}
		return data;
	}
	
	private JSONArray getRandomList(JSONObject item){
		JSONArray data = null;
		try{
			if(this.isNormal){
				data = item.getJSONArray("normal_random_list");
			}else{
				data = item.getJSONArray("abnormal_random_list");
			}
		}catch(JSONException e){
			e.printStackTrace();
		}
		return data;
	}
}

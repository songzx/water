package com.water.springmvcexample.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xmlmodel")
public class XmlModel implements Serializable {

	private String id;
	private Date _date;
	private String _content;
	//arraylist转不了 jxm
	private ArrayList<HashMap<String, Object>> arraylist = new ArrayList<>();
	private HashMap<String, Object> map = new HashMap<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date get_date() {
		return _date;
	}

	public void set_date(Date _date) {
		this._date = _date;
	}

	public String get_content() {
		return _content;
	}

	public void set_content(String _content) {
		this._content = _content;
	}

	public HashMap<String, Object> getMap() {
		map.put("_str", "内容1111111");
		map.put("createdate11", new Date());
		return map;
	}

	public void setMap(HashMap<String, Object> map) {
		this.map = map;
	}

	public ArrayList<HashMap<String, Object>> getArraylist() {
		map.put("_str12", "内容1111111");
		map.put("createdate112", new Date());
		arraylist.add(map);
		return arraylist;
	}

	public void setArraylist(ArrayList<HashMap<String, Object>> arraylist) {
		this.arraylist = arraylist;
	}

}

package com.mybox.model;

import android.text.Html;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class LiveChannelProgressBean implements Serializable {
	private String t;
	private String s;
	private String d;
	private String c;
	private String n;
	private String e;

	private String ints;
	private String inte;
	public String getInts() {
		return ints;
	}
	public void setInts(String ints) {
		this.ints = ints;
	}
	public String getInte() {
		return inte;
	}
	public void setInte(String inte) {
		this.inte = inte;
	}
	public String getT() {
		return t;
	}
	public void setT(String t) {
		this.t = t;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getN() {
		return n;
	}
	public void setN(String n) {
		this.n = n;
	}
	public String getE() {
		return e;
	}
	public void setE(String e) {
		this.e = e;
	}
	
	//TODO need modified
	public static List<LiveChannelProgressBean> convertFromJsonObject(String httpResult) throws Exception {
		List<LiveChannelProgressBean> jsonResult = new ArrayList<LiveChannelProgressBean>();
		try {
			JSONObject json = new JSONObject(httpResult);
			if ("".equals(json)) {
				return null;
			}
			LiveChannelProgressBean liveChannelProgressBean = new LiveChannelProgressBean();
			if (json.has("t")) {
				if (json.getString("t") != null && !json.getString("t").equals("")) {
					if (Html.fromHtml(json.getString("t")).toString() != null) {
						liveChannelProgressBean.setT(Html.fromHtml(json.getString("t")).toString());
					} else {
						liveChannelProgressBean.setT("");
					}
				} else {
					liveChannelProgressBean.setT("");
				}
			}else {
				liveChannelProgressBean.setT("");
			}
			if (json.has("s")) {
				liveChannelProgressBean.setS(json.getString("s"));
			}
			if (json.has("d")) {
				liveChannelProgressBean.setD(json.getString("d"));
			}
			if (json.has("c")) {
				liveChannelProgressBean.setC(json.getString("c"));
			}
			if (json.has("n")) {
				liveChannelProgressBean.setN(json.getString("n"));
			}
			if (json.has("e")) {
				liveChannelProgressBean.setE(json.getString("e"));
			}
			if (json.has("ints")) {
				liveChannelProgressBean.setInts(json.getString("ints"));
			}
			if (json.has("inte")) {
				liveChannelProgressBean.setInte(json.getString("inte"));
			}
			jsonResult.add(liveChannelProgressBean);
			return jsonResult;
		} catch (JSONException e) {
			throw new Exception("接口数据转换失败", e);
		}
	}
}

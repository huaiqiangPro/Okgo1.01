package com.mybox.model;

import java.io.Serializable;
import java.util.List;



public class AdBigImageData implements Serializable {

    /**
     * url : http://p1.img.cctvpic.com/fmspic/pd/1242698cbox20160427.jpg
     * text : 海量视频独家赛事 CBox央视影音为您打开视界
     * typeID : 10
     * jsadID : 0
     * width : 1242
     * height : 698
     * type : 4
     * click : http://adclick.g.doubleclick.net/pcs/click?xai=AKAOjsvpoi0C58Xd79R5K3PaCThK7FhDw1qnbzgOWTYtlRAZr1GDwKkio12i_bKYgH-H2KrXglnQ5Xjo-0dI6mQIYddUI45457mbYXbWMunQ69MzkgwVcuaBzha-JD6o5eQczCsRVVWh5Lq13hR9ZiXcjYkUpvPTbDB1ZWtD1aAUSQK04kvW2HOov7ZVLi6WINJ-egCBh3bZ&sai=AMfl-YSxYaMap0bG6m4jccX2aloelRNXohOjfC_OmYUXSYXzu5FGVBw-cMm3a86c0dOg4RrDvOxdsGVthA&sig=Cg0ArKJSzHMleUsga1TrEAE&urlfix=1&adurl=http://cbox.cctv.com/
     * events : [{"url":""},{"url":""}]
     */

    private String url;
    private String text;
    private String typeID;
    private String jsadID;
    private String width;
    private String height;
    private String type;
    private String click;
    /**
     * url :
     */

    private List<EventsBean> events;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getJsadID() {
        return jsadID;
    }

    public void setJsadID(String jsadID) {
        this.jsadID = jsadID;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }

    public List<EventsBean> getEvents() {
        return events;
    }

    public void setEvents(List<EventsBean> events) {
        this.events = events;
    }

    public static class EventsBean {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    @Override
    public String toString() {
        return "AdBigImageData{" +
                "url='" + url + '\'' +
                ", text='" + text + '\'' +
                ", typeID='" + typeID + '\'' +
                ", jsadID='" + jsadID + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", type='" + type + '\'' +
                ", click='" + click + '\'' +
                ", events=" + events +
                '}';
    }
}

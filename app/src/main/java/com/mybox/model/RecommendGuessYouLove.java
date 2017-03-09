package com.mybox.model;

import java.io.Serializable;
import java.util.List;

/**
 * (RTFSC)
 * 描述 CBoxV6.0
 *
 * @Author:  作者  huaiqiang
 * @Version: 版本  1.0  2015/12/9
 * @Version: 版本  1.1  2016/2/23  加入新集合relRes
 */
public class RecommendGuessYouLove implements Serializable {
    /**
     * userId : 31803330
     * isSpec : false
     * code : 9000
     * type : 运行正常
     * message : 系统正常
     * hotRes : [{"id":"42d5335451273ddf2b806c9d0a4e5c44","url":"http://tv.cntv.cn/video/C22919/42d5335451273ddf2b806c9d0a4e5c44","title":"《大美青海》 20141231 激情穿越柴达木","subTitle":"","picUrl":"http://p3.img.cctvpic.com/fmspic/wuxi/2014/12/31/42d5335451273ddf2b806c9d0a4e5c44_2.jpg","contGuid":"42d5335451273ddf2b806c9d0a4e5c44"},{"id":"7b83869f027b49aa643630865376d819","url":"http://tv.cntv.cn/video/C10486/7b83869f027b49aa643630865376d819","title":"心理访谈 2010年 第359期 \u201c肥姐\u201d的双面人生（上）","subTitle":"","picUrl":"http://p3.img.cctvpic.com/fmspic/2010/12/25/7b83869f027b49aa643630865376d819-180.jpg","contGuid":"7b83869f027b49aa643630865376d819"},{"id":"084523c813863ddd958986650a4e5c44","url":"http://tv.cntv.cn/video/VSET100147899483/084523c813863ddd958986650a4e5c44","title":"《中原崛起》 第一集 中原中国","subTitle":"","picUrl":"http://p2.img.cctvpic.com/fmspic/wuxi/2012/12/31/084523c813863ddd958986650a4e5c44_2.jpg","contGuid":"084523c813863ddd958986650a4e5c44"},{"id":"5472b161dc7b41c295d0022c4dd2ecec","url":"http://tv.cntv.cn/video/VSET100205950098/5472b161dc7b41c295d0022c4dd2ecec","title":"电影《美丽与哀愁》","subTitle":"","picUrl":"http://p3.img.cctvpic.com/fmspic/2015/11/17/5472b161dc7b41c295d0022c4dd2ecec-180.jpg","contGuid":"5472b161dc7b41c295d0022c4dd2ecec"},{"id":"29a865d9f1cc45cedf235ba16fa09afd","url":"http://tv.cntv.cn/video/C10299/29a865d9f1cc45cedf235ba16fa09afd","title":"欢乐送 2010-05-15","subTitle":"","picUrl":"http://p2.img.cctvpic.com/image/2009/qgds/2010/05/15/qgds_h264418000nero_aac32_20100515_1273925443335_2.jpg","contGuid":"29a865d9f1cc45cedf235ba16fa09afd"}]
     * newRes : [{"id":"8e255b7c2f25474baa510e66cdba0f91","url":"http://tv.cntv.cn/video/C39312/8e255b7c2f25474baa510e66cdba0f91","title":"[动画大放映]大耳朵图图-小明星（下）","subTitle":"","picUrl":"http://p4.img.cctvpic.com/fmspic/2012/12/31/8e255b7c2f25474baa510e66cdba0f91-180.jpg","contGuid":"8e255b7c2f25474baa510e66cdba0f91"},{"id":"e636438bb28e41f48d04fb5bb99414d5","url":"http://tv.cntv.cn/video/C30611/e636438bb28e41f48d04fb5bb99414d5","title":"[动画剧场]《卡卡和他的朋友们》 第13集 神奇斗篷","subTitle":"","picUrl":"http://p3.img.cctvpic.com/fmspic/2014/12/02/e636438bb28e41f48d04fb5bb99414d5-180.jpg","contGuid":"e636438bb28e41f48d04fb5bb99414d5"},{"id":"987ed2eb9ac2472a91d4fc675d96ecee","url":"http://tv.cntv.cn/video/C36571/987ed2eb9ac2472a91d4fc675d96ecee","title":"电影《私人订制》插曲《时间都去哪儿了》MV","subTitle":"","picUrl":"http://p1.img.cctvpic.com/fmspic/2013/12/31/987ed2eb9ac2472a91d4fc675d96ecee-180.jpg","contGuid":"987ed2eb9ac2472a91d4fc675d96ecee"},{"id":"8e209154402e3de0d39f48000a4e5c44","url":"http://tv.cntv.cn/video/VSET100250766507/8e209154402e3de0d39f48000a4e5c44","title":"《德耀南粤》 20151031 第五届广东省道德模范颁奖仪式","subTitle":"","picUrl":"http://p3.img.cctvpic.com/fmspic/wuxi/2015/10/31/8e209154402e3de0d39f48000a4e5c44_2.jpg","contGuid":"8e209154402e3de0d39f48000a4e5c44"},{"id":"c2e4942045a83de0ee85acdb0a4e5c44","url":"http://tv.cntv.cn/video/VSET100251383640/c2e4942045a83de0ee85acdb0a4e5c44","title":"《放飞梦想第三届亚洲微电影艺术节\u201c金海棠\u201d奖颁奖典礼》 20151107","subTitle":"","picUrl":"http://p2.img.cctvpic.com/fmspic/2015/11/08/c2e4942045a83de0ee85acdb0a4e5c44-3001.jpg","contGuid":"c2e4942045a83de0ee85acdb0a4e5c44"},{"id":"fb9e241f62e3470a8aa750a3e9356634","url":"http://tv.cntv.cn/video/C30620/fb9e241f62e3470a8aa750a3e9356634","title":"动漫世界 米奇妙妙屋 高飞的超级愿望","subTitle":"","picUrl":"http://p5.img.cctvpic.com/fmspic/2010/10/04/fb9e241f62e3470a8aa750a3e9356634-180.jpg","contGuid":"fb9e241f62e3470a8aa750a3e9356634"},{"id":"eab65805a9734d5f9d8d96565294286f","url":"http://tv.cntv.cn/video/C14323/eab65805a9734d5f9d8d96565294286f","title":"[中国电影报道]成龙拍新片 大腕打酱油 20121231","subTitle":"","picUrl":"http://p3.img.cctvpic.com/fmspic/2012/12/31/eab65805a9734d5f9d8d96565294286f-180.jpg","contGuid":"eab65805a9734d5f9d8d96565294286f"},{"id":"edd9509ef73d49768917cd08530540e9","url":"http://tv.cntv.cn/video/VSET100147219133/edd9509ef73d49768917cd08530540e9","title":"《东方卡萨布兰卡》 第16集","subTitle":"","picUrl":"http://p4.img.cctvpic.com/fmspic/2012/12/31/edd9509ef73d49768917cd08530540e9-180.jpg","contGuid":"edd9509ef73d49768917cd08530540e9"},{"id":"972d3a405d6d4010ac0aec77f5d7cd09","url":"http://tv.cntv.cn/video/C17604/972d3a405d6d4010ac0aec77f5d7cd09","title":"《华人世界》 20131231","subTitle":"","picUrl":"http://p4.img.cctvpic.com/fmspic/2013/12/31/972d3a405d6d4010ac0aec77f5d7cd09-180.jpg","contGuid":"972d3a405d6d4010ac0aec77f5d7cd09"},{"id":"9aa494f453643de0fa9640050a4e5c44","url":"http://tv.cntv.cn/video/VSET100249700063/9aa494f453643de0fa9640050a4e5c44","title":"《财经视角》 20151125","subTitle":"","picUrl":"http://p4.img.cctvpic.com/fmspic/2015/11/25/9aa494f453643de0fa9640050a4e5c44-10.jpg","contGuid":"9aa494f453643de0fa9640050a4e5c44"}]
     * relRes : null
     */

    private String userId;
    private boolean isSpec;
    private String code;
    private String type;
    private String message;



    private List<RelResEntity> relRes;
    /**
     * id : 42d5335451273ddf2b806c9d0a4e5c44
     * url : http://tv.cntv.cn/video/C22919/42d5335451273ddf2b806c9d0a4e5c44
     * title : 《大美青海》 20141231 激情穿越柴达木
     * subTitle :
     * picUrl : http://p3.img.cctvpic.com/fmspic/wuxi/2014/12/31/42d5335451273ddf2b806c9d0a4e5c44_2.jpg
     * contGuid : 42d5335451273ddf2b806c9d0a4e5c44
     */

    private List<HotResEntity> hotRes;

    public void setHotRes(List<HotResEntity> hotRes) {
        this.hotRes = hotRes;
    }

    public List<RelResEntity> getRelRes() {
        return relRes;
    }

    public void setRelRes(List<RelResEntity> relRes) {
        this.relRes = relRes;
    }

    public List<NewResEntity> getNewRes() {
        return newRes;
    }

    public void setNewRes(List<NewResEntity> newRes) {
        this.newRes = newRes;
    }

    /**
     * id : 8e255b7c2f25474baa510e66cdba0f91
     * url : http://tv.cntv.cn/video/C39312/8e255b7c2f25474baa510e66cdba0f91
     * title : [动画大放映]大耳朵图图-小明星（下）
     * subTitle :
     * picUrl : http://p4.img.cctvpic.com/fmspic/2012/12/31/8e255b7c2f25474baa510e66cdba0f91-180.jpg
     * contGuid : 8e255b7c2f25474baa510e66cdba0f91
     */


    private List<NewResEntity> newRes;

    public String getUserId() {
        return userId;
    }
    public boolean isIsSpec() {
        return isSpec;
    }
    public String getCode() {
        return code;
    }
    public String getType() {
        return type;
    }
    public String getMessage() {
        return message;
    }
    public List getHotRes() {
        return hotRes;
    }



    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setIsSpec(boolean isSpec) {
        this.isSpec = isSpec;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setMessage(String message) {
        this.message = message;
    }



    public static class NewResEntity implements Serializable {
        protected String id;
        protected String url;
        protected String title;
        protected String subTitle;
        protected String picUrl;
        protected String contGuid;

        public void setId(String id) {
            this.id = id;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public void setContGuid(String contGuid) {
            this.contGuid = contGuid;
        }

        public String getId() {
            return id;
        }

        public String getUrl() {
            return url;
        }

        public String getTitle() {
            return title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public String getContGuid() {
            return contGuid;
        }

        @Override
        public String toString() {
            return "NewResEntity{" +
                    "id='" + id + '\'' +
                    ", url='" + url + '\'' +
                    ", title='" + title + '\'' +
                    ", subTitle='" + subTitle + '\'' +
                    ", picUrl='" + picUrl + '\'' +
                    ", contGuid='" + contGuid + '\'' +
                    '}';
        }
    }
    public static class HotResEntity extends NewResEntity{}
    public static class RelResEntity extends NewResEntity{}
}

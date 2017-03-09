package com.mybox.model;

import java.io.Serializable;
import java.util.List;


/**
 * Created by ykglove on 2015/9/24.
 */
public class RecommendHomeColumnListInfo implements Serializable {

    /**
     * title : 标题
     * bigImg : [{"title":"互动大图测试(投票)","brief":"互动大图副标题测试","imgUrl":"http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/10/8/1444275804344_649.jpg","bigImgUrl":"","vsetType":"","vtype":"11","listUrl":"","vid":"","channelId":"","vsetId":"","vsetCid":"","vsetEm":"","interactid":"http://qr.cntv.cn/vote/index/?iid=38529","shareUrl":"","pcUrl":"","columnSo":"","vsetPageid":"","isShow":"","order":"1"}]
     * itemList : [{"title":"互动小图测试（边看边聊）","brief":"","imgUrl":"http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/10/8/1444275549489_999.jpg","bigImgUrl":"http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/7/16/1437005128262_802.jpg","vsetType":"","vtype":"14","listUrl":"","vid":"","channelId":"","vsetId":"","vsetCid":"","vsetEm":"","interactid":"38674","shareUrl":"","pcUrl":"","columnSo":"","vsetPageid":"","isShow":"","order":"1"},{"title":"互动小图测试（抽奖）(没有抽奖测试H5)","brief":"","imgUrl":"http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/10/8/1444275542812_292.jpg","bigImgUrl":"http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/7/16/1437004934653_145.jpg","vsetType":"","vtype":"13","listUrl":"","vid":"","channelId":"","vsetId":"","vsetCid":"","vsetEm":"","interactid":"http://qr.cntv.cn/answer/cover/?iid=34523","shareUrl":"","pcUrl":"","columnSo":"","vsetPageid":"","isShow":"","order":"2"},{"title":"互动小图测试（答题）","brief":"","imgUrl":"http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/10/8/1444275535975_11.jpg","bigImgUrl":"http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/7/16/1437004915380_434.jpg","vsetType":"","vtype":"12","listUrl":"","vid":"","channelId":"","vsetId":"","vsetCid":"","vsetEm":"","interactid":"http://qr.cntv.cn/answer/cover/?iid=34523","shareUrl":"","pcUrl":"","columnSo":"","vsetPageid":"","isShow":"","order":"3"},{"title":"互动小图测试（投票）","brief":"","imgUrl":"http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/10/8/1444275504102_201.jpg","bigImgUrl":"http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/7/16/1437004896649_702.jpg","vsetType":"","vtype":"11","listUrl":"","vid":"","channelId":"","vsetId":"VSET100236551426","vsetCid":"","vsetEm":"","interactid":"http://qr.cntv.cn/vote/index/?iid=38529","shareUrl":"","pcUrl":"","columnSo":"","vsetPageid":"","isShow":"","order":"4"},{"title":"互动小图测试（投票）","brief":"","imgUrl":"http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/10/8/1444275504102_201.jpg","bigImgUrl":"http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/7/16/1437004896649_702.jpg","vsetType":"","vtype":"11","listUrl":"","vid":"","channelId":"","vsetId":"VSET100236551426","vsetCid":"","vsetEm":"","interactid":"http://qr.cntv.cn/vote/index/?iid=38529","shareUrl":"","pcUrl":"","columnSo":"","vsetPageid":"","isShow":"","order":"5"},{"title":"互动小图测试（答题）","brief":"","imgUrl":"http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/10/8/1444275535975_11.jpg","bigImgUrl":"http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/7/16/1437004915380_434.jpg","vsetType":"","vtype":"12","listUrl":"","vid":"","channelId":"","vsetId":"","vsetCid":"","vsetEm":"","interactid":"http://qr.cntv.cn/answer/cover/?iid=34523","shareUrl":"","pcUrl":"","columnSo":"","vsetPageid":"","isShow":"","order":"6"},{"title":"互动小图测试（抽奖）(没有抽奖测试H5)","brief":"","imgUrl":"http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/10/8/1444275542812_292.jpg","bigImgUrl":"http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/7/16/1437004934653_145.jpg","vsetType":"","vtype":"13","listUrl":"","vid":"","channelId":"","vsetId":"","vsetCid":"","vsetEm":"","interactid":"http://qr.cntv.cn/answer/cover/?iid=34523","shareUrl":"","pcUrl":"","columnSo":"","vsetPageid":"","isShow":"","order":"7"},{"title":"互动小图测试（边看边聊）","brief":"","imgUrl":"http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/10/8/1444275549489_999.jpg","bigImgUrl":"http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/7/16/1437005128262_802.jpg","vsetType":"","vtype":"14","listUrl":"","vid":"","channelId":"","vsetId":"","vsetCid":"","vsetEm":"","interactid":"38674","shareUrl":"","pcUrl":"","columnSo":"","vsetPageid":"","isShow":"","order":"8"}]
     */

    private DataEntity data;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity implements Serializable {
        private String title;
        /**
         * title : 互动大图测试(投票)
         * brief : 互动大图副标题测试
         * imgUrl : http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/10/8/1444275804344_649.jpg
         * bigImgUrl :
         * vsetType :
         * vtype : 11
         * listUrl :
         * vid :
         * channelId :
         * vsetId :
         * vsetCid :
         * vsetEm :
         * interactid : http://qr.cntv.cn/vote/index/?iid=38529
         * shareUrl :
         * pcUrl :
         * columnSo :
         * vsetPageid :
         * isShow :
         * order : 1
         */

        private List<BigImgEntity> bigImg;
        /**
         * title : 互动小图测试（边看边聊）
         * brief :
         * imgUrl : http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/10/8/1444275549489_999.jpg
         * bigImgUrl : http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/7/16/1437005128262_802.jpg
         * vsetType :
         * vtype : 14
         * listUrl :
         * vid :
         * channelId :
         * vsetId :
         * vsetCid :
         * vsetEm :
         * interactid : 38674
         * shareUrl :
         * pcUrl :
         * columnSo :
         * vsetPageid :
         * isShow :
         * order : 1
         */

        private List<ItemListEntity> itemList;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setBigImg(List<BigImgEntity> bigImg) {
            this.bigImg = bigImg;
        }

        public void setItemList(List<ItemListEntity> itemList) {
            this.itemList = itemList;
        }

        public String getTitle() {
            return title;
        }

        public List<BigImgEntity> getBigImg() {
            return bigImg;
        }

        public List<ItemListEntity> getItemList() {
            return itemList;
        }

        public static class BigImgEntity implements Serializable {
            private String title;
            private String brief;
            private String imgUrl;
            private String bigImgUrl;
            private String vsetType;
            private String vtype;
            private String listUrl;
            private String vid;
            private String channelId;
            private String vsetId;
            private String vsetCid;
            private String vsetEm;
            private String interactid;
            private String shareUrl;
            private String pcUrl;
            private String cornerStr;
            private String cornerColour;

            private String adid;// 无用

            public String getAdid() {
                return adid;
            }

            public void setAdid(String adid) {
                this.adid = adid;
            }

            public String getCornerStr() {
                return cornerStr;
            }

            public void setCornerStr(String cornerStr) {
                this.cornerStr = cornerStr;
            }

            public String getCornerColour() {
                return cornerColour;
            }

            public void setCornerColour(String cornerColour) {
                this.cornerColour = cornerColour;
            }
            private String categoryId;
            private String categoryUrl;
            private String categoryAid;

            public String getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(String categoryId) {
                this.categoryId = categoryId;
            }

            public String getCategoryUrl() {
                return categoryUrl;
            }

            public void setCategoryUrl(String categoryUrl) {
                this.categoryUrl = categoryUrl;
            }

            public String getCategoryAid() {
                return categoryAid;
            }

            public void setCategoryAid(String categoryAid) {
                this.categoryAid = categoryAid;
            }

            private String columnSo;
            private String vsetPageid;
            private String isShow;
            private String order;

            public void setTitle(String title) {
                this.title = title;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public void setBigImgUrl(String bigImgUrl) {
                this.bigImgUrl = bigImgUrl;
            }

            public void setVsetType(String vsetType) {
                this.vsetType = vsetType;
            }

            public void setVtype(String vtype) {
                this.vtype = vtype;
            }

            public void setListUrl(String listUrl) {
                this.listUrl = listUrl;
            }

            public void setVid(String vid) {
                this.vid = vid;
            }

            public void setChannelId(String channelId) {
                this.channelId = channelId;
            }

            public void setVsetId(String vsetId) {
                this.vsetId = vsetId;
            }

            public void setVsetCid(String vsetCid) {
                this.vsetCid = vsetCid;
            }

            public void setVsetEm(String vsetEm) {
                this.vsetEm = vsetEm;
            }

            public void setInteractid(String interactid) {
                this.interactid = interactid;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }

            public void setPcUrl(String pcUrl) {
                this.pcUrl = pcUrl;
            }

            public void setColumnSo(String columnSo) {
                this.columnSo = columnSo;
            }

            public void setVsetPageid(String vsetPageid) {
                this.vsetPageid = vsetPageid;
            }

            public void setIsShow(String isShow) {
                this.isShow = isShow;
            }

            public void setOrder(String order) {
                this.order = order;
            }

            public String getTitle() {
                return title;
            }

            public String getBrief() {
                return brief;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public String getBigImgUrl() {
                return bigImgUrl;
            }

            public String getVsetType() {
                return vsetType;
            }

            public String getVtype() {
                return vtype;
            }

            public String getListUrl() {
                return listUrl;
            }

            public String getVid() {
                return vid;
            }

            public String getChannelId() {
                return channelId;
            }

            public String getVsetId() {
                return vsetId;
            }

            public String getVsetCid() {
                return vsetCid;
            }

            public String getVsetEm() {
                return vsetEm;
            }

            public String getInteractid() {
                return interactid;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public String getPcUrl() {
                return pcUrl;
            }

            public String getColumnSo() {
                return columnSo;
            }

            public String getVsetPageid() {
                return vsetPageid;
            }

            public String getIsShow() {
                return isShow;
            }

            public String getOrder() {
                return order;
            }
        }

        public static class ItemListEntity implements Serializable {
            private String title;
            private String brief;
            private String imgUrl;
            private String bigImgUrl;
            private String vsetType;
            private String vtype;
            private String listUrl;
            private String vid;
            private String channelId;
            private String vsetId;
            private String vsetCid;
            private String vsetEm;
            private String interactid;
            private String shareUrl;
            private String pcUrl;
            private String cornerStr;
            private String cornerColour;
            private String video_length;
            private String adid;

            public String getAdid() {
                return adid;
            }

            public void setAdid(String adid) {
                this.adid = adid;
            }

            public String getVideo_length() {
                return video_length;
            }

            public void setVideo_length(String video_length) {
                this.video_length = video_length;
            }



            public String getCornerStr() {
                return cornerStr;
            }

            public void setCornerStr(String cornerStr) {
                this.cornerStr = cornerStr;
            }

            public String getCornerColour() {
                return cornerColour;
            }

            public void setCornerColour(String cornerColour) {
                this.cornerColour = cornerColour;
            }
            private String categoryId;
            private String categoryUrl;
            private String categoryAid;

            public String getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(String categoryId) {
                this.categoryId = categoryId;
            }

            public String getCategoryUrl() {
                return categoryUrl;
            }

            public void setCategoryUrl(String categoryUrl) {
                this.categoryUrl = categoryUrl;
            }

            public String getCategoryAid() {
                return categoryAid;
            }

            public void setCategoryAid(String categoryAid) {
                this.categoryAid = categoryAid;
            }

            private String columnSo;
            private String vsetPageid;
            private String isShow;
            private String order;

            public void setTitle(String title) {
                this.title = title;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public void setBigImgUrl(String bigImgUrl) {
                this.bigImgUrl = bigImgUrl;
            }

            public void setVsetType(String vsetType) {
                this.vsetType = vsetType;
            }

            public void setVtype(String vtype) {
                this.vtype = vtype;
            }

            public void setListUrl(String listUrl) {
                this.listUrl = listUrl;
            }

            public void setVid(String vid) {
                this.vid = vid;
            }

            public void setChannelId(String channelId) {
                this.channelId = channelId;
            }

            public void setVsetId(String vsetId) {
                this.vsetId = vsetId;
            }

            public void setVsetCid(String vsetCid) {
                this.vsetCid = vsetCid;
            }

            public void setVsetEm(String vsetEm) {
                this.vsetEm = vsetEm;
            }

            public void setInteractid(String interactid) {
                this.interactid = interactid;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }

            public void setPcUrl(String pcUrl) {
                this.pcUrl = pcUrl;
            }

            public void setColumnSo(String columnSo) {
                this.columnSo = columnSo;
            }

            public void setVsetPageid(String vsetPageid) {
                this.vsetPageid = vsetPageid;
            }

            public void setIsShow(String isShow) {
                this.isShow = isShow;
            }

            public void setOrder(String order) {
                this.order = order;
            }

            public String getTitle() {
                return title;
            }

            public String getBrief() {
                return brief;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public String getBigImgUrl() {
                return bigImgUrl;
            }

            public String getVsetType() {
                return vsetType;
            }

            public String getVtype() {
                return vtype;
            }

            public String getListUrl() {
                return listUrl;
            }

            public String getVid() {
                return vid;
            }

            public String getChannelId() {
                return channelId;
            }

            public String getVsetId() {
                return vsetId;
            }

            public String getVsetCid() {
                return vsetCid;
            }

            public String getVsetEm() {
                return vsetEm;
            }

            public String getInteractid() {
                return interactid;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public String getPcUrl() {
                return pcUrl;
            }

            public String getColumnSo() {
                return columnSo;
            }

            public String getVsetPageid() {
                return vsetPageid;
            }

            public String getIsShow() {
                return isShow;
            }

            public String getOrder() {
                return order;
            }
        }
    }
}

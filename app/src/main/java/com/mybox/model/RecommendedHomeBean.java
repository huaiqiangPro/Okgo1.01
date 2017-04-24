package com.mybox.model;

import java.io.Serializable;
import java.util.List;



public class RecommendedHomeBean implements Serializable {

    private DataEntity data;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity implements Serializable {

        private String title;
        private String liveCategoryControl;
        private String normalLiveListControl;
        private List<CategoryListEntity> categoryList;
        private List<BigImgEntity> bigImg;
        private List<LiveCategoryListEntity> liveCategoryList;
        private List<NormalLiveListEntity> normalLiveList;
        private List<ColumnListEntity> columnList;
        private String interactliveControl;
        private List<Interactlive1Bean> interactlive1;

        private AdBigImageData ad1;
        private AdBigImageData ad2;



        public List<CategoryListEntity> getCategoryList() {
            return categoryList;
        }

        public void setCategoryList(List<CategoryListEntity> categoryList) {
            this.categoryList = categoryList;
        }

        public String getLiveCategoryControl() {
            return liveCategoryControl;
        }

        public void setLiveCategoryControl(String liveCategoryControl) {
            this.liveCategoryControl = liveCategoryControl;
        }

        public String getNormalLiveListControl() {
            return normalLiveListControl;
        }

        public void setNormalLiveListControl(String normalLiveListControl) {
            this.normalLiveListControl = normalLiveListControl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<BigImgEntity> getBigImg() {
            return bigImg;
        }

        public AdBigImageData getAd1() {
            return ad1;
        }

        public void setAd1(AdBigImageData ad1) {
            this.ad1 = ad1;
        }

        public AdBigImageData getAd2() {
            return ad2;
        }

        public void setAd2(AdBigImageData ad2) {
            this.ad2 = ad2;
        }

        public void setBigImg(List<BigImgEntity> bigImg) {
            this.bigImg = bigImg;
        }

        public List<LiveCategoryListEntity> getLiveCategoryList() {
            return liveCategoryList;
        }

        public void setLiveCategoryList(List<LiveCategoryListEntity> liveCategoryList) {
            this.liveCategoryList = liveCategoryList;
        }

        public List<NormalLiveListEntity> getNormalLiveList() {
            return normalLiveList;
        }

        public void setNormalLiveList(List<NormalLiveListEntity> normalLiveList) {
            this.normalLiveList = normalLiveList;
        }

        public List<ColumnListEntity> getColumnList() {
            return columnList;
        }

        public void setColumnList(List<ColumnListEntity> columnList) {
            this.columnList = columnList;
        }

        /*********************************
         * 6.15v  推荐首页增加 互动直播入口
         *************************/
        public String getInteractliveControl() {
            return interactliveControl;
        }

        public void setInteractliveControl(String interactliveControl) {
            this.interactliveControl = interactliveControl;
        }

        public List<Interactlive1Bean> getInteractlive1() {
            return interactlive1;
        }

        public void setInteractlive1(List<Interactlive1Bean> interactlive1) {
            this.interactlive1 = interactlive1;
        }

        public static class CategoryListEntity implements Serializable {
            private String title;
            private String imgUrl;
            private String bigImgUrl;
            private String listUrl;
            private String cid;
            private String category;
            private String adid;
            private String sign;
            private String order;

            //6.15新增字段
            private String istop;

            //6.16新增字段
            private String cornerStr;
            private String cornerColour;

            public String getCornerColour() {
                return cornerColour;
            }

            public void setCornerColour(String cornerColour) {
                this.cornerColour = cornerColour;
            }

            public String getCornerStr() {
                return cornerStr;
            }

            public void setCornerStr(String cornerStr) {
                this.cornerStr = cornerStr;
            }

            public String getIstop() {
                return istop;
            }

            public void setIstop(String istop) {
                this.istop = istop;
            }


            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getBigImgUrl() {
                return bigImgUrl;
            }

            public void setBigImgUrl(String bigImgUrl) {
                this.bigImgUrl = bigImgUrl;
            }

            public String getListUrl() {
                return listUrl;
            }

            public void setListUrl(String listUrl) {
                this.listUrl = listUrl;
            }

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getAdid() {
                return adid;
            }

            public void setAdid(String adid) {
                this.adid = adid;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getOrder() {
                return order;
            }

            public void setOrder(String order) {
                this.order = order;
            }

            @Override
            public boolean equals(Object o) {
                if (o != null && o instanceof CategoryListEntity) {
                    boolean resule = true;
                    CategoryListEntity bean = (CategoryListEntity) o;

                    if (!this.title .equals(bean.getTitle()) ) {
                        resule = false;
                    }
                    if(!this.sign .equals(bean.getSign())){
                        resule = false;
                    }
                    return resule;
                } else {
                    return super.equals(o);
                }
            }
        }

        public static class BigImgEntity implements Serializable {
            /**
             * title : 推荐大图标题
             * brief : 简介
             * imgUrl :
             * bigImgUrl : http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/9/14/1442224244452_605.jpg
             * vsetType : http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/9/14/1442224244452_605.jpg
             * vtype : 1
             * listUrl :
             * vid : b1a1a663b7e048bcaf9f17d273abb568
             * channelId :
             * vsetId :
             * vsetCid :
             * shareUrl :
             * pcUrl :
             * categoryId :
             * columnSo :
             * vsetPageid :
             * isShow :
             * order : 1
             */

            private String title;
            private String brief;
            private String imgUrl;
            private String bigImgUrl;
            private String vsetType;
            private String adid;
            private String interactid;
            private String vtype;
            private String listUrl;
            private String vid;
            private String channelId;
            private String vsetId;
            private String vsetCid;
            private String shareUrl;
            private String pcUrl;
            private String cornerStr;
            private String cornerColour;
            private String categoryId;
            private String categoryUrl;
            private String categoryAid;
            private String columnSo;
            private String vsetPageid;
            private String isShow;
            private String order;

            public String getAdid() {
                return adid;
            }

            public void setAdid(String adid) {
                this.adid = adid;
            }

            public String getInteractid() {
                return interactid;
            }

            public void setInteractid(String interactid) {
                this.interactid = interactid;
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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getBigImgUrl() {
                return bigImgUrl;
            }

            public void setBigImgUrl(String bigImgUrl) {
                this.bigImgUrl = bigImgUrl;
            }

            public String getVsetType() {
                return vsetType;
            }

            public void setVsetType(String vsetType) {
                this.vsetType = vsetType;
            }

            public String getVtype() {
                return vtype;
            }

            public void setVtype(String vtype) {
                this.vtype = vtype;
            }

            public String getListUrl() {
                return listUrl;
            }

            public void setListUrl(String listUrl) {
                this.listUrl = listUrl;
            }

            public String getVid() {
                return vid;
            }

            public void setVid(String vid) {
                this.vid = vid;
            }

            public String getChannelId() {
                return channelId;
            }

            public void setChannelId(String channelId) {
                this.channelId = channelId;
            }

            public String getVsetId() {
                return vsetId;
            }

            public void setVsetId(String vsetId) {
                this.vsetId = vsetId;
            }

            public String getVsetCid() {
                return vsetCid;
            }

            public void setVsetCid(String vsetCid) {
                this.vsetCid = vsetCid;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }

            public String getPcUrl() {
                return pcUrl;
            }

            public void setPcUrl(String pcUrl) {
                this.pcUrl = pcUrl;
            }

            public String getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(String categoryId) {
                this.categoryId = categoryId;
            }

            public String getColumnSo() {
                return columnSo;
            }

            public void setColumnSo(String columnSo) {
                this.columnSo = columnSo;
            }

            public String getVsetPageid() {
                return vsetPageid;
            }

            public void setVsetPageid(String vsetPageid) {
                this.vsetPageid = vsetPageid;
            }

            public String getIsShow() {
                return isShow;
            }

            public void setIsShow(String isShow) {
                this.isShow = isShow;
            }

            public String getOrder() {
                return order;
            }

            public void setOrder(String order) {
                this.order = order;
            }

        }

        public static class LiveCategoryListEntity implements Serializable {
            /**
             * title : 频道分类列表
             * imgUrl :
             * bigImgUrl :
             * channelListUrl : http://115.182.9.42:8080/cbox60/channellist.json
             * order : 1
             */

            private String title;
            private String imgUrl;
            private String bigImgUrl;
            private String channelListUrl;
            private String order;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getBigImgUrl() {
                return bigImgUrl;
            }

            public void setBigImgUrl(String bigImgUrl) {
                this.bigImgUrl = bigImgUrl;
            }

            public String getChannelListUrl() {
                return channelListUrl;
            }

            public void setChannelListUrl(String channelListUrl) {
                this.channelListUrl = channelListUrl;
            }

            public String getOrder() {
                return order;
            }

            public void setOrder(String order) {
                this.order = order;
            }
        }

        public static class NormalLiveListEntity implements Serializable {
            /**
             * title : CCTV-1
             * channelImg : http://p3.img.cctvpic.com/photoAlbum/page/performance/img/2014/9/9/1410228232518_630.png
             * channelBigImg : http://p3.img.cctvpic.com/photoAlbum/page/performance/img/2014/9/9/1410228232518_630.png
             * p2pUrl : pa://cctv_p2p_hdcctv1
             * channelId : cctv1
             * channelType :
             * multiChannelUrl :
             * shareUrl : http://tv.cntv.cn/live/cctv1/
             * order : 1
             */
            public int requestCount;
            private String title;
            private String channelImg;
            private String channelBigImg;
            private String p2pUrl;
            private String channelId;
            private String audioUrl;
            private String channelType;
            private String multiChannelUrl;
            private String shareUrl;
            private String order;
            private String title1;
            private String interactType;

            public String getInteractType() {
                return interactType;
            }

            public void setInteractType(String interactType) {
                this.interactType = interactType;
            }

            public String getAudioUrl() {
                return audioUrl;

            }

            public void setAudioUrl(String audioUrl) {
                this.audioUrl = audioUrl;
            }

            public String getTitle1() {
                return title1;
            }

            public void setTitle1(String title1) {
                this.title1 = title1;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getChannelImg() {
                return channelImg;
            }

            public void setChannelImg(String channelImg) {
                this.channelImg = channelImg;
            }

            public String getChannelBigImg() {
                return channelBigImg;
            }

            public void setChannelBigImg(String channelBigImg) {
                this.channelBigImg = channelBigImg;
            }

            public String getP2pUrl() {
                return p2pUrl;
            }

            public void setP2pUrl(String p2pUrl) {
                this.p2pUrl = p2pUrl;
            }

            public String getChannelId() {
                return channelId;
            }

            public void setChannelId(String channelId) {
                this.channelId = channelId;
            }

            public String getChannelType() {
                return channelType;
            }

            public void setChannelType(String channelType) {
                this.channelType = channelType;
            }

            public String getMultiChannelUrl() {
                return multiChannelUrl;
            }

            public void setMultiChannelUrl(String multiChannelUrl) {
                this.multiChannelUrl = multiChannelUrl;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }

            public String getOrder() {
                return order;
            }

            public void setOrder(String order) {
                this.order = order;
            }
        }

        public static class ColumnListEntity implements Serializable, Cloneable {
            /**
             * title : 分类标题
             * showControl : 1
             * templateType : 1
             * templateUrl : http://115.182.9.42:8080/cbox60/tuijianfenlei.json
             * adImgUrl :
             * adBigImgUrl :
             * categoryControl : 1
             * categoryUrl : http://serv.cbox.cntv.cn/json/dianboerjiye/jilupian/jilupianzhuantiweihuqu/rlsgswl/sy/index.json
             * categoryId : 5
             * categoryCid : cid=00
             * categoryAdid :
             * categorySign :
             * order : 1
             */

            private String title;
            private String showControl;
            private String templateType;
            private String templateUrl;
            private String adImgUrl;
            private String adBigImgUrl;
            private String categoryControl;
            private String categoryUrl;
            private String categoryId;
            private String categoryCid;
            private String categoryAdid;
            private String categorySign;
            private String isDoubleTitle;
            private String order;
            private boolean isClone = false;
            private Object info;

            public String getIsDoubleTitle() {
                return isDoubleTitle;
            }

            public void setIsDoubleTitle(String isDoubleTitle) {
                this.isDoubleTitle = isDoubleTitle;
            }

            public boolean isClone() {
                return isClone;
            }

            @Override
            public ColumnListEntity clone() throws CloneNotSupportedException {
                ColumnListEntity entry = (ColumnListEntity) super.clone();
                entry.isClone = true;
                return entry;
            }

            public Object getInfo() {
                return info;
            }

            public void setInfo(Object info) {
                this.info = info;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getShowControl() {
                return showControl;
            }

            public void setShowControl(String showControl) {
                this.showControl = showControl;
            }

            public String getTemplateType() {
                return templateType;
            }

            public void setTemplateType(String templateType) {
                this.templateType = templateType;
            }

            public String getTemplateUrl() {
                return templateUrl;
            }

            public void setTemplateUrl(String templateUrl) {
                this.templateUrl = templateUrl;
            }

            public String getAdImgUrl() {
                return adImgUrl;
            }

            public void setAdImgUrl(String adImgUrl) {
                this.adImgUrl = adImgUrl;
            }

            public String getAdBigImgUrl() {
                return adBigImgUrl;
            }

            public void setAdBigImgUrl(String adBigImgUrl) {
                this.adBigImgUrl = adBigImgUrl;
            }

            public String getCategoryControl() {
                return categoryControl;
            }

            public void setCategoryControl(String categoryControl) {
                this.categoryControl = categoryControl;
            }

            public String getCategoryUrl() {
                return categoryUrl;
            }

            public void setCategoryUrl(String categoryUrl) {
                this.categoryUrl = categoryUrl;
            }

            public String getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(String categoryId) {
                this.categoryId = categoryId;
            }

            public String getCategoryCid() {
                return categoryCid;
            }

            public void setCategoryCid(String categoryCid) {
                this.categoryCid = categoryCid;
            }

            public String getCategoryAdid() {
                return categoryAdid;
            }

            public void setCategoryAdid(String categoryAdid) {
                this.categoryAdid = categoryAdid;
            }

            public String getCategorySign() {
                return categorySign;
            }

            public void setCategorySign(String categorySign) {
                this.categorySign = categorySign;
            }

            public String getOrder() {
                return order;
            }

            public void setOrder(String order) {
                this.order = order;
            }
        }

        public static class Interactlive1Bean implements Serializable {
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
            private String video_length;
            private String vsetCid;
            private String vsetEm;
            private String shareUrl;
            private String pcUrl;
            private String interactid;
            private String categoryId;
            private String categoryUrl;
            private String categoryAid;
            private String cornerStr;
            private String cornerColour;
            private String columnSo;
            private String vsetPageid;
            private String isShow;
            private String order;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getBigImgUrl() {
                return bigImgUrl;
            }

            public void setBigImgUrl(String bigImgUrl) {
                this.bigImgUrl = bigImgUrl;
            }

            public String getVsetType() {
                return vsetType;
            }

            public void setVsetType(String vsetType) {
                this.vsetType = vsetType;
            }

            public String getVtype() {
                return vtype;
            }

            public void setVtype(String vtype) {
                this.vtype = vtype;
            }

            public String getListUrl() {
                return listUrl;
            }

            public void setListUrl(String listUrl) {
                this.listUrl = listUrl;
            }

            public String getVid() {
                return vid;
            }

            public void setVid(String vid) {
                this.vid = vid;
            }

            public String getChannelId() {
                return channelId;
            }

            public void setChannelId(String channelId) {
                this.channelId = channelId;
            }

            public String getVsetId() {
                return vsetId;
            }

            public void setVsetId(String vsetId) {
                this.vsetId = vsetId;
            }

            public String getVideo_length() {
                return video_length;
            }

            public void setVideo_length(String video_length) {
                this.video_length = video_length;
            }

            public String getVsetCid() {
                return vsetCid;
            }

            public void setVsetCid(String vsetCid) {
                this.vsetCid = vsetCid;
            }

            public String getVsetEm() {
                return vsetEm;
            }

            public void setVsetEm(String vsetEm) {
                this.vsetEm = vsetEm;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }

            public String getPcUrl() {
                return pcUrl;
            }

            public void setPcUrl(String pcUrl) {
                this.pcUrl = pcUrl;
            }

            public String getInteractid() {
                return interactid;
            }

            public void setInteractid(String interactid) {
                this.interactid = interactid;
            }

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

            public String getColumnSo() {
                return columnSo;
            }

            public void setColumnSo(String columnSo) {
                this.columnSo = columnSo;
            }

            public String getVsetPageid() {
                return vsetPageid;
            }

            public void setVsetPageid(String vsetPageid) {
                this.vsetPageid = vsetPageid;
            }

            public String getIsShow() {
                return isShow;
            }

            public void setIsShow(String isShow) {
                this.isShow = isShow;
            }

            public String getOrder() {
                return order;
            }

            public void setOrder(String order) {
                this.order = order;
            }
        }

    }
}

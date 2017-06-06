package com.yizhan.ouyu.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/6/1.
 */

public class KaiYanVideo implements Parcelable {


    /**
     * type : video
     * data : {"dataType":"VideoBeanForClient","id":18395,"title":"穿山越海的机械零件","slogan":"当想象力遇到科技，就会发生奇迹","description":"想象力是未来存在的原力，是上天入地的奇迹。这条广告视频把机械零件不停打乱重组，看似凌乱的它们却总能找到自己的位置。无论是创意还是动画效果，都值得满分送上。From ManvsMachine","provider":{"name":"Vimeo","alias":"vimeo","icon":"http://img.kaiyanapp.com/c3ad630be461cbb081649c9e21d6cbe3.png"},"category":"广告","author":null,"cover":{"feed":"http://img.kaiyanapp.com/6cb07ededf5f5814f6765a6c642473e0.jpeg?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/6cb07ededf5f5814f6765a6c642473e0.jpeg?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/0d0597426e182c8bed5de1824207cba0.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":"http://img.kaiyanapp.com/6cb07ededf5f5814f6765a6c642473e0.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim"},"playUrl":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=18395&editionType=default&source=ucloud","thumbPlayUrl":"http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/1496203327988_08911728.mp4","duration":90,"webUrl":{"raw":"http://www.eyepetizer.net/detail.html?vid=18395","forWeibo":"http://wandou.im/3mloet"},"releaseTime":1496278800000,"library":"DAILY","playInfo":[{"height":480,"width":854,"urlList":[{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=18395&editionType=normal&source=ucloud","size":10228507},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=18395&editionType=normal&source=qcloud","size":10228507}],"name":"标清","type":"normal","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=18395&editionType=normal&source=ucloud"},{"height":720,"width":1280,"urlList":[{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=18395&editionType=high&source=ucloud","size":17365870},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=18395&editionType=high&source=qcloud","size":17365870}],"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=18395&editionType=high&source=ucloud"}],"consumption":{"collectionCount":27,"shareCount":27,"replyCount":1},"campaign":null,"waterMarks":null,"adTrack":null,"tags":[{"id":16,"name":"广告","actionUrl":"eyepetizer://tag/16/?title=%E5%B9%BF%E5%91%8A","adTrack":null},{"id":74,"name":"CG","actionUrl":"eyepetizer://tag/74/?title=CG","adTrack":null},{"id":562,"name":"汽车","actionUrl":"eyepetizer://tag/562/?title=%E6%B1%BD%E8%BD%A6","adTrack":null},{"id":204,"name":"精致","actionUrl":"eyepetizer://tag/204/?title=%E7%B2%BE%E8%87%B4","adTrack":null}],"type":"NORMAL","titlePgc":null,"descriptionPgc":null,"remark":"","idx":0,"shareAdTrack":null,"favoriteAdTrack":null,"webAdTrack":null,"date":1496278800000,"promotion":null,"label":null,"labelList":[],"descriptionEditor":"想象力是未来存在的原力，是上天入地的奇迹。这条广告视频把机械零件不停打乱重组，看似凌乱的它们却总能找到自己的位置。无论是创意还是动画效果，都值得满分送上。From ManvsMachine","collected":false,"played":false,"subtitles":[],"lastViewTime":null}
     * tag : 0
     */

    private String type;
    private DataBean data;
    private String tag;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public static class DataBean implements Parcelable {
        /**
         * dataType : VideoBeanForClient
         * id : 18395
         * title : 穿山越海的机械零件
         * slogan : 当想象力遇到科技，就会发生奇迹
         * description : 想象力是未来存在的原力，是上天入地的奇迹。这条广告视频把机械零件不停打乱重组，看似凌乱的它们却总能找到自己的位置。无论是创意还是动画效果，都值得满分送上。From ManvsMachine
         * provider : {"name":"Vimeo","alias":"vimeo","icon":"http://img.kaiyanapp.com/c3ad630be461cbb081649c9e21d6cbe3.png"}
         * category : 广告
         * author : null
         * cover : {"feed":"http://img.kaiyanapp.com/6cb07ededf5f5814f6765a6c642473e0.jpeg?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/6cb07ededf5f5814f6765a6c642473e0.jpeg?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/0d0597426e182c8bed5de1824207cba0.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":"http://img.kaiyanapp.com/6cb07ededf5f5814f6765a6c642473e0.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim"}
         * playUrl : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=18395&editionType=default&source=ucloud
         * thumbPlayUrl : http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/1496203327988_08911728.mp4
         * duration : 90
         * webUrl : {"raw":"http://www.eyepetizer.net/detail.html?vid=18395","forWeibo":"http://wandou.im/3mloet"}
         * releaseTime : 1496278800000
         * library : DAILY
         * playInfo : [{"height":480,"width":854,"urlList":[{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=18395&editionType=normal&source=ucloud","size":10228507},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=18395&editionType=normal&source=qcloud","size":10228507}],"name":"标清","type":"normal","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=18395&editionType=normal&source=ucloud"},{"height":720,"width":1280,"urlList":[{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=18395&editionType=high&source=ucloud","size":17365870},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=18395&editionType=high&source=qcloud","size":17365870}],"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=18395&editionType=high&source=ucloud"}]
         * consumption : {"collectionCount":27,"shareCount":27,"replyCount":1}
         * campaign : null
         * waterMarks : null
         * adTrack : null
         * tags : [{"id":16,"name":"广告","actionUrl":"eyepetizer://tag/16/?title=%E5%B9%BF%E5%91%8A","adTrack":null},{"id":74,"name":"CG","actionUrl":"eyepetizer://tag/74/?title=CG","adTrack":null},{"id":562,"name":"汽车","actionUrl":"eyepetizer://tag/562/?title=%E6%B1%BD%E8%BD%A6","adTrack":null},{"id":204,"name":"精致","actionUrl":"eyepetizer://tag/204/?title=%E7%B2%BE%E8%87%B4","adTrack":null}]
         * type : NORMAL
         * titlePgc : null
         * descriptionPgc : null
         * remark :
         * idx : 0
         * shareAdTrack : null
         * favoriteAdTrack : null
         * webAdTrack : null
         * date : 1496278800000
         * promotion : null
         * label : null
         * labelList : []
         * descriptionEditor : 想象力是未来存在的原力，是上天入地的奇迹。这条广告视频把机械零件不停打乱重组，看似凌乱的它们却总能找到自己的位置。无论是创意还是动画效果，都值得满分送上。From ManvsMachine
         * collected : false
         * played : false
         * subtitles : []
         * lastViewTime : null
         */

        private String dataType;
        private int id;
        private String title;
        private String slogan;
        private String description;
        private ProviderBean provider;
        private String category;
        private Object author;
        private CoverBean cover;
        private String playUrl;
        private String thumbPlayUrl;
        private int duration;
        private WebUrlBean webUrl;
        private long releaseTime;
        private String library;
        private ConsumptionBean consumption;
        private Object campaign;
        private Object waterMarks;
        private Object adTrack;
        private String type;
        private Object titlePgc;
        private Object descriptionPgc;
        private String remark;
        private int idx;
        private Object shareAdTrack;
        private Object favoriteAdTrack;
        private Object webAdTrack;
        private long date;
        private Object promotion;
        private Object label;
        private String descriptionEditor;
        private boolean collected;
        private boolean played;
        private Object lastViewTime;
        private List<PlayInfoBean> playInfo;
        private List<TagsBean> tags;

        private List<KaiYanVideo> itemList;
        private HeaderBean header;

        private String text;
        private String font;

        private String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getFont() {
            return font;
        }

        public void setFont(String font) {
            this.font = font;
        }

        public HeaderBean getHeader() {
            return header;
        }

        public void setHeader(HeaderBean header) {
            this.header = header;
        }

        public List<KaiYanVideo> getItemList() {
            return itemList;
        }

        public void setItemList(List<KaiYanVideo> itemList) {
            this.itemList = itemList;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSlogan() {
            return slogan;
        }

        public void setSlogan(String slogan) {
            this.slogan = slogan;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public ProviderBean getProvider() {
            return provider;
        }

        public void setProvider(ProviderBean provider) {
            this.provider = provider;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public Object getAuthor() {
            return author;
        }

        public void setAuthor(Object author) {
            this.author = author;
        }

        public CoverBean getCover() {
            return cover;
        }

        public void setCover(CoverBean cover) {
            this.cover = cover;
        }

        public String getPlayUrl() {
            return playUrl;
        }

        public void setPlayUrl(String playUrl) {
            this.playUrl = playUrl;
        }

        public String getThumbPlayUrl() {
            return thumbPlayUrl;
        }

        public void setThumbPlayUrl(String thumbPlayUrl) {
            this.thumbPlayUrl = thumbPlayUrl;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public WebUrlBean getWebUrl() {
            return webUrl;
        }

        public void setWebUrl(WebUrlBean webUrl) {
            this.webUrl = webUrl;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public String getLibrary() {
            return library;
        }

        public void setLibrary(String library) {
            this.library = library;
        }

        public ConsumptionBean getConsumption() {
            return consumption;
        }

        public void setConsumption(ConsumptionBean consumption) {
            this.consumption = consumption;
        }

        public Object getCampaign() {
            return campaign;
        }

        public void setCampaign(Object campaign) {
            this.campaign = campaign;
        }

        public Object getWaterMarks() {
            return waterMarks;
        }

        public void setWaterMarks(Object waterMarks) {
            this.waterMarks = waterMarks;
        }

        public Object getAdTrack() {
            return adTrack;
        }

        public void setAdTrack(Object adTrack) {
            this.adTrack = adTrack;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getTitlePgc() {
            return titlePgc;
        }

        public void setTitlePgc(Object titlePgc) {
            this.titlePgc = titlePgc;
        }

        public Object getDescriptionPgc() {
            return descriptionPgc;
        }

        public void setDescriptionPgc(Object descriptionPgc) {
            this.descriptionPgc = descriptionPgc;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getIdx() {
            return idx;
        }

        public void setIdx(int idx) {
            this.idx = idx;
        }

        public Object getShareAdTrack() {
            return shareAdTrack;
        }

        public void setShareAdTrack(Object shareAdTrack) {
            this.shareAdTrack = shareAdTrack;
        }

        public Object getFavoriteAdTrack() {
            return favoriteAdTrack;
        }

        public void setFavoriteAdTrack(Object favoriteAdTrack) {
            this.favoriteAdTrack = favoriteAdTrack;
        }

        public Object getWebAdTrack() {
            return webAdTrack;
        }

        public void setWebAdTrack(Object webAdTrack) {
            this.webAdTrack = webAdTrack;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public Object getPromotion() {
            return promotion;
        }

        public void setPromotion(Object promotion) {
            this.promotion = promotion;
        }

        public Object getLabel() {
            return label;
        }

        public void setLabel(Object label) {
            this.label = label;
        }

        public String getDescriptionEditor() {
            return descriptionEditor;
        }

        public void setDescriptionEditor(String descriptionEditor) {
            this.descriptionEditor = descriptionEditor;
        }

        public boolean isCollected() {
            return collected;
        }

        public void setCollected(boolean collected) {
            this.collected = collected;
        }

        public boolean isPlayed() {
            return played;
        }

        public void setPlayed(boolean played) {
            this.played = played;
        }

        public Object getLastViewTime() {
            return lastViewTime;
        }

        public void setLastViewTime(Object lastViewTime) {
            this.lastViewTime = lastViewTime;
        }

        public List<PlayInfoBean> getPlayInfo() {
            return playInfo;
        }

        public void setPlayInfo(List<PlayInfoBean> playInfo) {
            this.playInfo = playInfo;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }



        public static class ProviderBean implements Parcelable {
            /**
             * name : Vimeo
             * alias : vimeo
             * icon : http://img.kaiyanapp.com/c3ad630be461cbb081649c9e21d6cbe3.png
             */

            private String name;
            private String alias;
            private String icon;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAlias() {
                return alias;
            }

            public void setAlias(String alias) {
                this.alias = alias;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.name);
                dest.writeString(this.alias);
                dest.writeString(this.icon);
            }

            public ProviderBean() {
            }

            protected ProviderBean(Parcel in) {
                this.name = in.readString();
                this.alias = in.readString();
                this.icon = in.readString();
            }

            public static final Parcelable.Creator<ProviderBean> CREATOR = new Parcelable.Creator<ProviderBean>() {
                @Override
                public ProviderBean createFromParcel(Parcel source) {
                    return new ProviderBean(source);
                }

                @Override
                public ProviderBean[] newArray(int size) {
                    return new ProviderBean[size];
                }
            };
        }

        public static class CoverBean implements Parcelable {
            /**
             * feed : http://img.kaiyanapp.com/6cb07ededf5f5814f6765a6c642473e0.jpeg?imageMogr2/quality/60/format/jpg
             * detail : http://img.kaiyanapp.com/6cb07ededf5f5814f6765a6c642473e0.jpeg?imageMogr2/quality/60/format/jpg
             * blurred : http://img.kaiyanapp.com/0d0597426e182c8bed5de1824207cba0.jpeg?imageMogr2/quality/60/format/jpg
             * sharing : null
             * homepage : http://img.kaiyanapp.com/6cb07ededf5f5814f6765a6c642473e0.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
             */

            private String feed;
            private String detail;
            private String blurred;
            private Object sharing;
            private String homepage;

            public String getFeed() {
                return feed;
            }

            public void setFeed(String feed) {
                this.feed = feed;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getBlurred() {
                return blurred;
            }

            public void setBlurred(String blurred) {
                this.blurred = blurred;
            }

            public Object getSharing() {
                return sharing;
            }

            public void setSharing(Object sharing) {
                this.sharing = sharing;
            }

            public String getHomepage() {
                return homepage;
            }

            public void setHomepage(String homepage) {
                this.homepage = homepage;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.feed);
                dest.writeString(this.detail);
                dest.writeString(this.blurred);
                dest.writeParcelable((Parcelable) this.sharing, flags);
                dest.writeString(this.homepage);
            }

            public CoverBean() {
            }

            protected CoverBean(Parcel in) {
                this.feed = in.readString();
                this.detail = in.readString();
                this.blurred = in.readString();
                this.sharing = in.readParcelable(Object.class.getClassLoader());
                this.homepage = in.readString();
            }

            public static final Parcelable.Creator<CoverBean> CREATOR = new Parcelable.Creator<CoverBean>() {
                @Override
                public CoverBean createFromParcel(Parcel source) {
                    return new CoverBean(source);
                }

                @Override
                public CoverBean[] newArray(int size) {
                    return new CoverBean[size];
                }
            };
        }

        public static class WebUrlBean implements Parcelable {
            /**
             * raw : http://www.eyepetizer.net/detail.html?vid=18395
             * forWeibo : http://wandou.im/3mloet
             */

            private String raw;
            private String forWeibo;

            public String getRaw() {
                return raw;
            }

            public void setRaw(String raw) {
                this.raw = raw;
            }

            public String getForWeibo() {
                return forWeibo;
            }

            public void setForWeibo(String forWeibo) {
                this.forWeibo = forWeibo;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.raw);
                dest.writeString(this.forWeibo);
            }

            public WebUrlBean() {
            }

            protected WebUrlBean(Parcel in) {
                this.raw = in.readString();
                this.forWeibo = in.readString();
            }

            public static final Parcelable.Creator<WebUrlBean> CREATOR = new Parcelable.Creator<WebUrlBean>() {
                @Override
                public WebUrlBean createFromParcel(Parcel source) {
                    return new WebUrlBean(source);
                }

                @Override
                public WebUrlBean[] newArray(int size) {
                    return new WebUrlBean[size];
                }
            };
        }

        public static class ConsumptionBean implements Parcelable {
            /**
             * collectionCount : 27
             * shareCount : 27
             * replyCount : 1
             */

            private int collectionCount;
            private int shareCount;
            private int replyCount;

            public int getCollectionCount() {
                return collectionCount;
            }

            public void setCollectionCount(int collectionCount) {
                this.collectionCount = collectionCount;
            }

            public int getShareCount() {
                return shareCount;
            }

            public void setShareCount(int shareCount) {
                this.shareCount = shareCount;
            }

            public int getReplyCount() {
                return replyCount;
            }

            public void setReplyCount(int replyCount) {
                this.replyCount = replyCount;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.collectionCount);
                dest.writeInt(this.shareCount);
                dest.writeInt(this.replyCount);
            }

            public ConsumptionBean() {
            }

            protected ConsumptionBean(Parcel in) {
                this.collectionCount = in.readInt();
                this.shareCount = in.readInt();
                this.replyCount = in.readInt();
            }

            public static final Parcelable.Creator<ConsumptionBean> CREATOR = new Parcelable.Creator<ConsumptionBean>() {
                @Override
                public ConsumptionBean createFromParcel(Parcel source) {
                    return new ConsumptionBean(source);
                }

                @Override
                public ConsumptionBean[] newArray(int size) {
                    return new ConsumptionBean[size];
                }
            };
        }

        public static class PlayInfoBean implements Parcelable {
            /**
             * height : 480
             * width : 854
             * urlList : [{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=18395&editionType=normal&source=ucloud","size":10228507},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=18395&editionType=normal&source=qcloud","size":10228507}]
             * name : 标清
             * type : normal
             * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=18395&editionType=normal&source=ucloud
             */

            private int height;
            private int width;
            private String name;
            private String type;
            private String url;
            private List<UrlListBean> urlList;

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public List<UrlListBean> getUrlList() {
                return urlList;
            }

            public void setUrlList(List<UrlListBean> urlList) {
                this.urlList = urlList;
            }

            public static class UrlListBean implements Parcelable {
                /**
                 * name : ucloud
                 * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=18395&editionType=normal&source=ucloud
                 * size : 10228507
                 */


                private String name;
                private String url;
                private int size;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.name);
                    dest.writeString(this.url);
                    dest.writeInt(this.size);
                }

                public UrlListBean() {
                }

                protected UrlListBean(Parcel in) {
                    this.name = in.readString();
                    this.url = in.readString();
                    this.size = in.readInt();
                }

                public static final Parcelable.Creator<UrlListBean> CREATOR = new Parcelable.Creator<UrlListBean>() {
                    @Override
                    public UrlListBean createFromParcel(Parcel source) {
                        return new UrlListBean(source);
                    }

                    @Override
                    public UrlListBean[] newArray(int size) {
                        return new UrlListBean[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.height);
                dest.writeInt(this.width);
                dest.writeString(this.name);
                dest.writeString(this.type);
                dest.writeString(this.url);
                dest.writeTypedList(this.urlList);
            }

            public PlayInfoBean() {
            }

            protected PlayInfoBean(Parcel in) {
                this.height = in.readInt();
                this.width = in.readInt();
                this.name = in.readString();
                this.type = in.readString();
                this.url = in.readString();
                this.urlList = in.createTypedArrayList(UrlListBean.CREATOR);
            }

            public static final Parcelable.Creator<PlayInfoBean> CREATOR = new Parcelable.Creator<PlayInfoBean>() {
                @Override
                public PlayInfoBean createFromParcel(Parcel source) {
                    return new PlayInfoBean(source);
                }

                @Override
                public PlayInfoBean[] newArray(int size) {
                    return new PlayInfoBean[size];
                }
            };
        }

        public static class TagsBean implements Parcelable {
            /**
             * id : 16
             * name : 广告
             * actionUrl : eyepetizer://tag/16/?title=%E5%B9%BF%E5%91%8A
             * adTrack : null
             */

            private int id;
            private String name;
            private String actionUrl;
            private Object adTrack;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getActionUrl() {
                return actionUrl;
            }

            public void setActionUrl(String actionUrl) {
                this.actionUrl = actionUrl;
            }

            public Object getAdTrack() {
                return adTrack;
            }

            public void setAdTrack(Object adTrack) {
                this.adTrack = adTrack;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.name);
                dest.writeString(this.actionUrl);
                dest.writeParcelable((Parcelable) this.adTrack, flags);
            }

            public TagsBean() {
            }

            protected TagsBean(Parcel in) {
                this.id = in.readInt();
                this.name = in.readString();
                this.actionUrl = in.readString();
                this.adTrack = in.readParcelable(Object.class.getClassLoader());
            }

            public static final Parcelable.Creator<TagsBean> CREATOR = new Parcelable.Creator<TagsBean>() {
                @Override
                public TagsBean createFromParcel(Parcel source) {
                    return new TagsBean(source);
                }

                @Override
                public TagsBean[] newArray(int size) {
                    return new TagsBean[size];
                }
            };
        }

        public static class HeaderBean implements Parcelable {

            /**
             * id : 157
             * title : null
             * font : normal
             * cover : http://img.kaiyanapp.com/f3d0c79c2acc58ab2a72c82dbdd6bec3.jpeg?imageMogr2/quality/60/format/jpg
             * label : {"text":"","card":"","detail":null}
             * actionUrl : eyepetizer://webview/?title=%E5%A4%A7%E9%BE%84%E5%84%BF%E7%AB%A5%E7%8B%82%E6%AC%A2%E8%8A%82%EF%BC%8C%E6%80%AA%E8%B6%A3%E6%B8%B8%E4%B9%90%E5%9B%AD&url=http%3A%2F%2Fwww.eyepetizer.net%2Fvideos_article.html%3Fnid%3D157%26shareable%3Dtrue
             * labelList : [{"text":"","actionUrl":null}]
             */

            private int id;
            private String title;
            private String font;
            private String cover;
            private LabelBean label;
            private String actionUrl;
            private List<LabelListBean> labelList;
            private String description;
            private  List<String> iconList;

            public List<String> getIconList() {
                return iconList;
            }

            public void setIconList(List<String> iconList) {
                this.iconList = iconList;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getFont() {
                return font;
            }

            public void setFont(String font) {
                this.font = font;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public LabelBean getLabel() {
                return label;
            }

            public void setLabel(LabelBean label) {
                this.label = label;
            }

            public String getActionUrl() {
                return actionUrl;
            }

            public void setActionUrl(String actionUrl) {
                this.actionUrl = actionUrl;
            }

            public List<LabelListBean> getLabelList() {
                return labelList;
            }

            public void setLabelList(List<LabelListBean> labelList) {
                this.labelList = labelList;
            }

            public static class LabelBean implements Parcelable {
                /**
                 * text :
                 * card :
                 * detail : null
                 */

                private String text;
                private String card;
                private Object detail;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public String getCard() {
                    return card;
                }

                public void setCard(String card) {
                    this.card = card;
                }

                public Object getDetail() {
                    return detail;
                }

                public void setDetail(Object detail) {
                    this.detail = detail;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.text);
                    dest.writeString(this.card);
                    dest.writeParcelable((Parcelable) this.detail, flags);
                }

                public LabelBean() {
                }

                protected LabelBean(Parcel in) {
                    this.text = in.readString();
                    this.card = in.readString();
                    this.detail = in.readParcelable(Object.class.getClassLoader());
                }

                public static final Parcelable.Creator<LabelBean> CREATOR = new Parcelable.Creator<LabelBean>() {
                    @Override
                    public LabelBean createFromParcel(Parcel source) {
                        return new LabelBean(source);
                    }

                    @Override
                    public LabelBean[] newArray(int size) {
                        return new LabelBean[size];
                    }
                };
            }

            public static class LabelListBean implements Parcelable {
                /**
                 * text :
                 * actionUrl : null
                 */

                private String text;
                private Object actionUrl;


                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public Object getActionUrl() {
                    return actionUrl;
                }

                public void setActionUrl(Object actionUrl) {
                    this.actionUrl = actionUrl;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.text);
                    dest.writeParcelable((Parcelable) this.actionUrl, flags);
                }

                public LabelListBean() {
                }

                protected LabelListBean(Parcel in) {
                    this.text = in.readString();
                    this.actionUrl = in.readParcelable(Object.class.getClassLoader());
                }

                public static final Parcelable.Creator<LabelListBean> CREATOR = new Parcelable.Creator<LabelListBean>() {
                    @Override
                    public LabelListBean createFromParcel(Parcel source) {
                        return new LabelListBean(source);
                    }

                    @Override
                    public LabelListBean[] newArray(int size) {
                        return new LabelListBean[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.title);
                dest.writeString(this.font);
                dest.writeString(this.cover);
                dest.writeParcelable(this.label, flags);
                dest.writeString(this.actionUrl);
                dest.writeTypedList(this.labelList);
                dest.writeString(this.description);
                dest.writeStringList(this.iconList);
            }

            public HeaderBean() {
            }

            protected HeaderBean(Parcel in) {
                this.id = in.readInt();
                this.title = in.readString();
                this.font = in.readString();
                this.cover = in.readString();
                this.label = in.readParcelable(LabelBean.class.getClassLoader());
                this.actionUrl = in.readString();
                this.labelList = in.createTypedArrayList(LabelListBean.CREATOR);
                this.description = in.readString();
                this.iconList = in.createStringArrayList();
            }

            public static final Parcelable.Creator<HeaderBean> CREATOR = new Parcelable.Creator<HeaderBean>() {
                @Override
                public HeaderBean createFromParcel(Parcel source) {
                    return new HeaderBean(source);
                }

                @Override
                public HeaderBean[] newArray(int size) {
                    return new HeaderBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.dataType);
            dest.writeInt(this.id);
            dest.writeString(this.title);
            dest.writeString(this.slogan);
            dest.writeString(this.description);
            dest.writeParcelable(this.provider, flags);
            dest.writeString(this.category);
            dest.writeParcelable((Parcelable) this.author, flags);
            dest.writeParcelable(this.cover, flags);
            dest.writeString(this.playUrl);
            dest.writeString(this.thumbPlayUrl);
            dest.writeInt(this.duration);
            dest.writeParcelable(this.webUrl, flags);
            dest.writeLong(this.releaseTime);
            dest.writeString(this.library);
            dest.writeParcelable(this.consumption, flags);
            dest.writeParcelable((Parcelable) this.campaign, flags);
            dest.writeParcelable((Parcelable) this.waterMarks, flags);
            dest.writeParcelable((Parcelable) this.adTrack, flags);
            dest.writeString(this.type);
            dest.writeParcelable((Parcelable) this.titlePgc, flags);
            dest.writeParcelable((Parcelable) this.descriptionPgc, flags);
            dest.writeString(this.remark);
            dest.writeInt(this.idx);
            dest.writeParcelable((Parcelable) this.shareAdTrack, flags);
            dest.writeParcelable((Parcelable) this.favoriteAdTrack, flags);
            dest.writeParcelable((Parcelable) this.webAdTrack, flags);
            dest.writeLong(this.date);
            dest.writeParcelable((Parcelable) this.promotion, flags);
            dest.writeParcelable((Parcelable) this.label, flags);
            dest.writeString(this.descriptionEditor);
            dest.writeByte(this.collected ? (byte) 1 : (byte) 0);
            dest.writeByte(this.played ? (byte) 1 : (byte) 0);
            dest.writeParcelable((Parcelable) this.lastViewTime, flags);
            dest.writeTypedList(this.playInfo);
            dest.writeTypedList(this.tags);

            dest.writeList(this.itemList);
            dest.writeParcelable(this.header, flags);
            dest.writeString(this.text);
            dest.writeString(this.font);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.dataType = in.readString();
            this.id = in.readInt();
            this.title = in.readString();
            this.slogan = in.readString();
            this.description = in.readString();
            this.provider = in.readParcelable(ProviderBean.class.getClassLoader());
            this.category = in.readString();
            this.author = in.readParcelable(Object.class.getClassLoader());
            this.cover = in.readParcelable(CoverBean.class.getClassLoader());
            this.playUrl = in.readString();
            this.thumbPlayUrl = in.readString();
            this.duration = in.readInt();
            this.webUrl = in.readParcelable(WebUrlBean.class.getClassLoader());
            this.releaseTime = in.readLong();
            this.library = in.readString();
            this.consumption = in.readParcelable(ConsumptionBean.class.getClassLoader());
            this.campaign = in.readParcelable(Object.class.getClassLoader());
            this.waterMarks = in.readParcelable(Object.class.getClassLoader());
            this.adTrack = in.readParcelable(Object.class.getClassLoader());
            this.type = in.readString();
            this.titlePgc = in.readParcelable(Object.class.getClassLoader());
            this.descriptionPgc = in.readParcelable(Object.class.getClassLoader());
            this.remark = in.readString();
            this.idx = in.readInt();
            this.shareAdTrack = in.readParcelable(Object.class.getClassLoader());
            this.favoriteAdTrack = in.readParcelable(Object.class.getClassLoader());
            this.webAdTrack = in.readParcelable(Object.class.getClassLoader());
            this.date = in.readLong();
            this.promotion = in.readParcelable(Object.class.getClassLoader());
            this.label = in.readParcelable(Object.class.getClassLoader());
            this.descriptionEditor = in.readString();
            this.collected = in.readByte() != 0;
            this.played = in.readByte() != 0;
            this.lastViewTime = in.readParcelable(Object.class.getClassLoader());
            this.playInfo = in.createTypedArrayList(PlayInfoBean.CREATOR);
            this.tags = in.createTypedArrayList(TagsBean.CREATOR);
            this.itemList = new ArrayList<KaiYanVideo>();
            in.readList(this.itemList, KaiYanVideo.class.getClassLoader());
            this.header = in.readParcelable(HeaderBean.class.getClassLoader());
            this.text = in.readString();
            this.font = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeParcelable(this.data, flags);
        dest.writeString(this.tag);
    }

    public KaiYanVideo() {
    }

    protected KaiYanVideo(Parcel in) {
        this.type = in.readString();
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.tag = in.readString();
    }

    public static final Parcelable.Creator<KaiYanVideo> CREATOR = new Parcelable.Creator<KaiYanVideo>() {
        @Override
        public KaiYanVideo createFromParcel(Parcel source) {
            return new KaiYanVideo(source);
        }

        @Override
        public KaiYanVideo[] newArray(int size) {
            return new KaiYanVideo[size];
        }
    };
}

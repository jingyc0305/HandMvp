package jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

/**
 * Created by JIngYuchun on 2017/10/13.
 */

public class VodByTagBean {

    /**
     * orderBy : {"createdAt":"desc"}
     * pageSize : 10
     * totalResults : 1
     * totalPages : 1
     * currentPage : 1
     * rows : [{"videoId":271661,"videoName":"思美人","videoType":0,"description":"",
     * "videoStatus":1,"area":"大陆","firstChar":"","score":8.7,"userId":27,"fromSrc":2,
     * "isDownload":1,"createdAt":1493435172,"updatedAt":1497149817,"chargeType":0,"urlSource":0,
     * "recomm":"屈原拒做秦相愤然投江","intro":"全72集","vip":0,"params":{},"cinema":"","cinemaPrice":0,
     * "videoPrice":0,"years":"2017","isShare":0,"isReview":1,"limitStart":0,"limitEnd":0,
     * "videoCategory":2,"videoImage":"http://mmy.imgoss.starschina
     * .com/yunying/lanmutu/%E6%80%9D%E7%BE%8E%E4%BA%BA6.jpg","videoImageY":"http://mmy.imgoss
     * .starschina.com/oms/vod/271661/v/b/images/20174291493436267933_28.jpg",
     * "videoImageOttX":"","videoImageOttY":"","videoInfo":[{"title":"评分","value":8.7},
     * {"title":"导演/主持人","value":"张孝正/丁仰国"},{"title":"演员/嘉宾",
     * "value":"马可/乔振宇/张馨予/易烊千玺/刘芸/斓曦/梁田/李贤宰/尹铸胜"},{"title":"地域","value":"大陆"},{"title":"类型",
     * "value":"爱情/历史人物/古装正剧"},{"title":"年代","value":"2017"},{"title":"集数/期数","value":"80"}],
     * "rows":[{"title":"思美人72","duration":0,"isDownload":1,"pUrlId":1204783,"mediaId":1204783,
     * "isp2p":0,"vip":0,"sort":72}]}]
     */

    private OrderByBean orderBy;
    private String pageSize;
    private int totalResults;
    private int totalPages;
    private String currentPage;
    private List<RowsBeanX> rows;


    public OrderByBean getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(OrderByBean orderBy) {
        this.orderBy = orderBy;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public List<RowsBeanX> getRows() {
        return rows;
    }

    public void setRows(List<RowsBeanX> rows) {
        this.rows = rows;
    }

    public static class OrderByBean {
        /**
         * createdAt : desc
         */

        private String createdAt;

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
    }

    public static class RowsBeanX  extends SectionEntity<String>{
        /**
         * videoId : 271661
         * videoName : 思美人
         * videoType : 0
         * description :
         * videoStatus : 1
         * area : 大陆
         * firstChar :
         * score : 8.7
         * userId : 27
         * fromSrc : 2
         * isDownload : 1
         * createdAt : 1493435172
         * updatedAt : 1497149817
         * chargeType : 0
         * urlSource : 0
         * recomm : 屈原拒做秦相愤然投江
         * intro : 全72集
         * vip : 0
         * params : {}
         * cinema :
         * cinemaPrice : 0
         * videoPrice : 0
         * years : 2017
         * isShare : 0
         * isReview : 1
         * limitStart : 0
         * limitEnd : 0
         * videoCategory : 2
         * videoImage : http://mmy.imgoss.starschina
         * .com/yunying/lanmutu/%E6%80%9D%E7%BE%8E%E4%BA%BA6.jpg
         * videoImageY : http://mmy.imgoss.starschina
         * .com/oms/vod/271661/v/b/images/20174291493436267933_28.jpg
         * videoImageOttX :
         * videoImageOttY :
         * videoInfo : [{"title":"评分","value":8.7},{"title":"导演/主持人","value":"张孝正/丁仰国"},
         * {"title":"演员/嘉宾","value":"马可/乔振宇/张馨予/易烊千玺/刘芸/斓曦/梁田/李贤宰/尹铸胜"},{"title":"地域",
         * "value":"大陆"},{"title":"类型","value":"爱情/历史人物/古装正剧"},{"title":"年代","value":"2017"},
         * {"title":"集数/期数","value":"80"}]
         * rows : [{"title":"思美人72","duration":0,"isDownload":1,"pUrlId":1204783,
         * "mediaId":1204783,"isp2p":0,"vip":0,"sort":72}]
         */
        private boolean isMore;
        private int videoId;
        private String videoName;
        private int videoType;
        private String description;
        private int videoStatus;
        private String area;
        private String firstChar;
        private double score;
        private int userId;
        private int fromSrc;
        private int isDownload;
        private int createdAt;
        private int updatedAt;
        private int chargeType;
        private int urlSource;
        private String recomm;
        private String intro;
        private int vip;
        private ParamsBean params;
        private String cinema;
        private int cinemaPrice;
        private int videoPrice;
        private String years;
        private int isShare;
        private int isReview;
        private int limitStart;
        private int limitEnd;
        private int videoCategory;
        private String videoImage;
        private String videoImageY;
        private String videoImageOttX;
        private String videoImageOttY;
        private List<VideoInfoBean> videoInfo;
        private List<RowsBean> rows;
        public RowsBeanX(String s) {
            super(s);
        }
        public RowsBeanX(boolean isHeader, String header, boolean isMroe) {
            super(isHeader, header);
            this.isMore = isMroe;
        }

        public boolean isMore() {
            return isMore;
        }

        public void setMore(boolean more) {
            isMore = more;
        }

        public int getVideoId() {
            return videoId;
        }

        public void setVideoId(int videoId) {
            this.videoId = videoId;
        }

        public String getVideoName() {
            return videoName;
        }

        public void setVideoName(String videoName) {
            this.videoName = videoName;
        }

        public int getVideoType() {
            return videoType;
        }

        public void setVideoType(int videoType) {
            this.videoType = videoType;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getVideoStatus() {
            return videoStatus;
        }

        public void setVideoStatus(int videoStatus) {
            this.videoStatus = videoStatus;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getFirstChar() {
            return firstChar;
        }

        public void setFirstChar(String firstChar) {
            this.firstChar = firstChar;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getFromSrc() {
            return fromSrc;
        }

        public void setFromSrc(int fromSrc) {
            this.fromSrc = fromSrc;
        }

        public int getIsDownload() {
            return isDownload;
        }

        public void setIsDownload(int isDownload) {
            this.isDownload = isDownload;
        }

        public int getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(int createdAt) {
            this.createdAt = createdAt;
        }

        public int getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(int updatedAt) {
            this.updatedAt = updatedAt;
        }

        public int getChargeType() {
            return chargeType;
        }

        public void setChargeType(int chargeType) {
            this.chargeType = chargeType;
        }

        public int getUrlSource() {
            return urlSource;
        }

        public void setUrlSource(int urlSource) {
            this.urlSource = urlSource;
        }

        public String getRecomm() {
            return recomm;
        }

        public void setRecomm(String recomm) {
            this.recomm = recomm;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public int getVip() {
            return vip;
        }

        public void setVip(int vip) {
            this.vip = vip;
        }

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public String getCinema() {
            return cinema;
        }

        public void setCinema(String cinema) {
            this.cinema = cinema;
        }

        public int getCinemaPrice() {
            return cinemaPrice;
        }

        public void setCinemaPrice(int cinemaPrice) {
            this.cinemaPrice = cinemaPrice;
        }

        public int getVideoPrice() {
            return videoPrice;
        }

        public void setVideoPrice(int videoPrice) {
            this.videoPrice = videoPrice;
        }

        public String getYears() {
            return years;
        }

        public void setYears(String years) {
            this.years = years;
        }

        public int getIsShare() {
            return isShare;
        }

        public void setIsShare(int isShare) {
            this.isShare = isShare;
        }

        public int getIsReview() {
            return isReview;
        }

        public void setIsReview(int isReview) {
            this.isReview = isReview;
        }

        public int getLimitStart() {
            return limitStart;
        }

        public void setLimitStart(int limitStart) {
            this.limitStart = limitStart;
        }

        public int getLimitEnd() {
            return limitEnd;
        }

        public void setLimitEnd(int limitEnd) {
            this.limitEnd = limitEnd;
        }

        public int getVideoCategory() {
            return videoCategory;
        }

        public void setVideoCategory(int videoCategory) {
            this.videoCategory = videoCategory;
        }

        public String getVideoImage() {
            return videoImage;
        }

        public void setVideoImage(String videoImage) {
            this.videoImage = videoImage;
        }

        public String getVideoImageY() {
            return videoImageY;
        }

        public void setVideoImageY(String videoImageY) {
            this.videoImageY = videoImageY;
        }

        public String getVideoImageOttX() {
            return videoImageOttX;
        }

        public void setVideoImageOttX(String videoImageOttX) {
            this.videoImageOttX = videoImageOttX;
        }

        public String getVideoImageOttY() {
            return videoImageOttY;
        }

        public void setVideoImageOttY(String videoImageOttY) {
            this.videoImageOttY = videoImageOttY;
        }

        public List<VideoInfoBean> getVideoInfo() {
            return videoInfo;
        }

        public void setVideoInfo(List<VideoInfoBean> videoInfo) {
            this.videoInfo = videoInfo;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class ParamsBean {
        }

        public static class VideoInfoBean {
            /**
             * title : 评分
             * value : 8.7
             */

            private String title;
            private Object value;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Object getValue() {
                return value;
            }

            public void setValue(Object value) {
                this.value = value;
            }
        }

        public static class RowsBean {
            /**
             * title : 思美人72
             * duration : 0
             * isDownload : 1
             * pUrlId : 1204783
             * mediaId : 1204783
             * isp2p : 0
             * vip : 0
             * sort : 72
             */

            private String title;
            private int duration;
            private int isDownload;
            private int pUrlId;
            private int mediaId;
            private int isp2p;
            private int vip;
            private int sort;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public int getIsDownload() {
                return isDownload;
            }

            public void setIsDownload(int isDownload) {
                this.isDownload = isDownload;
            }

            public int getPUrlId() {
                return pUrlId;
            }

            public void setPUrlId(int pUrlId) {
                this.pUrlId = pUrlId;
            }

            public int getMediaId() {
                return mediaId;
            }

            public void setMediaId(int mediaId) {
                this.mediaId = mediaId;
            }

            public int getIsp2p() {
                return isp2p;
            }

            public void setIsp2p(int isp2p) {
                this.isp2p = isp2p;
            }

            public int getVip() {
                return vip;
            }

            public void setVip(int vip) {
                this.vip = vip;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }
        }
    }
}

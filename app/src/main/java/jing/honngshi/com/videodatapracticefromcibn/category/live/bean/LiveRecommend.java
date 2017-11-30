package jing.honngshi.com.videodatapracticefromcibn.category.live.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

/**
 * Created by JIngYuchun on 2017/11/22.
 */

public class LiveRecommend {

    private List<RoomBean> room;
    private List<?> ad;

    public List<RoomBean> getRoom() {
        return room;
    }

    public void setRoom(List<RoomBean> room) {
        this.room = room;
    }

    public List<?> getAd() {
        return ad;
    }

    public void setAd(List<?> ad) {
        this.ad = ad;
    }

    public static class RoomBean{
        /**
         * id : 0
         * name : 精彩推荐
         * is_default : 1
         * icon :
         * slug :
         * category_more :
         * type : 1
         * screen : 0
         * list : [{"beauty_cover":"http://static01.quanmin
         * .tv/201709/9329e752-2f6b-4216-b1e1-b6094eedb5d8.jpg","no":20844861,
         * "first_play_at":"1970-01-01 08:00:00","category_name":"王者荣耀","gender":1,
         * "thumb":"http://static01.quanmin
         * .tv/201709/9329e752-2f6b-4216-b1e1-b6094eedb5d8.jpg@s_0,w_390,h_219,q_100","oldNo":"",
         * "room_tag":"","last_play_at":"1970-01-01 08:00:00","screen":0,"video":"http://thumb
         * .quanmin.tv/1576711468.mp4?t=1511344500","title":"这真的不是一个老关羽","recommend_image":"",
         * "is_shield":false,"nick":"无双","uid":1576711468,"view":"81640","category_id":17,
         * "stream":"http://flv.quanmin.tv/live/1576711468.flv","slug":null,
         * "live_thumb":"http://snap.quanmin
         * .tv/1576711468-1511344658-936.jpg?imageView2/2/w/390/",
         * "love_cover":"http://static01.quanmin
         * .tv/201709/9329e752-2f6b-4216-b1e1-b6094eedb5d8.jpg","level":0,"like":0,
         * "video_quality":null,"weight":0,"starlight":13529,"check":true,"avatar":"http://a.img
         * .shouyintv.cn/eLsg201-normal","follow":79306,"play_count":0,"play_true":0,"fans":0,
         * "block_image":"","max_view":0,"default_image":"","last_end_at":"1970-01-01 08:00:00",
         * "position":"外太空","create_at":"2017-11-22 17:30:20",
         * "last_thumb":"1576711468-1511267111-77.jpg","landscape":1,"category_slug":"wangzhe",
         * "anniversary":0,"play_status":true,"status":2,"cid":6,"coin":13529,"frame":"",
         * "link":"http://www.quanmin.tv/20844861"},{"first_play_at":"1970-01-01 08:00:00",
         * "category_name":"吃喝玩乐","oldNo":"","room_tag":"猎奇;推荐",
         * "recommend_new_image":"http://static01.quanmin
         * .tv/201707/1500215494650d60bfe04b948143f40dab2dc54927e92.jpg","screen":0,
         * "view":"27904","slug":"","live_thumb":"http://snap.quanmin
         * .tv/887643-1511344626-397.jpg?imageView2/2/w/390/","love_cover":"","level":0,"like":0,
         * "video_quality":"","weight":0,"starlight":2854,"check":true,"play_count":0,
         * "play_true":0,"block_image":"","default_image":"","position":"外太空","status":2,"cid":6,
         * "coin":2854,"frame":"","beauty_cover":"","no":887643,"gender":1,"thumb":"http://snap
         * .quanmin.tv/887643-1511344626-397.jpg?imageView2/2/w/390/","last_play_at":"1970-01-01
         * 08:00:00","video":"http://thumb.quanmin.tv/887643.mp4?t=1511344500",
         * "title":"★今晚19点可以喂蛇了「异朽阁」","recommend_image":"","is_shield":false,"nick":"No咩泡泡",
         * "uid":887643,"category_id":78,"stream":"http://flv.quanmin.tv/live/887643.flv",
         * "avatar":"http://a.img.shouyintv.cn/0QEU201-normal","follow":91935,"fans":0,
         * "max_view":0,"last_end_at":"1970-01-01 08:00:00",
         * "app_shuffling_image":"http://static01.quanmin
         * .tv/201709/65097d34-f070-44d2-8f67-fe8c15cda237.jpg","create_at":"2017-11-22
         * 17:53:08","last_thumb":"887643-1511272147-203.jpg","landscape":1,
         * "category_slug":"chihewanle","anniversary":0,"play_status":true,"link":"http://www
         * .quanmin.tv/887643"},{"beauty_cover":"","no":418090,"first_play_at":"1970-01-01
         * 08:00:00","category_name":"绝地求生","gender":-1,"thumb":"http://snap.quanmin
         * .tv/418090-1511344666-987.jpg?imageView2/2/w/390/","oldNo":"","room_tag":"",
         * "last_play_at":"1970-01-01 08:00:00","screen":0,"video":"http://thumb.quanmin
         * .tv/418090.mp4?t=1511344500","title":"绿三儿：全民吃鸡精英solo赛，闹呢","recommend_image":"",
         * "is_shield":false,"nick":"我在鸭绿江边丶","uid":418090,"view":"49716","category_id":68,
         * "stream":"http://flv.quanmin.tv/live/418090.flv","slug":"san33",
         * "live_thumb":"http://snap.quanmin.tv/418090-1511344666-987.jpg?imageView2/2/w/390/",
         * "love_cover":"","level":0,"like":0,"video_quality":"","weight":0,"starlight":123,
         * "check":true,"avatar":"http://a.img.shouyintv.cn/faZO201-normal","follow":63175,
         * "play_count":0,"play_true":0,"fans":0,"block_image":"","max_view":0,
         * "default_image":"","last_end_at":"1970-01-01 08:00:00","position":"",
         * "create_at":"2017-11-22 14:59:09","last_thumb":"418090-1511285977-112.jpg",
         * "landscape":1,"category_slug":"juediqiusheng","anniversary":0,"play_status":true,
         * "status":2,"cid":6,"coin":123,"frame":"","link":"http://www.quanmin.tv/v/san33"},
         * {"beauty_cover":"http://static01.quanmin
         * .tv/201711/d7efc9e7-f6f4-4033-afd4-b17be2e7d78d.jpg","no":4976444,
         * "first_play_at":"1970-01-01 08:00:00","category_name":"全民星秀","gender":0,
         * "thumb":"http://static01.quanmin.tv/201711/d7efc9e7-f6f4-4033-afd4-b17be2e7d78d
         * .jpg@s_0,w_390,h_219,q_100","oldNo":"","room_tag":"推荐","last_play_at":"1970-01-01
         * 08:00:00","screen":0,"video":"http://thumb.quanmin.tv/4976444.mp4?t=1511344500",
         * "title":"尬歌尬舞尬聊","recommend_image":"","is_shield":false,"nick":"龙姑娘s","uid":4976444,
         * "view":"11991","category_id":4,"stream":"http://flv.quanmin.tv/live/4976444.flv",
         * "slug":"longnv","live_thumb":"http://snap.quanmin
         * .tv/4976444-1511344634-294.jpg?imageView2/2/w/390/",
         * "love_cover":"http://static01.quanmin.tv/201711/d7efc9e7-f6f4-4033-afd4-b17be2e7d78d
         * .jpg","level":0,"like":0,"video_quality":"","weight":0,"starlight":306626,
         * "check":true,"avatar":"http://a.img.shouyintv.cn/Q9pK301-normal","follow":11920,
         * "play_count":0,"play_true":0,"fans":0,"block_image":"","max_view":0,
         * "default_image":"","last_end_at":"1970-01-01 08:00:00","position":"上海市",
         * "create_at":"2017-11-22 16:30:56","last_thumb":"4976444-1511273605-306.jpg",
         * "landscape":1,"category_slug":"beauty","anniversary":0,"play_status":true,"status":2,
         * "cid":6,"coin":306626,"frame":"","link":"http://www.quanmin.tv/v/longnv"},
         * {"beauty_cover":"","no":24676347,"first_play_at":"1970-01-01 08:00:00",
         * "category_name":"QQ飞车手游","gender":1,"thumb":"http://snap.quanmin
         * .tv/1717413160-1511344668-907.jpg?imageView2/2/w/390/","oldNo":"","room_tag":"",
         * "last_play_at":"1970-01-01 08:00:00","screen":0,"video":"http://thumb.quanmin
         * .tv/1717413160.mp4?t=1511344500","title":"Q飞手游全民Tv紫轩子蓦","recommend_image":"",
         * "is_shield":false,"nick":"全民Tv紫轩子蓦","uid":1717413160,"view":"4676","category_id":77,
         * "stream":"http://flv.quanmin.tv/live/1717413160.flv","slug":null,
         * "live_thumb":"http://snap.quanmin
         * .tv/1717413160-1511344668-907.jpg?imageView2/2/w/390/","love_cover":"","level":0,
         * "like":0,"video_quality":null,"weight":0,"starlight":1,"check":true,"avatar":"http://a
         * .img.shouyintv.cn/oYtK301-normal","follow":3,"play_count":0,"play_true":0,"fans":0,
         * "block_image":"","max_view":0,"default_image":"","last_end_at":"1970-01-01 08:00:00",
         * "position":"外太空","create_at":"2017-11-22 17:00:01","last_thumb":null,"landscape":1,
         * "category_slug":"qqfeicheshouyou","anniversary":0,"play_status":true,"status":2,
         * "cid":6,"coin":1,"frame":"","link":"http://www.quanmin.tv/24676347"},
         * {"beauty_cover":"http://static01.quanmin
         * .tv/201711/f7be3c01-42ed-4f28-a0d4-c0fd5f3bfe3a.jpg","no":24503962,
         * "first_play_at":"1970-01-01 08:00:00","category_name":"Showing","gender":0,
         * "thumb":"http://a.img.shouyintv.cn/T46K301-big","oldNo":"","room_tag":"",
         * "last_play_at":"1970-01-01 08:00:00","screen":1,"video":"http://thumb.quanmin
         * .tv/1665120100.mp4?t=1511344200","title":"新人求关注求守护 么么哒","recommend_image":"",
         * "is_shield":false,"nick":"夏夏summer","uid":1665120100,"view":"3060","category_id":29,
         * "stream":"http://flv.quanmin.tv/live/1665120100.flv","slug":null,
         * "live_thumb":"http://a.img.shouyintv.cn/T46K301-big",
         * "love_cover":"http://static01.quanmin.tv/201711/f7be3c01-42ed-4f28-a0d4-c0fd5f3bfe3a
         * .jpg","level":0,"like":0,"video_quality":null,"weight":0,"starlight":1214,
         * "check":true,"avatar":"http://a.img.shouyintv.cn/T46K301-normal","follow":1154,
         * "play_count":0,"play_true":0,"fans":0,"block_image":"","max_view":0,
         * "default_image":"","last_end_at":"1970-01-01 08:00:00","position":"成都市",
         * "create_at":"2017-11-22 16:14:45","last_thumb":"1665120100-1510995885-516.jpg",
         * "landscape":0,"category_slug":"showing","anniversary":0,"play_status":true,"status":2,
         * "cid":6,"coin":1214,"frame":"","link":"http://www.quanmin.tv/24503962"}]
         */

        private int id;
        private String name;
        private int is_default;
        private String icon;
        private String slug;
        private String category_more;
        private int type;
        private int screen;
        private List<ListBean> list;


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

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getCategory_more() {
            return category_more;
        }

        public void setCategory_more(String category_more) {
            this.category_more = category_more;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getScreen() {
            return screen;
        }

        public void setScreen(int screen) {
            this.screen = screen;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean  extends SectionEntity<String> {
            /**
             * beauty_cover : http://static01.quanmin
             * .tv/201709/9329e752-2f6b-4216-b1e1-b6094eedb5d8.jpg
             * no : 20844861
             * first_play_at : 1970-01-01 08:00:00
             * category_name : 王者荣耀
             * gender : 1
             * thumb : http://static01.quanmin
             * .tv/201709/9329e752-2f6b-4216-b1e1-b6094eedb5d8.jpg@s_0,w_390,h_219,q_100
             * oldNo :
             * room_tag :
             * last_play_at : 1970-01-01 08:00:00
             * screen : 0
             * video : http://thumb.quanmin.tv/1576711468.mp4?t=1511344500
             * title : 这真的不是一个老关羽
             * recommend_image :
             * is_shield : false
             * nick : 无双
             * uid : 1576711468
             * view : 81640
             * category_id : 17
             * stream : http://flv.quanmin.tv/live/1576711468.flv
             * slug : null
             * live_thumb : http://snap.quanmin.tv/1576711468-1511344658-936.jpg?imageView2/2/w/390/
             * love_cover : http://static01.quanmin
             * .tv/201709/9329e752-2f6b-4216-b1e1-b6094eedb5d8.jpg
             * level : 0
             * like : 0
             * video_quality : null
             * weight : 0
             * starlight : 13529
             * check : true
             * avatar : http://a.img.shouyintv.cn/eLsg201-normal
             * follow : 79306
             * play_count : 0
             * play_true : 0
             * fans : 0
             * block_image :
             * max_view : 0
             * default_image :
             * last_end_at : 1970-01-01 08:00:00
             * position : 外太空
             * create_at : 2017-11-22 17:30:20
             * last_thumb : 1576711468-1511267111-77.jpg
             * landscape : 1
             * category_slug : wangzhe
             * anniversary : 0
             * play_status : true
             * status : 2
             * cid : 6
             * coin : 13529
             * frame :
             * link : http://www.quanmin.tv/20844861
             * recommend_new_image : http://static01.quanmin
             * .tv/201707/1500215494650d60bfe04b948143f40dab2dc54927e92.jpg
             * app_shuffling_image : http://static01.quanmin
             * .tv/201709/65097d34-f070-44d2-8f67-fe8c15cda237.jpg
             */

            private String headerIcon;
            //是否显示更多
            private boolean isShowMore;
            //是否显示换一换
            private boolean isShowRefreshBtn;
            private String beauty_cover;
            private int no;
            private String first_play_at;
            private String category_name;
            private int gender;
            private String thumb;
            private String oldNo;
            private String room_tag;
            private String last_play_at;
            private int screen;
            private String video;
            private String title;
            private String recommend_image;
            private boolean is_shield;
            private String nick;
            private int uid;
            private String view;
            private int category_id;
            private String stream;
            private Object slug;
            private String live_thumb;
            private String love_cover;
            private int level;
            private int like;
            private Object video_quality;
            private int weight;
            private int starlight;
            private boolean check;
            private String avatar;
            private int follow;
            private int play_count;
            private int play_true;
            private int fans;
            private String block_image;
            private int max_view;
            private String default_image;
            private String last_end_at;
            private String position;
            private String create_at;
            private String last_thumb;
            private int landscape;
            private String category_slug;
            private int anniversary;
            private boolean play_status;
            private int status;
            private int cid;
            private int coin;
            private String frame;
            private String link;
            private String recommend_new_image;
            private String app_shuffling_image;
            public ListBean(boolean isHeader, String header) {
                super(isHeader, header);
                this.isHeader = false;
            }
            public void setHeaderIcon(String headerIcon) {
                this.headerIcon = headerIcon;
            }
            public String getHeaderIcon() {
                return headerIcon;
            }
            public boolean isShowMore() {
                return isShowMore;
            }

            public void setShowMore(boolean showMore) {
                isShowMore = showMore;
            }

            public boolean isShowRefreshBtn() {
                return isShowRefreshBtn;
            }

            public void setShowRefreshBtn(boolean showRefreshBtn) {
                isShowRefreshBtn = showRefreshBtn;
            }

            public String getBeauty_cover() {
                return beauty_cover;
            }

            public void setBeauty_cover(String beauty_cover) {
                this.beauty_cover = beauty_cover;
            }

            public int getNo() {
                return no;
            }

            public void setNo(int no) {
                this.no = no;
            }

            public String getFirst_play_at() {
                return first_play_at;
            }

            public void setFirst_play_at(String first_play_at) {
                this.first_play_at = first_play_at;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getOldNo() {
                return oldNo;
            }

            public void setOldNo(String oldNo) {
                this.oldNo = oldNo;
            }

            public String getRoom_tag() {
                return room_tag;
            }

            public void setRoom_tag(String room_tag) {
                this.room_tag = room_tag;
            }

            public String getLast_play_at() {
                return last_play_at;
            }

            public void setLast_play_at(String last_play_at) {
                this.last_play_at = last_play_at;
            }

            public int getScreen() {
                return screen;
            }

            public void setScreen(int screen) {
                this.screen = screen;
            }

            public String getVideo() {
                return video;
            }

            public void setVideo(String video) {
                this.video = video;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getRecommend_image() {
                return recommend_image;
            }

            public void setRecommend_image(String recommend_image) {
                this.recommend_image = recommend_image;
            }

            public boolean isIs_shield() {
                return is_shield;
            }

            public void setIs_shield(boolean is_shield) {
                this.is_shield = is_shield;
            }

            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
                this.nick = nick;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getView() {
                return view;
            }

            public void setView(String view) {
                this.view = view;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public String getStream() {
                return stream;
            }

            public void setStream(String stream) {
                this.stream = stream;
            }

            public Object getSlug() {
                return slug;
            }

            public void setSlug(Object slug) {
                this.slug = slug;
            }

            public String getLive_thumb() {
                return live_thumb;
            }

            public void setLive_thumb(String live_thumb) {
                this.live_thumb = live_thumb;
            }

            public String getLove_cover() {
                return love_cover;
            }

            public void setLove_cover(String love_cover) {
                this.love_cover = love_cover;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getLike() {
                return like;
            }

            public void setLike(int like) {
                this.like = like;
            }

            public Object getVideo_quality() {
                return video_quality;
            }

            public void setVideo_quality(Object video_quality) {
                this.video_quality = video_quality;
            }

            public int getWeight() {
                return weight;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }

            public int getStarlight() {
                return starlight;
            }

            public void setStarlight(int starlight) {
                this.starlight = starlight;
            }

            public boolean isCheck() {
                return check;
            }

            public void setCheck(boolean check) {
                this.check = check;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getFollow() {
                return follow;
            }

            public void setFollow(int follow) {
                this.follow = follow;
            }

            public int getPlay_count() {
                return play_count;
            }

            public void setPlay_count(int play_count) {
                this.play_count = play_count;
            }

            public int getPlay_true() {
                return play_true;
            }

            public void setPlay_true(int play_true) {
                this.play_true = play_true;
            }

            public int getFans() {
                return fans;
            }

            public void setFans(int fans) {
                this.fans = fans;
            }

            public String getBlock_image() {
                return block_image;
            }

            public void setBlock_image(String block_image) {
                this.block_image = block_image;
            }

            public int getMax_view() {
                return max_view;
            }

            public void setMax_view(int max_view) {
                this.max_view = max_view;
            }

            public String getDefault_image() {
                return default_image;
            }

            public void setDefault_image(String default_image) {
                this.default_image = default_image;
            }

            public String getLast_end_at() {
                return last_end_at;
            }

            public void setLast_end_at(String last_end_at) {
                this.last_end_at = last_end_at;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }

            public String getLast_thumb() {
                return last_thumb;
            }

            public void setLast_thumb(String last_thumb) {
                this.last_thumb = last_thumb;
            }

            public int getLandscape() {
                return landscape;
            }

            public void setLandscape(int landscape) {
                this.landscape = landscape;
            }

            public String getCategory_slug() {
                return category_slug;
            }

            public void setCategory_slug(String category_slug) {
                this.category_slug = category_slug;
            }

            public int getAnniversary() {
                return anniversary;
            }

            public void setAnniversary(int anniversary) {
                this.anniversary = anniversary;
            }

            public boolean isPlay_status() {
                return play_status;
            }

            public void setPlay_status(boolean play_status) {
                this.play_status = play_status;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }

            public int getCoin() {
                return coin;
            }

            public void setCoin(int coin) {
                this.coin = coin;
            }

            public String getFrame() {
                return frame;
            }

            public void setFrame(String frame) {
                this.frame = frame;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getRecommend_new_image() {
                return recommend_new_image;
            }

            public void setRecommend_new_image(String recommend_new_image) {
                this.recommend_new_image = recommend_new_image;
            }

            public String getApp_shuffling_image() {
                return app_shuffling_image;
            }

            public void setApp_shuffling_image(String app_shuffling_image) {
                this.app_shuffling_image = app_shuffling_image;
            }
        }
    }
}

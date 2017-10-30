package jing.honngshi.com.videodatapracticefromcibn.category.live.bean;


import java.util.List;

public class GetAllListData {
    private int code;
    private String msg;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {


        private RecommendDataBean recommend_data;
        private List<BannerBean> banner;
        private List<EntranceIconsBean> entranceIcons;
        private List<PartitionsBean> partitions;

        public RecommendDataBean getRecommend_data() {
            return recommend_data;
        }

        public void setRecommend_data(RecommendDataBean recommend_data) {
            this.recommend_data = recommend_data;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<EntranceIconsBean> getEntranceIcons() {
            return entranceIcons;
        }

        public void setEntranceIcons(List<EntranceIconsBean> entranceIcons) {
            this.entranceIcons = entranceIcons;
        }

        public List<PartitionsBean> getPartitions() {
            return partitions;
        }

        public void setPartitions(List<PartitionsBean> partitions) {
            this.partitions = partitions;
        }

        public static class RecommendDataBean {


            private PartitionBean partition;
            private List<LivesBean> lives;
            private List<BannerDataBean> banner_data;

            public PartitionBean getPartition() {
                return partition;
            }

            public void setPartition(PartitionBean partition) {
                this.partition = partition;
            }

            public List<LivesBean> getLives() {
                return lives;
            }

            public void setLives(List<LivesBean> lives) {
                this.lives = lives;
            }

            public List<BannerDataBean> getBanner_data() {
                return banner_data;
            }

            public void setBanner_data(List<BannerDataBean> banner_data) {
                this.banner_data = banner_data;
            }

            public static class PartitionBean {
                /**
                 * id : 0
                 * name : 推荐主播
                 * area : hot
                 * sub_icon : {"src":"http://static.hdslb
                 * .com/live-static/images/mobile/android/small/xhdpi/-1.png?20171020172700",
                 * "height":"42","width":"42"}
                 * count : 1406
                 */

                private int id;
                private String name;
                private String area;
                private SubIconBean sub_icon;
                private int count;

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

                public String getArea() {
                    return area;
                }

                public void setArea(String area) {
                    this.area = area;
                }

                public SubIconBean getSub_icon() {
                    return sub_icon;
                }

                public void setSub_icon(SubIconBean sub_icon) {
                    this.sub_icon = sub_icon;
                }

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public static class SubIconBean {
                    /**
                     * src : http://static.hdslb
                     * .com/live-static/images/mobile/android/small/xhdpi/-1.png?20171020172700
                     * height : 42
                     * width : 42
                     */

                    private String src;
                    private String height;
                    private String width;

                    public String getSrc() {
                        return src;
                    }

                    public void setSrc(String src) {
                        this.src = src;
                    }

                    public String getHeight() {
                        return height;
                    }

                    public void setHeight(String height) {
                        this.height = height;
                    }

                    public String getWidth() {
                        return width;
                    }

                    public void setWidth(String width) {
                        this.width = width;
                    }
                }
            }

            public static class LivesBean {
                /**
                 * owner : {"face":"http://i2.hdslb
                 * .com/bfs/face/8c1104f4703910861ac9eb1de397a7df8e2dac3c.jpg","mid":175887557,
                 * "name":"晓庄哒哒"}
                 * cover : {"src":"http://i0.hdslb
                 * .com/bfs/live/55e5abaa7eb7ef2488ade0ecbac418ac664600e6.jpg","height":180,
                 * "width":320}
                 * room_id : 5135568
                 * check_version : 0
                 * online : 9913
                 * area : 单机联机
                 * area_id : 1
                 * title : 恐怖惊悚游戏（胆小勿进）！录制的视频
                 * playurl : http://dl.live-play.acgvideo
                 * .com/live-dl/115412/live_175887557_2868244.flv?wsSecret
                 * =c68932d95efbcf7a00843aa24c610c7d&wsTime=1508553602
                 * accept_quality : 4
                 * broadcast_type : 0
                 * is_tv : 0
                 * area_v2_id : 107
                 * area_v2_name : 其他游戏
                 * area_v2_parent_id : 2
                 * area_v2_parent_name : 游戏
                 * corner :
                 */

                private OwnerBean owner;
                private CoverBean cover;
                private int room_id;
                private int check_version;
                private int online;
                private String area;
                private int area_id;
                private String title;
                private String playurl;
                private String accept_quality;
                private int broadcast_type;
                private int is_tv;
                private int area_v2_id;
                private String area_v2_name;
                private int area_v2_parent_id;
                private String area_v2_parent_name;
                private String corner;

                public OwnerBean getOwner() {
                    return owner;
                }

                public void setOwner(OwnerBean owner) {
                    this.owner = owner;
                }

                public CoverBean getCover() {
                    return cover;
                }

                public void setCover(CoverBean cover) {
                    this.cover = cover;
                }

                public int getRoom_id() {
                    return room_id;
                }

                public void setRoom_id(int room_id) {
                    this.room_id = room_id;
                }

                public int getCheck_version() {
                    return check_version;
                }

                public void setCheck_version(int check_version) {
                    this.check_version = check_version;
                }

                public int getOnline() {
                    return online;
                }

                public void setOnline(int online) {
                    this.online = online;
                }

                public String getArea() {
                    return area;
                }

                public void setArea(String area) {
                    this.area = area;
                }

                public int getArea_id() {
                    return area_id;
                }

                public void setArea_id(int area_id) {
                    this.area_id = area_id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getPlayurl() {
                    return playurl;
                }

                public void setPlayurl(String playurl) {
                    this.playurl = playurl;
                }

                public String getAccept_quality() {
                    return accept_quality;
                }

                public void setAccept_quality(String accept_quality) {
                    this.accept_quality = accept_quality;
                }

                public int getBroadcast_type() {
                    return broadcast_type;
                }

                public void setBroadcast_type(int broadcast_type) {
                    this.broadcast_type = broadcast_type;
                }

                public int getIs_tv() {
                    return is_tv;
                }

                public void setIs_tv(int is_tv) {
                    this.is_tv = is_tv;
                }

                public int getArea_v2_id() {
                    return area_v2_id;
                }

                public void setArea_v2_id(int area_v2_id) {
                    this.area_v2_id = area_v2_id;
                }

                public String getArea_v2_name() {
                    return area_v2_name;
                }

                public void setArea_v2_name(String area_v2_name) {
                    this.area_v2_name = area_v2_name;
                }

                public int getArea_v2_parent_id() {
                    return area_v2_parent_id;
                }

                public void setArea_v2_parent_id(int area_v2_parent_id) {
                    this.area_v2_parent_id = area_v2_parent_id;
                }

                public String getArea_v2_parent_name() {
                    return area_v2_parent_name;
                }

                public void setArea_v2_parent_name(String area_v2_parent_name) {
                    this.area_v2_parent_name = area_v2_parent_name;
                }

                public String getCorner() {
                    return corner;
                }

                public void setCorner(String corner) {
                    this.corner = corner;
                }

                public static class OwnerBean {
                    /**
                     * face : http://i2.hdslb
                     * .com/bfs/face/8c1104f4703910861ac9eb1de397a7df8e2dac3c.jpg
                     * mid : 175887557
                     * name : 晓庄哒哒
                     */

                    private String face;
                    private int mid;
                    private String name;

                    public String getFace() {
                        return face;
                    }

                    public void setFace(String face) {
                        this.face = face;
                    }

                    public int getMid() {
                        return mid;
                    }

                    public void setMid(int mid) {
                        this.mid = mid;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }
                }

                public static class CoverBean {
                    /**
                     * src : http://i0.hdslb
                     * .com/bfs/live/55e5abaa7eb7ef2488ade0ecbac418ac664600e6.jpg
                     * height : 180
                     * width : 320
                     */

                    private String src;
                    private int height;
                    private int width;

                    public String getSrc() {
                        return src;
                    }

                    public void setSrc(String src) {
                        this.src = src;
                    }

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
                }
            }

            public static class BannerDataBean {
                /**
                 * owner : {"face":"http://i1.hdslb
                 * .com/bfs/face/456faf0d3f626447b8aac471025b1f809b6efa09.jpg","mid":32122361,
                 * "name":"麻辣图图"}
                 * cover : {"src":"http://i0.hdslb
                 * .com/bfs/live/bb7ca6dd8abf826e471242b8206de4fb3afeef64.jpg","height":180,
                 * "width":320}
                 * room_id : 280070
                 * check_version : 0
                 * online : 1737
                 * area : 绘画专区
                 * area_id : 9
                 * title : 摸摸军姬~
                 * playurl : http://live-play.acgvideo
                 * .com/live/315/live_32122361_6331550.flv?wsSecret
                 * =483bd65a0a97085dd6e564394f572406&wsTime=59c32d4e
                 * accept_quality : 4
                 * broadcast_type : 0
                 * is_tv : 0
                 * area_v2_id : 51
                 * area_v2_name : 原创绘画
                 * area_v2_parent_id : 4
                 * area_v2_parent_name : 绘画
                 * corner :
                 * is_clip : 1
                 */

                private OwnerBeanX owner;
                private CoverBeanX cover;
                private int room_id;
                private int check_version;
                private int online;
                private String area;
                private int area_id;
                private String title;
                private String playurl;
                private String accept_quality;
                private int broadcast_type;
                private int is_tv;
                private int area_v2_id;
                private String area_v2_name;
                private int area_v2_parent_id;
                private String area_v2_parent_name;
                private String corner;
                private int is_clip;

                public OwnerBeanX getOwner() {
                    return owner;
                }

                public void setOwner(OwnerBeanX owner) {
                    this.owner = owner;
                }

                public CoverBeanX getCover() {
                    return cover;
                }

                public void setCover(CoverBeanX cover) {
                    this.cover = cover;
                }

                public int getRoom_id() {
                    return room_id;
                }

                public void setRoom_id(int room_id) {
                    this.room_id = room_id;
                }

                public int getCheck_version() {
                    return check_version;
                }

                public void setCheck_version(int check_version) {
                    this.check_version = check_version;
                }

                public int getOnline() {
                    return online;
                }

                public void setOnline(int online) {
                    this.online = online;
                }

                public String getArea() {
                    return area;
                }

                public void setArea(String area) {
                    this.area = area;
                }

                public int getArea_id() {
                    return area_id;
                }

                public void setArea_id(int area_id) {
                    this.area_id = area_id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getPlayurl() {
                    return playurl;
                }

                public void setPlayurl(String playurl) {
                    this.playurl = playurl;
                }

                public String getAccept_quality() {
                    return accept_quality;
                }

                public void setAccept_quality(String accept_quality) {
                    this.accept_quality = accept_quality;
                }

                public int getBroadcast_type() {
                    return broadcast_type;
                }

                public void setBroadcast_type(int broadcast_type) {
                    this.broadcast_type = broadcast_type;
                }

                public int getIs_tv() {
                    return is_tv;
                }

                public void setIs_tv(int is_tv) {
                    this.is_tv = is_tv;
                }

                public int getArea_v2_id() {
                    return area_v2_id;
                }

                public void setArea_v2_id(int area_v2_id) {
                    this.area_v2_id = area_v2_id;
                }

                public String getArea_v2_name() {
                    return area_v2_name;
                }

                public void setArea_v2_name(String area_v2_name) {
                    this.area_v2_name = area_v2_name;
                }

                public int getArea_v2_parent_id() {
                    return area_v2_parent_id;
                }

                public void setArea_v2_parent_id(int area_v2_parent_id) {
                    this.area_v2_parent_id = area_v2_parent_id;
                }

                public String getArea_v2_parent_name() {
                    return area_v2_parent_name;
                }

                public void setArea_v2_parent_name(String area_v2_parent_name) {
                    this.area_v2_parent_name = area_v2_parent_name;
                }

                public String getCorner() {
                    return corner;
                }

                public void setCorner(String corner) {
                    this.corner = corner;
                }

                public int getIs_clip() {
                    return is_clip;
                }

                public void setIs_clip(int is_clip) {
                    this.is_clip = is_clip;
                }

                public static class OwnerBeanX {
                    /**
                     * face : http://i1.hdslb
                     * .com/bfs/face/456faf0d3f626447b8aac471025b1f809b6efa09.jpg
                     * mid : 32122361
                     * name : 麻辣图图
                     */

                    private String face;
                    private int mid;
                    private String name;

                    public String getFace() {
                        return face;
                    }

                    public void setFace(String face) {
                        this.face = face;
                    }

                    public int getMid() {
                        return mid;
                    }

                    public void setMid(int mid) {
                        this.mid = mid;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }
                }

                public static class CoverBeanX {
                    /**
                     * src : http://i0.hdslb
                     * .com/bfs/live/bb7ca6dd8abf826e471242b8206de4fb3afeef64.jpg
                     * height : 180
                     * width : 320
                     */

                    private String src;
                    private int height;
                    private int width;

                    public String getSrc() {
                        return src;
                    }

                    public void setSrc(String src) {
                        this.src = src;
                    }

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
                }
            }
        }

        public static class BannerBean {
            /**
             * title : 相簿粗粗
             * img : http://i0.hdslb.com/bfs/live/c5e35220665c39e4838e09362b05e068b0fe1c2e.jpg
             * remark : 相簿粗粗
             * link : http://live.bilibili.com/AppBanner/index?id=617
             */

            private String title;
            private String img;
            private String remark;
            private String link;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }
        }

        public static class EntranceIconsBean {
            /**
             * id : 9
             * name : 绘画专区
             * entrance_icon : {"src":"http://static.hdslb
             * .com/live-static/images/mobile/android/big/xhdpi/9_big.png?20170927172700",
             * "height":"89","width":"89"}
             */

            private int id;
            private String name;
            private EntranceIconBean entrance_icon;

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

            public EntranceIconBean getEntrance_icon() {
                return entrance_icon;
            }

            public void setEntrance_icon(EntranceIconBean entrance_icon) {
                this.entrance_icon = entrance_icon;
            }

            public static class EntranceIconBean {
                /**
                 * src : http://static.hdslb
                 * .com/live-static/images/mobile/android/big/xhdpi/9_big.png?20170927172700
                 * height : 89
                 * width : 89
                 */

                private String src;
                private String height;
                private String width;

                public String getSrc() {
                    return src;
                }

                public void setSrc(String src) {
                    this.src = src;
                }

                public String getHeight() {
                    return height;
                }

                public void setHeight(String height) {
                    this.height = height;
                }

                public String getWidth() {
                    return width;
                }

                public void setWidth(String width) {
                    this.width = width;
                }
            }
        }

        public static class PartitionsBean {


            private PartitionBeanX partition;
            private List<LivesBeanX> lives;

            public PartitionBeanX getPartition() {
                return partition;
            }

            public void setPartition(PartitionBeanX partition) {
                this.partition = partition;
            }

            public List<LivesBeanX> getLives() {
                return lives;
            }

            public void setLives(List<LivesBeanX> lives) {
                this.lives = lives;
            }

            public static class PartitionBeanX {
                /**
                 * id : 1
                 * name : 娱乐
                 * sub_icon : {"src":"http://s1.hdslb
                 * .com/bfs/static/blive/live-assets/mobile/android/android/1_2x
                 * .png?201709151052","height":"42","width":"42"}
                 * count : 438
                 */

                private int id;
                private String name;
                private SubIconBeanX sub_icon;
                private int count;

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

                public SubIconBeanX getSub_icon() {
                    return sub_icon;
                }

                public void setSub_icon(SubIconBeanX sub_icon) {
                    this.sub_icon = sub_icon;
                }

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public static class SubIconBeanX {
                    /**
                     * src : http://s1.hdslb
                     * .com/bfs/static/blive/live-assets/mobile/android/android/1_2x
                     * .png?201709151052
                     * height : 42
                     * width : 42
                     */

                    private String src;
                    private String height;
                    private String width;

                    public String getSrc() {
                        return src;
                    }

                    public void setSrc(String src) {
                        this.src = src;
                    }

                    public String getHeight() {
                        return height;
                    }

                    public void setHeight(String height) {
                        this.height = height;
                    }

                    public String getWidth() {
                        return width;
                    }

                    public void setWidth(String width) {
                        this.width = width;
                    }
                }
            }

            public static class LivesBeanX {
                /**
                 * roomid : 1281416
                 * uid : 35999694
                 * title : 悠闲的一天从摸鱼开始
                 * uname : 骚可爱的地瓜m
                 * online : 1814
                 * user_cover : http://i0.hdslb
                 * .com/bfs/live/5f3ecea6ab18023fdd45bc5a4bd903cdc9e43068.jpg
                 * user_cover_flag : 1
                 * system_cover : http://i0.hdslb.com/bfs/live/1281416.jpg?10211008
                 * link : /1281416
                 * face : http://i2.hdslb.com/bfs/face/81a17e5236c793edb3d19cce8720547beca816ca.jpg
                 * cover_size : {"height":180,"width":320}
                 * parent_id : 1
                 * parent_name : 娱乐
                 * area_id : 26
                 * area_name : 日常
                 * play_url : http://xl.live-play.acgvideo
                 * .com/live-xl/458031/live_35999694_2231495.flv?wsSecret
                 * =b935943c6b9e24ec612999e70aa70328&wsTime=1508553602
                 * accept_quality : 4
                 * broadcast_type : 0
                 * is_tv : 0
                 * corner :
                 */

                private int roomid;
                private int uid;
                private String title;
                private String uname;
                private int online;
                private String user_cover;
                private int user_cover_flag;
                private String system_cover;
                private String link;
                private String face;
                private CoverSizeBean cover_size;
                private int parent_id;
                private String parent_name;
                private int area_id;
                private String area_name;
                private String play_url;
                private String accept_quality;
                private int broadcast_type;
                private int is_tv;
                private String corner;

                public int getRoomid() {
                    return roomid;
                }

                public void setRoomid(int roomid) {
                    this.roomid = roomid;
                }

                public int getUid() {
                    return uid;
                }

                public void setUid(int uid) {
                    this.uid = uid;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getUname() {
                    return uname;
                }

                public void setUname(String uname) {
                    this.uname = uname;
                }

                public int getOnline() {
                    return online;
                }

                public void setOnline(int online) {
                    this.online = online;
                }

                public String getUser_cover() {
                    return user_cover;
                }

                public void setUser_cover(String user_cover) {
                    this.user_cover = user_cover;
                }

                public int getUser_cover_flag() {
                    return user_cover_flag;
                }

                public void setUser_cover_flag(int user_cover_flag) {
                    this.user_cover_flag = user_cover_flag;
                }

                public String getSystem_cover() {
                    return system_cover;
                }

                public void setSystem_cover(String system_cover) {
                    this.system_cover = system_cover;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getFace() {
                    return face;
                }

                public void setFace(String face) {
                    this.face = face;
                }

                public CoverSizeBean getCover_size() {
                    return cover_size;
                }

                public void setCover_size(CoverSizeBean cover_size) {
                    this.cover_size = cover_size;
                }

                public int getParent_id() {
                    return parent_id;
                }

                public void setParent_id(int parent_id) {
                    this.parent_id = parent_id;
                }

                public String getParent_name() {
                    return parent_name;
                }

                public void setParent_name(String parent_name) {
                    this.parent_name = parent_name;
                }

                public int getArea_id() {
                    return area_id;
                }

                public void setArea_id(int area_id) {
                    this.area_id = area_id;
                }

                public String getArea_name() {
                    return area_name;
                }

                public void setArea_name(String area_name) {
                    this.area_name = area_name;
                }

                public String getPlay_url() {
                    return play_url;
                }

                public void setPlay_url(String play_url) {
                    this.play_url = play_url;
                }

                public String getAccept_quality() {
                    return accept_quality;
                }

                public void setAccept_quality(String accept_quality) {
                    this.accept_quality = accept_quality;
                }

                public int getBroadcast_type() {
                    return broadcast_type;
                }

                public void setBroadcast_type(int broadcast_type) {
                    this.broadcast_type = broadcast_type;
                }

                public int getIs_tv() {
                    return is_tv;
                }

                public void setIs_tv(int is_tv) {
                    this.is_tv = is_tv;
                }

                public String getCorner() {
                    return corner;
                }

                public void setCorner(String corner) {
                    this.corner = corner;
                }

                public static class CoverSizeBean {
                    /**
                     * height : 180
                     * width : 320
                     */

                    private int height;
                    private int width;

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
                }
            }
        }
    }
}

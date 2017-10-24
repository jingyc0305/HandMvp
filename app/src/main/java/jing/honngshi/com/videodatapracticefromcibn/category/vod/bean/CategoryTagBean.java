package jing.honngshi.com.videodatapracticefromcibn.category.vod.bean;

import java.io.Serializable;

/**
 * Created by JIngYuchun on 2017/10/13.
 */

public class CategoryTagBean implements Serializable{

    /**
     * tagId : 160
     * name : 古装正剧
     */

    private int tagId;
    private String name;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

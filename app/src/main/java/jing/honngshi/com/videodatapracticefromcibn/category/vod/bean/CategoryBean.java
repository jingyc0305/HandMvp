package jing.honngshi.com.videodatapracticefromcibn.category.vod.bean;

import java.io.Serializable;

/**
 * Created by JIngYuchun on 2017/10/12.
 */

public class CategoryBean implements Serializable{

    /**
     * categoryId : 9
     * name : 电视剧
     */

    private int categoryId;
    private String name;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.cs.com.listimageview.four.bean;

import java.util.List;

/**
 * Create by Chenshuai
 * Date 2019/8/8/008 10:26
 * Descripton
 */
public class HomeBean {

    private List<DataBeanX> data;

    public List<DataBeanX> getData() {
        return data;
    }

    public void setData(List<DataBeanX> data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * modelname : 焦点新闻
         * type : banner
         * data : [{"image":"https://b.bdstatic.com/boxlib/20180120/2018012017100383423448679.jpg","url":"http://pic.chinadaily.com.cn/2018-01/20/content_35544757.htm","title":"西昌铁路警方用表情包宣传爱路小知识","id":"whyidzhi","order":"1"},{"image":"https://b.bdstatic.com/boxlib/20180120/2018012017100311270281486.jpg","url":"http://pic.chinadaily.com.cn/2018-01/20/content_35544758.htm","title":"成都熊猫基地太阳产房全新升级","id":"whyidzhi","order":"2"},{"image":"https://b.bdstatic.com/boxlib/20180120/2018012017100392134086973.jpg","url":"http://pic.chinadaily.com.cn/2018-01/20/content_35544759.htm","title":"长沙\u201c90后\u201d交警用手绘记录交警故事","id":"whyidzhi","order":"3"}]
         */

        private String modelname;
        private String type;
        private List<DataBean> data;

        public String getModelname() {
            return modelname;
        }

        public void setModelname(String modelname) {
            this.modelname = modelname;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * image : https://b.bdstatic.com/boxlib/20180120/2018012017100383423448679.jpg
             * url : http://pic.chinadaily.com.cn/2018-01/20/content_35544757.htm
             * title : 西昌铁路警方用表情包宣传爱路小知识
             * id : whyidzhi
             * order : 1
             */

            private String image;
            private String url;
            private String title;
            private String id;
            private String order;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

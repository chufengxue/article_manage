package cn.wu.am.param;

import org.hibernate.validator.constraints.Length;

// 编辑新文章时的
public class NewArticleParam {
    @Length(min = 2, max = 100, message = "文章标题长度为2到100个字符")
    private String title;
    @Length(min = 10, max = 2600, message = "文章内容长度为10到2600个字符")
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

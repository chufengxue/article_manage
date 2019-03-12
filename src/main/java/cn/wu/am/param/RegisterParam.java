package cn.wu.am.param;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class RegisterParam {
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @NotEmpty(message="密码不能为空")
    @Length(min=6,message="密码长度不能小于6位")
    private String password;
    @NotEmpty(message="确认密码不能为空")
    @Length(min=6,message="确认密码长度不能小于6位")
    private String password2;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}

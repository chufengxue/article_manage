package cn.wu.am;

import cn.wu.am.dao.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.wu.am.dao")
public class ArticleManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticleManageApplication.class, args);

	}

}

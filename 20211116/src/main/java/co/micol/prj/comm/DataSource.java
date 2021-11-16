package co.micol.prj.comm;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DataSource { //가장 기본적인 싱클턴 클래스이다..
	private static SqlSessionFactory sqlSessionFactory;
		
	private DataSource() {}//외부에서 생성자를 못만들도록 나를 만드는 역할..
	
	public static SqlSessionFactory getDataSource() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSessionFactory;
	}
}

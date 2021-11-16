package co.micol.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.comm.Command;

public class HomeCommand implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		//처음 접근 했을 때 돌려주는 페이지.
		return "home/home"; //홈 디렉토리 밑에 home.jsp를 실행해라는 의미이다.
	}

}

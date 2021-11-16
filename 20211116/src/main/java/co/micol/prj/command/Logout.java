package co.micol.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.micol.prj.comm.Command;

public class Logout implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		//로그아웃 처리.
		HttpSession session = request.getSession();
		session.invalidate();//remove는 서버가 가지고 있는 session속성중 하나를 없앤다는 의미이고 지금 invalidate는 세션 자체를 없앤다는 의미이다.
		
		return "home.do";
	}

}

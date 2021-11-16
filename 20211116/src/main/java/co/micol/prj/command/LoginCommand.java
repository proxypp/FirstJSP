package co.micol.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.micol.prj.comm.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.MemberVO;
import co.micol.prj.member.serviceImpl.MemberServiceImpl;

public class LoginCommand implements Command {
	
	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		MemberService memberDao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		HttpSession session = request.getSession(); //서버가 만들어놓은 세션객체 가져오기.  //전역변수처럼 사용하기. 세션에 담기면 아무페이지에서나 읽을수가 있다.
		String message = null;
		
		vo.setId(request.getParameter("id"));
		vo.setPassword(request.getParameter("password"));
		vo = memberDao.memberSelect(vo);
		
		if (vo != null) {
			//session 객체 만들어주기 후 응답하기. 세션객체가 생성될때 고유한 ID가 생성된다. 1000개든 10000개든
			session.setAttribute("id", vo.getId()); //전역변수처럼 사용하기.
			session.setAttribute("name", vo.getName()); //전역변수처럼 사용하기.
			session.setAttribute("author", vo.getAuthor()); //전역변수처럼 사용하기.
			message = vo.getName()+"님 환영합니다.";
		}else {
			message = "아이디 또는 패스워드가 틀립니다.";
		}
		
		request.setAttribute("message", message); //결과를 담아서 보낼때.  //이 메세지는 이 memberLogin.jsp에서만 사용할 수 있다.
		
		return "member/memberLogin"; //돌려줄 페이지, 보여줄 페이지.
	}

}

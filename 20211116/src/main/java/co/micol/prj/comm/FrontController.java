package co.micol.prj.comm;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.command.HomeCommand;
import co.micol.prj.command.LoginCommand;
import co.micol.prj.command.LoginForm;
import co.micol.prj.command.Logout;
import co.micol.prj.command.MemberList;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashMap<String, Command> map = new HashMap<String, Command>();

    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }


	public void init(ServletConfig config) throws ServletException {
		//초기값 설정("/login.do",new LoginCommand()")
		map.put("/home.do", new HomeCommand()); //홈 페이지를 보여주는 Command
		map.put("/login.do", new LoginCommand()); //로그인 처리...
		map.put("/memberList.do", new MemberList()); //멤버 목록 보기..
		map.put("/loginForm.do", new LoginForm()); //로그인 폼 호출..
		map.put("/logout.do", new Logout()); //로그아웃.
		
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청을 분석하고 처리하는 부분.
		request.setCharacterEncoding("utf-8"); // 한글깨짐 방지.
		String uri = request.getRequestURI(); //URI를 구한다.
		String contextPath = request.getContextPath(); // contextPath 구함.
		String page = uri.substring(contextPath.length()); //실제 요청을 구함.
		
		//System.out.println(request.getRemoteAddr()+"=======");
		
		Command command = map.get(page); //요청에 대한 수행할 command를 찾음.    //인터페이스는 자기 자신을 초기화 하지 못함.. 그래서 map.put(key, value)의 value값인 new ~~을 이용해 초기화한다.
		String viewPage = command.run(request, response); //처음 home/home;
		
		//WEB-INF에 접근 할 수 있도록 viewResolve를 만듬.
		
		if(!viewPage.endsWith(".do")) {
			viewPage = "WEB-INF/views/"+viewPage+".jsp";
		}
		
		//응답을 처리한다.    //RequestDispatcher는 받은 요청을 같이 실어서 보내줄 수 있게하는 메소드이다.
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
		
	}
}

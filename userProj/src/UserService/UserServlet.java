package UserService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		UserDAO dao = new UserDAO();
		List<UserVO> list = dao.getUserList();
		
		JSONArray ary = new JSONArray();
		for(UserVO vo : list) {
			JSONObject obj = new JSONObject();
			obj.put("userId", vo.getUserId());
			obj.put("userPass", vo.getUserPass());
			obj.put("userName", vo.getUserName());
			obj.put("userPhone", vo.getUserPhone());
			obj.put("gender", vo.getUserGender());
			ary.add(obj);
		}
		response.getWriter().print(ary);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String id = request.getParameter("userId");
		String pass = request.getParameter("userPass");
		String name = request.getParameter("userName");
		String phone = request.getParameter("userPhone");
		String gender = request.getParameter("gender");
		
		UserDAO dao = new UserDAO();
		UserVO vo = new UserVO();
		
		vo.setUserId(id);
		vo.setUserPass(pass);
		vo.setUserName(name);
		vo.setUserPhone(phone);
		vo.setUserGender(gender);
		
		UserVO uvo = dao.insertUser(vo);
		
		JSONObject obj = new JSONObject();
		obj.put("userId", uvo.getUserId());
		obj.put("userPass", uvo.getUserPass());
		obj.put("userName", uvo.getUserName());
		obj.put("userPhone", uvo.getUserPhone());
		obj.put("gender", uvo.getUserGender());
		
		response.getWriter().print(obj);
	}

}

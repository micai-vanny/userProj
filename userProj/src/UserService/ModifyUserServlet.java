package UserService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

@WebServlet("/modifyUserServlet")
public class ModifyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ModifyUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		String pass = request.getParameter("userPass");
		String phone = request.getParameter("userPhone");
		String id = request.getParameter("userId");
		
		UserDAO dao = new UserDAO();
		UserVO vo = new UserVO();
		
		vo.setUserPass(pass);
		vo.setUserPhone(phone);
		vo.setUserId(id);
		
		JSONObject obj = new JSONObject();
		if(dao.modifyUser(vo)) {
			obj.put("returnCode", "Success");
		}else {
			obj.put("returnCode", "Fail");
		}
		response.getWriter().print(obj);
	}

}

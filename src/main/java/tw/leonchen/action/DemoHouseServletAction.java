package tw.leonchen.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import tw.leonchen.model.HouseBean;
import tw.leonchen.util.HibernateUtil;


@WebServlet("/DemoHouseServletAction.do")
public class DemoHouseServletAction extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
	}
	
	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		SessionFactory factory= HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		Query<HouseBean> query =  session.createQuery("from HouseBean",HouseBean.class);
		List<HouseBean> lists = query.list();
		
		for(HouseBean hBean : lists) {
			out.write("HouseId" + hBean.getHouseid()+"<br/>");
			out.write("House Name:"+hBean.getHousename()+"<br/>");
		}
		
		out.close();
	}

}

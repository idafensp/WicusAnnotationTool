package net.oegupm.wicus.webannotator.ui;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SkeletonGenerator
 */
@WebServlet("/SkeletonGenerator")
public class SkeletonGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String FormatParser = "/FormatParser.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SkeletonGenerator() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher(FormatParser);
	    view.forward(request, response);
	}

}

package com.FileDownload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.File.dao.Dao;

public class Download extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String User = request.getParameter("User");
		// File name which has to be downloaded
		String file = Dao.getImageByUser(User);
		// Location of file which has to be downloaded
		String root = getServletContext().getRealPath("/");
		String Path = root + File.separator + User + File.separator + Dao.getImageByUser(User);
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file + "\"");

		FileInputStream fileInputStream = new FileInputStream(Path);

		int i;
		while ((i = fileInputStream.read()) != -1) {
			out.write(i);
		}
		fileInputStream.close();
	}
	
//	public static void main(String[] args) {
//		File curDir = new File("./resources");
//		getAllFiles(curDir);
//	}
//
//	private static void getAllFiles(File curDir) {
//
//		File[] filesList = curDir.listFiles();
//		for (File f : filesList) {
//			if (f.isDirectory())
//				System.out.println(f.getName());
//			if (f.isFile()) {
//				System.out.println(f.getName());
//			}
//		}
//
//	}

}
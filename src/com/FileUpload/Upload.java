package com.FileUpload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.File.dao.Dao;

public class Upload extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		if (isMultipart) {
			// Create a factory for disk-based file items
			FileItemFactory factory = new DiskFileItemFactory();
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				// Parse the request
				List<?> items = upload.parseRequest(req);
				String User = "";
				Iterator<?> iterator = items.iterator();
				while (iterator.hasNext()) {
					FileItem item = (FileItem) iterator.next();
					if (!item.isFormField()) {
						// Get file value from here
//						String fieldName = item.getFieldName();
						String fileName = item.getName();
//						String contentType = item.getContentType();
//						boolean isInMemory = item.isInMemory();
//						long sizeInBytes = item.getSize();
						if (fileName != "") {
							String root = getServletContext().getRealPath("/");
							File path = new File(root + File.separator + User);
							if (!path.exists()) {
								boolean status = path.mkdirs();
								if(status) {
									System.out.println("Directory Made");
								}else {
									System.out.println("Error in making Directory");
								}
							}
							String FileName = System.currentTimeMillis() + ".jpg";
							File uploadedFile = new File(path + File.separator + FileName);
							System.out.println(uploadedFile.getAbsolutePath());
							item.write(uploadedFile);
							out.println("Success");
							Dao.save(User, FileName);
						} else
							out.println("Failed");

					} else if (item.getFieldName().equals("uname")) {
						// To Get Folder Name
						User = item.getString();
					} else {
						System.out.println(item.getFieldName());
						System.out.println(item.getString());
					}
				}
			} catch (FileUploadException e) {
				out.println(e);
			} catch (Exception e) {
				out.println(e);
			}
		} else {
			out.println("Not Multipart");
		}

	}

}
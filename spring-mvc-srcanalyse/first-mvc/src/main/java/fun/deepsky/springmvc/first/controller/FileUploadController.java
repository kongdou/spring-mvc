package fun.deepsky.springmvc.first.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

	@RequestMapping("/file-upload")
	public ModelAndView upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		if (!file.isEmpty()) {
			// 文件存放路径
			String path = request.getServletContext().getRealPath("/");
			String name = new Date().getTime() + "_" + file.getOriginalFilename();
			File destFile = new File(path, name);
			// 转存文件
			try {
				file.transferTo(destFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			// 访问的url
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/" + name;
			System.out.println(url);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/upload.jsp");
		return mv;
	}
}

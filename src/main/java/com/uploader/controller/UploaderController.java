package com.uploader.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uploader.entity.EpaperRequest;
import com.uploader.exceptions.ParsingException;
import com.uploader.exceptions.SchemaException;
import com.uploader.parser.EpaperRequestXMLParser;
import com.uploader.service.EpaperRequestService;

/**
 * Main application controller
 * 
 * @author michal
 *
 */
@Controller
public class UploaderController {

	@Autowired
	private EpaperRequestService service; // injects service
	@Autowired
	private EpaperRequestXMLParser xmlParser; // injects XML parser

	/**
	 * Shows main form
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String showForm(Model model) {
		List<EpaperRequest> requests = service.getEpaperRequests();
		model.addAttribute("requests", requests);
		return "file-uploader";
	}

	/**
	 * Uploads XML file
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/")
	public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		String msg = "File " + file.getOriginalFilename();
		try {
			if (xmlParser.validate(file.getInputStream())) { // if file is valid, save it to database
				EpaperRequest request = xmlParser.parse(file.getInputStream());
				request.setFileName(file.getOriginalFilename()); // add file name
				service.addEpaperRequest(request);
				msg += " was uploaded successfully.";
				redirectAttributes.addFlashAttribute("info", msg);
			} else {
				msg += " is invalid XML file.";
				redirectAttributes.addFlashAttribute("error", msg);
			}
		} catch (ParsingException | SchemaException e1) {
			redirectAttributes.addFlashAttribute("error", e1.getMessage());
		} catch (IOException e2) {
			redirectAttributes.addFlashAttribute("error", "Error when uploading file");
		}

		return "redirect:/";
	}
}

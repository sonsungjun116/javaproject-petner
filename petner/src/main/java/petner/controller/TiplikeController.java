package petner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import petner.service.TiplikeService;

@Controller
public class TiplikeController {

	@Autowired
	private TiplikeService ts;
}

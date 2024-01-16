package com.playdata.eungae.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.playdata.eungae.appointment.service.AppointmentService;
import com.playdata.eungae.review.dto.RequestReviewFormDto;
import com.playdata.eungae.review.service.ReviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewViewController {

	private final ReviewService reviewService;
	private final AppointmentService appointmentService;

	@GetMapping("/post")
	public String reviewWrite(
		Model model,
		@RequestParam Long appointmentSeq
	) {
		appointmentService.checkAppointmentStatus(appointmentSeq);
		model.addAttribute("appointmentSeq", appointmentSeq);
		return "contents/member/review-write";
	}

	@PostMapping("/post")
	// redirect 시 ResponseStatus 어노테이션을 붙이면 redirect가 실행되지 않습니다.
	// @ResponseStatus(HttpStatus.CREATED)
	public String createReview(
		@Valid RequestReviewFormDto requestReviewFormDto
	) {
		reviewService.createReview(requestReviewFormDto);
		return "redirect:/my/reviews";
	}

}

package org.e2e.labe2e01.review.domain;

import org.e2e.labe2e01.review.infrastructure.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> findReviewsByAuthorId(Long authorId) {
        return reviewRepository.findByAuthorId(authorId);
    }

    public List<Review> findReviewsByTargetId(Long targetId) {
        return reviewRepository.findByTargetId(targetId);
    }

    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}

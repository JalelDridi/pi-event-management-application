import { Component  } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Review } from '../../ReviewModels/Review.Model';
import { ReviewService } from '../../reviewservices/review.service';
import { BadWordsFilterService } from '../../reviewservices/badwordsfilter.service';
import Swal from 'sweetalert2';
import { HttpErrorResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';

@Component({
 selector: 'app-add-review',
 templateUrl: './add-review.component.html',
 styleUrls: ['./add-review.component.css'],
})
export class AddReviewComponent {
 currentDate = new Date();
 formattedDate: string;

 constructor(
    private reviewService: ReviewService, 
    private filterService: BadWordsFilterService, 
    private datePipe: DatePipe 
 ) {
    this.formattedDate = this.datePipe.transform(this.currentDate, "yyyy-MM-ddTHH:mm:ss.SSSZ");
 }

 submitReview(form: NgForm) {
    if (form.valid) {
      const content = form.value.content;
      if (this.filterService.containsBadWords(content)) {
        this.showBadWordsAlert();
        return;
      }
      const rating = form.value.rating;
      const newReview = new Review("268", 89, rating, content, this.formattedDate ?? '');
      this.showConfirmationModal(newReview, form);
    }
 }
  
 cancelForm(form: NgForm) {
    form.resetForm();
 }

 showBadWordsAlert(): void {
    Swal.fire({
      icon: 'warning',
      title: 'Oops...',
      text: 'Review contains bad words. Please remove them.',
    });
 }

 showConfirmationModal(newReview: Review, form: NgForm) {
    const formdata = form.value;
    const message = `<div class="message">
        <p>Rating: ${formdata.rating}/5</p>
        <p>Content: ${formdata.content}</p>
      </div>`;
    Swal.fire({
      html: `Are you sure to add this review?<br><br>${message}`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#1b5f01',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Add Review',
      cancelButtonText: 'No, cancel!',
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {
        this.reviewService.createReview(newReview).subscribe(
          (response) => {
            console.log('Review added successfully:', response);
            Swal.fire({
              icon: 'success',
              title: 'Success!',
              text: 'Review added successfully!',
            });
            form.resetForm();
          },
          (error: HttpErrorResponse) => {
            console.error('Error adding review:', error);
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: 'An error occurred while adding the review.',
            });
          }
        );
      }
    });
 }
}
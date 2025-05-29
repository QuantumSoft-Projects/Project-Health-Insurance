import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
 
@Component({
  selector: 'app-review',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {
  averageRating: number = 4.3;
  totalReviews: number = 0;
  reviews: any[] = [];
  filteredReviews: any[] = [];
  activeFilter: string = 'All';
  currentPage: number = 1;
  reviewsPerPage: number = 5;
 
  filters: string[] = ['All', 'Claims', 'Customer Service', 'Premium', 'Recent'];
 
  ratingCounts: Record<number, number> = {
    5: 260,
    4: 512,
    3: 342,
    2: 128,
    1: 45
  };
 
  showReviewForm: boolean = false;
  selectedRating: number = 0;
 
  reviewData = {
    userName: '',
    comment: '',
    policy: { id: '' },
    hospital: { id: '' },
    doctor: { id: '' }
  };
 
  policies = [
    { id: '1', name: 'Policy 1' },
    { id: '2', name: 'Policy 2' }
  ];
 
  hospitals = [
    { id: '1', name: 'Hospital 1' },
    { id: '2', name: 'Hospital 2' }
  ];
 
  doctors = [
    { id: '1', name: 'Dr. Smith', specialization: 'Cardiology' },
    { id: '2', name: 'Dr. Jones', specialization: 'Dermatology' }
  ];
 
  constructor() {}
 
  ngOnInit(): void {
    this.loadReviews();
    this.totalReviews = this.reviews.length;
    this.filterReviews();
    this.updateAverageRating();
  }
 
  loadReviews(): void {
    this.reviews = [
      {
        userName: 'Amit Sharma',
        date: new Date('2023-05-15'),
        rating: 5,
        category: 'Claims',
        title: 'Excellent claim settlement',
        content: 'The claim process was smooth and efficient. My hospitalization bills were settled within 10 days without any hassle.',
        helpfulCount: 24
      },
      {
        userName: 'Priya Patel',
        date: new Date('2023-06-22'),
        rating: 4,
        category: 'Customer Service',
        title: 'Helpful customer support',
        content: 'Agents were knowledgeable and polite. Only issue was slightly long wait times during peak hours.',
        helpfulCount: 12
      },
      {
        userName: 'Rahul Verma',
        date: new Date('2023-07-05'),
        rating: 5,
        category: 'Premium',
        title: 'Great value for money',
        content: 'Affordable premiums with comprehensive coverage. Especially appreciate the wellness benefits included.',
        helpfulCount: 18
      },
      {
        userName: 'Neha Gupta',
        date: new Date('2023-08-10'),
        rating: 3,
        category: 'Claims',
        title: 'Average experience',
        content: 'Claim took 3 weeks to process with multiple follow-ups required. But eventually got settled correctly.',
        helpfulCount: 5
      },
      {
        userName: 'Sanjay Singh',
        date: new Date('2023-09-18'),
        rating: 4,
        category: 'Customer Service',
        title: 'Good overall service',
        content: 'Prompt responses to my queries. The mobile app could be improved but service is reliable.',
        helpfulCount: 8
      }
    ];
  }
 
  filterReviews(): void {
    let tempReviews = [...this.reviews];
 
    if (this.activeFilter !== 'All' && this.activeFilter !== 'Recent') {
      tempReviews = tempReviews.filter(review => review.category === this.activeFilter);
    } else if (this.activeFilter === 'Recent') {
      tempReviews = tempReviews.sort((a, b) => b.date.getTime() - a.date.getTime());
    }
 
    this.totalReviews = tempReviews.length;
    this.currentPage = 1;
    this.filteredReviews = this.paginateReviews(tempReviews);
  }
 
  setFilter(filter: string): void {
    this.activeFilter = filter;
    this.currentPage = 1;
    this.filterReviews();
  }
 
  getRatingPercentage(rating: number): number {
    const count = this.getRatingCount(rating);
    return this.totalReviews ? (count / this.totalReviews) * 100 : 0;
  }
 
  getRatingCount(rating: number): number {
    return this.reviews.filter(r => r.rating === rating).length;
  }
 
  updateAverageRating(): void {
    if (this.reviews.length === 0) {
      this.averageRating = 0;
      return;
    }
 
    const total = this.reviews.reduce((sum, r) => sum + r.rating, 0);
    this.averageRating = parseFloat((total / this.reviews.length).toFixed(1));
  }
 
  getUserInitials(name: string): string {
    return name.split(' ').map(n => n[0]).join('').toUpperCase();
  }
 
  markHelpful(review: any): void {
    review.helpfulCount++;
  }
 
  getPageNumbers(): number[] {
    const totalPages = Math.ceil(this.totalReviews / this.reviewsPerPage);
    return Array.from({ length: totalPages }, (_, i) => i + 1);
  }
 
  paginateReviews(reviews: any[]): any[] {
    const start = (this.currentPage - 1) * this.reviewsPerPage;
    return reviews.slice(start, start + this.reviewsPerPage);
  }
 
  goToPage(page: number): void {
    this.currentPage = page;
    this.filterReviews();
  }
 
  openReviewForm(): void {
    this.showReviewForm = true;
  }
 
  closeReviewForm(): void {
    this.showReviewForm = false;
  }
 
  setReviewRating(rating: number): void {
    this.selectedRating = rating;
  }
 
  setRating(rating: number): void {
    this.setReviewRating(rating);
  }
 
  submitReview(): void {
    if (this.reviewData.userName && this.reviewData.comment && this.selectedRating) {
      const newReview = {
        userName: this.reviewData.userName,
        date: new Date(),
        rating: this.selectedRating,
        category: 'Recent',
        title: 'New Review',
        content: this.reviewData.comment,
        helpfulCount: 0
      };
 
      this.reviews.unshift(newReview);
      this.updateAverageRating();
      this.totalReviews = this.reviews.length;
 
      this.filterReviews();
      alert('Thank you for your feedback!');
      this.resetReviewForm();
      this.closeReviewForm();
    } else {
      alert('Please complete all required fields.');
    }
  }
 
  resetReviewForm(): void {
    this.reviewData = {
      userName: '',
      comment: '',
      policy: { id: '' },
      hospital: { id: '' },
      doctor: { id: '' }
    };
    this.selectedRating = 0;
  }
}
 
 
import { Directive, ElementRef, Input, OnDestroy, OnInit } from '@angular/core';
 
@Directive({
  selector: '[appCounter]',
  standalone: true
})
export class CounterDirective implements OnInit, OnDestroy {
  @Input('countTo') countTo: number = 0;
 
  private observer!: IntersectionObserver;
  private hasCounted = false;
 
  constructor(private el: ElementRef) {}
 
  ngOnInit(): void {
    this.observer = new IntersectionObserver(entries => {
      entries.forEach(entry => {
        if (entry.isIntersecting && !this.hasCounted) {
          this.animateCount();
          this.hasCounted = true;
        }
      });
    });
 
    this.observer.observe(this.el.nativeElement);
  }
 
  animateCount() {
    const el = this.el.nativeElement;
    const duration = 1500; // Total animation duration in ms
    const frameRate = 60;
    const totalFrames = Math.round(duration / (1000 / frameRate));
    let frame = 0;
 
    const countFrom = 0;
    const countTo = this.countTo;
    const easeOutQuad = (t: number) => t * (2 - t); // easing function
 
    const counter = setInterval(() => {
      frame++;
      const progress = easeOutQuad(frame / totalFrames);
      const currentValue = Math.round(countFrom + (countTo - countFrom) * progress);
      el.innerText = currentValue;
 
      if (frame === totalFrames) {
        clearInterval(counter);
      }
    }, 1000 / frameRate);
  }
 
  ngOnDestroy(): void {
    if (this.observer) {
      this.observer.disconnect();
    }
  }
}
   
 
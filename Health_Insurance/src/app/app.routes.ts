import { Routes, UrlSerializer } from '@angular/router';
import { QuoteModalComponent } from './Components/quote-modal/quote-modal.component';
import { PageAppointmentComponent } from './Components/Pages/page-appointment/page-appointment.component';
import { PageMembersComponent } from './Components/Pages/page-members/page-members.component';
import { PageTestimonialComponent } from './Components/Pages/page-testimonial/page-testimonial.component';
import { Page404Component } from './Components/Pages/page404/page404.component';
import { PagefeatureComponent } from './Components/Pages/pagefeature/pagefeature.component';
import { OurServiceComponent } from './Components/our-service/our-service.component';
import { HomeComponent } from './Components/home/home.component';
import { ContactUsComponent } from './Components/contact-us/contact-us.component';
import { LoginOptionsComponent } from './Components/login-options/login-options.component';
import { SuperadminLoginComponent } from './Components/superadmin-login/superadmin-login.component';
import { UserLoginComponent } from './Components/user-login/user-login.component';
import { AdminLoginComponent } from './Components/admin-login/admin-login.component';
import { UserSignUpComponent } from './Components/user-sign-up/user-sign-up.component';
import { AboutUsComponent } from './Components/Pages/about-us/about-us.component';
import { AdminDashboardComponent } from './Components/admin-dashboard/admin-dashboard.component';
import { SuperadminDashboardComponent } from './Components/superadmin-dashboard/superadmin-dashboard.component';
import { AdminRegistrationComponent } from './Components/admin-registration/admin-registration.component';
import { HospitalComponent } from './Components/hospital/hospital.component';
import { CustomerSupportComponent } from './Components/customer-support/customer-support.component';
import { TermsConditionComponent } from './Components/terms-condition/terms-condition.component';
import { ReviewComponent } from './Components/review/review.component';
import { HealthInsuranceComponent } from './Components/health-insurance/health-insurance.component';
import { CarInsuranceComponent } from './Components/car-insurance/car-insurance.component';
import { LifeInsuranceComponent } from './Components/life-insurance/life-insurance.component';
import { TravelInsuranceComponent } from './Components/travel-insurance/travel-insurance.component';
import { HomeInsuranceComponent } from './Components/home-insurance/home-insurance.component';
import { TermInsuranceComponent } from './Components/term-insurance/term-insurance.component';


export const routes: Routes = [
  { path:'', redirectTo: 'home', pathMatch: 'full' },
  { path:'home', component: HomeComponent },
    {path:'quote', component: QuoteModalComponent },
    {path:'appointment-page',component:PageAppointmentComponent},
    {path:'member-page', component: PageMembersComponent },
    {path:'testimonial-page', component: PageTestimonialComponent },
    {path:'404-page', component: Page404Component },
    {path:'feature-page', component: PagefeatureComponent },
    {path:'ourServices', component: OurServiceComponent },
    {path:'contactUs', component: ContactUsComponent },
    {path:'login-options',component:LoginOptionsComponent},
    {path:'superadmin', component: SuperadminLoginComponent },
    {path:'admin-login', component: AdminLoginComponent },
    {path:'user', component: UserLoginComponent },
    {path:'signup',component:UserSignUpComponent},
    {path:'about-us',component:AboutUsComponent},
    {path:'admin-dashboard',component:AdminDashboardComponent},
    {path:'superadminDashboard',component:SuperadminDashboardComponent},
    {path:'adminregistration',component:AdminRegistrationComponent},
    {path:'hospital',component:HospitalComponent},
    {path:'customerSupport',component:CustomerSupportComponent},
     {path:'termsCondtion',component:TermsConditionComponent},
     {path:'review',component:ReviewComponent},
     {path:'healthInsurance',component:HealthInsuranceComponent},
     {path:'carInsurance',component:CarInsuranceComponent},
     {path:'lifeInsurance',component:LifeInsuranceComponent},
     {path:'travelInsurance',component:TravelInsuranceComponent},
     {path:'homeInsurance',component:HomeInsuranceComponent},
     {path:'termInsurance',component:TermInsuranceComponent},


    {path: '**', redirectTo: '404-page' }



  // other routes...
];
